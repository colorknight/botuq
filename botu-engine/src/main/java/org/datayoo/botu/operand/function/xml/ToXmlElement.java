package org.datayoo.botu.operand.function.xml;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ToXmlElement extends AbstractFunction {

  public static final String FUNCTION_NAME = "toXmlElement";

  protected SAXReader reader = null;

  public ToXmlElement(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
    reader = new SAXReader();
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    try {
      Document document = reader.read(
          new ByteArrayInputStream(o.toString().getBytes()));
      return document.getRootElement();
    } catch (DocumentException e) {
      throw new RuntimeException("Read data failed!", e);
    }
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    try {
      Document document = reader.read(
          new ByteArrayInputStream(o.toString().getBytes()));
      return document.getRootElement();
    } catch (DocumentException e) {
      throw new RuntimeException("Read data failed!", e);
    }
  }

}
