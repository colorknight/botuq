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

import org.datayoo.base.types.DataTypeUtils;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

/**
 * @author Tang Tadin
 */
public class SubString extends AbstractFunction {

  public static final String FUNCTION_NAME = "subString";

  protected Operand field;

  protected Operand start;

  protected Operand end = null;

  public SubString(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    if (parameters.size() < 2 || parameters.size() > 3)
      throw new IllegalArgumentException(
          "The function format is 'subString(field, start[, end])'");
    field = parameters.get(0);
    start = parameters.get(1);
    if (parameters.size() == 3) {
      end = parameters.get(2);
    }
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object obj = field.operate(entityMap);
    if (obj == null)
      return null;
    String src = obj.toString();
    int s = DataTypeUtils.intValue(start.operate(entityMap).toString());
    int e = src.length();
    if (end != null)
      e = DataTypeUtils.intValue(end.operate(entityMap).toString());
    s = repos(src, s);
    e = repos(src, e);
    return src.substring(s, e);
  }

  protected int repos(String s, int pos) {
    int len = s.length();
    if (pos > len) {
      pos = len;
    } else if (pos < 0) {
      pos = len + pos;
      if (pos < 0)
        return 0;
    }
    return pos;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object obj = field.operate(entityArray);
    if (obj == null)
      return null;
    String src = obj.toString();
    int s = DataTypeUtils.intValue(start.operate(entityArray).toString());
    int e = src.length();
    if (end != null)
      e = DataTypeUtils.intValue(end.operate(entityMap).toString());
    s = repos(src, s);
    e = repos(src, e);
    return src.substring(s, e);
  }

}
