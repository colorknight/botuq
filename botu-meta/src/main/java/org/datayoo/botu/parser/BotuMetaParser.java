package org.datayoo.botu.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.Validate;
import org.datayoo.botu.antlr.BotuLexer;
import org.datayoo.botu.antlr.BotuParser;
import org.datayoo.botu.exception.BotuRuntimeException;
import org.datayoo.botu.metadata.BotuMetadata;

import java.io.InputStream;
import java.io.Reader;

public abstract class BotuMetaParser {

  public static BotuMetadata parseFromFile(String fileName) {
    Validate.notEmpty(fileName, "fileName is empty!");
    try {
      CharStream charStream = CharStreams.fromFileName(fileName);
      BotuLexer botuLexer = new BotuLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(botuLexer);
      BotuParser botuParser = new BotuParser(tokens);
      BotuParser.BotuUnitContext context = botuParser.botuUnit();
      if (context.exception != null) {
        throw new BotuRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      return context.botuMetadata;
    } catch (Throwable t) {
      throw new BotuRuntimeException(t.getMessage(), t);
    }
  }

  public static BotuMetadata parseFromStream(InputStream inputStream) {
    Validate.notNull(inputStream, "inputStream is null!");
    try {
      CharStream charStream = CharStreams.fromStream(inputStream);
      BotuLexer botuLexer = new BotuLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(botuLexer);
      BotuParser botuParser = new BotuParser(tokens);
      BotuParser.BotuUnitContext context = botuParser.botuUnit();
      if (context.exception != null) {
        throw new BotuRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      return context.botuMetadata;
    } catch (Throwable t) {
      throw new BotuRuntimeException(t.getMessage(), t);
    }
  }

  public static BotuMetadata parseFromReader(Reader reader) {
    Validate.notNull(reader, "reader is null!");
    try {
      CharStream charStream = CharStreams.fromReader(reader);
      BotuLexer botuLexer = new BotuLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(botuLexer);
      BotuParser botuParser = new BotuParser(tokens);
      BotuParser.BotuUnitContext context = botuParser.botuUnit();
      if (context.exception != null) {
        throw new BotuRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      return context.botuMetadata;
    } catch (Throwable t) {
      throw new BotuRuntimeException(t.getMessage(), t);
    }
  }

  public static BotuMetadata parseFromString(String data) {
    Validate.notEmpty(data, "data is empty!");
    try {
      CharStream charStream = CharStreams.fromString(data);
      BotuLexer botuLexer = new BotuLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(botuLexer);
      BotuParser botuParser = new BotuParser(tokens);
      BotuParser.BotuUnitContext context = botuParser.botuUnit();
      if (context.exception != null) {
        throw new BotuRuntimeException(context.exception.getMessage(),
            context.exception);
      }
      return context.botuMetadata;
    } catch (Throwable t) {
      throw new BotuRuntimeException(t.getMessage(), t);
    }
  }
}
