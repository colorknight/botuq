// Generated from org/datayoo/botu/antlr/BotuParser.g4 by ANTLR 4.7.1
package org.datayoo.botu.antlr;

import java.util.LinkedList;
import org.datayoo.botu.metadata.*;
import org.datayoo.botu.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BotuParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BREAK=1, BOTU=2, CASE=3, DEFAULT=4, ELSE=5, IF=6, IFO=7, FOR=8, WHILE=9, 
		PACK=10, SWITCH=11, RETURN=12, CONTINUE=13, DECIMAL_LITERAL=14, HEX_LITERAL=15, 
		OCT_LITERAL=16, BINARY_LITERAL=17, FLOAT_LITERAL=18, HEX_FLOAT_LITERAL=19, 
		BOOL_LITERAL=20, CHAR_LITERAL=21, STRING_LITERAL=22, NULL_LITERAL=23, 
		LPAREN=24, RPAREN=25, LBRACE=26, RBRACE=27, LBRACK=28, RBRACK=29, SEMI=30, 
		COMMA=31, DOT=32, ASSIGN=33, BANG=34, COLON=35, GT=36, LT=37, EQUAL=38, 
		LE=39, GE=40, NOTEQUAL=41, AND=42, OR=43, ADD=44, SUB=45, MUL=46, DIV=47, 
		BITAND=48, BITOR=49, CARET=50, SWANGDASH=51, MOD=52, LSHIFT=53, RSHIFT=54, 
		ADD_ASSIGN=55, SUB_ASSIGN=56, MUL_ASSIGN=57, DIV_ASSIGN=58, AND_ASSIGN=59, 
		OR_ASSIGN=60, XOR_ASSIGN=61, MOD_ASSIGN=62, LSHIFT_ASSIGN=63, RSHIFT_ASSIGN=64, 
		PLUSPLUS=65, MINUSMINUS=66, WS=67, COMMENT=68, LINE_COMMENT=69, IDENTIFIER=70;
	public static final int
		RULE_botuUnit = 0, RULE_block = 1, RULE_statement = 2, RULE_botu_break = 3, 
		RULE_botu_continue = 4, RULE_botu_return = 5, RULE_ifBlock = 6, RULE_packBlock = 7, 
		RULE_switchBlock = 8, RULE_switchBlockStatementGroup = 9, RULE_switchLabel = 10, 
		RULE_defaultBlockStatement = 11, RULE_forBlock = 12, RULE_forControl = 13, 
		RULE_forInit = 14, RULE_whileBlock = 15, RULE_assignmentExpression = 16, 
		RULE_assignment = 17, RULE_collectionAssignment = 18, RULE_operateAssignment = 19, 
		RULE_expression = 20, RULE_logicAndExpression = 21, RULE_exclusiveOrExpression = 22, 
		RULE_bitOrExpression = 23, RULE_bitAndExpression = 24, RULE_equalExpression = 25, 
		RULE_compareExpression = 26, RULE_shiftExpression = 27, RULE_additiveExpression = 28, 
		RULE_multiplicativeExpression = 29, RULE_unaryExpression = 30, RULE_primary = 31, 
		RULE_parExpression = 32, RULE_member = 33, RULE_memberMethodCall = 34, 
		RULE_varPrimary = 35, RULE_variable = 36, RULE_methodCall = 37, RULE_expressionList = 38, 
		RULE_literal = 39, RULE_integerLiteral = 40, RULE_floatLiteral = 41;
	public static final String[] ruleNames = {
		"botuUnit", "block", "statement", "botu_break", "botu_continue", "botu_return", 
		"ifBlock", "packBlock", "switchBlock", "switchBlockStatementGroup", "switchLabel", 
		"defaultBlockStatement", "forBlock", "forControl", "forInit", "whileBlock", 
		"assignmentExpression", "assignment", "collectionAssignment", "operateAssignment", 
		"expression", "logicAndExpression", "exclusiveOrExpression", "bitOrExpression", 
		"bitAndExpression", "equalExpression", "compareExpression", "shiftExpression", 
		"additiveExpression", "multiplicativeExpression", "unaryExpression", "primary", 
		"parExpression", "member", "memberMethodCall", "varPrimary", "variable", 
		"methodCall", "expressionList", "literal", "integerLiteral", "floatLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'break'", "'botu'", "'case'", "'default'", "'else'", "'if'", "'ifo'", 
		"'for'", "'while'", "'pack'", "'switch'", "'return'", "'continue'", null, 
		null, null, null, null, null, null, null, null, "'null'", "'('", "')'", 
		"'{'", "'}'", "'['", "']'", "';'", "','", "'.'", "'='", "'!'", "':'", 
		"'>'", "'<'", "'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'+'", "'-'", 
		"'*'", "'/'", "'&'", "'|'", "'^'", "'~'", "'%'", "'<<'", "'>>'", "'+='", 
		"'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'<<='", "'>>='", 
		"'++'", "'--'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "BREAK", "BOTU", "CASE", "DEFAULT", "ELSE", "IF", "IFO", "FOR", 
		"WHILE", "PACK", "SWITCH", "RETURN", "CONTINUE", "DECIMAL_LITERAL", "HEX_LITERAL", 
		"OCT_LITERAL", "BINARY_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", 
		"BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "NULL_LITERAL", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", 
		"ASSIGN", "BANG", "COLON", "GT", "LT", "EQUAL", "LE", "GE", "NOTEQUAL", 
		"AND", "OR", "ADD", "SUB", "MUL", "DIV", "BITAND", "BITOR", "CARET", "SWANGDASH", 
		"MOD", "LSHIFT", "RSHIFT", "ADD_ASSIGN", "SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", 
		"AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "MOD_ASSIGN", "LSHIFT_ASSIGN", 
		"RSHIFT_ASSIGN", "PLUSPLUS", "MINUSMINUS", "WS", "COMMENT", "LINE_COMMENT", 
		"IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BotuParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BotuParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BotuUnitContext extends ParserRuleContext {
		public BotuMetadata botuMetadata;
		public Token t;
		public Token n;
		public BlockContext blockCtx;
		public TerminalNode EOF() { return getToken(BotuParser.EOF, 0); }
		public TerminalNode BOTU() { return getToken(BotuParser.BOTU, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(BotuParser.STRING_LITERAL, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BotuUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_botuUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBotuUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBotuUnit(this);
		}
	}

	public final BotuUnitContext botuUnit() throws RecognitionException {
		BotuUnitContext _localctx = new BotuUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_botuUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			((BotuUnitContext)_localctx).t = match(BOTU);
			setState(85);
			match(LPAREN);
			setState(86);
			((BotuUnitContext)_localctx).n = match(STRING_LITERAL);
			setState(87);
			match(RPAREN);
			setState(88);
			((BotuUnitContext)_localctx).blockCtx = block();
			setState(89);
			match(EOF);
			}
			_ctx.stop = _input.LT(-1);

			    String name = ((BotuUnitContext)_localctx).n.getText();
			    ((BotuUnitContext)_localctx).botuMetadata =  new BotuMetadata(StringUtils.toMemString(name));
			    _localctx.botuMetadata.setStatement(((BotuUnitContext)_localctx).blockCtx.blockMetadata);
			    _localctx.botuMetadata.setLineNo(((BotuUnitContext)_localctx).t.getLine());

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public BlockMetadata blockMetadata;
		public StatementContext statCtx;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);

		    ((BlockContext)_localctx).blockMetadata =  new BlockMetadata();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(LBRACE);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BREAK) | (1L << IF) | (1L << IFO) | (1L << FOR) | (1L << WHILE) | (1L << PACK) | (1L << SWITCH) | (1L << RETURN) | (1L << CONTINUE) | (1L << LBRACE))) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(92);
				((BlockContext)_localctx).statCtx = statement();
				_localctx.blockMetadata.getStatements().add(((BlockContext)_localctx).statCtx.statementMetadata);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statementable statementMetadata;
		public BlockContext blockCtx;
		public Botu_breakContext breakCtx;
		public Botu_continueContext continueCtx;
		public Botu_returnContext returnCtx;
		public IfBlockContext ifCtx;
		public PackBlockContext packCtx;
		public SwitchBlockContext switchCtx;
		public ForBlockContext forCtx;
		public WhileBlockContext whileCtx;
		public AssignmentExpressionContext expressionCtx;
		public MethodCallContext methodCtx;
		public MemberMethodCallContext memberMethodCtx;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Botu_breakContext botu_break() {
			return getRuleContext(Botu_breakContext.class,0);
		}
		public Botu_continueContext botu_continue() {
			return getRuleContext(Botu_continueContext.class,0);
		}
		public Botu_returnContext botu_return() {
			return getRuleContext(Botu_returnContext.class,0);
		}
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public PackBlockContext packBlock() {
			return getRuleContext(PackBlockContext.class,0);
		}
		public SwitchBlockContext switchBlock() {
			return getRuleContext(SwitchBlockContext.class,0);
		}
		public ForBlockContext forBlock() {
			return getRuleContext(ForBlockContext.class,0);
		}
		public WhileBlockContext whileBlock() {
			return getRuleContext(WhileBlockContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MemberMethodCallContext memberMethodCall() {
			return getRuleContext(MemberMethodCallContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				((StatementContext)_localctx).blockCtx = block();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).blockCtx.blockMetadata;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				((StatementContext)_localctx).breakCtx = botu_break();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).breakCtx.breakMetadata; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				((StatementContext)_localctx).continueCtx = botu_continue();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).continueCtx.continueMetadata; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				((StatementContext)_localctx).returnCtx = botu_return();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).returnCtx.returnMetadata; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				((StatementContext)_localctx).ifCtx = ifBlock();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).ifCtx.ifMetadata;
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(117);
				((StatementContext)_localctx).packCtx = packBlock();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).packCtx.packMetadata;
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(120);
				((StatementContext)_localctx).switchCtx = switchBlock();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).switchCtx.switchMetadata;
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(123);
				((StatementContext)_localctx).forCtx = forBlock();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).forCtx.forMetadata;
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(126);
				((StatementContext)_localctx).whileCtx = whileBlock();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).whileCtx.whileMetadata;
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(129);
				((StatementContext)_localctx).expressionCtx = assignmentExpression();
				setState(130);
				match(SEMI);
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).expressionCtx.expressionMetadata;
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(133);
				((StatementContext)_localctx).methodCtx = methodCall();
				setState(134);
				match(SEMI);

				((StatementContext)_localctx).methodCtx.methodMetadata.toProcessFuncType();
				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).methodCtx.methodMetadata;

				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(137);
				((StatementContext)_localctx).memberMethodCtx = memberMethodCall();
				setState(138);
				match(SEMI);

				((StatementContext)_localctx).statementMetadata =  ((StatementContext)_localctx).memberMethodCtx.expressionMetadata;

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Botu_breakContext extends ParserRuleContext {
		public BreakMetadata breakMetadata;
		public Token t;
		public TerminalNode BREAK() { return getToken(BotuParser.BREAK, 0); }
		public Botu_breakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_botu_break; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBotu_break(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBotu_break(this);
		}
	}

	public final Botu_breakContext botu_break() throws RecognitionException {
		Botu_breakContext _localctx = new Botu_breakContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_botu_break);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			((Botu_breakContext)_localctx).t = match(BREAK);
			setState(144);
			match(SEMI);

			    ((Botu_breakContext)_localctx).breakMetadata =  new BreakMetadata();
			    _localctx.breakMetadata.setLineNo(((Botu_breakContext)_localctx).t.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Botu_continueContext extends ParserRuleContext {
		public ContinueMetadata continueMetadata;
		public Token t;
		public TerminalNode CONTINUE() { return getToken(BotuParser.CONTINUE, 0); }
		public Botu_continueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_botu_continue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBotu_continue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBotu_continue(this);
		}
	}

	public final Botu_continueContext botu_continue() throws RecognitionException {
		Botu_continueContext _localctx = new Botu_continueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_botu_continue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			((Botu_continueContext)_localctx).t = match(CONTINUE);
			setState(148);
			match(SEMI);

			    ((Botu_continueContext)_localctx).continueMetadata =  new ContinueMetadata();
			    _localctx.continueMetadata.setLineNo(((Botu_continueContext)_localctx).t.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Botu_returnContext extends ParserRuleContext {
		public ReturnMetadata returnMetadata;
		public Token t;
		public TerminalNode RETURN() { return getToken(BotuParser.RETURN, 0); }
		public Botu_returnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_botu_return; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBotu_return(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBotu_return(this);
		}
	}

	public final Botu_returnContext botu_return() throws RecognitionException {
		Botu_returnContext _localctx = new Botu_returnContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_botu_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((Botu_returnContext)_localctx).t = match(RETURN);
			setState(152);
			match(SEMI);

			    ((Botu_returnContext)_localctx).returnMetadata =  new ReturnMetadata();
			    _localctx.returnMetadata.setLineNo(((Botu_returnContext)_localctx).t.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfBlockContext extends ParserRuleContext {
		public IfMetadata ifMetadata;
		public Token t;
		public ParExpressionContext conditionCtx;
		public StatementContext ifCtx;
		public StatementContext elseCtx;
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode IF() { return getToken(BotuParser.IF, 0); }
		public TerminalNode IFO() { return getToken(BotuParser.IFO, 0); }
		public TerminalNode ELSE() { return getToken(BotuParser.ELSE, 0); }
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterIfBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitIfBlock(this);
		}
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifBlock);

		    IfMetadata elseMetadata = null;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			((IfBlockContext)_localctx).t = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==IF || _la==IFO) ) {
				((IfBlockContext)_localctx).t = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(156);
			((IfBlockContext)_localctx).conditionCtx = parExpression();
			setState(157);
			((IfBlockContext)_localctx).ifCtx = statement();

			    if (((IfBlockContext)_localctx).t.getText().equals("if")) {
			        ((IfBlockContext)_localctx).ifMetadata =  new IfMetadata(((IfBlockContext)_localctx).conditionCtx.expressionMetadata, false);
			    } else {
			        ((IfBlockContext)_localctx).ifMetadata =  new IfMetadata(((IfBlockContext)_localctx).conditionCtx.expressionMetadata, true);
			    }
			    _localctx.ifMetadata.setStatement(((IfBlockContext)_localctx).ifCtx.statementMetadata);
			    _localctx.ifMetadata.setLineNo(((IfBlockContext)_localctx).t.getLine());

			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(159);
				match(ELSE);
				setState(160);
				((IfBlockContext)_localctx).elseCtx = statement();

				    if (((IfBlockContext)_localctx).elseCtx.statementMetadata instanceof IfMetadata) {
				        elseMetadata = (IfMetadata)((IfBlockContext)_localctx).elseCtx.statementMetadata;
				        _localctx.ifMetadata.getBranches().add(elseMetadata);
				        _localctx.ifMetadata.getBranches().addAll(elseMetadata.getBranches());
				    } else {
				        elseMetadata = new IfMetadata();
				        elseMetadata.setStatement(((IfBlockContext)_localctx).elseCtx.statementMetadata);
				        _localctx.ifMetadata.getBranches().add(elseMetadata);
				    }

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackBlockContext extends ParserRuleContext {
		public PackMetadata packMetadata;
		public Token t;
		public Token s;
		public Token m;
		public AssignmentExpressionContext assignmentCtx;
		public TerminalNode PACK() { return getToken(BotuParser.PACK, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(BotuParser.STRING_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(BotuParser.BOOL_LITERAL, 0); }
		public List<AssignmentExpressionContext> assignmentExpression() {
			return getRuleContexts(AssignmentExpressionContext.class);
		}
		public AssignmentExpressionContext assignmentExpression(int i) {
			return getRuleContext(AssignmentExpressionContext.class,i);
		}
		public PackBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterPackBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitPackBlock(this);
		}
	}

	public final PackBlockContext packBlock() throws RecognitionException {
		PackBlockContext _localctx = new PackBlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_packBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			((PackBlockContext)_localctx).t = match(PACK);
			setState(166);
			match(LPAREN);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING_LITERAL) {
				{
				setState(167);
				((PackBlockContext)_localctx).s = match(STRING_LITERAL);
				}
			}

			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(170);
				match(COMMA);
				setState(171);
				((PackBlockContext)_localctx).m = match(BOOL_LITERAL);
				}
			}

			setState(174);
			match(RPAREN);

			    boolean mapMode = true;
			    if (((PackBlockContext)_localctx).m != null) {
			        mapMode = Boolean.valueOf(Boolean.valueOf(((PackBlockContext)_localctx).m.getText()));
			    }
			    if (((PackBlockContext)_localctx).s != null) {
			        String schemaName = ((PackBlockContext)_localctx).s.getText();
			        ((PackBlockContext)_localctx).packMetadata =  new PackMetadata(StringUtils.toMemString(schemaName), mapMode);
			    } else {
			        ((PackBlockContext)_localctx).packMetadata =  new PackMetadata(null, mapMode);
			    }
			    _localctx.packMetadata.setLineNo(((PackBlockContext)_localctx).t.getLine());

			setState(176);
			match(LBRACE);
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(177);
				((PackBlockContext)_localctx).assignmentCtx = assignmentExpression();

				    _localctx.packMetadata.getExpressions().add(((PackBlockContext)_localctx).assignmentCtx.expressionMetadata);

				setState(179);
				match(SEMI);
				}
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(186);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBlockContext extends ParserRuleContext {
		public SwitchMetadata switchMetadata;
		public Token t;
		public ExpressionContext expCtx;
		public SwitchBlockStatementGroupContext casesCtx;
		public DefaultBlockStatementContext defaultCtx;
		public TerminalNode SWITCH() { return getToken(BotuParser.SWITCH, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public DefaultBlockStatementContext defaultBlockStatement() {
			return getRuleContext(DefaultBlockStatementContext.class,0);
		}
		public SwitchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterSwitchBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitSwitchBlock(this);
		}
	}

	public final SwitchBlockContext switchBlock() throws RecognitionException {
		SwitchBlockContext _localctx = new SwitchBlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_switchBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((SwitchBlockContext)_localctx).t = match(SWITCH);
			setState(189);
			match(LPAREN);
			setState(190);
			((SwitchBlockContext)_localctx).expCtx = expression();
			setState(191);
			match(RPAREN);

			    ((SwitchBlockContext)_localctx).switchMetadata =  new SwitchMetadata(((SwitchBlockContext)_localctx).expCtx.expressionMetadata);
			    _localctx.switchMetadata.setLineNo(((SwitchBlockContext)_localctx).t.getLine());

			setState(193);
			match(LBRACE);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(194);
				((SwitchBlockContext)_localctx).casesCtx = switchBlockStatementGroup();
				 _localctx.switchMetadata.getCases().addAll(((SwitchBlockContext)_localctx).casesCtx.caseMetadatas);
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(202);
				((SwitchBlockContext)_localctx).defaultCtx = defaultBlockStatement();

				    _localctx.switchMetadata.setDefaultCase(((SwitchBlockContext)_localctx).defaultCtx.defaultMetadata);

				}
			}

			setState(207);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public List<CaseMetadata> caseMetadatas;
		public SwitchLabelContext valueCtx;
		public StatementContext statCtx;
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitSwitchBlockStatementGroup(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_switchBlockStatementGroup);

		    ((SwitchBlockStatementGroupContext)_localctx).caseMetadatas =  new LinkedList<CaseMetadata>();
		    CaseMetadata caseMetadata;
		    BlockMetadata blockMetadata = new BlockMetadata();

		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(212); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(209);
					((SwitchBlockStatementGroupContext)_localctx).valueCtx = switchLabel();

					caseMetadata = new CaseMetadata(((SwitchBlockStatementGroupContext)_localctx).valueCtx.expressionMetadata);
					caseMetadata.setLineNo(((SwitchBlockStatementGroupContext)_localctx).valueCtx.expressionMetadata.getLineNo());
					_localctx.caseMetadatas.add(caseMetadata);

					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(214); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BREAK) | (1L << IF) | (1L << IFO) | (1L << FOR) | (1L << WHILE) | (1L << PACK) | (1L << SWITCH) | (1L << RETURN) | (1L << CONTINUE) | (1L << LBRACE))) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(216);
				((SwitchBlockStatementGroupContext)_localctx).statCtx = statement();
				blockMetadata.getStatements().add(((SwitchBlockStatementGroupContext)_localctx).statCtx.statementMetadata);
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    caseMetadata.setStatement(blockMetadata);

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchLabelContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public LiteralContext literalCtx;
		public TerminalNode CASE() { return getToken(BotuParser.CASE, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitSwitchLabel(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_switchLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(CASE);
			{
			setState(225);
			((SwitchLabelContext)_localctx).literalCtx = literal();
			}
			setState(226);
			match(COLON);

			((SwitchLabelContext)_localctx).expressionMetadata =  ((SwitchLabelContext)_localctx).literalCtx.constantMetadata;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultBlockStatementContext extends ParserRuleContext {
		public BlockMetadata defaultMetadata;
		public Token t;
		public StatementContext statCtx;
		public TerminalNode DEFAULT() { return getToken(BotuParser.DEFAULT, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public DefaultBlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultBlockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterDefaultBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitDefaultBlockStatement(this);
		}
	}

	public final DefaultBlockStatementContext defaultBlockStatement() throws RecognitionException {
		DefaultBlockStatementContext _localctx = new DefaultBlockStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_defaultBlockStatement);

		    ((DefaultBlockStatementContext)_localctx).defaultMetadata =  new BlockMetadata();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			((DefaultBlockStatementContext)_localctx).t = match(DEFAULT);
			setState(230);
			match(COLON);
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BREAK) | (1L << IF) | (1L << IFO) | (1L << FOR) | (1L << WHILE) | (1L << PACK) | (1L << SWITCH) | (1L << RETURN) | (1L << CONTINUE) | (1L << LBRACE))) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(231);
				((DefaultBlockStatementContext)_localctx).statCtx = statement();
				_localctx.defaultMetadata.getStatements().add(((DefaultBlockStatementContext)_localctx).statCtx.statementMetadata);
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForBlockContext extends ParserRuleContext {
		public ForMetadata forMetadata;
		public ForControlContext forCtrlCtx;
		public StatementContext statCtx;
		public TerminalNode FOR() { return getToken(BotuParser.FOR, 0); }
		public ForControlContext forControl() {
			return getRuleContext(ForControlContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterForBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitForBlock(this);
		}
	}

	public final ForBlockContext forBlock() throws RecognitionException {
		ForBlockContext _localctx = new ForBlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(FOR);
			setState(240);
			match(LPAREN);
			setState(241);
			((ForBlockContext)_localctx).forCtrlCtx = forControl();
			setState(242);
			match(RPAREN);
			setState(243);
			((ForBlockContext)_localctx).statCtx = statement();

			    ((ForBlockContext)_localctx).forMetadata =  ((ForBlockContext)_localctx).forCtrlCtx.forMetadata;
			    _localctx.forMetadata.setStatement(((ForBlockContext)_localctx).statCtx.statementMetadata);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForControlContext extends ParserRuleContext {
		public ForMetadata forMetadata;
		public Token id;
		public Token t;
		public ExpressionContext expCtx;
		public ForInitContext initExpCtx;
		public ExpressionContext condExpCtx;
		public ExpressionListContext afterExpCtx;
		public TerminalNode IDENTIFIER() { return getToken(BotuParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitForControl(this);
		}
	}

	public final ForControlContext forControl() throws RecognitionException {
		ForControlContext _localctx = new ForControlContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forControl);

		        List initExpressions = null;
		        List updateExpressions = null;
		        ExpressionMetadata expressionMetadata = null;

		int _la;
		try {
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				((ForControlContext)_localctx).id = match(IDENTIFIER);
				setState(247);
				((ForControlContext)_localctx).t = match(COLON);
				setState(248);
				((ForControlContext)_localctx).expCtx = expression();

				        ((ForControlContext)_localctx).forMetadata =  new ForMetadata(((ForControlContext)_localctx).id.getText(), ((ForControlContext)_localctx).expCtx.expressionMetadata);
				        _localctx.forMetadata.setLineNo(((ForControlContext)_localctx).t.getLine());
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 14)) & ~0x3f) == 0 && ((1L << (_la - 14)) & ((1L << (DECIMAL_LITERAL - 14)) | (1L << (HEX_LITERAL - 14)) | (1L << (OCT_LITERAL - 14)) | (1L << (BINARY_LITERAL - 14)) | (1L << (FLOAT_LITERAL - 14)) | (1L << (HEX_FLOAT_LITERAL - 14)) | (1L << (BOOL_LITERAL - 14)) | (1L << (CHAR_LITERAL - 14)) | (1L << (STRING_LITERAL - 14)) | (1L << (NULL_LITERAL - 14)) | (1L << (LPAREN - 14)) | (1L << (BANG - 14)) | (1L << (SWANGDASH - 14)) | (1L << (PLUSPLUS - 14)) | (1L << (MINUSMINUS - 14)) | (1L << (IDENTIFIER - 14)))) != 0)) {
					{
					setState(251);
					((ForControlContext)_localctx).initExpCtx = forInit();
					initExpressions = ((ForControlContext)_localctx).initExpCtx.expressionMetadatas;
					}
				}

				setState(256);
				((ForControlContext)_localctx).t = match(SEMI);
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 14)) & ~0x3f) == 0 && ((1L << (_la - 14)) & ((1L << (DECIMAL_LITERAL - 14)) | (1L << (HEX_LITERAL - 14)) | (1L << (OCT_LITERAL - 14)) | (1L << (BINARY_LITERAL - 14)) | (1L << (FLOAT_LITERAL - 14)) | (1L << (HEX_FLOAT_LITERAL - 14)) | (1L << (BOOL_LITERAL - 14)) | (1L << (CHAR_LITERAL - 14)) | (1L << (STRING_LITERAL - 14)) | (1L << (NULL_LITERAL - 14)) | (1L << (LPAREN - 14)) | (1L << (BANG - 14)) | (1L << (SWANGDASH - 14)) | (1L << (PLUSPLUS - 14)) | (1L << (MINUSMINUS - 14)) | (1L << (IDENTIFIER - 14)))) != 0)) {
					{
					setState(257);
					((ForControlContext)_localctx).condExpCtx = expression();
					expressionMetadata = ((ForControlContext)_localctx).condExpCtx.expressionMetadata;
					}
				}

				setState(262);
				match(SEMI);
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 14)) & ~0x3f) == 0 && ((1L << (_la - 14)) & ((1L << (DECIMAL_LITERAL - 14)) | (1L << (HEX_LITERAL - 14)) | (1L << (OCT_LITERAL - 14)) | (1L << (BINARY_LITERAL - 14)) | (1L << (FLOAT_LITERAL - 14)) | (1L << (HEX_FLOAT_LITERAL - 14)) | (1L << (BOOL_LITERAL - 14)) | (1L << (CHAR_LITERAL - 14)) | (1L << (STRING_LITERAL - 14)) | (1L << (NULL_LITERAL - 14)) | (1L << (LPAREN - 14)) | (1L << (BANG - 14)) | (1L << (SWANGDASH - 14)) | (1L << (PLUSPLUS - 14)) | (1L << (MINUSMINUS - 14)) | (1L << (IDENTIFIER - 14)))) != 0)) {
					{
					setState(263);
					((ForControlContext)_localctx).afterExpCtx = expressionList();
					updateExpressions = ((ForControlContext)_localctx).afterExpCtx.expressionMetadatas;
					}
				}


				        ((ForControlContext)_localctx).forMetadata =  new ForMetadata(initExpressions, expressionMetadata, updateExpressions);
				        _localctx.forMetadata.setLineNo(((ForControlContext)_localctx).t.getLine());
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public List<ExpressionMetadata> expressionMetadatas;
		public AssignmentExpressionContext expCtx;
		public ExpressionListContext expListCtx;
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitForInit(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forInit);

		((ForInitContext)_localctx).expressionMetadatas =  new LinkedList();

		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				((ForInitContext)_localctx).expCtx = assignmentExpression();

				        _localctx.expressionMetadatas.add(((ForInitContext)_localctx).expCtx.expressionMetadata);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				((ForInitContext)_localctx).expListCtx = expressionList();
				((ForInitContext)_localctx).expressionMetadatas =  ((ForInitContext)_localctx).expListCtx.expressionMetadatas;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileBlockContext extends ParserRuleContext {
		public WhileMetadata whileMetadata;
		public Token t;
		public ParExpressionContext expCtx;
		public StatementContext statCtx;
		public TerminalNode WHILE() { return getToken(BotuParser.WHILE, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterWhileBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitWhileBlock(this);
		}
	}

	public final WhileBlockContext whileBlock() throws RecognitionException {
		WhileBlockContext _localctx = new WhileBlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_whileBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			((WhileBlockContext)_localctx).t = match(WHILE);
			setState(280);
			((WhileBlockContext)_localctx).expCtx = parExpression();
			setState(281);
			((WhileBlockContext)_localctx).statCtx = statement();

			    ((WhileBlockContext)_localctx).whileMetadata =  new WhileMetadata(((WhileBlockContext)_localctx).expCtx.expressionMetadata);
			    _localctx.whileMetadata.setStatement(((WhileBlockContext)_localctx).statCtx.statementMetadata);
			    _localctx.whileMetadata.setLineNo(((WhileBlockContext)_localctx).t.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public VariableContext varCtx;
		public Token t;
		public AssignmentContext assignmentCtx;
		public MemberContext memberCtx;
		public OperateAssignmentContext opAssignmentCtx;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public MemberContext member() {
			return getRuleContext(MemberContext.class,0);
		}
		public OperateAssignmentContext operateAssignment() {
			return getRuleContext(OperateAssignmentContext.class,0);
		}
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitAssignmentExpression(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_assignmentExpression);
		try {
			setState(297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284);
				((AssignmentExpressionContext)_localctx).varCtx = variable();
				setState(285);
				((AssignmentExpressionContext)_localctx).t = match(ASSIGN);
				setState(286);
				((AssignmentExpressionContext)_localctx).assignmentCtx = assignment();

				    ((AssignmentExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.EVALUATION_OP, "=", ((AssignmentExpressionContext)_localctx).varCtx.variableMetadata, ((AssignmentExpressionContext)_localctx).assignmentCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(((AssignmentExpressionContext)_localctx).t.getLine());

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				((AssignmentExpressionContext)_localctx).memberCtx = member();
				setState(290);
				((AssignmentExpressionContext)_localctx).t = match(ASSIGN);
				setState(291);
				((AssignmentExpressionContext)_localctx).assignmentCtx = assignment();

				    ((AssignmentExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.EVALUATION_OP, "=", ((AssignmentExpressionContext)_localctx).memberCtx.expressionMetadata, ((AssignmentExpressionContext)_localctx).assignmentCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(((AssignmentExpressionContext)_localctx).t.getLine());

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				((AssignmentExpressionContext)_localctx).opAssignmentCtx = operateAssignment();

				    ((AssignmentExpressionContext)_localctx).expressionMetadata =  ((AssignmentExpressionContext)_localctx).opAssignmentCtx.expressionMetadata;

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public CollectionAssignmentContext collectionCtx;
		public ExpressionContext exprCtx;
		public CollectionAssignmentContext collectionAssignment() {
			return getRuleContext(CollectionAssignmentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_assignment);
		try {
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(299);
				((AssignmentContext)_localctx).collectionCtx = collectionAssignment();
				((AssignmentContext)_localctx).expressionMetadata =  ((AssignmentContext)_localctx).collectionCtx.collectionMetadata;
				}
				break;
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NULL_LITERAL:
			case LPAREN:
			case BANG:
			case SWANGDASH:
			case PLUSPLUS:
			case MINUSMINUS:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				((AssignmentContext)_localctx).exprCtx = expression();
				((AssignmentContext)_localctx).expressionMetadata =  ((AssignmentContext)_localctx).exprCtx.expressionMetadata;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CollectionAssignmentContext extends ParserRuleContext {
		public CollectionMetadata collectionMetadata;
		public AssignmentContext exprCtx;
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public CollectionAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterCollectionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitCollectionAssignment(this);
		}
	}

	public final CollectionAssignmentContext collectionAssignment() throws RecognitionException {
		CollectionAssignmentContext _localctx = new CollectionAssignmentContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_collectionAssignment);

		    ((CollectionAssignmentContext)_localctx).collectionMetadata =  new CollectionMetadata();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(LBRACE);
			setState(308);
			((CollectionAssignmentContext)_localctx).exprCtx = assignment();

			    _localctx.collectionMetadata.getExpressionMetadatas().add(((CollectionAssignmentContext)_localctx).exprCtx.expressionMetadata);

			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(310);
				match(COMMA);
				setState(311);
				((CollectionAssignmentContext)_localctx).exprCtx = assignment();

				    _localctx.collectionMetadata.getExpressionMetadatas().add(((CollectionAssignmentContext)_localctx).exprCtx.expressionMetadata);

				}
				}
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(319);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperateAssignmentContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public VariableContext varCtx;
		public Token bop;
		public ExpressionContext exprCtx;
		public MemberContext idCtx;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MemberContext member() {
			return getRuleContext(MemberContext.class,0);
		}
		public OperateAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operateAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterOperateAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitOperateAssignment(this);
		}
	}

	public final OperateAssignmentContext operateAssignment() throws RecognitionException {
		OperateAssignmentContext _localctx = new OperateAssignmentContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_operateAssignment);
		int _la;
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				((OperateAssignmentContext)_localctx).varCtx = variable();
				setState(322);
				((OperateAssignmentContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (ADD_ASSIGN - 55)) | (1L << (SUB_ASSIGN - 55)) | (1L << (MUL_ASSIGN - 55)) | (1L << (DIV_ASSIGN - 55)) | (1L << (AND_ASSIGN - 55)) | (1L << (OR_ASSIGN - 55)) | (1L << (XOR_ASSIGN - 55)) | (1L << (MOD_ASSIGN - 55)) | (1L << (LSHIFT_ASSIGN - 55)) | (1L << (RSHIFT_ASSIGN - 55)))) != 0)) ) {
					((OperateAssignmentContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(323);
				((OperateAssignmentContext)_localctx).exprCtx = expression();

				    ((OperateAssignmentContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.EVALUATION_OP, ((OperateAssignmentContext)_localctx).bop.getText(), ((OperateAssignmentContext)_localctx).varCtx.variableMetadata, ((OperateAssignmentContext)_localctx).exprCtx.expressionMetadata);

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(326);
				((OperateAssignmentContext)_localctx).idCtx = member();
				setState(327);
				((OperateAssignmentContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (ADD_ASSIGN - 55)) | (1L << (SUB_ASSIGN - 55)) | (1L << (MUL_ASSIGN - 55)) | (1L << (DIV_ASSIGN - 55)) | (1L << (AND_ASSIGN - 55)) | (1L << (OR_ASSIGN - 55)) | (1L << (XOR_ASSIGN - 55)) | (1L << (MOD_ASSIGN - 55)) | (1L << (LSHIFT_ASSIGN - 55)) | (1L << (RSHIFT_ASSIGN - 55)))) != 0)) ) {
					((OperateAssignmentContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(328);
				((OperateAssignmentContext)_localctx).exprCtx = expression();

				    ((OperateAssignmentContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.EVALUATION_OP, ((OperateAssignmentContext)_localctx).bop.getText(), ((OperateAssignmentContext)_localctx).idCtx.expressionMetadata, ((OperateAssignmentContext)_localctx).exprCtx.expressionMetadata);

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public LogicAndExpressionContext lCtx;
		public Token bop;
		public LogicAndExpressionContext rCtx;
		public List<LogicAndExpressionContext> logicAndExpression() {
			return getRuleContexts(LogicAndExpressionContext.class);
		}
		public LogicAndExpressionContext logicAndExpression(int i) {
			return getRuleContext(LogicAndExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			((ExpressionContext)_localctx).lCtx = logicAndExpression();

			    lExpr = ((ExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(335);
				((ExpressionContext)_localctx).bop = match(OR);
				setState(336);
				((ExpressionContext)_localctx).rCtx = logicAndExpression();

				    ((ExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.LOGIC_OP, ((ExpressionContext)_localctx).bop.getText(), lExpr, ((ExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((ExpressionContext)_localctx).expressionMetadata =  ((ExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicAndExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public ExclusiveOrExpressionContext lCtx;
		public Token bop;
		public ExclusiveOrExpressionContext rCtx;
		public List<ExclusiveOrExpressionContext> exclusiveOrExpression() {
			return getRuleContexts(ExclusiveOrExpressionContext.class);
		}
		public ExclusiveOrExpressionContext exclusiveOrExpression(int i) {
			return getRuleContext(ExclusiveOrExpressionContext.class,i);
		}
		public LogicAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterLogicAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitLogicAndExpression(this);
		}
	}

	public final LogicAndExpressionContext logicAndExpression() throws RecognitionException {
		LogicAndExpressionContext _localctx = new LogicAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_logicAndExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			((LogicAndExpressionContext)_localctx).lCtx = exclusiveOrExpression();

			    lExpr = ((LogicAndExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(346);
				((LogicAndExpressionContext)_localctx).bop = match(AND);
				setState(347);
				((LogicAndExpressionContext)_localctx).rCtx = exclusiveOrExpression();

				    ((LogicAndExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.LOGIC_OP, ((LogicAndExpressionContext)_localctx).bop.getText(), lExpr, ((LogicAndExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((LogicAndExpressionContext)_localctx).expressionMetadata =  ((LogicAndExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExclusiveOrExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public BitOrExpressionContext lCtx;
		public Token bop;
		public BitOrExpressionContext rCtx;
		public List<BitOrExpressionContext> bitOrExpression() {
			return getRuleContexts(BitOrExpressionContext.class);
		}
		public BitOrExpressionContext bitOrExpression(int i) {
			return getRuleContext(BitOrExpressionContext.class,i);
		}
		public ExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterExclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitExclusiveOrExpression(this);
		}
	}

	public final ExclusiveOrExpressionContext exclusiveOrExpression() throws RecognitionException {
		ExclusiveOrExpressionContext _localctx = new ExclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_exclusiveOrExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			((ExclusiveOrExpressionContext)_localctx).lCtx = bitOrExpression();

			    lExpr = ((ExclusiveOrExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CARET) {
				{
				{
				setState(357);
				((ExclusiveOrExpressionContext)_localctx).bop = match(CARET);
				setState(358);
				((ExclusiveOrExpressionContext)_localctx).rCtx = bitOrExpression();

				    ((ExclusiveOrExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.BIT_OP, ((ExclusiveOrExpressionContext)_localctx).bop.getText(), lExpr, ((ExclusiveOrExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((ExclusiveOrExpressionContext)_localctx).expressionMetadata =  ((ExclusiveOrExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitOrExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public BitAndExpressionContext lCtx;
		public Token bop;
		public BitAndExpressionContext rCtx;
		public List<BitAndExpressionContext> bitAndExpression() {
			return getRuleContexts(BitAndExpressionContext.class);
		}
		public BitAndExpressionContext bitAndExpression(int i) {
			return getRuleContext(BitAndExpressionContext.class,i);
		}
		public BitOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBitOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBitOrExpression(this);
		}
	}

	public final BitOrExpressionContext bitOrExpression() throws RecognitionException {
		BitOrExpressionContext _localctx = new BitOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_bitOrExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			((BitOrExpressionContext)_localctx).lCtx = bitAndExpression();

			    lExpr = ((BitOrExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(368);
				((BitOrExpressionContext)_localctx).bop = match(BITOR);
				setState(369);
				((BitOrExpressionContext)_localctx).rCtx = bitAndExpression();

				    ((BitOrExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.BIT_OP, ((BitOrExpressionContext)_localctx).bop.getText(), lExpr, ((BitOrExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((BitOrExpressionContext)_localctx).expressionMetadata =  ((BitOrExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitAndExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public EqualExpressionContext lCtx;
		public Token bop;
		public EqualExpressionContext rCtx;
		public List<EqualExpressionContext> equalExpression() {
			return getRuleContexts(EqualExpressionContext.class);
		}
		public EqualExpressionContext equalExpression(int i) {
			return getRuleContext(EqualExpressionContext.class,i);
		}
		public BitAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterBitAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitBitAndExpression(this);
		}
	}

	public final BitAndExpressionContext bitAndExpression() throws RecognitionException {
		BitAndExpressionContext _localctx = new BitAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_bitAndExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			((BitAndExpressionContext)_localctx).lCtx = equalExpression();

			    lExpr = ((BitAndExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(385);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(379);
				((BitAndExpressionContext)_localctx).bop = match(BITAND);
				setState(380);
				((BitAndExpressionContext)_localctx).rCtx = equalExpression();

				    ((BitAndExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.BIT_OP, ((BitAndExpressionContext)_localctx).bop.getText(), lExpr, ((BitAndExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((BitAndExpressionContext)_localctx).expressionMetadata =  ((BitAndExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public CompareExpressionContext lCtx;
		public Token bop;
		public CompareExpressionContext rCtx;
		public List<CompareExpressionContext> compareExpression() {
			return getRuleContexts(CompareExpressionContext.class);
		}
		public CompareExpressionContext compareExpression(int i) {
			return getRuleContext(CompareExpressionContext.class,i);
		}
		public EqualExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterEqualExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitEqualExpression(this);
		}
	}

	public final EqualExpressionContext equalExpression() throws RecognitionException {
		EqualExpressionContext _localctx = new EqualExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_equalExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			((EqualExpressionContext)_localctx).lCtx = compareExpression();

			    lExpr = ((EqualExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQUAL || _la==NOTEQUAL) {
				{
				{
				setState(390);
				((EqualExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
					((EqualExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(391);
				((EqualExpressionContext)_localctx).rCtx = compareExpression();

				    ((EqualExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.COMPARE_OP, ((EqualExpressionContext)_localctx).bop.getText(), lExpr, ((EqualExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((EqualExpressionContext)_localctx).expressionMetadata =  ((EqualExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompareExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public ShiftExpressionContext lCtx;
		public Token bop;
		public ShiftExpressionContext rCtx;
		public List<ShiftExpressionContext> shiftExpression() {
			return getRuleContexts(ShiftExpressionContext.class);
		}
		public ShiftExpressionContext shiftExpression(int i) {
			return getRuleContext(ShiftExpressionContext.class,i);
		}
		public CompareExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compareExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterCompareExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitCompareExpression(this);
		}
	}

	public final CompareExpressionContext compareExpression() throws RecognitionException {
		CompareExpressionContext _localctx = new CompareExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_compareExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			((CompareExpressionContext)_localctx).lCtx = shiftExpression();

			    lExpr = ((CompareExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(407);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << LE) | (1L << GE))) != 0)) {
				{
				{
				setState(401);
				((CompareExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << LE) | (1L << GE))) != 0)) ) {
					((CompareExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(402);
				((CompareExpressionContext)_localctx).rCtx = shiftExpression();

				    ((CompareExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.COMPARE_OP, ((CompareExpressionContext)_localctx).bop.getText(), lExpr, ((CompareExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(409);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((CompareExpressionContext)_localctx).expressionMetadata =  ((CompareExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShiftExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public AdditiveExpressionContext lCtx;
		public Token bop;
		public AdditiveExpressionContext rCtx;
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public ShiftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitShiftExpression(this);
		}
	}

	public final ShiftExpressionContext shiftExpression() throws RecognitionException {
		ShiftExpressionContext _localctx = new ShiftExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_shiftExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			((ShiftExpressionContext)_localctx).lCtx = additiveExpression();

			    lExpr = ((ShiftExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(418);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LSHIFT || _la==RSHIFT) {
				{
				{
				setState(412);
				((ShiftExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==LSHIFT || _la==RSHIFT) ) {
					((ShiftExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(413);
				((ShiftExpressionContext)_localctx).rCtx = additiveExpression();

				    ((ShiftExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.SHIFT_OP, ((ShiftExpressionContext)_localctx).bop.getText(), lExpr, ((ShiftExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((ShiftExpressionContext)_localctx).expressionMetadata =  ((ShiftExpressionContext)_localctx).lCtx.expressionMetadata;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public MultiplicativeExpressionContext lCtx;
		public Token bop;
		public MultiplicativeExpressionContext rCtx;
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_additiveExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			((AdditiveExpressionContext)_localctx).lCtx = multiplicativeExpression();

			    lExpr = ((AdditiveExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(423);
				((AdditiveExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((AdditiveExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(424);
				((AdditiveExpressionContext)_localctx).rCtx = multiplicativeExpression();

				    ((AdditiveExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.ARITHMETIC_OP, ((AdditiveExpressionContext)_localctx).bop.getText(), lExpr, ((AdditiveExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(431);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((AdditiveExpressionContext)_localctx).expressionMetadata =  lExpr;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public UnaryExpressionContext lCtx;
		public Token bop;
		public UnaryExpressionContext rCtx;
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_multiplicativeExpression);

		    ExpressionMetadata lExpr;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			((MultiplicativeExpressionContext)_localctx).lCtx = unaryExpression();

			    lExpr = ((MultiplicativeExpressionContext)_localctx).lCtx.expressionMetadata;

			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(434);
				((MultiplicativeExpressionContext)_localctx).bop = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					((MultiplicativeExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(435);
				((MultiplicativeExpressionContext)_localctx).rCtx = unaryExpression();

				    ((MultiplicativeExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.ARITHMETIC_OP, ((MultiplicativeExpressionContext)_localctx).bop.getText(), lExpr, ((MultiplicativeExpressionContext)_localctx).rCtx.expressionMetadata);
				    _localctx.expressionMetadata.setLineNo(lExpr.getLineNo());
				    lExpr = _localctx.expressionMetadata;

				}
				}
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			    if (_localctx.expressionMetadata == null) {
			        ((MultiplicativeExpressionContext)_localctx).expressionMetadata =  lExpr;
			    }

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public Token op;
		public PrimaryContext primaryCtx;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitUnaryExpression(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_unaryExpression);
		int _la;
		try {
			setState(453);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(444);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (BANG - 34)) | (1L << (SWANGDASH - 34)) | (1L << (PLUSPLUS - 34)) | (1L << (MINUSMINUS - 34)))) != 0)) {
					{
					setState(443);
					((UnaryExpressionContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (BANG - 34)) | (1L << (SWANGDASH - 34)) | (1L << (PLUSPLUS - 34)) | (1L << (MINUSMINUS - 34)))) != 0)) ) {
						((UnaryExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(446);
				((UnaryExpressionContext)_localctx).primaryCtx = primary();

				    if (((UnaryExpressionContext)_localctx).op != null) {
				        ExpressionType expressionType;
				        if (((UnaryExpressionContext)_localctx).op.getText().equals("!")) {
				            expressionType = ExpressionType.LOGIC_OP;
				        } else if (((UnaryExpressionContext)_localctx).op.getText().equals("~")) {
				            expressionType = ExpressionType.BIT_OP;
				        } else {
				            expressionType = ExpressionType.ARITHMETIC_OP;
				        }
				        ((UnaryExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(expressionType, ((UnaryExpressionContext)_localctx).op.getText(), ((UnaryExpressionContext)_localctx).primaryCtx.expressionMetadata);
				        _localctx.expressionMetadata.setLineNo(((UnaryExpressionContext)_localctx).op.getLine());
				    } else {
				        ((UnaryExpressionContext)_localctx).expressionMetadata =  ((UnaryExpressionContext)_localctx).primaryCtx.expressionMetadata;
				    }

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(449);
				((UnaryExpressionContext)_localctx).primaryCtx = primary();
				setState(450);
				((UnaryExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLUSPLUS || _la==MINUSMINUS) ) {
					((UnaryExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				    if (((UnaryExpressionContext)_localctx).op != null) {
				        ((UnaryExpressionContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.ARITHMETIC_OP, "a"+((UnaryExpressionContext)_localctx).op.getText(), ((UnaryExpressionContext)_localctx).primaryCtx.expressionMetadata);
				        _localctx.expressionMetadata.setLineNo(((UnaryExpressionContext)_localctx).op.getLine());
				    }

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public MemberContext memberCtx;
		public LiteralContext literalCtx;
		public ParExpressionContext exprCtx;
		public MemberContext member() {
			return getRuleContext(MemberContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_primary);
		try {
			setState(464);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(455);
				((PrimaryContext)_localctx).memberCtx = member();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).memberCtx.expressionMetadata;
				}
				break;
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(458);
				((PrimaryContext)_localctx).literalCtx = literal();
				((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).literalCtx.constantMetadata;
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(461);
				((PrimaryContext)_localctx).exprCtx = parExpression();
				 ((PrimaryContext)_localctx).expressionMetadata =  ((PrimaryContext)_localctx).exprCtx.expressionMetadata;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParExpressionContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public ExpressionContext exprCtx;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitParExpression(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(LPAREN);
			setState(467);
			((ParExpressionContext)_localctx).exprCtx = expression();
			setState(468);
			match(RPAREN);

			((ParExpressionContext)_localctx).expressionMetadata =  ((ParExpressionContext)_localctx).exprCtx.expressionMetadata;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public VarPrimaryContext varCtx;
		public Token bop;
		public ExpressionContext indexCtx;
		public List<VarPrimaryContext> varPrimary() {
			return getRuleContexts(VarPrimaryContext.class);
		}
		public VarPrimaryContext varPrimary(int i) {
			return getRuleContext(VarPrimaryContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitMember(this);
		}
	}

	public final MemberContext member() throws RecognitionException {
		MemberContext _localctx = new MemberContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_member);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			((MemberContext)_localctx).varCtx = varPrimary();
			((MemberContext)_localctx).expressionMetadata =  ((MemberContext)_localctx).varCtx.expressionMetadata;
			setState(480);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(473);
				((MemberContext)_localctx).bop = match(LBRACK);
				setState(474);
				((MemberContext)_localctx).indexCtx = expression();
				((MemberContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.COLLACC_OP, ((MemberContext)_localctx).bop.getText(), _localctx.expressionMetadata, ((MemberContext)_localctx).indexCtx.expressionMetadata); _localctx.expressionMetadata.setLineNo(((MemberContext)_localctx).bop.getLine());
				setState(476);
				match(RBRACK);
				}
				}
				setState(482);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(498);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(483);
				((MemberContext)_localctx).bop = match(DOT);
				setState(484);
				((MemberContext)_localctx).varCtx = varPrimary();
				 ((MemberContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.MEMBER, ((MemberContext)_localctx).bop.getText(), _localctx.expressionMetadata, ((MemberContext)_localctx).varCtx.expressionMetadata); _localctx.expressionMetadata.setLineNo(((MemberContext)_localctx).bop.getLine());
				setState(493);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACK) {
					{
					{
					setState(486);
					((MemberContext)_localctx).bop = match(LBRACK);
					setState(487);
					((MemberContext)_localctx).indexCtx = expression();
					((MemberContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.COLLACC_OP, ((MemberContext)_localctx).bop.getText(), _localctx.expressionMetadata, ((MemberContext)_localctx).indexCtx.expressionMetadata); _localctx.expressionMetadata.setLineNo(((MemberContext)_localctx).bop.getLine());
					setState(489);
					match(RBRACK);
					}
					}
					setState(495);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(500);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberMethodCallContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public VarPrimaryContext varCtx;
		public Token bop;
		public ExpressionContext indexCtx;
		public MethodCallContext methodCtx;
		public VarPrimaryContext varPrimary() {
			return getRuleContext(VarPrimaryContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<MethodCallContext> methodCall() {
			return getRuleContexts(MethodCallContext.class);
		}
		public MethodCallContext methodCall(int i) {
			return getRuleContext(MethodCallContext.class,i);
		}
		public MemberMethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberMethodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterMemberMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitMemberMethodCall(this);
		}
	}

	public final MemberMethodCallContext memberMethodCall() throws RecognitionException {
		MemberMethodCallContext _localctx = new MemberMethodCallContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_memberMethodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
			((MemberMethodCallContext)_localctx).varCtx = varPrimary();
			((MemberMethodCallContext)_localctx).expressionMetadata =  ((MemberMethodCallContext)_localctx).varCtx.expressionMetadata;
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(503);
				((MemberMethodCallContext)_localctx).bop = match(LBRACK);
				setState(504);
				((MemberMethodCallContext)_localctx).indexCtx = expression();
				((MemberMethodCallContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.COLLACC_OP, ((MemberMethodCallContext)_localctx).bop.getText(), _localctx.expressionMetadata, ((MemberMethodCallContext)_localctx).indexCtx.expressionMetadata); _localctx.expressionMetadata.setLineNo(((MemberMethodCallContext)_localctx).bop.getLine());
				setState(506);
				match(RBRACK);
				}
				}
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(517); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(513);
				((MemberMethodCallContext)_localctx).bop = match(DOT);
				setState(514);
				((MemberMethodCallContext)_localctx).methodCtx = methodCall();
				((MemberMethodCallContext)_localctx).expressionMetadata =  new ExpressionMetadata(ExpressionType.PROCESS_METHOD, ((MemberMethodCallContext)_localctx).bop.getText(), _localctx.expressionMetadata, ((MemberMethodCallContext)_localctx).methodCtx.methodMetadata); _localctx.expressionMetadata.setLineNo(((MemberMethodCallContext)_localctx).bop.getLine());
				}
				}
				setState(519); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarPrimaryContext extends ParserRuleContext {
		public ExpressionMetadata expressionMetadata;
		public VariableContext varCtx;
		public MethodCallContext methodCtx;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public VarPrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varPrimary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterVarPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitVarPrimary(this);
		}
	}

	public final VarPrimaryContext varPrimary() throws RecognitionException {
		VarPrimaryContext _localctx = new VarPrimaryContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_varPrimary);
		try {
			setState(527);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(521);
				((VarPrimaryContext)_localctx).varCtx = variable();
				((VarPrimaryContext)_localctx).expressionMetadata =  ((VarPrimaryContext)_localctx).varCtx.variableMetadata;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(524);
				((VarPrimaryContext)_localctx).methodCtx = methodCall();
				((VarPrimaryContext)_localctx).expressionMetadata =  ((VarPrimaryContext)_localctx).methodCtx.methodMetadata;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public VariableMetadata variableMetadata;
		public Token id;
		public TerminalNode IDENTIFIER() { return getToken(BotuParser.IDENTIFIER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529);
			((VariableContext)_localctx).id = match(IDENTIFIER);

			((VariableContext)_localctx).variableMetadata =  new VariableMetadata(((VariableContext)_localctx).id.getText());
			_localctx.variableMetadata.setLineNo(((VariableContext)_localctx).id.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public MethodMetadata methodMetadata;
		public Token t;
		public ExpressionListContext paramCtx;
		public TerminalNode IDENTIFIER() { return getToken(BotuParser.IDENTIFIER, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitMethodCall(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_methodCall);

		    List<ExpressionMetadata> parameters = null;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			((MethodCallContext)_localctx).t = match(IDENTIFIER);
			setState(533);
			match(LPAREN);
			setState(537);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 14)) & ~0x3f) == 0 && ((1L << (_la - 14)) & ((1L << (DECIMAL_LITERAL - 14)) | (1L << (HEX_LITERAL - 14)) | (1L << (OCT_LITERAL - 14)) | (1L << (BINARY_LITERAL - 14)) | (1L << (FLOAT_LITERAL - 14)) | (1L << (HEX_FLOAT_LITERAL - 14)) | (1L << (BOOL_LITERAL - 14)) | (1L << (CHAR_LITERAL - 14)) | (1L << (STRING_LITERAL - 14)) | (1L << (NULL_LITERAL - 14)) | (1L << (LPAREN - 14)) | (1L << (BANG - 14)) | (1L << (SWANGDASH - 14)) | (1L << (PLUSPLUS - 14)) | (1L << (MINUSMINUS - 14)) | (1L << (IDENTIFIER - 14)))) != 0)) {
				{
				setState(534);
				((MethodCallContext)_localctx).paramCtx = expressionList();
				 parameters = ((MethodCallContext)_localctx).paramCtx.expressionMetadatas;
				}
			}

			setState(539);
			match(RPAREN);

			    ((MethodCallContext)_localctx).methodMetadata =  new MethodMetadata(((MethodCallContext)_localctx).t.getText());
			    if (parameters != null)
			        _localctx.methodMetadata.setParameters(parameters);
			    _localctx.methodMetadata.setLineNo(((MethodCallContext)_localctx).t.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionMetadata> expressionMetadatas;
		public ExpressionContext expressionCtx;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expressionList);

		    ((ExpressionListContext)_localctx).expressionMetadatas =  new LinkedList<ExpressionMetadata>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			((ExpressionListContext)_localctx).expressionCtx = expression();

			_localctx.expressionMetadatas.add(((ExpressionListContext)_localctx).expressionCtx.expressionMetadata);

			setState(550);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(544);
				match(COMMA);
				setState(545);
				((ExpressionListContext)_localctx).expressionCtx = expression();

				_localctx.expressionMetadatas.add(((ExpressionListContext)_localctx).expressionCtx.expressionMetadata);

				}
				}
				setState(552);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public ConstantMetadata constantMetadata;
		public IntegerLiteralContext ti;
		public FloatLiteralContext tf;
		public Token tc;
		public Token ts;
		public Token tb;
		public Token tn;
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class,0);
		}
		public TerminalNode CHAR_LITERAL() { return getToken(BotuParser.CHAR_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(BotuParser.STRING_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(BotuParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(BotuParser.NULL_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_literal);

		    String text = null;
		    ExpressionType type = ExpressionType.INTEGER;

		try {
			setState(567);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(553);
				((LiteralContext)_localctx).ti = integerLiteral();

				text = ((LiteralContext)_localctx).ti.token.getText();
				if (text.endsWith("l") || text.endsWith("L")) {
				    type = ExpressionType.LONG;
				}
				((LiteralContext)_localctx).constantMetadata =  new ConstantMetadata(type, text);
				_localctx.constantMetadata.setLineNo(((LiteralContext)_localctx).ti.token.getLine());

				}
				break;
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(556);
				((LiteralContext)_localctx).tf = floatLiteral();

				text = ((LiteralContext)_localctx).tf.token.getText();
				if (text.endsWith("d") || text.endsWith("D")) {
				    type = ExpressionType.DOUBLE;
				}
				((LiteralContext)_localctx).constantMetadata =  new ConstantMetadata(type, text);
				_localctx.constantMetadata.setLineNo(((LiteralContext)_localctx).tf.token.getLine());

				}
				break;
			case CHAR_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(559);
				((LiteralContext)_localctx).tc = match(CHAR_LITERAL);

				((LiteralContext)_localctx).constantMetadata =  new ConstantMetadata(ExpressionType.CHAR, ((LiteralContext)_localctx).tc.getText());
				_localctx.constantMetadata.setLineNo(((LiteralContext)_localctx).tc.getLine());

				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(561);
				((LiteralContext)_localctx).ts = match(STRING_LITERAL);

				((LiteralContext)_localctx).constantMetadata =  new ConstantMetadata(ExpressionType.STRING, ((LiteralContext)_localctx).ts.getText());
				_localctx.constantMetadata.setLineNo(((LiteralContext)_localctx).ts.getLine());

				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(563);
				((LiteralContext)_localctx).tb = match(BOOL_LITERAL);

				((LiteralContext)_localctx).constantMetadata =  new ConstantMetadata(ExpressionType.BOOLEAN, ((LiteralContext)_localctx).tb.getText());
				_localctx.constantMetadata.setLineNo(((LiteralContext)_localctx).tb.getLine());

				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(565);
				((LiteralContext)_localctx).tn = match(NULL_LITERAL);

				((LiteralContext)_localctx).constantMetadata =  new ConstantMetadata(ExpressionType.NULL, ((LiteralContext)_localctx).tn.getText());
				_localctx.constantMetadata.setLineNo(((LiteralContext)_localctx).tn.getLine());

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public Token token;
		public Token t;
		public TerminalNode DECIMAL_LITERAL() { return getToken(BotuParser.DECIMAL_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(BotuParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(BotuParser.OCT_LITERAL, 0); }
		public TerminalNode BINARY_LITERAL() { return getToken(BotuParser.BINARY_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitIntegerLiteral(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_integerLiteral);
		try {
			setState(577);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(569);
				((IntegerLiteralContext)_localctx).t = match(DECIMAL_LITERAL);
				((IntegerLiteralContext)_localctx).token =  ((IntegerLiteralContext)_localctx).t;
				}
				break;
			case HEX_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(571);
				((IntegerLiteralContext)_localctx).t = match(HEX_LITERAL);
				((IntegerLiteralContext)_localctx).token =  ((IntegerLiteralContext)_localctx).t;
				}
				break;
			case OCT_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(573);
				((IntegerLiteralContext)_localctx).t = match(OCT_LITERAL);
				((IntegerLiteralContext)_localctx).token =  ((IntegerLiteralContext)_localctx).t;
				}
				break;
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(575);
				((IntegerLiteralContext)_localctx).t = match(BINARY_LITERAL);
				((IntegerLiteralContext)_localctx).token =  ((IntegerLiteralContext)_localctx).t;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatLiteralContext extends ParserRuleContext {
		public Token token;
		public Token t;
		public TerminalNode FLOAT_LITERAL() { return getToken(BotuParser.FLOAT_LITERAL, 0); }
		public TerminalNode HEX_FLOAT_LITERAL() { return getToken(BotuParser.HEX_FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BotuParserListener ) ((BotuParserListener)listener).exitFloatLiteral(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_floatLiteral);
		try {
			setState(583);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(579);
				((FloatLiteralContext)_localctx).t = match(FLOAT_LITERAL);
				((FloatLiteralContext)_localctx).token =  ((FloatLiteralContext)_localctx).t;
				}
				break;
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(581);
				((FloatLiteralContext)_localctx).t = match(HEX_FLOAT_LITERAL);
				((FloatLiteralContext)_localctx).token =  ((FloatLiteralContext)_localctx).t;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3H\u024c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3b\n\3\f\3\16\3e\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\5\4\u0090\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a6\n\b\3\t\3\t\3\t"+
		"\5\t\u00ab\n\t\3\t\3\t\5\t\u00af\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b8"+
		"\n\t\f\t\16\t\u00bb\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7"+
		"\n\u00c8\n\n\f\n\16\n\u00cb\13\n\3\n\3\n\3\n\5\n\u00d0\n\n\3\n\3\n\3\13"+
		"\3\13\3\13\6\13\u00d7\n\13\r\13\16\13\u00d8\3\13\3\13\3\13\7\13\u00de"+
		"\n\13\f\13\16\13\u00e1\13\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7"+
		"\r\u00ed\n\r\f\r\16\r\u00f0\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0101\n\17\3\17\3\17\3\17"+
		"\3\17\5\17\u0107\n\17\3\17\3\17\3\17\3\17\5\17\u010d\n\17\3\17\5\17\u0110"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0118\n\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u012c\n\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0134\n\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\7\24\u013d\n\24\f\24\16\24\u0140\13\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u014e"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0156\n\26\f\26\16\26\u0159\13"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0161\n\27\f\27\16\27\u0164\13"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u016c\n\30\f\30\16\30\u016f\13"+
		"\30\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0177\n\31\f\31\16\31\u017a\13"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u0182\n\32\f\32\16\32\u0185\13"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u018d\n\33\f\33\16\33\u0190\13"+
		"\33\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0198\n\34\f\34\16\34\u019b\13"+
		"\34\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u01a3\n\35\f\35\16\35\u01a6\13"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u01ae\n\36\f\36\16\36\u01b1\13"+
		"\36\3\37\3\37\3\37\3\37\3\37\3\37\7\37\u01b9\n\37\f\37\16\37\u01bc\13"+
		"\37\3 \5 \u01bf\n \3 \3 \3 \3 \3 \3 \3 \5 \u01c8\n \3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\5!\u01d3\n!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\7#\u01e1"+
		"\n#\f#\16#\u01e4\13#\3#\3#\3#\3#\3#\3#\3#\3#\7#\u01ee\n#\f#\16#\u01f1"+
		"\13#\7#\u01f3\n#\f#\16#\u01f6\13#\3$\3$\3$\3$\3$\3$\3$\7$\u01ff\n$\f$"+
		"\16$\u0202\13$\3$\3$\3$\3$\6$\u0208\n$\r$\16$\u0209\3%\3%\3%\3%\3%\3%"+
		"\5%\u0212\n%\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\5\'\u021c\n\'\3\'\3\'\3\'\3"+
		"(\3(\3(\3(\3(\3(\7(\u0227\n(\f(\16(\u022a\13(\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\3)\3)\3)\3)\3)\5)\u023a\n)\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0244\n*\3+"+
		"\3+\3+\3+\5+\u024a\n+\3+\2\2,\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 "+
		"\"$&(*,.\60\62\64\668:<>@BDFHJLNPRT\2\13\3\2\b\t\3\29B\4\2((++\4\2&\'"+
		")*\3\2\678\3\2./\4\2\60\61\66\66\5\2$$\65\65CD\3\2CD\2\u025f\2V\3\2\2"+
		"\2\4]\3\2\2\2\6\u008f\3\2\2\2\b\u0091\3\2\2\2\n\u0095\3\2\2\2\f\u0099"+
		"\3\2\2\2\16\u009d\3\2\2\2\20\u00a7\3\2\2\2\22\u00be\3\2\2\2\24\u00d6\3"+
		"\2\2\2\26\u00e2\3\2\2\2\30\u00e7\3\2\2\2\32\u00f1\3\2\2\2\34\u010f\3\2"+
		"\2\2\36\u0117\3\2\2\2 \u0119\3\2\2\2\"\u012b\3\2\2\2$\u0133\3\2\2\2&\u0135"+
		"\3\2\2\2(\u014d\3\2\2\2*\u014f\3\2\2\2,\u015a\3\2\2\2.\u0165\3\2\2\2\60"+
		"\u0170\3\2\2\2\62\u017b\3\2\2\2\64\u0186\3\2\2\2\66\u0191\3\2\2\28\u019c"+
		"\3\2\2\2:\u01a7\3\2\2\2<\u01b2\3\2\2\2>\u01c7\3\2\2\2@\u01d2\3\2\2\2B"+
		"\u01d4\3\2\2\2D\u01d9\3\2\2\2F\u01f7\3\2\2\2H\u0211\3\2\2\2J\u0213\3\2"+
		"\2\2L\u0216\3\2\2\2N\u0220\3\2\2\2P\u0239\3\2\2\2R\u0243\3\2\2\2T\u0249"+
		"\3\2\2\2VW\7\4\2\2WX\7\32\2\2XY\7\30\2\2YZ\7\33\2\2Z[\5\4\3\2[\\\7\2\2"+
		"\3\\\3\3\2\2\2]c\7\34\2\2^_\5\6\4\2_`\b\3\1\2`b\3\2\2\2a^\3\2\2\2be\3"+
		"\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\35\2\2g\5\3\2\2\2h"+
		"i\5\4\3\2ij\b\4\1\2j\u0090\3\2\2\2kl\5\b\5\2lm\b\4\1\2m\u0090\3\2\2\2"+
		"no\5\n\6\2op\b\4\1\2p\u0090\3\2\2\2qr\5\f\7\2rs\b\4\1\2s\u0090\3\2\2\2"+
		"tu\5\16\b\2uv\b\4\1\2v\u0090\3\2\2\2wx\5\20\t\2xy\b\4\1\2y\u0090\3\2\2"+
		"\2z{\5\22\n\2{|\b\4\1\2|\u0090\3\2\2\2}~\5\32\16\2~\177\b\4\1\2\177\u0090"+
		"\3\2\2\2\u0080\u0081\5 \21\2\u0081\u0082\b\4\1\2\u0082\u0090\3\2\2\2\u0083"+
		"\u0084\5\"\22\2\u0084\u0085\7 \2\2\u0085\u0086\b\4\1\2\u0086\u0090\3\2"+
		"\2\2\u0087\u0088\5L\'\2\u0088\u0089\7 \2\2\u0089\u008a\b\4\1\2\u008a\u0090"+
		"\3\2\2\2\u008b\u008c\5F$\2\u008c\u008d\7 \2\2\u008d\u008e\b\4\1\2\u008e"+
		"\u0090\3\2\2\2\u008fh\3\2\2\2\u008fk\3\2\2\2\u008fn\3\2\2\2\u008fq\3\2"+
		"\2\2\u008ft\3\2\2\2\u008fw\3\2\2\2\u008fz\3\2\2\2\u008f}\3\2\2\2\u008f"+
		"\u0080\3\2\2\2\u008f\u0083\3\2\2\2\u008f\u0087\3\2\2\2\u008f\u008b\3\2"+
		"\2\2\u0090\7\3\2\2\2\u0091\u0092\7\3\2\2\u0092\u0093\7 \2\2\u0093\u0094"+
		"\b\5\1\2\u0094\t\3\2\2\2\u0095\u0096\7\17\2\2\u0096\u0097\7 \2\2\u0097"+
		"\u0098\b\6\1\2\u0098\13\3\2\2\2\u0099\u009a\7\16\2\2\u009a\u009b\7 \2"+
		"\2\u009b\u009c\b\7\1\2\u009c\r\3\2\2\2\u009d\u009e\t\2\2\2\u009e\u009f"+
		"\5B\"\2\u009f\u00a0\5\6\4\2\u00a0\u00a5\b\b\1\2\u00a1\u00a2\7\7\2\2\u00a2"+
		"\u00a3\5\6\4\2\u00a3\u00a4\b\b\1\2\u00a4\u00a6\3\2\2\2\u00a5\u00a1\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\17\3\2\2\2\u00a7\u00a8\7\f\2\2\u00a8\u00aa"+
		"\7\32\2\2\u00a9\u00ab\7\30\2\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2"+
		"\u00ab\u00ae\3\2\2\2\u00ac\u00ad\7!\2\2\u00ad\u00af\7\26\2\2\u00ae\u00ac"+
		"\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\33\2\2"+
		"\u00b1\u00b2\b\t\1\2\u00b2\u00b9\7\34\2\2\u00b3\u00b4\5\"\22\2\u00b4\u00b5"+
		"\b\t\1\2\u00b5\u00b6\7 \2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b8"+
		"\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2"+
		"\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\7\35\2\2\u00bd\21\3\2\2\2\u00be\u00bf"+
		"\7\r\2\2\u00bf\u00c0\7\32\2\2\u00c0\u00c1\5*\26\2\u00c1\u00c2\7\33\2\2"+
		"\u00c2\u00c3\b\n\1\2\u00c3\u00c9\7\34\2\2\u00c4\u00c5\5\24\13\2\u00c5"+
		"\u00c6\b\n\1\2\u00c6\u00c8\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c8\u00cb\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cf\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\5\30\r\2\u00cd\u00ce\b\n\1\2\u00ce\u00d0\3"+
		"\2\2\2\u00cf\u00cc\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\7\35\2\2\u00d2\23\3\2\2\2\u00d3\u00d4\5\26\f\2\u00d4\u00d5\b\13"+
		"\1\2\u00d5\u00d7\3\2\2\2\u00d6\u00d3\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00df\3\2\2\2\u00da\u00db\5\6"+
		"\4\2\u00db\u00dc\b\13\1\2\u00dc\u00de\3\2\2\2\u00dd\u00da\3\2\2\2\u00de"+
		"\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\25\3\2\2"+
		"\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7\5\2\2\u00e3\u00e4\5P)\2\u00e4\u00e5"+
		"\7%\2\2\u00e5\u00e6\b\f\1\2\u00e6\27\3\2\2\2\u00e7\u00e8\7\6\2\2\u00e8"+
		"\u00ee\7%\2\2\u00e9\u00ea\5\6\4\2\u00ea\u00eb\b\r\1\2\u00eb\u00ed\3\2"+
		"\2\2\u00ec\u00e9\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\31\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\7\n\2"+
		"\2\u00f2\u00f3\7\32\2\2\u00f3\u00f4\5\34\17\2\u00f4\u00f5\7\33\2\2\u00f5"+
		"\u00f6\5\6\4\2\u00f6\u00f7\b\16\1\2\u00f7\33\3\2\2\2\u00f8\u00f9\7H\2"+
		"\2\u00f9\u00fa\7%\2\2\u00fa\u00fb\5*\26\2\u00fb\u00fc\b\17\1\2\u00fc\u0110"+
		"\3\2\2\2\u00fd\u00fe\5\36\20\2\u00fe\u00ff\b\17\1\2\u00ff\u0101\3\2\2"+
		"\2\u0100\u00fd\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0106"+
		"\7 \2\2\u0103\u0104\5*\26\2\u0104\u0105\b\17\1\2\u0105\u0107\3\2\2\2\u0106"+
		"\u0103\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010c\7 "+
		"\2\2\u0109\u010a\5N(\2\u010a\u010b\b\17\1\2\u010b\u010d\3\2\2\2\u010c"+
		"\u0109\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0110\b\17"+
		"\1\2\u010f\u00f8\3\2\2\2\u010f\u0100\3\2\2\2\u0110\35\3\2\2\2\u0111\u0112"+
		"\5\"\22\2\u0112\u0113\b\20\1\2\u0113\u0118\3\2\2\2\u0114\u0115\5N(\2\u0115"+
		"\u0116\b\20\1\2\u0116\u0118\3\2\2\2\u0117\u0111\3\2\2\2\u0117\u0114\3"+
		"\2\2\2\u0118\37\3\2\2\2\u0119\u011a\7\13\2\2\u011a\u011b\5B\"\2\u011b"+
		"\u011c\5\6\4\2\u011c\u011d\b\21\1\2\u011d!\3\2\2\2\u011e\u011f\5J&\2\u011f"+
		"\u0120\7#\2\2\u0120\u0121\5$\23\2\u0121\u0122\b\22\1\2\u0122\u012c\3\2"+
		"\2\2\u0123\u0124\5D#\2\u0124\u0125\7#\2\2\u0125\u0126\5$\23\2\u0126\u0127"+
		"\b\22\1\2\u0127\u012c\3\2\2\2\u0128\u0129\5(\25\2\u0129\u012a\b\22\1\2"+
		"\u012a\u012c\3\2\2\2\u012b\u011e\3\2\2\2\u012b\u0123\3\2\2\2\u012b\u0128"+
		"\3\2\2\2\u012c#\3\2\2\2\u012d\u012e\5&\24\2\u012e\u012f\b\23\1\2\u012f"+
		"\u0134\3\2\2\2\u0130\u0131\5*\26\2\u0131\u0132\b\23\1\2\u0132\u0134\3"+
		"\2\2\2\u0133\u012d\3\2\2\2\u0133\u0130\3\2\2\2\u0134%\3\2\2\2\u0135\u0136"+
		"\7\34\2\2\u0136\u0137\5$\23\2\u0137\u013e\b\24\1\2\u0138\u0139\7!\2\2"+
		"\u0139\u013a\5$\23\2\u013a\u013b\b\24\1\2\u013b\u013d\3\2\2\2\u013c\u0138"+
		"\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f"+
		"\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u0142\7\35\2\2\u0142\'\3\2\2"+
		"\2\u0143\u0144\5J&\2\u0144\u0145\t\3\2\2\u0145\u0146\5*\26\2\u0146\u0147"+
		"\b\25\1\2\u0147\u014e\3\2\2\2\u0148\u0149\5D#\2\u0149\u014a\t\3\2\2\u014a"+
		"\u014b\5*\26\2\u014b\u014c\b\25\1\2\u014c\u014e\3\2\2\2\u014d\u0143\3"+
		"\2\2\2\u014d\u0148\3\2\2\2\u014e)\3\2\2\2\u014f\u0150\5,\27\2\u0150\u0157"+
		"\b\26\1\2\u0151\u0152\7-\2\2\u0152\u0153\5,\27\2\u0153\u0154\b\26\1\2"+
		"\u0154\u0156\3\2\2\2\u0155\u0151\3\2\2\2\u0156\u0159\3\2\2\2\u0157\u0155"+
		"\3\2\2\2\u0157\u0158\3\2\2\2\u0158+\3\2\2\2\u0159\u0157\3\2\2\2\u015a"+
		"\u015b\5.\30\2\u015b\u0162\b\27\1\2\u015c\u015d\7,\2\2\u015d\u015e\5."+
		"\30\2\u015e\u015f\b\27\1\2\u015f\u0161\3\2\2\2\u0160\u015c\3\2\2\2\u0161"+
		"\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163-\3\2\2\2"+
		"\u0164\u0162\3\2\2\2\u0165\u0166\5\60\31\2\u0166\u016d\b\30\1\2\u0167"+
		"\u0168\7\64\2\2\u0168\u0169\5\60\31\2\u0169\u016a\b\30\1\2\u016a\u016c"+
		"\3\2\2\2\u016b\u0167\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d"+
		"\u016e\3\2\2\2\u016e/\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0171\5\62\32"+
		"\2\u0171\u0178\b\31\1\2\u0172\u0173\7\63\2\2\u0173\u0174\5\62\32\2\u0174"+
		"\u0175\b\31\1\2\u0175\u0177\3\2\2\2\u0176\u0172\3\2\2\2\u0177\u017a\3"+
		"\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\61\3\2\2\2\u017a"+
		"\u0178\3\2\2\2\u017b\u017c\5\64\33\2\u017c\u0183\b\32\1\2\u017d\u017e"+
		"\7\62\2\2\u017e\u017f\5\64\33\2\u017f\u0180\b\32\1\2\u0180\u0182\3\2\2"+
		"\2\u0181\u017d\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0184"+
		"\3\2\2\2\u0184\63\3\2\2\2\u0185\u0183\3\2\2\2\u0186\u0187\5\66\34\2\u0187"+
		"\u018e\b\33\1\2\u0188\u0189\t\4\2\2\u0189\u018a\5\66\34\2\u018a\u018b"+
		"\b\33\1\2\u018b\u018d\3\2\2\2\u018c\u0188\3\2\2\2\u018d\u0190\3\2\2\2"+
		"\u018e\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f\65\3\2\2\2\u0190\u018e"+
		"\3\2\2\2\u0191\u0192\58\35\2\u0192\u0199\b\34\1\2\u0193\u0194\t\5\2\2"+
		"\u0194\u0195\58\35\2\u0195\u0196\b\34\1\2\u0196\u0198\3\2\2\2\u0197\u0193"+
		"\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a\3\2\2\2\u019a"+
		"\67\3\2\2\2\u019b\u0199\3\2\2\2\u019c\u019d\5:\36\2\u019d\u01a4\b\35\1"+
		"\2\u019e\u019f\t\6\2\2\u019f\u01a0\5:\36\2\u01a0\u01a1\b\35\1\2\u01a1"+
		"\u01a3\3\2\2\2\u01a2\u019e\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2"+
		"\2\2\u01a4\u01a5\3\2\2\2\u01a59\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01a8"+
		"\5<\37\2\u01a8\u01af\b\36\1\2\u01a9\u01aa\t\7\2\2\u01aa\u01ab\5<\37\2"+
		"\u01ab\u01ac\b\36\1\2\u01ac\u01ae\3\2\2\2\u01ad\u01a9\3\2\2\2\u01ae\u01b1"+
		"\3\2\2\2\u01af\u01ad\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0;\3\2\2\2\u01b1"+
		"\u01af\3\2\2\2\u01b2\u01b3\5> \2\u01b3\u01ba\b\37\1\2\u01b4\u01b5\t\b"+
		"\2\2\u01b5\u01b6\5> \2\u01b6\u01b7\b\37\1\2\u01b7\u01b9\3\2\2\2\u01b8"+
		"\u01b4\3\2\2\2\u01b9\u01bc\3\2\2\2\u01ba\u01b8\3\2\2\2\u01ba\u01bb\3\2"+
		"\2\2\u01bb=\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bd\u01bf\t\t\2\2\u01be\u01bd"+
		"\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\5@!\2\u01c1"+
		"\u01c2\b \1\2\u01c2\u01c8\3\2\2\2\u01c3\u01c4\5@!\2\u01c4\u01c5\t\n\2"+
		"\2\u01c5\u01c6\b \1\2\u01c6\u01c8\3\2\2\2\u01c7\u01be\3\2\2\2\u01c7\u01c3"+
		"\3\2\2\2\u01c8?\3\2\2\2\u01c9\u01ca\5D#\2\u01ca\u01cb\b!\1\2\u01cb\u01d3"+
		"\3\2\2\2\u01cc\u01cd\5P)\2\u01cd\u01ce\b!\1\2\u01ce\u01d3\3\2\2\2\u01cf"+
		"\u01d0\5B\"\2\u01d0\u01d1\b!\1\2\u01d1\u01d3\3\2\2\2\u01d2\u01c9\3\2\2"+
		"\2\u01d2\u01cc\3\2\2\2\u01d2\u01cf\3\2\2\2\u01d3A\3\2\2\2\u01d4\u01d5"+
		"\7\32\2\2\u01d5\u01d6\5*\26\2\u01d6\u01d7\7\33\2\2\u01d7\u01d8\b\"\1\2"+
		"\u01d8C\3\2\2\2\u01d9\u01da\5H%\2\u01da\u01e2\b#\1\2\u01db\u01dc\7\36"+
		"\2\2\u01dc\u01dd\5*\26\2\u01dd\u01de\b#\1\2\u01de\u01df\7\37\2\2\u01df"+
		"\u01e1\3\2\2\2\u01e0\u01db\3\2\2\2\u01e1\u01e4\3\2\2\2\u01e2\u01e0\3\2"+
		"\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01f4\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e5"+
		"\u01e6\7\"\2\2\u01e6\u01e7\5H%\2\u01e7\u01ef\b#\1\2\u01e8\u01e9\7\36\2"+
		"\2\u01e9\u01ea\5*\26\2\u01ea\u01eb\b#\1\2\u01eb\u01ec\7\37\2\2\u01ec\u01ee"+
		"\3\2\2\2\u01ed\u01e8\3\2\2\2\u01ee\u01f1\3\2\2\2\u01ef\u01ed\3\2\2\2\u01ef"+
		"\u01f0\3\2\2\2\u01f0\u01f3\3\2\2\2\u01f1\u01ef\3\2\2\2\u01f2\u01e5\3\2"+
		"\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5"+
		"E\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f7\u01f8\5H%\2\u01f8\u0200\b$\1\2\u01f9"+
		"\u01fa\7\36\2\2\u01fa\u01fb\5*\26\2\u01fb\u01fc\b$\1\2\u01fc\u01fd\7\37"+
		"\2\2\u01fd\u01ff\3\2\2\2\u01fe\u01f9\3\2\2\2\u01ff\u0202\3\2\2\2\u0200"+
		"\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201\u0207\3\2\2\2\u0202\u0200\3\2"+
		"\2\2\u0203\u0204\7\"\2\2\u0204\u0205\5L\'\2\u0205\u0206\b$\1\2\u0206\u0208"+
		"\3\2\2\2\u0207\u0203\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u0207\3\2\2\2\u0209"+
		"\u020a\3\2\2\2\u020aG\3\2\2\2\u020b\u020c\5J&\2\u020c\u020d\b%\1\2\u020d"+
		"\u0212\3\2\2\2\u020e\u020f\5L\'\2\u020f\u0210\b%\1\2\u0210\u0212\3\2\2"+
		"\2\u0211\u020b\3\2\2\2\u0211\u020e\3\2\2\2\u0212I\3\2\2\2\u0213\u0214"+
		"\7H\2\2\u0214\u0215\b&\1\2\u0215K\3\2\2\2\u0216\u0217\7H\2\2\u0217\u021b"+
		"\7\32\2\2\u0218\u0219\5N(\2\u0219\u021a\b\'\1\2\u021a\u021c\3\2\2\2\u021b"+
		"\u0218\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021e\7\33"+
		"\2\2\u021e\u021f\b\'\1\2\u021fM\3\2\2\2\u0220\u0221\5*\26\2\u0221\u0228"+
		"\b(\1\2\u0222\u0223\7!\2\2\u0223\u0224\5*\26\2\u0224\u0225\b(\1\2\u0225"+
		"\u0227\3\2\2\2\u0226\u0222\3\2\2\2\u0227\u022a\3\2\2\2\u0228\u0226\3\2"+
		"\2\2\u0228\u0229\3\2\2\2\u0229O\3\2\2\2\u022a\u0228\3\2\2\2\u022b\u022c"+
		"\5R*\2\u022c\u022d\b)\1\2\u022d\u023a\3\2\2\2\u022e\u022f\5T+\2\u022f"+
		"\u0230\b)\1\2\u0230\u023a\3\2\2\2\u0231\u0232\7\27\2\2\u0232\u023a\b)"+
		"\1\2\u0233\u0234\7\30\2\2\u0234\u023a\b)\1\2\u0235\u0236\7\26\2\2\u0236"+
		"\u023a\b)\1\2\u0237\u0238\7\31\2\2\u0238\u023a\b)\1\2\u0239\u022b\3\2"+
		"\2\2\u0239\u022e\3\2\2\2\u0239\u0231\3\2\2\2\u0239\u0233\3\2\2\2\u0239"+
		"\u0235\3\2\2\2\u0239\u0237\3\2\2\2\u023aQ\3\2\2\2\u023b\u023c\7\20\2\2"+
		"\u023c\u0244\b*\1\2\u023d\u023e\7\21\2\2\u023e\u0244\b*\1\2\u023f\u0240"+
		"\7\22\2\2\u0240\u0244\b*\1\2\u0241\u0242\7\23\2\2\u0242\u0244\b*\1\2\u0243"+
		"\u023b\3\2\2\2\u0243\u023d\3\2\2\2\u0243\u023f\3\2\2\2\u0243\u0241\3\2"+
		"\2\2\u0244S\3\2\2\2\u0245\u0246\7\24\2\2\u0246\u024a\b+\1\2\u0247\u0248"+
		"\7\25\2\2\u0248\u024a\b+\1\2\u0249\u0245\3\2\2\2\u0249\u0247\3\2\2\2\u024a"+
		"U\3\2\2\2.c\u008f\u00a5\u00aa\u00ae\u00b9\u00c9\u00cf\u00d8\u00df\u00ee"+
		"\u0100\u0106\u010c\u010f\u0117\u012b\u0133\u013e\u014d\u0157\u0162\u016d"+
		"\u0178\u0183\u018e\u0199\u01a4\u01af\u01ba\u01be\u01c7\u01d2\u01e2\u01ef"+
		"\u01f4\u0200\u0209\u0211\u021b\u0228\u0239\u0243\u0249";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}