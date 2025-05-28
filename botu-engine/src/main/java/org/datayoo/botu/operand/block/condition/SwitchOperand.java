package org.datayoo.botu.operand.block.condition;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.operand.JumpMode;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

import java.util.*;

public class SwitchOperand extends AbstractCodeBlcokOperand {

  protected Operand keyOperand;

  protected Map<Object, Integer> valueIndexMap = new HashMap<Object, Integer>();

  protected List<CaseOperand> caseOperands = new ArrayList<CaseOperand>();

  protected List<ProcessOperand> defaultOperands = new LinkedList<ProcessOperand>();

  public SwitchOperand(Operand keyOperand) {
    Validate.notNull(keyOperand, "keyOperand is null!");
    this.keyOperand = keyOperand;
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    Object value = keyOperand.operate(entityMap);
    Integer index = valueIndexMap.get(value);
    if (index != null) {
      for (int i = index.intValue(); i < caseOperands.size(); i++) {
        CaseOperand caseOperand = caseOperands.get(i);
        caseOperand.operate(entityMap, stateContext);
        if (stateContext.getJumpMode() != null) {
          // switch 语句只支持break
          if (stateContext.getJumpMode() != JumpMode.RETURN) {
            stateContext.clear();
            return null;
          } else {
            return null;
          }
        }
      }
    } else {
      operate(entityMap, defaultOperands, stateContext);
      if (stateContext.getJumpMode() == JumpMode.BREAK) {
        stateContext.clear();
      }
    }
    return null;
  }

  public void setCaseOperands(List<CaseOperand> caseOperands) {
    if (caseOperands != null) {
      this.caseOperands = new ArrayList<CaseOperand>(caseOperands);
      int i = 0;
      for (CaseOperand caseOperand : caseOperands) {
        valueIndexMap.put(caseOperand.value, i++);
      }
    }
  }

  public List<ProcessOperand> getDefaultOperands() {
    return defaultOperands;
  }

  public void setDefaultOperands(List<ProcessOperand> defaultOperands) {
    if (defaultOperands != null)
      this.defaultOperands = defaultOperands;
  }

  public Operand getKeyOperand() {
    return keyOperand;
  }
}
