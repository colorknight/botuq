// Generated from org/datayoo/botu/antlr/BotuParser.g4 by ANTLR 4.7.1
package org.datayoo.botu.antlr;

import java.util.LinkedList;
import org.datayoo.botu.metadata.*;
import org.datayoo.botu.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BotuParser}.
 */
public interface BotuParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BotuParser#botuUnit}.
	 * @param ctx the parse tree
	 */
	void enterBotuUnit(BotuParser.BotuUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#botuUnit}.
	 * @param ctx the parse tree
	 */
	void exitBotuUnit(BotuParser.BotuUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(BotuParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(BotuParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(BotuParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(BotuParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#botu_break}.
	 * @param ctx the parse tree
	 */
	void enterBotu_break(BotuParser.Botu_breakContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#botu_break}.
	 * @param ctx the parse tree
	 */
	void exitBotu_break(BotuParser.Botu_breakContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#botu_continue}.
	 * @param ctx the parse tree
	 */
	void enterBotu_continue(BotuParser.Botu_continueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#botu_continue}.
	 * @param ctx the parse tree
	 */
	void exitBotu_continue(BotuParser.Botu_continueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#botu_return}.
	 * @param ctx the parse tree
	 */
	void enterBotu_return(BotuParser.Botu_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#botu_return}.
	 * @param ctx the parse tree
	 */
	void exitBotu_return(BotuParser.Botu_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(BotuParser.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(BotuParser.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#packBlock}.
	 * @param ctx the parse tree
	 */
	void enterPackBlock(BotuParser.PackBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#packBlock}.
	 * @param ctx the parse tree
	 */
	void exitPackBlock(BotuParser.PackBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlock(BotuParser.SwitchBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlock(BotuParser.SwitchBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(BotuParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(BotuParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(BotuParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(BotuParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#defaultBlockStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultBlockStatement(BotuParser.DefaultBlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#defaultBlockStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultBlockStatement(BotuParser.DefaultBlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#forBlock}.
	 * @param ctx the parse tree
	 */
	void enterForBlock(BotuParser.ForBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#forBlock}.
	 * @param ctx the parse tree
	 */
	void exitForBlock(BotuParser.ForBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(BotuParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(BotuParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(BotuParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(BotuParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void enterWhileBlock(BotuParser.WhileBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void exitWhileBlock(BotuParser.WhileBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(BotuParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(BotuParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(BotuParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(BotuParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#collectionAssignment}.
	 * @param ctx the parse tree
	 */
	void enterCollectionAssignment(BotuParser.CollectionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#collectionAssignment}.
	 * @param ctx the parse tree
	 */
	void exitCollectionAssignment(BotuParser.CollectionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#operateAssignment}.
	 * @param ctx the parse tree
	 */
	void enterOperateAssignment(BotuParser.OperateAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#operateAssignment}.
	 * @param ctx the parse tree
	 */
	void exitOperateAssignment(BotuParser.OperateAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(BotuParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(BotuParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#logicAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicAndExpression(BotuParser.LogicAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#logicAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicAndExpression(BotuParser.LogicAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(BotuParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(BotuParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#bitOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpression(BotuParser.BitOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#bitOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpression(BotuParser.BitOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#bitAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpression(BotuParser.BitAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#bitAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpression(BotuParser.BitAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#equalExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpression(BotuParser.EqualExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#equalExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpression(BotuParser.EqualExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#compareExpression}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpression(BotuParser.CompareExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#compareExpression}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpression(BotuParser.CompareExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(BotuParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(BotuParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(BotuParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(BotuParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(BotuParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(BotuParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(BotuParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(BotuParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(BotuParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(BotuParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(BotuParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(BotuParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#member}.
	 * @param ctx the parse tree
	 */
	void enterMember(BotuParser.MemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#member}.
	 * @param ctx the parse tree
	 */
	void exitMember(BotuParser.MemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#memberMethodCall}.
	 * @param ctx the parse tree
	 */
	void enterMemberMethodCall(BotuParser.MemberMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#memberMethodCall}.
	 * @param ctx the parse tree
	 */
	void exitMemberMethodCall(BotuParser.MemberMethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#varPrimary}.
	 * @param ctx the parse tree
	 */
	void enterVarPrimary(BotuParser.VarPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#varPrimary}.
	 * @param ctx the parse tree
	 */
	void exitVarPrimary(BotuParser.VarPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(BotuParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(BotuParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(BotuParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(BotuParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(BotuParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(BotuParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(BotuParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(BotuParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(BotuParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(BotuParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BotuParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(BotuParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BotuParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(BotuParser.FloatLiteralContext ctx);
}