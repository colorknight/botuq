package org.datayoo.botu.operand.factory;

import org.datayoo.botu.dict.BotuDictionary;
import org.datayoo.botu.metadata.*;
import org.datayoo.botu.operand.arithmetic.MinusMinusOperand;
import org.datayoo.botu.operand.arithmetic.PlusPlusOperand;
import org.datayoo.botu.operand.collection.CollectionOperand;
import org.datayoo.botu.operand.constant.CharConstant;
import org.datayoo.botu.operand.constant.StringConstant;
import org.datayoo.botu.operand.function.Length;
import org.datayoo.botu.operand.function.SimpleMapping;
import org.datayoo.botu.operand.function.Special;
import org.datayoo.botu.operand.function.biz.*;
import org.datayoo.botu.operand.function.convert.*;
import org.datayoo.botu.operand.function.file.GetFileName;
import org.datayoo.botu.operand.function.file.GetFileNameExtension;
import org.datayoo.botu.operand.function.file.GetFileNameWithoutExtension;
import org.datayoo.botu.operand.function.file.GetFilePath;
import org.datayoo.botu.operand.function.generator.UUIDGenerator;
import org.datayoo.botu.operand.function.inside.DictMapping;
import org.datayoo.botu.operand.function.inside.Field;
import org.datayoo.botu.operand.function.inside.PackMap;
import org.datayoo.botu.operand.function.parse.*;
import org.datayoo.botu.operand.function.string.*;
import org.datayoo.botu.operand.function.time.*;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.constant.*;
import org.datayoo.moql.operand.expression.arithmetic.*;
import org.datayoo.moql.operand.expression.array.ArrayExpression;
import org.datayoo.moql.operand.expression.bit.*;
import org.datayoo.moql.operand.expression.logic.AndExpression;
import org.datayoo.moql.operand.expression.logic.NotExpression;
import org.datayoo.moql.operand.expression.logic.OrExpression;
import org.datayoo.moql.operand.expression.member.MemberFunctionExpression;
import org.datayoo.moql.operand.expression.member.MemberVariableExpression;
import org.datayoo.moql.operand.expression.relation.*;
import org.datayoo.moql.operand.function.Function;
import org.datayoo.moql.operand.function.factory.FunctionFactory;
import org.datayoo.moql.operand.function.factory.FunctionFactoryImpl;
import org.datayoo.moql.operand.variable.SingleVariable;
import org.datayoo.moql.operand.variable.Variable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class BotuOperandFactory {

  protected static FunctionFactory functionFactory = new FunctionFactoryImpl();

  public static final String FUNCTION_CTX_DICT = "functx.dict";

  static {
    functionFactory.registFunction(PatternParse.FUNCTION_NAME,
        PatternParse.class.getName());
    functionFactory.registFunction(SplitParse.FUNCTION_NAME,
        SplitParse.class.getName());
    functionFactory.registFunction(PatternSplitParse.FUNCTION_NAME,
        PatternSplitParse.class.getName());
    functionFactory.registFunction(JsonParse.FUNCTION_NAME,
        JsonParse.class.getName());
    functionFactory.registFunction(Field.FUNCTION_NAME, Field.class.getName());
    functionFactory.registFunction(DictMapping.FUNCTION_NAME,
        DictMapping.class.getName());
    functionFactory.registFunction(KeyPositionSplitParse.FUNCTION_NAME,
        KeyPositionSplitParse.class.getName());
    functionFactory.registFunction(PackMap.FUNCTION_NAME,
        PackMap.class.getName());
    functionFactory.registFunction(ParseToMap.FUNCTION_NAME,
        ParseToMap.class.getName());
    functionFactory.forceRegistFunction(SubString.FUNCTION_NAME,
        SubString.class.getName());
    functionFactory.forceRegistFunction(Trim.FUNCTION_NAME,
        Trim.class.getName());
    functionFactory.forceRegistFunction(IndexString.FUNCTION_NAME,
        IndexString.class.getName());
    functionFactory.forceRegistFunction(DecodeBase64.FUNCTION_NAME,
        DecodeBase64.class.getName());
    functionFactory.forceRegistFunction(StringFormat.FUNCTION_NAME,
        StringFormat.class.getName());
    functionFactory.forceRegistFunction(Format2Time.FUNCTION_NAME,
        Format2Time.class.getName());
    functionFactory.forceRegistFunction(Time2String.FUNCTION_NAME,
        Time2String.class.getName());
    functionFactory.forceRegistFunction(ToTime.FUNCTION_NAME,
        ToTime.class.getName());
    functionFactory.forceRegistFunction(DiffTime.FUNCTION_NAME,
        DiffTime.class.getName());
    functionFactory.forceRegistFunction(Convert2Ip.FUNCTION_NAME,
        Convert2Ip.class.getName());
    functionFactory.forceRegistFunction(Convert2Mac.FUNCTION_NAME,
        Convert2Mac.class.getName());
    functionFactory.forceRegistFunction(SimpleMapping.FUNCTION_NAME,
        SimpleMapping.class.getName());
    functionFactory.forceRegistFunction(ParseSyslogFacility.FUNCTION_NAME,
        ParseSyslogFacility.class.getName());
    functionFactory.forceRegistFunction(ParseSyslogSeverity.FUNCTION_NAME,
        ParseSyslogSeverity.class.getName());
    functionFactory.forceRegistFunction(IsPublicIpAddress.FUNCTION_NAME,
        IsPublicIpAddress.class.getName());
    functionFactory.forceRegistFunction(ReplaceString.FUNCTION_NAME,
        ReplaceString.class.getName());
    functionFactory.forceRegistFunction(RegExtract.FUNCTION_NAME,
        RegExtract.class.getName());
    functionFactory.forceRegistFunction(GetFileName.FUNCTION_NAME,
        GetFileName.class.getName());
    functionFactory.forceRegistFunction(GetFilePath.FUNCTION_NAME,
        GetFilePath.class.getName());
    functionFactory.forceRegistFunction(GetFileNameExtension.FUNCTION_NAME,
        GetFileNameExtension.class.getName());
    functionFactory.forceRegistFunction(
        GetFileNameWithoutExtension.FUNCTION_NAME,
        GetFileNameWithoutExtension.class.getName());
    functionFactory.forceRegistFunction(StringSplit.FUNCTION_NAME,
        StringSplit.class.getName());
    functionFactory.forceRegistFunction(Length.FUNCTION_NAME,
        Length.class.getName());
    functionFactory.forceRegistFunction(Special.FUNCTION_NAME,
        Special.class.getName());
    functionFactory.forceRegistFunction(Unescape.FUNCTION_NAME,
        Unescape.class.getName());
    functionFactory.forceRegistFunction(UUIDGenerator.FUNCTION_NAME,
        UUIDGenerator.class.getName());
    functionFactory.forceRegistFunction(Escape.FUNCTION_NAME,
        Escape.class.getName());
    functionFactory.forceRegistFunction(Now.FUNCTION_NAME, Now.class.getName());
    functionFactory.forceRegistFunction(ParseEmailAccount.FUNCTION_NAME,
        ParseEmailAccount.class.getName());
    functionFactory.forceRegistFunction(ParseEmailDomain.FUNCTION_NAME,
        ParseEmailDomain.class.getName());
    functionFactory.forceRegistFunction(GetMaskBits.FUNCTION_NAME,
        GetMaskBits.class.getName());
    functionFactory.forceRegistFunction(Base64.FUNCTION_NAME,
        Base64.class.getName());
    functionFactory.forceRegistFunction(ToDouble.FUNCTION_NAME,
        ToDouble.class.getName());
    functionFactory.forceRegistFunction(ToFloat.FUNCTION_NAME,
        ToFloat.class.getName());
    functionFactory.forceRegistFunction(ToInt.FUNCTION_NAME,
        ToInt.class.getName());
    functionFactory.forceRegistFunction(ToLowercase.FUNCTION_NAME,
        ToLowercase.class.getName());
    functionFactory.forceRegistFunction(ToUppercase.FUNCTION_NAME,
        ToUppercase.class.getName());
  }

  public static Operand createOperand(ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    ExpressionType expressionType = expressionMetadata.getExpressionType();
    if (expressionType.equals(ExpressionType.IDENTIFIER)) {
      return createIdentifier(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.STRING)) {
      return createString(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.CHAR)) {
      return createChar(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.INTEGER)) {
      return createInteger(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.LONG)) {
      return createLong(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.FLOAT)) {
      return createFloat(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.DOUBLE)) {
      return createDouble(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.BOOLEAN)) {
      return createBoolean(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.NULL)) {
      return createNull(expressionMetadata);
    } else if (expressionType.equals(ExpressionType.COLLECTION)) {
      return createCollection(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.METHOD)
        || expressionType.equals(ExpressionType.PROCESS_METHOD)) {
      if (expressionMetadata instanceof MethodMetadata)
        return createFunction(expressionMetadata, functionContext);
      else {
        return createMemberFunction(expressionMetadata, functionContext);
      }
    } else if (expressionType.equals(ExpressionType.MEMBER)) {
      return createMember(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.COLLACC_OP)) {
      return createArrayAccess(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.ARITHMETIC_OP)) {
      return createArithmeticOperand(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.COMPARE_OP)) {
      return createRelationOperand(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.LOGIC_OP)) {
      return createLogicOperand(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.SHIFT_OP)) {
      return createShiftOperand(expressionMetadata, functionContext);
    } else if (expressionType.equals(ExpressionType.BIT_OP)) {
      return createBitwiseOperand(expressionMetadata, functionContext);
    } else {
      throw new UnsupportedOperationException("");
    }
  }

  protected static Operand createIdentifier(
      ExpressionMetadata expressionMetadata) {
    VariableMetadata variableMetadata = (VariableMetadata) expressionMetadata;
    return new SingleVariable(variableMetadata.getName());
  }

  protected static Operand createString(ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new StringConstant(constantMetadata.getValue());
  }

  protected static Operand createChar(ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new CharConstant(constantMetadata.getValue());
  }

  protected static Operand createInteger(
      ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new IntegerConstant(constantMetadata.getValue());
  }

  protected static Operand createLong(ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new LongConstant(constantMetadata.getValue());
  }

  protected static Operand createFloat(ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new FloatConstant(constantMetadata.getValue());
  }

  protected static Operand createDouble(ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new DoubleConstant(constantMetadata.getValue());
  }

  protected static Operand createBoolean(
      ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new BooleanConstant(constantMetadata.getValue());
  }

  protected static Operand createNull(ExpressionMetadata expressionMetadata) {
    ConstantMetadata constantMetadata = (ConstantMetadata) expressionMetadata;
    return new NullConstant();
  }

  protected static Operand createCollection(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    List<Operand> operands = new LinkedList<Operand>();
    CollectionMetadata collectionMetadata = (CollectionMetadata) expressionMetadata;
    for (ExpressionMetadata exprMetadata : collectionMetadata.getExpressionMetadatas()) {
      Operand operand = createOperand(exprMetadata, functionContext);
      operands.add(operand);
    }
    return new CollectionOperand(operands);
  }

  protected static Operand createFunction(ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    List<Operand> operands = new LinkedList<Operand>();
    MethodMetadata methodMetadata = (MethodMetadata) expressionMetadata;
    for (ExpressionMetadata paramMetadata : methodMetadata.getParameters()) {
      Operand operand = createOperand(paramMetadata, functionContext);
      operands.add(operand);
    }
    Operand operand = functionFactory.createFunction(methodMetadata.getName(),
        operands);
    if (operand instanceof DictMapping) {
      DictMapping dict = (DictMapping) operand;
      dict.initDictionary(
          (Map<String, BotuDictionary>) functionContext.get(FUNCTION_CTX_DICT));
    }
    return operand;
  }

  protected static Operand createMember(ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    ExpressionType expressionType = expressionMetadata.getrOperand()
        .getExpressionType();
    if (expressionType.equals(ExpressionType.IDENTIFIER))
      return createMemberIdentifier(expressionMetadata, functionContext);
    return createMemberFunction(expressionMetadata, functionContext);
  }

  protected static Operand createMemberIdentifier(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand target = createOperand(expressionMetadata.getlOperand(),
        functionContext);
    Operand id = createIdentifier(expressionMetadata.getrOperand());
    return new MemberVariableExpression(target, (Variable) id);
  }

  protected static Operand createMemberFunction(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand target = createOperand(expressionMetadata.getlOperand(),
        functionContext);
    Operand function = createFunction(expressionMetadata.getrOperand(),
        functionContext);
    return new MemberFunctionExpression(target, (Function) function);
  }

  protected static Operand createArrayAccess(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand target = createOperand(expressionMetadata.getlOperand(),
        functionContext);
    Operand index = createOperand(expressionMetadata.getrOperand(),
        functionContext);
    return new ArrayExpression(target, index);
  }

  protected static Operand createArithmeticOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand lOperand = createOperand(expressionMetadata.getlOperand(),
        functionContext);
    Operand rOperand = null;
    if (expressionMetadata.getrOperand() != null) {
      rOperand = createOperand(expressionMetadata.getrOperand(),
          functionContext);
    }
    switch (expressionMetadata.getOperator()) {
      case "+":
        return new AddExpression(lOperand, rOperand);
      case "-":
        return new SubtractExpression(lOperand, rOperand);
      case "*":
        return new MultiplyExpression(lOperand, rOperand);
      case "/":
        return new DivideExpression(lOperand, rOperand);
      case "%":
        return new ModularExpression(lOperand, rOperand);
      case "++":
        return new PlusPlusOperand((Variable) lOperand, true);
      case "a++":
        return new PlusPlusOperand((Variable) lOperand, false);
      case "--":
        return new MinusMinusOperand((Variable) lOperand, true);
      case "a--":
        return new MinusMinusOperand((Variable) lOperand, false);
      default:
        throw new IllegalArgumentException(
            String.format("Invalid operator '%s'",
                expressionMetadata.getOperator()));
    }

  }

  protected static Operand createRelationOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand lOperand = createOperand(expressionMetadata.getlOperand(),
        functionContext);
    Operand rOperand = createOperand(expressionMetadata.getrOperand(),
        functionContext);
    switch (expressionMetadata.getOperator()) {
      case "==":
        return new EqualExpression(lOperand, rOperand);
      case ">":
        return new GreatThanExpression(lOperand, rOperand);
      case "<":
        return new LittleThanExpression(lOperand, rOperand);
      case ">=":
        return new GreatAndEqualExpression(lOperand, rOperand);
      case "<=":
        return new LittleAndEqualExpression(lOperand, rOperand);
      case "<>":
      case "!=":
        return new NotEqualExpression(lOperand, rOperand);
      default:
        throw new IllegalArgumentException(
            String.format("Invalid operator '%s'",
                expressionMetadata.getOperator()));
    }
  }

  protected static Operand createLogicOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand lOperand = null;
    if (expressionMetadata.getlOperand() != null)
      lOperand = createOperand(expressionMetadata.getlOperand(),
          functionContext);
    Operand rOperand = createOperand(expressionMetadata.getrOperand(),
        functionContext);
    switch (expressionMetadata.getOperator()) {
      case "!":
        return new NotExpression(rOperand);
      case "||":
        return new OrExpression(lOperand, rOperand);
      case "&&":
        return new AndExpression(lOperand, rOperand);
      default:
        throw new IllegalArgumentException(
            String.format("Invalid operator '%s'",
                expressionMetadata.getOperator()));
    }

  }

  protected static Operand createShiftOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand lOperand = createOperand(expressionMetadata.getlOperand(),
        functionContext);
    Operand rOperand = createOperand(expressionMetadata.getrOperand(),
        functionContext);
    switch (expressionMetadata.getOperator()) {
      case "<<":
        return new LeftShiftExpression(lOperand, rOperand);
      case ">>":
        return new RightShiftExpression(lOperand, rOperand);
      default:
        throw new IllegalArgumentException(
            String.format("Invalid operator '%s'",
                expressionMetadata.getOperator()));
    }
  }

  protected static Operand createBitwiseOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    Operand lOperand = null;
    if (expressionMetadata.getlOperand() != null)
      lOperand = createOperand(expressionMetadata.getlOperand(),
          functionContext);
    Operand rOperand = createOperand(expressionMetadata.getrOperand(),
        functionContext);
    switch (expressionMetadata.getOperator()) {
      case "~":
        return new BitwiseNotExpression(rOperand);
      case "|":
        return new BitwiseOrExpression(lOperand, rOperand);
      case "&":
        return new BitwiseAndExpression(lOperand, rOperand);
      case "^":
        return new BitwiseXorExpression(lOperand, rOperand);
      default:
        throw new IllegalArgumentException(
            String.format("Invalid operator '%s'",
                expressionMetadata.getOperator()));
    }
  }

  public static void registFunction(String name, String className) {
    functionFactory.registFunction(name, className);
  }

  public static void unregistFunction(String name) {
    functionFactory.unregistFunction(name);
  }
}
