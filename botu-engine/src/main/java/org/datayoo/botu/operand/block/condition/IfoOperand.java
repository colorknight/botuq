package org.datayoo.botu.operand.block.condition;

import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.moql.EntityMap;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class IfoOperand extends IfOperand {

  protected static final int RESORT_TRIGGER = 1000;

  protected int count = 0;

  protected SortComparator sortComparator = new SortComparator();

  // 按匹配频率对解析算子执行顺序进行优化
  protected List<CountedOperand> conditionOperands = new LinkedList<>();

  protected ConditionOperand elseOperand;

  public IfoOperand(ConditionOperand conditionOperand) {
    addConditionOperand(conditionOperand);
    this.source = conditionOperand.getSource();
  }

  @Override
  public Object operate(EntityMap stack, StateContext stateContext) {
    boolean done = false;
    synchronized (conditionOperands) {
      for (CountedOperand operand : conditionOperands) {
        boolean ret = (boolean) operand.operand.operate(stack, stateContext);
        if (ret) {
          operand.count++;
          done = true;
          break;
        }
      }
      if (!done && elseOperand != null) {
        elseOperand.operate(stack, stateContext);
      }
      if (++count == RESORT_TRIGGER) {
        count = 0;
        // 基于当前匹配频率对规则进行排序
        Collections.sort(conditionOperands, sortComparator);
        for (CountedOperand operand : conditionOperands) {
          operand.count = 0;
        }
      }
    }
    return null;
  }

  public void addConditionOperand(ConditionOperand conditionOperand) {
    if (conditionOperand.getCondition() != null) {
      CountedOperand countedOperand = new CountedOperand(conditionOperand);
      conditionOperands.add(countedOperand);
    } else {
      elseOperand = conditionOperand;
    }
  }

  protected class CountedOperand {

    protected ProcessOperand operand;

    protected int count = 0;

    public CountedOperand(ProcessOperand processOperand) {
      operand = processOperand;
    }

  }

  protected class SortComparator implements Comparator<CountedOperand> {
    @Override
    public int compare(CountedOperand o1, CountedOperand o2) {
      return o1.count - o2.count;
    }
  }
}
