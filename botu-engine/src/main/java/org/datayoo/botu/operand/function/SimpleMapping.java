/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.datayoo.botu.operand.function;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.Constant;
import org.datayoo.moql.operand.constant.StringConstant;
import org.datayoo.moql.operand.function.AbstractFunction;
import org.datayoo.util.JsonConvertor;

import java.util.List;
import java.util.Map;

/**
 * @author Tang Tadin
 */
public class SimpleMapping extends AbstractFunction {

  public static final String FUNCTION_NAME = "simpleMapping";

  protected Operand field;

  protected Map<String, String> mappingRules;

  protected Object defaultValue;

  public SimpleMapping(List<Operand> parameters) {
    super(FUNCTION_NAME, 3, parameters);
    field = parameters.get(0);
    Operand operand = parameters.get(1);
    if (!(operand instanceof StringConstant)) {
      throw new UnsupportedOperationException(
          "The mappingRules parameter should be a string constant in simpleMapping function!");
    }
    mappingRules = (Map<String, String>) JsonConvertor.fromJson(
        (String) operand.operate((EntityMap) null));
    operand = parameters.get(2);
    if (!(operand instanceof Constant)) {
      throw new UnsupportedOperationException(
          "The defaultValue parameter should be a constant in simpleMapping function!");
    }
    this.defaultValue = operand.operate((EntityMap) null);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object value = field.operate(entityMap);
    if (null == value)
      return null;
    value = mappingRules.get(value.toString());
    if (value == null)
      return defaultValue;
    return value;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object value = field.operate(entityArray);
    if (null == value)
      return null;
    value = mappingRules.get(value.toString());
    if (value == null)
      return defaultValue;
    return value;
  }

}
