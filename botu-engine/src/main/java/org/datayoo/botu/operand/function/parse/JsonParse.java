package org.datayoo.botu.operand.function.parse;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.BooleanConstant;
import org.datayoo.moql.operand.function.AbstractFunction;
import org.datayoo.util.JsonConvertor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonParse extends AbstractFunction {

  public static final String JO_SUFFIX = "Jo";

  public static final String FUNCTION_NAME = "jsonParse";

  protected Operand field;

  protected boolean flat = false;

  public JsonParse(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    field = parameters.get(0);
    if (parameters.size() > 1) {
      if (!(parameters.get(1) instanceof BooleanConstant)) {
        throw new IllegalArgumentException(
            "The flat parameter should be a boolean constant in JsonParse function!");
      }
      flat = (Boolean) parameters.get(1).operate((EntityMap) null);
    }
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = (Object) field.operate(entityMap);
    if (o == null)
      return null;
    Object jo = JsonConvertor.fromJson(o.toString());
    if (flat) {
      if (jo.getClass().isArray()) {
        entityMap.putEntity(field.getName() + JO_SUFFIX, jo);
      } else {
        List<String> path = new LinkedList<>();
        flatJo(jo, entityMap, path);
      }
    } else {
      entityMap.putEntity(field.getName() + JO_SUFFIX, jo);
    }
    return jo;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException();
  }

  protected void flatJo(Object jo, EntityMap entityMap, List<String> path) {
    String flatName = toFlatName(path);
    if (jo instanceof Map) {
      Map<String, Object> joMap = (Map) jo;
      for (Map.Entry<String, Object> entry : joMap.entrySet()) {
        path.add(entry.getKey());
        flatJo(entry.getValue(), entityMap, path);
        path.remove(path.size() - 1);
      }
    } else {
      entityMap.putEntity(flatName, jo);
    }
  }

  protected String toFlatName(List<String> path) {
    StringBuilder sbud = new StringBuilder();
    for (String seg : path) {
      if (sbud.length() != 0) {
        sbud.append('.');
      }
      sbud.append(seg);
    }
    return sbud.toString();
  }
}
