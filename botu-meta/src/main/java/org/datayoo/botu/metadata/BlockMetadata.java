package org.datayoo.botu.metadata;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BlockMetadata implements Statementable, Serializable {

  protected List<Statementable> statements = new LinkedList<Statementable>();

  public List<Statementable> getStatements() {
    return statements;
  }

  public void setStatements(List<Statementable> statements) {
    this.statements = statements;
  }
}
