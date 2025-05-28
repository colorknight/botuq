package org.datayoo.util;

import org.apache.commons.lang3.Validate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class ReflectionUtils {
  private static Map<Class<?>, Class<?>> primitiveMap = new HashMap<Class<?>, Class<?>>();

  static {
    primitiveMap.put(boolean.class, Boolean.class);
    primitiveMap.put(byte.class, Byte.class);
    primitiveMap.put(char.class, Character.class);
    primitiveMap.put(short.class, Short.class);
    primitiveMap.put(int.class, Integer.class);
    primitiveMap.put(long.class, Long.class);
    primitiveMap.put(float.class, Float.class);
    primitiveMap.put(double.class, Double.class);
  }

  public static Method getMethod(Class<?> clazz, String name,
      Class<?>... paramTypes) throws NoSuchMethodException {
    Validate.notNull(clazz, "Class must not be null");
    Validate.notNull(name, "Method name must not be null");
    Method method = innerGetMethod(clazz, name, paramTypes);
    if (method != null)
      return method;
    Class<?>[] interfaces = clazz.getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      method = innerGetMethod(interfaces[i], name, paramTypes);
      if (method != null)
        return method;
    }
    throw new NoSuchMethodException(
        String.format("%s.%s%s", clazz.getName(), name,
            argumentTypesToString(paramTypes)));
  }

  protected static Method innerGetMethod(Class<?> clazz, String name,
      Class<?>... paramTypes) {
    Class<?> searchType = clazz;
    List<Method> latentMethods = new LinkedList<>();
    while (searchType != null) {
      Method[] methods = (searchType.isInterface() ?
          searchType.getMethods() :
          searchType.getDeclaredMethods());
      for (Method method : methods) {
        if (name.equals(method.getName())) {
          if (compatibleCompare(method.getParameterTypes(), paramTypes)) {
            return method;
          } else {
            if (paramTypes != null
                && method.getParameterTypes().length == paramTypes.length)
              latentMethods.add(method);
          }
        }
      }
      searchType = searchType.getSuperclass();
    }
    return guessMethod(latentMethods, paramTypes);
  }

  protected static Method guessMethod(List<Method> latentMethods,
      Class<?>... paramTypes) {
    for (Method method : latentMethods) {
      if (compatibleCompare(paramTypes, method.getParameterTypes())) {
        return method;
      }
    }
    return null;
  }

  public static boolean compatibleCompare(Class<?>[] methodParameterTypes,
      Class<?>[] paramTypes) {
    if (methodParameterTypes == null) {
      return paramTypes == null || paramTypes.length == 0;
    }

    if (paramTypes == null) {
      return methodParameterTypes.length == 0;
    }

    if (methodParameterTypes.length != paramTypes.length) {
      return false;
    }

    for (int i = 0; i < methodParameterTypes.length; i++) {
      if (!methodParameterTypes[i].isAssignableFrom(paramTypes[i])) {
        if (methodParameterTypes[i].isPrimitive()) {
          if (!primitiveMap.get(methodParameterTypes[i]).equals(paramTypes[i]))
            return false;
        } else if (paramTypes[i].isPrimitive()) {
          if (!primitiveMap.get(paramTypes[i]).equals(methodParameterTypes[i]))
            return false;
        } else {
          return false;
        }
      }
    }

    return true;
  }

  protected static String argumentTypesToString(Class<?>[] argTypes) {
    StringBuilder buf = new StringBuilder();
    buf.append("(");
    if (argTypes != null) {
      for (int i = 0; i < argTypes.length; i++) {
        if (i > 0) {
          buf.append(", ");
        }
        Class<?> c = argTypes[i];
        buf.append((c == null) ? "null" : c.getName());
      }
    }
    buf.append(")");
    return buf.toString();
  }

}
