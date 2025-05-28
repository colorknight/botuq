parser grammar BotuParser;

options {
language = Java;
//backtrack=true;
tokenVocab=BotuLexer;
}

//tokens {
//}

@header {
import java.util.LinkedList;
import org.datayoo.botu.metadata.*;
import org.datayoo.botu.util.*;
}
@lexer::header {
package org.datayoo.botu.antlr;

}

botuUnit returns[BotuMetadata botuMetadata]
@after {
    String name = $n.getText();
    $botuMetadata = new BotuMetadata(StringUtils.toMemString(name));
    $botuMetadata.setStatement($blockCtx.blockMetadata);
    $botuMetadata.setLineNo($t.getLine());
}
: t = BOTU'(' n = STRING_LITERAL ')' blockCtx = block EOF
;

// STATEMENTS / BLOCKS
block returns[BlockMetadata blockMetadata]
@init {
    $blockMetadata = new BlockMetadata();
}
: '{' (statCtx = statement {$blockMetadata.getStatements().add($statCtx.statementMetadata);})*'}'
;

statement returns[Statementable statementMetadata]
: blockCtx = block {$statementMetadata = $blockCtx.blockMetadata;}
| breakCtx = botu_break {$statementMetadata = $breakCtx.breakMetadata; }
| continueCtx = botu_continue {$statementMetadata = $continueCtx.continueMetadata; }
| returnCtx = botu_return {$statementMetadata = $returnCtx.returnMetadata; }
| ifCtx = ifBlock {$statementMetadata = $ifCtx.ifMetadata;}
| packCtx = packBlock {$statementMetadata = $packCtx.packMetadata;}
| switchCtx = switchBlock {$statementMetadata = $switchCtx.switchMetadata;}
| forCtx = forBlock {$statementMetadata = $forCtx.forMetadata;}
| whileCtx = whileBlock {$statementMetadata = $whileCtx.whileMetadata;}
| expressionCtx = assignmentExpression ';'{$statementMetadata = $expressionCtx.expressionMetadata;}
| methodCtx = methodCall ';' {
$methodCtx.methodMetadata.toProcessFuncType();
$statementMetadata = $methodCtx.methodMetadata;
}
| memberMethodCtx = memberMethodCall ';' {
$statementMetadata = $memberMethodCtx.expressionMetadata;
}
;

botu_break returns[BreakMetadata breakMetadata]
: t = BREAK ';'
{
    $breakMetadata = new BreakMetadata();
    $breakMetadata.setLineNo($t.getLine());
}
;

botu_continue returns[ContinueMetadata continueMetadata]
: t = CONTINUE ';'
{
    $continueMetadata = new ContinueMetadata();
    $continueMetadata.setLineNo($t.getLine());
}
;

botu_return returns[ReturnMetadata returnMetadata]
: t = RETURN ';'
{
    $returnMetadata = new ReturnMetadata();
    $returnMetadata.setLineNo($t.getLine());
}
;

ifBlock returns [IfMetadata ifMetadata]
@init{
    IfMetadata elseMetadata = null;
}
: t = (IF|IFO) conditionCtx = parExpression ifCtx = statement
{
    if ($t.getText().equals("if")) {
        $ifMetadata = new IfMetadata($conditionCtx.expressionMetadata, false);
    } else {
        $ifMetadata = new IfMetadata($conditionCtx.expressionMetadata, true);
    }
    $ifMetadata.setStatement($ifCtx.statementMetadata);
    $ifMetadata.setLineNo($t.getLine());
}
( ELSE elseCtx = statement
{
    if ($elseCtx.statementMetadata instanceof IfMetadata) {
        elseMetadata = (IfMetadata)$elseCtx.statementMetadata;
        $ifMetadata.getBranches().add(elseMetadata);
        $ifMetadata.getBranches().addAll(elseMetadata.getBranches());
    } else {
        elseMetadata = new IfMetadata();
        elseMetadata.setStatement($elseCtx.statementMetadata);
        $ifMetadata.getBranches().add(elseMetadata);
    }
}
)?
;

packBlock returns [PackMetadata packMetadata]
: t = PACK'(' s = STRING_LITERAL? (',' m = BOOL_LITERAL)? ')'
{
    boolean mapMode = true;
    if ($m != null) {
        mapMode = Boolean.valueOf(Boolean.valueOf($m.getText()));
    }
    if ($s != null) {
        String schemaName = $s.getText();
        $packMetadata = new PackMetadata(StringUtils.toMemString(schemaName), mapMode);
    } else {
        $packMetadata = new PackMetadata(null, mapMode);
    }
    $packMetadata.setLineNo($t.getLine());
}
'{' (assignmentCtx = assignmentExpression
{
    $packMetadata.getExpressions().add($assignmentCtx.expressionMetadata);
} ';'
)* '}'
;

switchBlock returns[SwitchMetadata switchMetadata]
: t = SWITCH '(' expCtx = expression ')'
{
    $switchMetadata = new SwitchMetadata($expCtx.expressionMetadata);
    $switchMetadata.setLineNo($t.getLine());
}
'{'
(casesCtx = switchBlockStatementGroup { $switchMetadata.getCases().addAll($casesCtx.caseMetadatas);})*
(defaultCtx = defaultBlockStatement
{
    $switchMetadata.setDefaultCase($defaultCtx.defaultMetadata);
}
)?
'}'
;

switchBlockStatementGroup returns[List<CaseMetadata> caseMetadatas]
@init{
    $caseMetadatas = new LinkedList<CaseMetadata>();
    CaseMetadata caseMetadata;
    BlockMetadata blockMetadata = new BlockMetadata();
}
@after{
    caseMetadata.setStatement(blockMetadata);
}
: (valueCtx = switchLabel
{
caseMetadata = new CaseMetadata($valueCtx.expressionMetadata);
caseMetadata.setLineNo($valueCtx.expressionMetadata.getLineNo());
$caseMetadatas.add(caseMetadata);
}
)+
(statCtx = statement {blockMetadata.getStatements().add($statCtx.statementMetadata);})*
;

switchLabel returns[ExpressionMetadata expressionMetadata]
: CASE (literalCtx = literal) ':' {
$expressionMetadata = $literalCtx.constantMetadata;
}
;

defaultBlockStatement returns[BlockMetadata defaultMetadata]
@init {
    $defaultMetadata = new BlockMetadata();
}
: t = DEFAULT':'
(statCtx = statement {$defaultMetadata.getStatements().add($statCtx.statementMetadata);})*
;

forBlock returns[ForMetadata forMetadata]
: FOR '(' forCtrlCtx = forControl ')' statCtx = statement {
    $forMetadata = $forCtrlCtx.forMetadata;
    $forMetadata.setStatement($statCtx.statementMetadata);
}
;

forControl returns[ForMetadata forMetadata]
@init {
        List initExpressions = null;
        List updateExpressions = null;
        ExpressionMetadata expressionMetadata = null;
}
    :   id = IDENTIFIER t = ':' expCtx = expression {
        $forMetadata = new ForMetadata($id.getText(), $expCtx.expressionMetadata);
        $forMetadata.setLineNo($t.getLine());
    }
    |   (initExpCtx = forInit {initExpressions = $initExpCtx.expressionMetadatas;})? t = ';'
        (condExpCtx = expression {expressionMetadata = $condExpCtx.expressionMetadata;})? ';'
        (afterExpCtx = expressionList {updateExpressions = $afterExpCtx.expressionMetadatas;})? {
        $forMetadata = new ForMetadata(initExpressions, expressionMetadata, updateExpressions);
        $forMetadata.setLineNo($t.getLine());
    }
    ;

forInit returns[List<ExpressionMetadata> expressionMetadatas]
@init {
$expressionMetadatas = new LinkedList();
}
    :   expCtx = assignmentExpression {
        $expressionMetadatas.add($expCtx.expressionMetadata);
    }
    |   expListCtx = expressionList {$expressionMetadatas = $expListCtx.expressionMetadatas;}
    ;

whileBlock returns[WhileMetadata whileMetadata]
: t = WHILE expCtx = parExpression statCtx = statement {
    $whileMetadata = new WhileMetadata($expCtx.expressionMetadata);
    $whileMetadata.setStatement($statCtx.statementMetadata);
    $whileMetadata.setLineNo($t.getLine());
}
;

assignmentExpression returns[ExpressionMetadata expressionMetadata]
: varCtx = variable t = '=' assignmentCtx = assignment
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.EVALUATION_OP, "=", $varCtx.variableMetadata, $assignmentCtx.expressionMetadata);
    $expressionMetadata.setLineNo($t.getLine());
}
| memberCtx = member t = '=' assignmentCtx = assignment
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.EVALUATION_OP, "=", $memberCtx.expressionMetadata, $assignmentCtx.expressionMetadata);
    $expressionMetadata.setLineNo($t.getLine());
}
| opAssignmentCtx = operateAssignment
{
    $expressionMetadata = $opAssignmentCtx.expressionMetadata;
}
;

assignment returns[ExpressionMetadata expressionMetadata]
: collectionCtx = collectionAssignment {$expressionMetadata = $collectionCtx.collectionMetadata;}
| exprCtx = expression {$expressionMetadata = $exprCtx.expressionMetadata;}
;

collectionAssignment returns[CollectionMetadata collectionMetadata]
@init{
    $collectionMetadata = new CollectionMetadata();
}
: '{' exprCtx = assignment
{
    $collectionMetadata.getExpressionMetadatas().add($exprCtx.expressionMetadata);
}
(',' exprCtx = assignment
{
    $collectionMetadata.getExpressionMetadatas().add($exprCtx.expressionMetadata);
}
)* '}'
;

operateAssignment returns[ExpressionMetadata expressionMetadata]
: varCtx = variable
bop=('+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '>>=' | '<<=' | '%=')
exprCtx = expression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.EVALUATION_OP, $bop.getText(), $varCtx.variableMetadata, $exprCtx.expressionMetadata);
}
| idCtx = member
bop=('+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '>>=' | '<<=' | '%=')
exprCtx = expression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.EVALUATION_OP, $bop.getText(), $idCtx.expressionMetadata, $exprCtx.expressionMetadata);
}
;

expression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = logicAndExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop='||' rCtx = logicAndExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.LOGIC_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

logicAndExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = exclusiveOrExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop='&&' rCtx = exclusiveOrExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.LOGIC_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

exclusiveOrExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = bitOrExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop='^' rCtx = bitOrExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.BIT_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

bitOrExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = bitAndExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop='|' rCtx = bitAndExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.BIT_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

bitAndExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = equalExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop='&' rCtx = equalExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.BIT_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

equalExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = compareExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop=('==' | '!=') rCtx = compareExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.COMPARE_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

compareExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = shiftExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop=('<=' | '>=' | '>' | '<') rCtx = shiftExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.COMPARE_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

shiftExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = $lCtx.expressionMetadata;
    }
}
:lCtx = additiveExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop=('<<' | '>>') rCtx = additiveExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.SHIFT_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

additiveExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = lExpr;
    }
}
:lCtx = multiplicativeExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop=('+'|'-') rCtx = multiplicativeExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.ARITHMETIC_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

multiplicativeExpression returns[ExpressionMetadata expressionMetadata]
@init{
    ExpressionMetadata lExpr;
}
@after {
    if ($expressionMetadata == null) {
        $expressionMetadata = lExpr;
    }
}
:lCtx = unaryExpression {
    lExpr = $lCtx.expressionMetadata;
}
(bop=('*'|'/'|'%') rCtx = unaryExpression
{
    $expressionMetadata = new ExpressionMetadata(ExpressionType.ARITHMETIC_OP, $bop.getText(), lExpr, $rCtx.expressionMetadata);
    $expressionMetadata.setLineNo(lExpr.getLineNo());
    lExpr = $expressionMetadata;
}
)*
;

unaryExpression returns[ExpressionMetadata expressionMetadata]
:op = ('!'|'~'|'++'|'--')? primaryCtx = primary
{
    if ($op != null) {
        ExpressionType expressionType;
        if ($op.getText().equals("!")) {
            expressionType = ExpressionType.LOGIC_OP;
        } else if ($op.getText().equals("~")) {
            expressionType = ExpressionType.BIT_OP;
        } else {
            expressionType = ExpressionType.ARITHMETIC_OP;
        }
        $expressionMetadata = new ExpressionMetadata(expressionType, $op.getText(), $primaryCtx.expressionMetadata);
        $expressionMetadata.setLineNo($op.getLine());
    } else {
        $expressionMetadata = $primaryCtx.expressionMetadata;
    }
}
| primaryCtx = primary op = ('++' | '--') {
    if ($op != null) {
        $expressionMetadata = new ExpressionMetadata(ExpressionType.ARITHMETIC_OP, "a"+$op.getText(), $primaryCtx.expressionMetadata);
        $expressionMetadata.setLineNo($op.getLine());
    }
}
;

primary returns[ExpressionMetadata expressionMetadata]
: memberCtx = member {$expressionMetadata = $memberCtx.expressionMetadata;}
| literalCtx = literal {$expressionMetadata = $literalCtx.constantMetadata;}
| exprCtx = parExpression { $expressionMetadata = $exprCtx.expressionMetadata;}
;

parExpression returns[ExpressionMetadata expressionMetadata]
: '(' exprCtx = expression ')'
{
$expressionMetadata = $exprCtx.expressionMetadata;
}
;

member returns[ExpressionMetadata expressionMetadata]
: varCtx = varPrimary {$expressionMetadata = $varCtx.expressionMetadata;}
	(bop = '[' indexCtx = expression {$expressionMetadata = new ExpressionMetadata(ExpressionType.COLLACC_OP, $bop.getText(), $expressionMetadata, $indexCtx.expressionMetadata); $expressionMetadata.setLineNo($bop.getLine());}']')*
	(bop = '.' varCtx = varPrimary { $expressionMetadata = new ExpressionMetadata(ExpressionType.MEMBER, $bop.getText(), $expressionMetadata, $varCtx.expressionMetadata); $expressionMetadata.setLineNo($bop.getLine());}
	(bop = '[' indexCtx = expression {$expressionMetadata = new ExpressionMetadata(ExpressionType.COLLACC_OP, $bop.getText(), $expressionMetadata, $indexCtx.expressionMetadata); $expressionMetadata.setLineNo($bop.getLine());}']')*)*
;

memberMethodCall returns[ExpressionMetadata expressionMetadata]
: varCtx = varPrimary {$expressionMetadata = $varCtx.expressionMetadata;}
	(bop = '[' indexCtx = expression {$expressionMetadata = new ExpressionMetadata(ExpressionType.COLLACC_OP, $bop.getText(), $expressionMetadata, $indexCtx.expressionMetadata); $expressionMetadata.setLineNo($bop.getLine());}']')*
	(bop = '.' methodCtx = methodCall {$expressionMetadata = new ExpressionMetadata(ExpressionType.PROCESS_METHOD, $bop.getText(), $expressionMetadata, $methodCtx.methodMetadata); $expressionMetadata.setLineNo($bop.getLine());})+
;

varPrimary returns[ExpressionMetadata expressionMetadata]
: varCtx = variable {$expressionMetadata = $varCtx.variableMetadata;}
| methodCtx = methodCall {$expressionMetadata = $methodCtx.methodMetadata;}
;

variable returns[VariableMetadata variableMetadata]
:id = IDENTIFIER
{
$variableMetadata = new VariableMetadata($id.getText());
$variableMetadata.setLineNo($id.getLine());
}
;

methodCall returns[MethodMetadata methodMetadata]
@init {
    List<ExpressionMetadata> parameters = null;
}
: t = IDENTIFIER '(' (paramCtx = expressionList { parameters = $paramCtx.expressionMetadatas;})? ')'
{
    $methodMetadata = new MethodMetadata($t.getText());
    if (parameters != null)
        $methodMetadata.setParameters(parameters);
    $methodMetadata.setLineNo($t.getLine());
}
;

expressionList returns[List<ExpressionMetadata> expressionMetadatas]
@init {
    $expressionMetadatas = new LinkedList<ExpressionMetadata>();
}
: expressionCtx = expression
{
$expressionMetadatas.add($expressionCtx.expressionMetadata);
}
(',' expressionCtx = expression
{
$expressionMetadatas.add($expressionCtx.expressionMetadata);
}
)*
;

literal returns[ConstantMetadata constantMetadata]
@init{
    String text = null;
    ExpressionType type = ExpressionType.INTEGER;
}
: ti = integerLiteral
{
text = $ti.token.getText();
if (text.endsWith("l") || text.endsWith("L")) {
    type = ExpressionType.LONG;
}
$constantMetadata = new ConstantMetadata(type, text);
$constantMetadata.setLineNo($ti.token.getLine());
}
| tf = floatLiteral
{
text = $tf.token.getText();
if (text.endsWith("d") || text.endsWith("D")) {
    type = ExpressionType.DOUBLE;
}
$constantMetadata = new ConstantMetadata(type, text);
$constantMetadata.setLineNo($tf.token.getLine());
}
| tc = CHAR_LITERAL
{
$constantMetadata = new ConstantMetadata(ExpressionType.CHAR, $tc.getText());
$constantMetadata.setLineNo($tc.getLine());
}
| ts = STRING_LITERAL
{
$constantMetadata = new ConstantMetadata(ExpressionType.STRING, $ts.getText());
$constantMetadata.setLineNo($ts.getLine());
}
| tb = BOOL_LITERAL
{
$constantMetadata = new ConstantMetadata(ExpressionType.BOOLEAN, $tb.getText());
$constantMetadata.setLineNo($tb.getLine());
}
| tn = NULL_LITERAL
{
$constantMetadata = new ConstantMetadata(ExpressionType.NULL, $tn.getText());
$constantMetadata.setLineNo($tn.getLine());
}
;

integerLiteral returns[Token token]
: t = DECIMAL_LITERAL {$token = $t;}
| t = HEX_LITERAL {$token = $t;}
| t = OCT_LITERAL {$token = $t;}
| t = BINARY_LITERAL {$token = $t;}
;
floatLiteral returns[Token token]
: t = FLOAT_LITERAL {$token = $t;}
| t = HEX_FLOAT_LITERAL {$token = $t;}
;