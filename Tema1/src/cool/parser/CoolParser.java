// Generated from /home/student/CPL_2022/Tema1/src/cool/CoolParser.g4 by ANTLR 4.10.1

    package cool.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, IF=2, THEN=3, ELSE=4, FI=5, BOOL=6, TYPE=7, CLASS=8, INHERITS=9, 
		NOT=10, ISVOID=11, NEW=12, ID=13, INT=14, FLOAT=15, STRING=16, SEMI=17, 
		COMMA=18, ASSIGN=19, LPAREN=20, RPAREN=21, LBRACE=22, RBRACE=23, PLUS=24, 
		MINUS=25, MULT=26, DIV=27, EQUAL=28, LT=29, LE=30, COLON=31, ASSIGN2=32, 
		NEG=33, DISPATCH=34, AT=35, LINE_COMMENT=36, BLOCK_COMMENT=37, WS=38;
	public static final int
		RULE_program = 0, RULE_definition = 1, RULE_classDef = 2, RULE_instruction = 3, 
		RULE_formal = 4, RULE_expr = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definition", "classDef", "instruction", "formal", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'then'", "'else'", "'fi'", null, null, "'class'", 
			"'inherits'", "'not'", "'isvoid'", "'new'", null, null, null, null, "';'", 
			"','", "'='", "'('", "')'", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", 
			"'=='", "'<'", "'<='", "':'", "'<-'", "'~'", "'.'", "'@'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "BOOL", "TYPE", "CLASS", "INHERITS", 
			"NOT", "ISVOID", "NEW", "ID", "INT", "FLOAT", "STRING", "SEMI", "COMMA", 
			"ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "MULT", 
			"DIV", "EQUAL", "LT", "LE", "COLON", "ASSIGN2", "NEG", "DISPATCH", "AT", 
			"LINE_COMMENT", "BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public ClassDefContext classDef;
		public List<ClassDefContext> class_definitions = new ArrayList<ClassDefContext>();
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(12);
				((ProgramContext)_localctx).classDef = classDef();
				((ProgramContext)_localctx).class_definitions.add(((ProgramContext)_localctx).classDef);
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(18);
			match(EOF);
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

	public static class DefinitionContext extends ParserRuleContext {
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
	 
		public DefinitionContext() { }
		public void copyFrom(DefinitionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MemberDefContext extends DefinitionContext {
		public Token name;
		public Token type;
		public ExprContext init;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode SEMI() { return getToken(CoolParser.SEMI, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode ASSIGN2() { return getToken(CoolParser.ASSIGN2, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MemberDefContext(DefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMemberDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMemberDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMemberDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodDefContext extends DefinitionContext {
		public Token name;
		public FormalContext formal;
		public List<FormalContext> params = new ArrayList<FormalContext>();
		public Token returnType;
		public ExprContext expr;
		public List<ExprContext> body = new ArrayList<ExprContext>();
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode SEMI() { return getToken(CoolParser.SEMI, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public MethodDefContext(DefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMethodDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMethodDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMethodDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definition);
		int _la;
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new MemberDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				((MemberDefContext)_localctx).name = match(ID);
				setState(21);
				match(COLON);
				setState(22);
				((MemberDefContext)_localctx).type = match(TYPE);
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN2) {
					{
					setState(23);
					match(ASSIGN2);
					setState(24);
					((MemberDefContext)_localctx).init = expr(0);
					}
				}

				setState(27);
				match(SEMI);
				}
				break;
			case 2:
				_localctx = new MethodDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				((MethodDefContext)_localctx).name = match(ID);
				setState(29);
				match(LPAREN);
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(30);
					((MethodDefContext)_localctx).formal = formal();
					((MethodDefContext)_localctx).params.add(((MethodDefContext)_localctx).formal);
					setState(35);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(31);
						match(COMMA);
						setState(32);
						((MethodDefContext)_localctx).formal = formal();
						((MethodDefContext)_localctx).params.add(((MethodDefContext)_localctx).formal);
						}
						}
						setState(37);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(40);
				match(RPAREN);
				setState(41);
				match(COLON);
				setState(42);
				((MethodDefContext)_localctx).returnType = match(TYPE);
				setState(43);
				match(LBRACE);
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << BOOL) | (1L << NOT) | (1L << ISVOID) | (1L << NEW) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << LPAREN) | (1L << MINUS) | (1L << NEG))) != 0)) {
					{
					{
					setState(44);
					((MethodDefContext)_localctx).expr = expr(0);
					((MethodDefContext)_localctx).body.add(((MethodDefContext)_localctx).expr);
					}
					}
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(50);
				match(RBRACE);
				setState(51);
				match(SEMI);
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

	public static class ClassDefContext extends ParserRuleContext {
		public Token name;
		public Token name2;
		public InstructionContext instruction;
		public List<InstructionContext> content = new ArrayList<InstructionContext>();
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode SEMI() { return getToken(CoolParser.SEMI, 0); }
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(CLASS);
			setState(55);
			((ClassDefContext)_localctx).name = match(ID);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(56);
				match(INHERITS);
				setState(57);
				((ClassDefContext)_localctx).name2 = match(ID);
				}
			}

			setState(60);
			match(LBRACE);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << BOOL) | (1L << NOT) | (1L << ISVOID) | (1L << NEW) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << LPAREN) | (1L << MINUS) | (1L << NEG))) != 0)) {
				{
				{
				setState(61);
				((ClassDefContext)_localctx).instruction = instruction();
				((ClassDefContext)_localctx).content.add(((ClassDefContext)_localctx).instruction);
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(RBRACE);
			setState(68);
			match(SEMI);
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

	public static class InstructionContext extends ParserRuleContext {
		public DefinitionContext definition() {
			return getRuleContext(DefinitionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(70);
				definition();
				}
				break;
			case 2:
				{
				setState(71);
				expr(0);
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

	public static class FormalContext extends ParserRuleContext {
		public Token name;
		public Token type;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			((FormalContext)_localctx).name = match(ID);
			setState(75);
			match(COLON);
			setState(76);
			((FormalContext)_localctx).type = match(TYPE);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewContext extends ExprContext {
		public ExprContext e;
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NewContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNew(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusMinusContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public PlusMinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterPlusMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitPlusMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitPlusMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolContext extends ExprContext {
		public TerminalNode BOOL() { return getToken(CoolParser.BOOL, 0); }
		public BoolContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsvoidContext extends ExprContext {
		public ExprContext e;
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsvoidContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIsvoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIsvoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIsvoid(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatContext extends ExprContext {
		public TerminalNode FLOAT() { return getToken(CoolParser.FLOAT, 0); }
		public FloatContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImplicitDispatchContext extends ExprContext {
		public Token name;
		public ExprContext expr;
		public List<ExprContext> args = new ArrayList<ExprContext>();
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public ImplicitDispatchContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterImplicitDispatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitImplicitDispatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitImplicitDispatch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(CoolParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegContext extends ExprContext {
		public Token op;
		public ExprContext e;
		public TerminalNode NEG() { return getToken(CoolParser.NEG, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNeg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNeg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNeg(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenContext extends ExprContext {
		public ExprContext e;
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends ExprContext {
		public Token op;
		public ExprContext e;
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultDivContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(CoolParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public MultDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMultDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMultDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMultDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryMinusContext extends ExprContext {
		public ExprContext e;
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryMinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterUnaryMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitUnaryMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitUnaryMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelationalContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public RelationalContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitRelational(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Assign2Context extends ExprContext {
		public ExprContext name;
		public ExprContext e;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ASSIGN2() { return getTokens(CoolParser.ASSIGN2); }
		public TerminalNode ASSIGN2(int i) {
			return getToken(CoolParser.ASSIGN2, i);
		}
		public Assign2Context(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssign2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssign2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssign2(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends ExprContext {
		public ExprContext cond;
		public ExprContext thenBranch;
		public ExprContext elseBranch;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends ExprContext {
		public ExprContext name;
		public ExprContext e;
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new ImplicitDispatchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(79);
				((ImplicitDispatchContext)_localctx).name = match(ID);
				setState(80);
				match(LPAREN);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << BOOL) | (1L << NOT) | (1L << ISVOID) | (1L << NEW) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << LPAREN) | (1L << MINUS) | (1L << NEG))) != 0)) {
					{
					setState(81);
					((ImplicitDispatchContext)_localctx).expr = expr(0);
					((ImplicitDispatchContext)_localctx).args.add(((ImplicitDispatchContext)_localctx).expr);
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(82);
						match(COMMA);
						setState(83);
						((ImplicitDispatchContext)_localctx).expr = expr(0);
						((ImplicitDispatchContext)_localctx).args.add(((ImplicitDispatchContext)_localctx).expr);
						}
						}
						setState(88);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(91);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new UnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(MINUS);
				setState(93);
				((UnaryMinusContext)_localctx).e = expr(17);
				}
				break;
			case 3:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				match(IF);
				setState(95);
				((IfContext)_localctx).cond = expr(0);
				setState(96);
				match(THEN);
				setState(97);
				((IfContext)_localctx).thenBranch = expr(0);
				setState(98);
				match(ELSE);
				setState(99);
				((IfContext)_localctx).elseBranch = expr(0);
				setState(100);
				match(FI);
				}
				break;
			case 4:
				{
				_localctx = new ParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102);
				match(LPAREN);
				setState(103);
				((ParenContext)_localctx).e = expr(0);
				setState(104);
				match(RPAREN);
				}
				break;
			case 5:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(ID);
				}
				break;
			case 6:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				match(INT);
				}
				break;
			case 7:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				match(FLOAT);
				}
				break;
			case 8:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				match(BOOL);
				}
				break;
			case 9:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(STRING);
				}
				break;
			case 10:
				{
				_localctx = new NegContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				((NegContext)_localctx).op = match(NEG);
				setState(112);
				((NegContext)_localctx).e = expr(4);
				}
				break;
			case 11:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				((NotContext)_localctx).op = match(NOT);
				setState(114);
				((NotContext)_localctx).e = expr(3);
				}
				break;
			case 12:
				{
				_localctx = new IsvoidContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(ISVOID);
				{
				setState(116);
				((IsvoidContext)_localctx).e = expr(0);
				}
				}
				break;
			case 13:
				{
				_localctx = new NewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(NEW);
				setState(118);
				((NewContext)_localctx).e = expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(142);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(140);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivContext(new ExprContext(_parentctx, _parentState));
						((MultDivContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(121);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(122);
						((MultDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((MultDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(123);
						((MultDivContext)_localctx).right = expr(17);
						}
						break;
					case 2:
						{
						_localctx = new PlusMinusContext(new ExprContext(_parentctx, _parentState));
						((PlusMinusContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(124);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(125);
						((PlusMinusContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((PlusMinusContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						((PlusMinusContext)_localctx).right = expr(16);
						}
						break;
					case 3:
						{
						_localctx = new RelationalContext(new ExprContext(_parentctx, _parentState));
						((RelationalContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(128);
						((RelationalContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << LT) | (1L << LE))) != 0)) ) {
							((RelationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(129);
						((RelationalContext)_localctx).right = expr(15);
						}
						break;
					case 4:
						{
						_localctx = new AssignContext(new ExprContext(_parentctx, _parentState));
						((AssignContext)_localctx).name = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(130);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(131);
						match(ASSIGN);
						setState(132);
						((AssignContext)_localctx).e = expr(13);
						}
						break;
					case 5:
						{
						_localctx = new Assign2Context(new ExprContext(_parentctx, _parentState));
						((Assign2Context)_localctx).name = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(133);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(136); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(134);
								match(ASSIGN2);
								setState(135);
								((Assign2Context)_localctx).e = expr(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(138); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u0092\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0005\u0000\u000e\b\u0000\n\u0000\f\u0000"+
		"\u0011\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\"\b\u0001"+
		"\n\u0001\f\u0001%\t\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001.\b\u0001\n\u0001\f\u0001"+
		"1\t\u0001\u0001\u0001\u0001\u0001\u0003\u00015\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002;\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002?\b\u0002\n\u0002\f\u0002B\t\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0003\u0003I\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005U\b\u0005\n\u0005"+
		"\f\u0005X\t\u0005\u0003\u0005Z\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005x\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0004\u0005\u0089\b\u0005\u000b\u0005\f\u0005\u008a\u0005\u0005\u008d"+
		"\b\u0005\n\u0005\f\u0005\u0090\t\u0005\u0001\u0005\u0000\u0001\n\u0006"+
		"\u0000\u0002\u0004\u0006\b\n\u0000\u0003\u0001\u0000\u001a\u001b\u0001"+
		"\u0000\u0018\u0019\u0001\u0000\u001c\u001e\u00a8\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u00024\u0001\u0000\u0000\u0000\u00046\u0001\u0000\u0000\u0000"+
		"\u0006H\u0001\u0000\u0000\u0000\bJ\u0001\u0000\u0000\u0000\nw\u0001\u0000"+
		"\u0000\u0000\f\u000e\u0003\u0004\u0002\u0000\r\f\u0001\u0000\u0000\u0000"+
		"\u000e\u0011\u0001\u0000\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f"+
		"\u0010\u0001\u0000\u0000\u0000\u0010\u0012\u0001\u0000\u0000\u0000\u0011"+
		"\u000f\u0001\u0000\u0000\u0000\u0012\u0013\u0005\u0000\u0000\u0001\u0013"+
		"\u0001\u0001\u0000\u0000\u0000\u0014\u0015\u0005\r\u0000\u0000\u0015\u0016"+
		"\u0005\u001f\u0000\u0000\u0016\u0019\u0005\u0007\u0000\u0000\u0017\u0018"+
		"\u0005 \u0000\u0000\u0018\u001a\u0003\n\u0005\u0000\u0019\u0017\u0001"+
		"\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001b\u0001"+
		"\u0000\u0000\u0000\u001b5\u0005\u0011\u0000\u0000\u001c\u001d\u0005\r"+
		"\u0000\u0000\u001d&\u0005\u0014\u0000\u0000\u001e#\u0003\b\u0004\u0000"+
		"\u001f \u0005\u0012\u0000\u0000 \"\u0003\b\u0004\u0000!\u001f\u0001\u0000"+
		"\u0000\u0000\"%\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000#$\u0001"+
		"\u0000\u0000\u0000$\'\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000"+
		"&\u001e\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000()\u0005\u0015\u0000\u0000)*\u0005\u001f\u0000\u0000*+\u0005"+
		"\u0007\u0000\u0000+/\u0005\u0016\u0000\u0000,.\u0003\n\u0005\u0000-,\u0001"+
		"\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000"+
		"/0\u0001\u0000\u0000\u000002\u0001\u0000\u0000\u00001/\u0001\u0000\u0000"+
		"\u000023\u0005\u0017\u0000\u000035\u0005\u0011\u0000\u00004\u0014\u0001"+
		"\u0000\u0000\u00004\u001c\u0001\u0000\u0000\u00005\u0003\u0001\u0000\u0000"+
		"\u000067\u0005\b\u0000\u00007:\u0005\r\u0000\u000089\u0005\t\u0000\u0000"+
		"9;\u0005\r\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000"+
		";<\u0001\u0000\u0000\u0000<@\u0005\u0016\u0000\u0000=?\u0003\u0006\u0003"+
		"\u0000>=\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001\u0000"+
		"\u0000\u0000@A\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000\u0000B@\u0001"+
		"\u0000\u0000\u0000CD\u0005\u0017\u0000\u0000DE\u0005\u0011\u0000\u0000"+
		"E\u0005\u0001\u0000\u0000\u0000FI\u0003\u0002\u0001\u0000GI\u0003\n\u0005"+
		"\u0000HF\u0001\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000I\u0007\u0001"+
		"\u0000\u0000\u0000JK\u0005\r\u0000\u0000KL\u0005\u001f\u0000\u0000LM\u0005"+
		"\u0007\u0000\u0000M\t\u0001\u0000\u0000\u0000NO\u0006\u0005\uffff\uffff"+
		"\u0000OP\u0005\r\u0000\u0000PY\u0005\u0014\u0000\u0000QV\u0003\n\u0005"+
		"\u0000RS\u0005\u0012\u0000\u0000SU\u0003\n\u0005\u0000TR\u0001\u0000\u0000"+
		"\u0000UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YQ\u0001"+
		"\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000"+
		"[x\u0005\u0015\u0000\u0000\\]\u0005\u0019\u0000\u0000]x\u0003\n\u0005"+
		"\u0011^_\u0005\u0002\u0000\u0000_`\u0003\n\u0005\u0000`a\u0005\u0003\u0000"+
		"\u0000ab\u0003\n\u0005\u0000bc\u0005\u0004\u0000\u0000cd\u0003\n\u0005"+
		"\u0000de\u0005\u0005\u0000\u0000ex\u0001\u0000\u0000\u0000fg\u0005\u0014"+
		"\u0000\u0000gh\u0003\n\u0005\u0000hi\u0005\u0015\u0000\u0000ix\u0001\u0000"+
		"\u0000\u0000jx\u0005\r\u0000\u0000kx\u0005\u000e\u0000\u0000lx\u0005\u000f"+
		"\u0000\u0000mx\u0005\u0006\u0000\u0000nx\u0005\u0010\u0000\u0000op\u0005"+
		"!\u0000\u0000px\u0003\n\u0005\u0004qr\u0005\n\u0000\u0000rx\u0003\n\u0005"+
		"\u0003st\u0005\u000b\u0000\u0000tx\u0003\n\u0005\u0000uv\u0005\f\u0000"+
		"\u0000vx\u0003\n\u0005\u0001wN\u0001\u0000\u0000\u0000w\\\u0001\u0000"+
		"\u0000\u0000w^\u0001\u0000\u0000\u0000wf\u0001\u0000\u0000\u0000wj\u0001"+
		"\u0000\u0000\u0000wk\u0001\u0000\u0000\u0000wl\u0001\u0000\u0000\u0000"+
		"wm\u0001\u0000\u0000\u0000wn\u0001\u0000\u0000\u0000wo\u0001\u0000\u0000"+
		"\u0000wq\u0001\u0000\u0000\u0000ws\u0001\u0000\u0000\u0000wu\u0001\u0000"+
		"\u0000\u0000x\u008e\u0001\u0000\u0000\u0000yz\n\u0010\u0000\u0000z{\u0007"+
		"\u0000\u0000\u0000{\u008d\u0003\n\u0005\u0011|}\n\u000f\u0000\u0000}~"+
		"\u0007\u0001\u0000\u0000~\u008d\u0003\n\u0005\u0010\u007f\u0080\n\u000e"+
		"\u0000\u0000\u0080\u0081\u0007\u0002\u0000\u0000\u0081\u008d\u0003\n\u0005"+
		"\u000f\u0082\u0083\n\f\u0000\u0000\u0083\u0084\u0005\u0013\u0000\u0000"+
		"\u0084\u008d\u0003\n\u0005\r\u0085\u0088\n\u000b\u0000\u0000\u0086\u0087"+
		"\u0005 \u0000\u0000\u0087\u0089\u0003\n\u0005\u0000\u0088\u0086\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008d\u0001"+
		"\u0000\u0000\u0000\u008cy\u0001\u0000\u0000\u0000\u008c|\u0001\u0000\u0000"+
		"\u0000\u008c\u007f\u0001\u0000\u0000\u0000\u008c\u0082\u0001\u0000\u0000"+
		"\u0000\u008c\u0085\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000"+
		"\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000"+
		"\u0000\u008f\u000b\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000"+
		"\u0000\u000f\u000f\u0019#&/4:@HVYw\u008a\u008c\u008e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}