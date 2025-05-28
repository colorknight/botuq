package org.datayoo.botu.engine;

import org.datayoo.botu.metadata.BotuMetadata;
import org.datayoo.botu.operand.factory.BotuProcessOperandFactory;
import org.datayoo.botu.operand.factory.ProcessOperandFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class BotuEngineFactory {

  protected ProcessOperandFactory processOperandFactory;

  public BotuEngineFactory(Properties properties) throws IOException {
    processOperandFactory = new BotuProcessOperandFactory(properties);
  }

  public void refreshDictionaries() throws IOException {
    processOperandFactory.refreshDictionaries();
  }

  public BotuEngine createBotuEngine(String fileName) {
    return new BotuEngine(fileName, processOperandFactory);
  }

  public BotuEngine createBotuEngine(InputStream inputStream) {
    return new BotuEngine(inputStream, processOperandFactory);
  }

  public BotuEngine createBotuEngine(Reader reader) {
    return new BotuEngine(reader, processOperandFactory);
  }

  public BotuEngine createBotuEngine(BotuMetadata botuMetadata) {
    return new BotuEngine(botuMetadata, processOperandFactory);
  }
}
