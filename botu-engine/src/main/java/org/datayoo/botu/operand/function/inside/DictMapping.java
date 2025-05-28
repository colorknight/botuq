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
package org.datayoo.botu.operand.function.inside;

import org.datayoo.botu.dict.BotuDictionary;
import org.datayoo.botu.dict.ItemMetadata;
import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Tang Tadin
 */
public class DictMapping extends AbstractFunction {

  public static final String FUNCTION_NAME = "dictMapping";

  protected String dictName;

  protected Operand[] keyFields;

  protected BotuDictionary dictionary;

  protected String version;

  protected Map<Object, ItemMetadata> itemMetadataMap;

  public DictMapping(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    // TODO Auto-generated constructor stub
    int i = 0;
    keyFields = new Operand[parameters.size() - 1];
    for (Operand operand : parameters) {
      if (i == 0) {
        if (!(operand instanceof StringConstant)) {
          throw new IllegalArgumentException(
              "The first parameter should be string constant in dict function!");
        }
        dictName = (String) operand.operate((EntityMap) null);
      } else {
        keyFields[i - 1] = operand;
      }
      i++;
    }
  }

  public void initDictionary(Map<String, BotuDictionary> dictionaryMap) {
    dictionary = dictionaryMap.get(dictName);
    if (dictionary == null) {
      throw new IllegalArgumentException("Initialize dictionary failed!");
    }
    itemMetadataMap = loadItems(dictionary);
    // 获得字典当前版本
    version = dictionary.getVersion();
  }

  protected Map<Object, ItemMetadata> loadItems(BotuDictionary dictionary) {
    Map<Object, ItemMetadata> itemMetadataMap = new HashMap<Object, ItemMetadata>();
    for (ItemMetadata im : dictionary.getItems()) {
      itemMetadataMap.put(im.getKeys()[0], im);
    }
    return itemMetadataMap;
  }

  /* (non-Javadoc)
   * @see org.moql.operand.function.AbstractFunction#innerOperate(org.moql.data.EntityMap)
   */
  @Override
  protected Object innerOperate(EntityMap entityMap) {
    // TODO Auto-generated method stub
    if (dictionary == null)
      return null;
    // 字典版本不匹配，重新装载
    if (!version.equals(dictionary.getVersion())) {
      itemMetadataMap = loadItems(this.dictionary);
      version = dictionary.getVersion();
    }
    Object value = keyFields[0].operate(entityMap);
    if (value instanceof Number) {
      value = ((Number) value).intValue();
    }
    ItemMetadata im = itemMetadataMap.get(value);
    if (im == null)
      return null;
    for (int i = 1; i < keyFields.length; i++) {
      value = keyFields[i].operate(entityMap);
      if (value instanceof Number) {
        value = ((Number) value).intValue();
      }
      if (!Objects.equals(value, im.getKeys()[i]))
        return null;
    }
    return im.getValue();
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException();
  }
}
