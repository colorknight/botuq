package org.datayoo.botu.operand.function.xml;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;
import org.dom4j.Node;

import java.util.LinkedList;
import java.util.List;

public class XPath extends AbstractFunction {

  public static final String FUNCTION_NAME = "xPath";

  protected Operand xPath;

  public XPath(List<Operand> parameters) {
    super(FUNCTION_NAME, 2, parameters);
    xPath = parameters.get(1);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    String xPath = (String) this.xPath.operate(entityMap);
    if (xPath == null)
      return null;
    List<Node> inputNodes;
    if (o instanceof List) {
      inputNodes = (List<Node>) o;
    } else {
      inputNodes = new LinkedList<>();
      inputNodes.add((Node) o);
    }
    List output = new LinkedList<>();
    for (Node inputNode : inputNodes) {
      output.addAll(inputNode.selectNodes(xPath));
    }
    return output;
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    String xPath = (String) this.xPath.operate(entityArray);
    if (xPath == null)
      return null;
    List<Node> inputNodes = (List<Node>) o;
    List output = new LinkedList<>();
    for (Node inputNode : inputNodes) {
      output.addAll(inputNode.selectNodes(xPath));
    }
    return output;
  }

}
