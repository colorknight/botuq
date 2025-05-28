package org.datayoo.base.biztypes;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

/**
 * @author tangtadin
 * @version 1.0
 * @description: TODO
 * @date 2023/2/7 11:59
 */
public class Email implements Serializable, Comparable<Email> {

  protected String name;

  protected String account;

  protected String domain;

  public Email(String email) {
    Validate.notEmpty(email, "email is empty!");
    parse(email);
  }

  protected void parse(String email) {
    int inx = email.indexOf('<');
    if (inx != -1) {
      String name = email.substring(0, inx);
      parseName(name);
      int end = email.indexOf('>');
      if (end == -1)
        email = email.substring(inx + 1);
      else
        email = email.substring(inx + 1, end);
    }
    inx = email.indexOf('@');
    account = email.substring(0, inx);
    domain = email.substring(inx + 1);
    if (name == null)
      name = account;
  }

  protected void parseName(String name) {
    name = name.trim();
    if (name.charAt(0) == '\"')
      name = name.substring(1);
    if (name.charAt(name.length() - 1) == '\"')
      name = name.substring(0, name.length() - 1);
    int inx = name.indexOf('@');
    if (inx == -1)
      this.name = name;
    else
      this.name = name.substring(0, inx);
  }

  public String getName() {
    return name;
  }

  public String getAccount() {
    return account;
  }

  public String getDomain() {
    return domain;
  }

  @Override
  public String toString() {
    StringBuilder sbud = new StringBuilder();
    sbud.append('\"');
    sbud.append(name);
    sbud.append("\"<");
    sbud.append(account);
    sbud.append('@');
    sbud.append(domain);
    sbud.append('>');
    return sbud.toString();
  }

  @Override
  public int compareTo(Email o) {
    int cmp = account.compareTo(o.account);
    if (cmp != 0)
      return cmp;
    cmp = domain.compareTo(o.domain);
    if (cmp != 0)
      return cmp;
    return 0;
  }
}
