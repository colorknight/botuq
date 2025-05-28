package org.datayoo.botu.operand;

import org.datayoo.moql.EntityMap;

public class StateContext {

  protected int maxLoopTimes;

  protected JumpMode jumpMode;

  protected EntityMap result = null;

  public StateContext(int maxLoopTimes) {
    this.maxLoopTimes = maxLoopTimes;
  }

  public JumpMode getJumpMode() {
    return jumpMode;
  }

  public void setJumpMode(JumpMode jumpMode) {
    this.jumpMode = jumpMode;
  }

  public void clear() {
    jumpMode = null;
    result = null;
  }

  public EntityMap getResult() {
    return result;
  }

  public void setResult(EntityMap result) {
    this.result = result;
  }

  public int getMaxLoopTimes() {
    return maxLoopTimes;
  }
}
