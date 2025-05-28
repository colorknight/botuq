package org.datayoo.botu.operand.function.parse;

import org.datayoo.util.ReflectionUtils;
import org.datayoo.botu.BotuConstants;
import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternParse extends AbstractFunction {

  public static final String FUNCTION_NAME = "patternParse";

  protected Operand field;

  protected Pattern pattern;

  protected Map<Integer, String> groupMap = new HashMap<Integer, String>();

  public PatternParse(List<Operand> parameters) {
    super(FUNCTION_NAME, 2, parameters);
    field = parameters.get(0);
    if (!(parameters.get(1) instanceof StringConstant)) {
      throw new IllegalArgumentException(
          "The pattern parameter should be a string constant in patternParse function!");
    }
    String patt = (String) parameters.get(1).operate((EntityMap) null);
    this.pattern = Pattern.compile(patt);
    buildGroupMap();
  }

  protected void buildGroupMap() {
    Map<String, Integer> namedGroups = getNamedGroups();
    for (Map.Entry<String, Integer> entry : namedGroups.entrySet()) {
      groupMap.put(entry.getValue(), entry.getKey());
    }
  }

  protected Map<String, Integer> getNamedGroups() {
    try {
      Method namedGroup = ReflectionUtils.getMethod(Pattern.class,
          "namedGroups");
      namedGroup.setAccessible(true);
      return (Map<String, Integer>) namedGroup.invoke(this.pattern);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Pattern getPattern() {
    return pattern;
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    String data = (String) field.operate(entityMap);
    if (data == null)
      return false;
    Matcher matcher = pattern.matcher(data);
    //认为数据是单条的，不做多条处理
    if (matcher.find()) {
      for (int i = 1; i <= matcher.groupCount(); i++) {
        String groupName = groupMap.get(i);
        if (groupName == null)
          groupName = BotuConstants.TMP_VAR_PREFIX + i;
        entityMap.putEntity(groupName, matcher.group(i));
      }
      return true;
    }
    return false;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException();
  }
}
