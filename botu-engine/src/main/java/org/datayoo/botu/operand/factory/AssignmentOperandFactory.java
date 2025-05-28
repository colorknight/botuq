package org.datayoo.botu.operand.factory;

import org.datayoo.botu.metadata.ExpressionMetadata;
import org.datayoo.botu.metadata.ExpressionType;
import org.datayoo.botu.metadata.VariableMetadata;
import org.datayoo.botu.operand.ProcessOperand;
import org.datayoo.botu.operand.arithmetic.PlusPlusOperand;
import org.datayoo.botu.operand.assignment.AssignmentOperand;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.variable.Variable;

import java.util.Map;

public abstract class AssignmentOperandFactory {

  public static ProcessOperand createAssignmentOperand(
      ExpressionMetadata expressionMetadata,
      Map<String, Object> functionContext) {
    VariableMetadata variableMetadata = (VariableMetadata) expressionMetadata.getlOperand();
    Operand operand = null;
    switch (expressionMetadata.getOperator()) {
      case "=":
        operand = BotuOperandFactory.createOperand(
            expressionMetadata.getrOperand(), functionContext);
        break;
      case "+=":
      case "-=":
      case "*=":
      case "/=":
      case "%=":
        String operator = expressionMetadata.getOperator().substring(0, 1);
        ExpressionMetadata newExpressionMetadata = new ExpressionMetadata(
            ExpressionType.ARITHMETIC_OP, operator, variableMetadata,
            expressionMetadata.getrOperand());
        operand = BotuOperandFactory.createOperand(newExpressionMetadata,
            functionContext);
        break;
      case "&=":
      case "|=":
      case "^=":
        operator = expressionMetadata.getOperator().substring(0, 1);
        newExpressionMetadata = new ExpressionMetadata(ExpressionType.BIT_OP,
            operator, variableMetadata, expressionMetadata.getrOperand());
        operand = BotuOperandFactory.createOperand(newExpressionMetadata,
            functionContext);
        break;
      case "<<=":
      case ">>=":
        operator = expressionMetadata.getOperator().substring(0, 1);
        newExpressionMetadata = new ExpressionMetadata(ExpressionType.SHIFT_OP,
            operator, variableMetadata, expressionMetadata.getrOperand());
        operand = BotuOperandFactory.createOperand(newExpressionMetadata,
            functionContext);
      case "++":
        operand = BotuOperandFactory.createOperand(variableMetadata,
            functionContext);
        return new PlusPlusOperand((Variable) operand, true);
      case "a++":
        operand = BotuOperandFactory.createOperand(variableMetadata,
            functionContext);
        return new PlusPlusOperand((Variable) operand, false);
      case "--":
        operand = BotuOperandFactory.createOperand(variableMetadata,
            functionContext);
        return new PlusPlusOperand((Variable) operand, true);
      case "a--":
        operand = BotuOperandFactory.createOperand(variableMetadata,
            functionContext);
      default:
        throw new IllegalArgumentException(
            String.format("Invalid operator '%s'",
                expressionMetadata.getOperator()));
    }
    AssignmentOperand assignmentOperand = new AssignmentOperand(
        variableMetadata.getName(), expressionMetadata.getOperator(), operand);
    assignmentOperand.setSource(expressionMetadata);
    return assignmentOperand;
  }

}
