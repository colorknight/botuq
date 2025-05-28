package org.datayoo.botu.metadata;

public enum ExpressionType {
  IDENTIFIER,
  CHAR,
  STRING,
  INTEGER,
  LONG,
  FLOAT,
  DOUBLE,
  BOOLEAN,
  NULL,
  //集合赋值
  COLLECTION,
  METHOD,
  MEMBER,
  //集合取值
  COLLACC_OP,
  ARITHMETIC_OP,
  COMPARE_OP,
  LOGIC_OP,
  SHIFT_OP,
  BIT_OP,
  EVALUATION_OP,
  PROCESS_METHOD
}
