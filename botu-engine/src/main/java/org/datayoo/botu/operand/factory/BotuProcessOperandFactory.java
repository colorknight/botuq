package org.datayoo.botu.operand.factory;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.BotuListener;
import org.datayoo.botu.dict.BotuDictionary;
import org.datayoo.botu.dict.BotuDictionaryLoader;
import org.datayoo.botu.event.FieldSerializationMetadata;
import org.datayoo.botu.event.SerializationSchema;
import org.datayoo.botu.event.SerializationSchemaLoader;
import org.datayoo.botu.metadata.*;
import org.datayoo.botu.operand.ProcessFunctonOperand;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.block.BlockOperand;
import org.datayoo.botu.operand.block.BreakOperand;
import org.datayoo.botu.operand.block.ContinueOperand;
import org.datayoo.botu.operand.block.ReturnOperand;
import org.datayoo.botu.operand.block.botu.BotuOperand;
import org.datayoo.botu.operand.block.condition.*;
import org.datayoo.botu.operand.block.loop.EnhanceForOperand;
import org.datayoo.botu.operand.block.loop.ForOperand;
import org.datayoo.botu.operand.block.loop.WhileOperand;
import org.datayoo.botu.operand.block.pack.FieldAssignmentOperand;
import org.datayoo.botu.operand.block.pack.PackOperand;
import org.datayoo.botu.types.*;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BotuProcessOperandFactory implements ProcessOperandFactory {

  public static final String BOTU_TYPE_WRAPPER_FILE = "botu.typewrappers";

  public static final String BOTU_SCHEMA_DIR = "botu.schema.dir";

  public static final String BOTU_SCHEMA_FILE_PATTERN = "botu.schema.file.pattern";

  public static final String BOTU_DICT_DIR = "botu.dictionary.dir";

  public static final String BOTU_DICT_FILE_PATTERN = "botu.dictionary.file.pattern";

  protected String schemaDirPath;

  protected String schemaFilePattern;

  protected String dicDirPath;

  protected String dicFilePattern;

  protected Set<TypeWrapper> typeWrappers = new HashSet<>();

  protected Map<String, SerializationSchema> schemaMap = new HashMap<String, SerializationSchema>();

  protected Map<String, BotuDictionary> dictionaryMap = new HashMap<String, BotuDictionary>();

  protected Map<String, Object> functionContext = new HashMap<String, Object>();

  {
    typeWrappers.add(new IntegerWrapper());
    typeWrappers.add(new LongWrapper());
    typeWrappers.add(new FloatWrapper());
    typeWrappers.add(new DoubleWrapper());
    typeWrappers.add(new BooleanWrapper());
    typeWrappers.add(new StringWrapper());
    typeWrappers.add(new DatetimeWrapper());
    typeWrappers.add(new IpWrapper());
    typeWrappers.add(new MacWrapper());
    typeWrappers.add(new MapWrapper());
  }

  public BotuProcessOperandFactory(Properties properties) throws IOException {
    Validate.notEmpty(properties, "properties is empty!");
    String typeWrapperFile = properties.getProperty(BOTU_TYPE_WRAPPER_FILE);
    loadTypeWrappers(typeWrapperFile);
    String schemaDirPath = properties.getProperty(BOTU_SCHEMA_DIR);
    String schemaFilePattern = properties.getProperty(BOTU_SCHEMA_FILE_PATTERN);
    loadSerializationSchemas(schemaDirPath, schemaFilePattern);
    String botuDirPath = properties.getProperty(BOTU_DICT_DIR);
    String dicFilePattern = properties.getProperty(BOTU_DICT_FILE_PATTERN);
    loadBotuDictionaries(botuDirPath, dicFilePattern);
    functionContext.put(BotuOperandFactory.FUNCTION_CTX_DICT, dictionaryMap);
  }

  protected void loadTypeWrappers(String typeWrapperFile) throws IOException {
    if (typeWrapperFile == null)
      return;
    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(new FileInputStream(typeWrapperFile)));
    try {
      String wrapper = bufferedReader.readLine();
      Class clazz = Class.forName(wrapper);
      typeWrappers.add((TypeWrapper) clazz.newInstance());
    } catch (Throwable t) {
      throw new IllegalArgumentException("load wrapper failed!", t);
    } finally {
      bufferedReader.close();
    }
  }

  protected void loadSerializationSchemas(String schemaDirPath,
      String schemaFilePattern) throws IOException {
    Validate.notEmpty(schemaDirPath, "schemaDirPath is empty!");
    Validate.notEmpty(schemaFilePattern, "schemaFilePattern is empty!");
    List<SerializationSchema> schemas = SerializationSchemaLoader.loadFromDir(
        schemaDirPath, schemaFilePattern);
    for (SerializationSchema schema : schemas) {
      schemaMap.put(schema.getName(), schema);
    }
    this.schemaDirPath = schemaDirPath;
    this.schemaFilePattern = schemaFilePattern;
  }

  protected void loadBotuDictionaries(String dicDirPath, String dicFilePattern)
      throws IOException {
    Validate.notEmpty(dicDirPath, "dicDirPath is empty!");
    Validate.notEmpty(dicFilePattern, "dicFilePattern is empty!");
    List<BotuDictionary> dictionaries = BotuDictionaryLoader.loadFromDir(
        dicDirPath, dicFilePattern);
    for (BotuDictionary dictionary : dictionaries) {
      dictionaryMap.put(dictionary.getName(), dictionary);
    }
    this.dicDirPath = dicDirPath;
    this.dicFilePattern = dicFilePattern;
  }

  public void refreshDictionaries() throws IOException {
    List<BotuDictionary> dictionaries = BotuDictionaryLoader.loadFromDir(
        dicDirPath, dicFilePattern);
    for (BotuDictionary dictionary : dictionaries) {
      BotuDictionary usingDict = dictionaryMap.get(dictionary.getName());
      if (usingDict == null) {
        dictionaryMap.put(dictionary.getName(), dictionary);
      } else {
        try {
          usingDict.refresh();
        } catch (Throwable t) {
          t.printStackTrace();
        }
      }
    }
  }

  public BotuProcessOperandFactory(List<SerializationSchema> schemas,
      List<BotuDictionary> dictionaries) {
    Validate.notEmpty(schemas, "schemas is empty!");
    for (SerializationSchema schema : schemas) {
      schemaMap.put(schema.getName(), schema);
    }
    if (dictionaries != null) {
      for (BotuDictionary dictionary : dictionaries) {
        dictionaryMap.put(dictionary.getName(), dictionary);
      }
    }
    functionContext.put(BotuOperandFactory.FUNCTION_CTX_DICT, dictionaryMap);
  }

  public ProcessOperand createProcessOperand(Statementable statement,
      BotuListener botuListener) {
    Validate.notNull(botuListener, "botuListener is null!");
    ProcessOperand processOperand;
    if (statement instanceof BlockMetadata) {
      processOperand = createBlockProcessOperand((BlockMetadata) statement,
          botuListener);
    } else if (statement instanceof BotuMetadata) {
      processOperand = createBotuOperand((BotuMetadata) statement,
          botuListener);
    } else if (statement instanceof IfMetadata) {
      processOperand = createIfOperand((IfMetadata) statement, botuListener);
    } else if (statement instanceof SwitchMetadata) {
      processOperand = createSwitchOperand((SwitchMetadata) statement,
          botuListener);
    } else if (statement instanceof ForMetadata) {
      processOperand = createForOperand((ForMetadata) statement, botuListener);
    } else if (statement instanceof WhileMetadata) {
      processOperand = createWhileOperand((WhileMetadata) statement,
          botuListener);
    } else if (statement instanceof PackMetadata) {
      processOperand = createPackOperand((PackMetadata) statement,
          botuListener);
    } else if (statement instanceof BreakMetadata) {
      processOperand = new BreakOperand();
    } else if (statement instanceof ContinueMetadata) {
      processOperand = new ContinueOperand();
    } else if (statement instanceof ReturnMetadata) {
      processOperand = new ReturnOperand();
    } else if (statement instanceof ExpressionMetadata) {
      ExpressionMetadata expressionMetadata = (ExpressionMetadata) statement;
      ExpressionType expressionType = expressionMetadata.getExpressionType();
      if (expressionType.equals(ExpressionType.EVALUATION_OP)
          || expressionType.equals(ExpressionType.ARITHMETIC_OP)) {
        return createAssignmentOperand(expressionMetadata, functionContext);
      }
      if (expressionType.equals(ExpressionType.PROCESS_METHOD)) {
        return createProcessFunctionOperand(expressionMetadata);
      }
      throw new IllegalArgumentException("");
    } else {
      throw new IllegalArgumentException("Invalid statement!");
    }
    return processOperand;
  }

  protected ProcessOperand createBlockProcessOperand(
      BlockMetadata blockMetadata, BotuListener botuListener) {
    BlockOperand blockOperand = new BlockOperand();
    for (Statementable statement : blockMetadata.getStatements()) {
      if (statement == null)
        continue;
      ProcessOperand processOperand = createProcessOperand(statement,
          botuListener);
      if (processOperand instanceof BlockOperand) {
        BlockOperand subBlockOperand = (BlockOperand) processOperand;
        blockOperand.getOperands().addAll(subBlockOperand.getOperands());
      } else {
        blockOperand.getOperands().add(processOperand);
      }
    }
    return blockOperand;
  }

  protected BotuOperand createBotuOperand(BotuMetadata botuMetadata,
      BotuListener botuListener) {
    BotuOperand botuOperand = new BotuOperand(botuMetadata.getName());
    botuOperand.setSource(botuMetadata);
    ProcessOperand processOperand = createProcessOperand(
        botuMetadata.getStatement(), botuListener);
    if (processOperand instanceof BlockOperand) {
      BlockOperand subBlockOperand = (BlockOperand) processOperand;
      botuOperand.getOperands().addAll(subBlockOperand.getOperands());
    } else {
      botuOperand.getOperands().add(processOperand);
    }
    return botuOperand;
  }

  protected IfOperand createIfOperand(IfMetadata ifMetadata,
      BotuListener botuListener) {
    ConditionOperand conditionOperand = createConditionOperand(ifMetadata,
        botuListener);
    IfOperand ifOperand = null;
    if (ifMetadata.isOptimized()) {
      ifOperand = new IfoOperand(conditionOperand);
    } else {
      ifOperand = new IfOperand(conditionOperand);
    }
    if (ifMetadata.getBranches().size() != 0) {
      for (IfMetadata ifm : ifMetadata.getBranches()) {
        ifOperand.addConditionOperand(
            createConditionOperand(ifm, botuListener));
      }
    }
    return ifOperand;
  }

  protected ConditionOperand createConditionOperand(IfMetadata ifMetadata,
      BotuListener botuListener) {
    ConditionOperand conditionOperand = null;
    if (ifMetadata.getCondition() != null) {
      Operand condition = BotuOperandFactory.createOperand(
          ifMetadata.getCondition(), functionContext);
      conditionOperand = new ConditionOperand(condition);
    } else {
      conditionOperand = new ConditionOperand();
    }
    conditionOperand.setSource(ifMetadata);
    ProcessOperand processOperand = createProcessOperand(
        ifMetadata.getStatement(), botuListener);
    if (processOperand instanceof BlockOperand) {
      BlockOperand subBlockOperand = (BlockOperand) processOperand;
      conditionOperand.getProcessOperands()
          .addAll(subBlockOperand.getOperands());
    } else {
      conditionOperand.getProcessOperands().add(processOperand);
    }
    return conditionOperand;
  }

  protected SwitchOperand createSwitchOperand(SwitchMetadata switchMetadata,
      BotuListener botuListener) {
    Operand key = BotuOperandFactory.createOperand(switchMetadata.getKey(),
        functionContext);
    SwitchOperand switchOperand = new SwitchOperand(key);
    switchOperand.setSource(switchMetadata);
    buildSwitchOperand(switchOperand, switchMetadata.getCases(), botuListener);
    if (switchMetadata.getDefaultCase() != null) {
      ProcessOperand processOperand = createProcessOperand(
          switchMetadata.getDefaultCase(), botuListener);
      if (processOperand instanceof BlockOperand) {
        BlockOperand subBlockOperand = (BlockOperand) processOperand;
        switchOperand.getDefaultOperands()
            .addAll(subBlockOperand.getOperands());
      } else {
        switchOperand.getDefaultOperands().add(processOperand);
      }
    }
    return switchOperand;
  }

  protected ProcessOperand createForOperand(ForMetadata forMetadata,
      BotuListener botuListener) {
    if (forMetadata.isEnhanceMode()) {
      return createEnhanceForOperand(forMetadata, botuListener);
    }
    List<ProcessOperand> initProcessOperands = createProcessOperands(
        forMetadata.getInitExpressions(), botuListener);
    Operand condition = null;
    if (forMetadata.getExpression() != null) {
      condition = BotuOperandFactory.createOperand(forMetadata.getExpression(),
          functionContext);
    }
    List<ProcessOperand> updateProcessOperands = createProcessOperands(
        forMetadata.getUpdateExpressions(), botuListener);
    ForOperand forOperand = new ForOperand(initProcessOperands, condition,
        updateProcessOperands);
    forOperand.setSource(forMetadata);
    ProcessOperand processOperand = createProcessOperand(
        forMetadata.getStatement(), botuListener);
    if (processOperand instanceof BlockOperand) {
      BlockOperand subBlockOperand = (BlockOperand) processOperand;
      forOperand.getProcessOperands().addAll(subBlockOperand.getOperands());
    } else {
      forOperand.getProcessOperands().add(processOperand);
    }
    return forOperand;
  }

  protected List<ProcessOperand> createProcessOperands(
      List<ExpressionMetadata> expressionMetadatas, BotuListener botuListener) {
    if (expressionMetadatas == null)
      return null;
    List<ProcessOperand> processOperands = new LinkedList<>();
    for (ExpressionMetadata expressionMetadata : expressionMetadatas) {
      ProcessOperand processOperand = createProcessOperand(expressionMetadata,
          botuListener);
      processOperands.add(processOperand);
    }
    return processOperands;
  }

  protected ProcessOperand createEnhanceForOperand(ForMetadata forMetadata,
      BotuListener botuListener) {
    Operand condition = BotuOperandFactory.createOperand(
        forMetadata.getExpression(), functionContext);
    EnhanceForOperand forOperand = new EnhanceForOperand(
        forMetadata.getIdentifier(), condition);
    forOperand.setSource(forMetadata);
    ProcessOperand processOperand = createProcessOperand(
        forMetadata.getStatement(), botuListener);
    if (processOperand instanceof BlockOperand) {
      BlockOperand subBlockOperand = (BlockOperand) processOperand;
      forOperand.getProcessOperands().addAll(subBlockOperand.getOperands());
    } else {
      forOperand.getProcessOperands().add(processOperand);
    }
    return forOperand;
  }

  protected ProcessOperand createWhileOperand(WhileMetadata whileMetadata,
      BotuListener botuListener) {
    Operand condition = BotuOperandFactory.createOperand(
        whileMetadata.getCondition(), functionContext);
    WhileOperand whileOperand = new WhileOperand(condition);
    whileOperand.setSource(whileMetadata);
    ProcessOperand processOperand = createProcessOperand(
        whileMetadata.getStatement(), botuListener);
    if (processOperand instanceof BlockOperand) {
      BlockOperand subBlockOperand = (BlockOperand) processOperand;
      whileOperand.getProcessOperands().addAll(subBlockOperand.getOperands());
    } else {
      whileOperand.getProcessOperands().add(processOperand);
    }
    return whileOperand;
  }

  protected void buildSwitchOperand(SwitchOperand switchOperand,
      List<CaseMetadata> caseMetadatas, BotuListener botuListener) {
    List<CaseOperand> caseOperands = new LinkedList<CaseOperand>();
    for (CaseMetadata caseMetadata : caseMetadatas) {
      Operand valueOperand = BotuOperandFactory.createOperand(
          caseMetadata.getValue(), functionContext);
      if (!valueOperand.isConstantReturn()) {
        throw new IllegalArgumentException(
            String.format("Case statement as line %d is invalid!",
                caseMetadata.getLineNo()));
      }
      CaseOperand caseOperand = new CaseOperand(
          valueOperand.operate((EntityMap) null));
      if (caseMetadata.getStatement() != null) {
        ProcessOperand processOperand = createProcessOperand(
            caseMetadata.getStatement(), botuListener);
        if (processOperand instanceof BlockOperand) {
          BlockOperand subBlockOperand = (BlockOperand) processOperand;
          caseOperand.getOperands().addAll(subBlockOperand.getOperands());
        } else {
          caseOperand.getOperands().add(processOperand);
        }
      }
      caseOperands.add(caseOperand);
    }
    switchOperand.setCaseOperands(caseOperands);
  }

  protected PackOperand createPackOperand(PackMetadata packMetadata,
      BotuListener botuListener) {
    if (packMetadata.getSchemaName() == null) {
      return new PackOperand();
    }
    SerializationSchema schema = schemaMap.get(packMetadata.getSchemaName());
    if (schema == null) {
      throw new IllegalArgumentException(
          String.format("There is no schema named '%s'",
              packMetadata.getSchemaName()));
    }
    PackOperand packOperand = new PackOperand(schema, packMetadata.isMapMode(),
        botuListener);
    packOperand.setSource(packMetadata);
    for (ExpressionMetadata expressionMetadata : packMetadata.getExpressions()) {
      FieldAssignmentOperand assignmentOperand = createFieldAssignmentOperand(
          schema, expressionMetadata);
      packOperand.getAssignmentOperands().add(assignmentOperand);
    }
    return packOperand;
  }

  protected FieldAssignmentOperand createFieldAssignmentOperand(
      SerializationSchema schema, ExpressionMetadata expressionMetadata) {
    ExpressionMetadata lExpr = expressionMetadata.getlOperand();
    if (!lExpr.getExpressionType().equals(ExpressionType.IDENTIFIER)
        && !lExpr.getExpressionType().equals(ExpressionType.COLLACC_OP)) {
      if (!(lExpr.getExpressionType().equals(ExpressionType.MEMBER)
          && lExpr.getrOperand().getExpressionType()
          .equals(ExpressionType.IDENTIFIER))) {
        throw new IllegalArgumentException(
            String.format("It's not a identifier at line %d!",
                expressionMetadata.getlOperand().getLineNo()));
      }
    }
    Operand fieldOperand = BotuOperandFactory.createOperand(
        expressionMetadata.getlOperand(), functionContext);
    Operand valueOperand = BotuOperandFactory.createOperand(
        expressionMetadata.getrOperand(), functionContext);
    FieldSerializationMetadata metadata = schema.getFieldSerializationMetadata(
        fieldOperand.getName());
    if (metadata == null) {
      throw new IllegalArgumentException(
          String.format("Invalid fieldName '%s'", fieldOperand.getName()));
    }
    FieldAssignmentOperand assignmentOperand = new FieldAssignmentOperand(
        metadata, getTypeWrapper(metadata), valueOperand);
    assignmentOperand.setSource(expressionMetadata);
    return assignmentOperand;
  }

  protected ProcessOperand createAssignmentOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    return AssignmentOperandFactory.createAssignmentOperand(expressionMetadata,
        functionContext);
  }

  protected TypeWrapper getTypeWrapper(FieldSerializationMetadata metadata) {
    for (TypeWrapper typeWrapper : typeWrappers) {
      if (typeWrapper.getDataType().getName().equals(metadata.getDataType())) {
        if (Objects.equals(typeWrapper.getBizType(), metadata.getBizType())) {
          try {
            return (TypeWrapper) typeWrapper.getClass().newInstance();
          } catch (Throwable t) {
          }
        }
      }
    }
    throw new IllegalArgumentException(
        String.format("The type '%s:%s' has no wrapper instance!",
            metadata.getDataType().name(), metadata.getBizType()));
  }

  protected ProcessFunctonOperand createProcessFunctionOperand(
      ExpressionMetadata methodMetadata) {
    Operand function = function = BotuOperandFactory.createOperand(
        methodMetadata, functionContext);
    ;
    ProcessFunctonOperand processFunctonOperand = new ProcessFunctonOperand(
        function);
    processFunctonOperand.setSource(methodMetadata);
    return processFunctonOperand;
  }

}
