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
public class Base64 extends AbstractFunction {

  public static final String FUNCTION_NAME = "base64";

  public Base64(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    // TODO Auto-generated method stub
    String obj = (String) parameters.get(0).operate(entityMap);
    if (obj == null)
      return null;
    return new String(
        org.apache.commons.codec.binary.Base64.encodeBase64(obj.getBytes()));
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    // TODO Auto-generated method stub
    String obj = (String) parameters.get(0).operate(entityArray);
    if (obj == null)
      return null;
    return new String(
        org.apache.commons.codec.binary.Base64.encodeBase64(obj.getBytes()));
  }

}
