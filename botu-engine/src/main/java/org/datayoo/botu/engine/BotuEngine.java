package org.datayoo.botu.engine;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.BotuConstants;
import org.datayoo.botu.BotuListener;
import org.datayoo.botu.metadata.BotuMetadata;
import org.datayoo.botu.operand.block.botu.BotuOperand;
import org.datayoo.botu.operand.factory.ProcessOperandFactory;
import org.datayoo.botu.parser.BotuMetaParser;
import org.datayoo.moql.EntityMap;

import java.io.InputStream;
import java.io.Reader;

public class BotuEngine {

  protected BotuOperand botuOperand;

  protected ProxyBotuListener proxyBotuListener = new ProxyBotuListener();

  public BotuEngine(String fileName,
      ProcessOperandFactory processOperandFactory) {
    Validate.notNull(processOperandFactory, "processOperandFactory is null!");
    BotuMetadata botuMetadata = BotuMetaParser.parseFromFile(fileName);
    botuOperand = (BotuOperand) processOperandFactory.createProcessOperand(
        botuMetadata, proxyBotuListener);
  }

  public BotuEngine(InputStream inputStream,
      ProcessOperandFactory processOperandFactory) {
    Validate.notNull(processOperandFactory, "processOperandFactory is null!");
    BotuMetadata botuMetadata = BotuMetaParser.parseFromStream(inputStream);
    botuOperand = (BotuOperand) processOperandFactory.createProcessOperand(
        botuMetadata, proxyBotuListener);
  }

  public BotuEngine(Reader reader,
      ProcessOperandFactory processOperandFactory) {
    Validate.notNull(processOperandFactory, "processOperandFactory is null!");
    BotuMetadata botuMetadata = BotuMetaParser.parseFromReader(reader);
    botuOperand = (BotuOperand) processOperandFactory.createProcessOperand(
        botuMetadata, proxyBotuListener);
  }

  public BotuEngine(BotuMetadata botuMetadata,
      ProcessOperandFactory processOperandFactory) {
    Validate.notNull(processOperandFactory, "processOperandFactory is null!");
    Validate.notNull(botuMetadata, "botuMetadata is null!");
    botuOperand = (BotuOperand) processOperandFactory.createProcessOperand(
        botuMetadata, proxyBotuListener);
  }

  public boolean parse(EntityMap stack) {
    stack.putEntity(BotuConstants.BOTU_NAME, botuOperand.getName());
    boolean bRet = botuOperand.parse(stack);
    stack.removeEntity(BotuConstants.BOTU_NAME);
    return bRet;
  }

  public String getName() {
    return botuOperand.getName();
  }

  public void addBotuListener(BotuListener listener) {
    Validate.notNull(listener, "listener is null!");
    proxyBotuListener.addBotuListener(listener);
  }

  public void removeBotuListener(BotuListener listener) {
    Validate.notNull(listener, "listener is null!");
    proxyBotuListener.removeBotuListener(listener);
  }

}
