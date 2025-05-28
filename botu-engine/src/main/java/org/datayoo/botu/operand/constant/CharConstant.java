package org.datayoo.botu.operand.constant;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.util.StringUtils;
import org.datayoo.moql.operand.constant.AbstractConstant;
import org.datayoo.moql.operand.constant.ConstantType;

public class CharConstant extends AbstractConstant {

  {
    constantType = ConstantType.STRING;
  }

  public CharConstant(String name) {
    Validate.notEmpty(name, "Parameter name is empty!");
    this.name = name;
    this.data = StringUtils.toMemChar(name);
  }
}
