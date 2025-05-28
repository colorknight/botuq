package org.datayoo.botu.operand.constant;

import org.datayoo.botu.util.StringUtils;
import org.datayoo.moql.operand.constant.ConstantType;

public class StringConstant
    extends org.datayoo.moql.operand.constant.StringConstant {

  {
    constantType = ConstantType.STRING;
  }

  public StringConstant(String name) {
    super("'n'");
    this.name = name;
    this.data = StringUtils.toMemString(name);
  }

}
