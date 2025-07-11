package org.datayoo.botu.operand.function.time;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class NowYear extends AbstractFunction {

  public static final String FUNCTION_NAME = "nowy";

  public NowYear(List<Operand> parameters) {
    super(FUNCTION_NAME, 0, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    System.out.println("xxxxx");
    return LocalDateTime.now().getYear();
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    return LocalDateTime.now().getYear();
  }

}
