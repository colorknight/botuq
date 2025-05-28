package org.datayoo.botu.operand.function.biz;

import org.datayoo.base.biztypes.Email;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.List;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2023/2/7 11:45
 */
public class ParseEmailDomain extends AbstractFunction {

  public static final String FUNCTION_NAME = "parseEmailDomain";

  public ParseEmailDomain(List<Operand> parameters) {
    super(FUNCTION_NAME, 1, parameters);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
    Object o = parameters.get(0).operate(entityMap);
    if (o == null)
      return null;
    return parse(o.toString());
  }

  protected String parse(String email) {
    int inx = email.indexOf(',');
    if (inx != -1) {
      String[] segs = email.split(",");
      return parseEmails(segs);
    } else {
      inx = email.indexOf(';');
      if (inx != -1) {
        String[] segs = email.split(";");
        return parseEmails(segs);
      } else {
        return parseEmail(email);
      }
    }
  }

  protected String parseEmails(String[] emails) {
    StringBuilder sbud = new StringBuilder();
    for (int i = 0; i < emails.length; i++) {
      if (i != 0)
        sbud.append(',');
      sbud.append(parseEmail(emails[i]));
    }
    return sbud.toString();
  }

  protected String parseEmail(String email) {
    Email e = new Email(email);
    return e.getDomain();
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    Object o = parameters.get(0).operate(entityArray);
    if (o == null)
      return null;
    return parse(o.toString());
  }

}
