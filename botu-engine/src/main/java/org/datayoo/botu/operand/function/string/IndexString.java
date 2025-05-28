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
package org.datayoo.botu.operand.function.string;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

/**
 * @author Tang Tadin
 */
public class IndexString extends AbstractFunction {

  public static final String FUNCTION_NAME = "indexString";

  protected Operand field;

  protected Operand pattern;

  protected Operand reversed;

  public IndexString(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    if (parameters.size() < 2 || parameters.size() > 3)
      throw new IllegalArgumentException(
          "The function format is 'indexString(field, pattern[, reserved])'");
    field = parameters.get(0);
    pattern = parameters.get(1);
    if (parameters.size() == 3)
      reversed = parameters.get(2);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object obj = field.operate(entityMap);
    if (obj == null)
      return null;
    boolean b = false;
    if (reversed != null)
      b = (Boolean) reversed.operate(entityMap);
    String src = obj.toString();
    if (!b)
      return src.indexOf((String) pattern.operate(entityMap));
    else
      return src.lastIndexOf((String) pattern.operate(entityMap));
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object obj = field.operate(entityArray);
    if (obj == null)
      return null;
    boolean b = false;
    if (reversed != null)
      b = (Boolean) reversed.operate(entityArray);
    String src = obj.toString();
    if (!b)
      return src.indexOf((String) pattern.operate(entityArray));
    else
      return src.lastIndexOf((String) pattern.operate(entityArray));
  }

}
