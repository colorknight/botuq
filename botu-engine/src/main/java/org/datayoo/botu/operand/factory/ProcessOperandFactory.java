package org.datayoo.botu.operand.factory;

import org.datayoo.botu.BotuListener;
import org.datayoo.botu.metadata.Statementable;
import org.datayoo.botu.operand.ProcessOperand;

import java.io.IOException;

public interface ProcessOperandFactory {
  ProcessOperand createProcessOperand(Statementable statement,
      BotuListener botuListener);

  void refreshDictionaries() throws IOException;
}
