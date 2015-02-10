// Generated from Prolog.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrologParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__50=1, T__49=2, T__48=3, T__47=4, T__46=5, T__45=6, T__44=7, T__43=8, 
		T__42=9, T__41=10, T__40=11, T__39=12, T__38=13, T__37=14, T__36=15, T__35=16, 
		T__34=17, T__33=18, T__32=19, T__31=20, T__30=21, T__29=22, T__28=23, 
		T__27=24, T__26=25, T__25=26, T__24=27, T__23=28, T__22=29, T__21=30, 
		T__20=31, T__19=32, T__18=33, T__17=34, T__16=35, T__15=36, T__14=37, 
		T__13=38, T__12=39, T__11=40, T__10=41, T__9=42, T__8=43, T__7=44, T__6=45, 
		T__5=46, T__4=47, T__3=48, T__2=49, T__1=50, T__0=51, LINECOMM=52, MULTLINECOMM=53, 
		WS=54, LBRACKET=55, RBRACKET=56, LPAREN=57, RPAREN=58, LBRACE=59, RBRACE=60, 
		COMMA=61, BAR=62, DOT=63, CHARARRAY=64, INT=65, FLOAT=66, CON=67, VAR=68;
	public static final String[] tokenNames = {
		"<INVALID>", "'=<'", "'*'", "'<'", "'\\='", "'<<'", "'*->'", "'-->'", 
		"'=:='", "'/\\'", "'@=<'", "'=@='", "'\\+'", "'->'", "'\\=@='", "' mod '", 
		"' div '", "':-'", "'@<'", "'@'", "'not '", "'='", "'>:<'", "'\\'", "'@>='", 
		"' is '", "'?-'", "'-'", "'**'", "'\\=='", "':'", "' rdiv '", "' rem '", 
		"'?'", "':<'", "'=..'", "'>>'", "'$'", "'^'", "'//'", "'@>'", "'\\/'", 
		"'+'", "';'", "'>'", "':='", "' as '", "'=\\='", "'=='", "'/'", "'>='", 
		"' xor '", "LINECOMM", "MULTLINECOMM", "WS", "'['", "']'", "'('", "')'", 
		"'{'", "'}'", "','", "'|'", "'.'", "CHARARRAY", "INT", "FLOAT", "CON", 
		"VAR"
	};
	public static final int
		RULE_predname = 0, RULE_argseq = 1, RULE_parenthesized = 2, RULE_expr = 3, 
		RULE_list = 4, RULE_predicate = 5, RULE_plrule = 6, RULE_query = 7, RULE_number = 8, 
		RULE_variable = 9, RULE_toprule = 10;
	public static final String[] ruleNames = {
		"predname", "argseq", "parenthesized", "expr", "list", "predicate", "plrule", 
		"query", "number", "variable", "toprule"
	};

	@Override
	public String getGrammarFileName() { return "Prolog.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrologParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PrednameContext extends ParserRuleContext {
		public TerminalNode CON() { return getToken(PrologParser.CON, 0); }
		public List<TerminalNode> DOT() { return getTokens(PrologParser.DOT); }
		public TerminalNode BAR(int i) {
			return getToken(PrologParser.BAR, i);
		}
		public List<TerminalNode> BAR() { return getTokens(PrologParser.BAR); }
		public TerminalNode DOT(int i) {
			return getToken(PrologParser.DOT, i);
		}
		public PrednameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterPredname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitPredname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitPredname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrednameContext predname() throws RecognitionException {
		PrednameContext _localctx = new PrednameContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_predname);
		int _la;
		try {
			int _alt;
			setState(28);
			switch (_input.LA(1)) {
			case CON:
				enterOuterAlt(_localctx, 1);
				{
				setState(22); match(CON);
				}
				break;
			case T__49:
			case T__48:
			case T__36:
			case T__35:
			case T__32:
			case T__30:
			case T__28:
			case T__26:
			case T__24:
			case T__21:
			case T__20:
			case T__19:
			case T__18:
			case T__14:
			case T__13:
			case T__9:
			case T__8:
			case T__7:
			case T__5:
			case T__2:
			case T__0:
			case BAR:
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(24); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(23);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << T__48) | (1L << T__36) | (1L << T__35) | (1L << T__32) | (1L << T__30) | (1L << T__28) | (1L << T__26) | (1L << T__24) | (1L << T__21) | (1L << T__20) | (1L << T__19) | (1L << T__18) | (1L << T__14) | (1L << T__13) | (1L << T__9) | (1L << T__8) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << T__0) | (1L << BAR) | (1L << DOT))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(26); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ArgseqContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrologParser.COMMA); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(PrologParser.COMMA, i);
		}
		public ArgseqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argseq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterArgseq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitArgseq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitArgseq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgseqContext argseq() throws RecognitionException {
		ArgseqContext _localctx = new ArgseqContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_argseq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); expr(0);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(31); match(COMMA);
				setState(32); expr(0);
				}
				}
				setState(37);
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

	public static class ParenthesizedContext extends ParserRuleContext {
		public ArgseqContext argseq() {
			return getRuleContext(ArgseqContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PrologParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(PrologParser.LPAREN, 0); }
		public ParenthesizedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesized; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterParenthesized(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitParenthesized(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitParenthesized(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedContext parenthesized() throws RecognitionException {
		ParenthesizedContext _localctx = new ParenthesizedContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_parenthesized);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); match(LPAREN);
			setState(39); argseq();
			setState(40); match(RPAREN);
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
		public ParenthesizedContext parenthesized() {
			return getRuleContext(ParenthesizedContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode CHARARRAY() { return getToken(PrologParser.CHARARRAY, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitExpr(this);
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				{
				setState(43); match(T__25);
				}
				setState(44); expr(14);
				}
				break;
			case 2:
				{
				setState(45);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__31) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(46); expr(10);
				}
				break;
			case 3:
				{
				{
				setState(47); match(T__14);
				}
				setState(48); expr(1);
				}
				break;
			case 4:
				{
				setState(55);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(49); parenthesized();
					}
					break;
				case 2:
					{
					setState(50); list();
					}
					break;
				case 3:
					{
					setState(51); predicate();
					}
					break;
				case 4:
					{
					setState(52); number();
					}
					break;
				case 5:
					{
					setState(53); variable();
					}
					break;
				case 6:
					{
					setState(54); match(CHARARRAY);
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(95);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(59);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(60);
						_la = _input.LA(1);
						if ( !(_la==T__44 || _la==T__34) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(61); expr(16);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(62);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						{
						setState(63); match(T__8);
						}
						setState(64); expr(14);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(65);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(66);
						_la = _input.LA(1);
						if ( !(_la==T__45 || _la==T__38) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(67); expr(13);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(68);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(69); match(T__6);
						setState(70); expr(12);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(71);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(72);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__50) | (1L << T__48) | (1L << T__47) | (1L << T__43) | (1L << T__41) | (1L << T__40) | (1L << T__37) | (1L << T__33) | (1L << T__29) | (1L << T__27) | (1L << T__22) | (1L << T__17) | (1L << T__11) | (1L << T__7) | (1L << T__4) | (1L << T__3) | (1L << T__1))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(73); expr(10);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(74);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(75); match(T__21);
						setState(76); expr(9);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(77);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(78);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << T__46) | (1L << T__36) | (1L << T__35) | (1L << T__20) | (1L << T__19) | (1L << T__15) | (1L << T__12) | (1L << T__2))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(79); expr(8);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(80);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(81);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__42) | (1L << T__24) | (1L << T__10) | (1L << T__9) | (1L << T__0))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(82); expr(7);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(83);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						{
						setState(84); match(T__18);
						}
						setState(85); expr(6);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(86);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						{
						setState(87); match(T__23);
						}
						setState(88); expr(5);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(89);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(90); match(T__13);
						}
						setState(91); expr(4);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(92);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(93);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__26) | (1L << T__16))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(94); expr(3);
						}
						break;
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class ListContext extends ParserRuleContext {
		public ArgseqContext argseq() {
			return getRuleContext(ArgseqContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode BAR() { return getToken(PrologParser.BAR, 0); }
		public TerminalNode LBRACKET() { return getToken(PrologParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PrologParser.RBRACKET, 0); }
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(LBRACKET);
			setState(109);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << T__48) | (1L << T__39) | (1L << T__36) | (1L << T__35) | (1L << T__32) | (1L << T__31) | (1L << T__30) | (1L << T__28) | (1L << T__26) | (1L << T__25) | (1L << T__24) | (1L << T__21) | (1L << T__20) | (1L << T__19) | (1L << T__18) | (1L << T__14) | (1L << T__13) | (1L << T__9) | (1L << T__8) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << T__0) | (1L << LBRACKET) | (1L << LPAREN) | (1L << BAR) | (1L << DOT))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CHARARRAY - 64)) | (1L << (INT - 64)) | (1L << (FLOAT - 64)) | (1L << (CON - 64)) | (1L << (VAR - 64)))) != 0)) {
				{
				setState(101); argseq();
				setState(107);
				_la = _input.LA(1);
				if (_la==BAR) {
					{
					setState(102); match(BAR);
					setState(105);
					switch (_input.LA(1)) {
					case LBRACKET:
						{
						setState(103); list();
						}
						break;
					case VAR:
						{
						setState(104); variable();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				}
			}

			setState(111); match(RBRACKET);
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

	public static class PredicateContext extends ParserRuleContext {
		public ParenthesizedContext parenthesized() {
			return getRuleContext(ParenthesizedContext.class,0);
		}
		public PrednameContext predname() {
			return getRuleContext(PrednameContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_predicate);
		try {
			setState(118);
			switch (_input.LA(1)) {
			case T__49:
			case T__48:
			case T__36:
			case T__35:
			case T__32:
			case T__30:
			case T__28:
			case T__26:
			case T__24:
			case T__21:
			case T__20:
			case T__19:
			case T__18:
			case T__14:
			case T__13:
			case T__9:
			case T__8:
			case T__7:
			case T__5:
			case T__2:
			case T__0:
			case BAR:
			case DOT:
			case CON:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(113); predname();
				setState(115);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(114); parenthesized();
					}
					break;
				}
				}
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(117); parenthesized();
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

	public static class PlruleContext extends ParserRuleContext {
		public ArgseqContext argseq() {
			return getRuleContext(ArgseqContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public PlruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterPlrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitPlrule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitPlrule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlruleContext plrule() throws RecognitionException {
		PlruleContext _localctx = new PlruleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_plrule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); predicate();
			setState(123);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(121); match(T__34);
				setState(122); argseq();
				}
			}

			setState(125); match(DOT);
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

	public static class QueryContext extends ParserRuleContext {
		public ArgseqContext argseq() {
			return getRuleContext(ArgseqContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(T__25);
			setState(128); argseq();
			setState(129); match(DOT);
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(PrologParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(PrologParser.INT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(131); match(T__24);
				}
			}

			setState(134);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		public TerminalNode VAR() { return getToken(PrologParser.VAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(VAR);
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

	public static class TopruleContext extends ParserRuleContext {
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public PlruleContext plrule(int i) {
			return getRuleContext(PlruleContext.class,i);
		}
		public List<PlruleContext> plrule() {
			return getRuleContexts(PlruleContext.class);
		}
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public TopruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_toprule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterToprule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitToprule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitToprule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopruleContext toprule() throws RecognitionException {
		TopruleContext _localctx = new TopruleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_toprule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << T__48) | (1L << T__36) | (1L << T__35) | (1L << T__32) | (1L << T__30) | (1L << T__28) | (1L << T__26) | (1L << T__25) | (1L << T__24) | (1L << T__21) | (1L << T__20) | (1L << T__19) | (1L << T__18) | (1L << T__14) | (1L << T__13) | (1L << T__9) | (1L << T__8) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << T__0) | (1L << LPAREN) | (1L << BAR) | (1L << DOT))) != 0) || _la==CON) {
				{
				setState(140);
				switch (_input.LA(1)) {
				case T__49:
				case T__48:
				case T__36:
				case T__35:
				case T__32:
				case T__30:
				case T__28:
				case T__26:
				case T__24:
				case T__21:
				case T__20:
				case T__19:
				case T__18:
				case T__14:
				case T__13:
				case T__9:
				case T__8:
				case T__7:
				case T__5:
				case T__2:
				case T__0:
				case LPAREN:
				case BAR:
				case DOT:
				case CON:
					{
					setState(138); plrule();
					}
					break;
				case T__25:
					{
					setState(139); query();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(144);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 15);
		case 1: return precpred(_ctx, 13);
		case 2: return precpred(_ctx, 12);
		case 3: return precpred(_ctx, 11);
		case 4: return precpred(_ctx, 9);
		case 5: return precpred(_ctx, 8);
		case 6: return precpred(_ctx, 7);
		case 7: return precpred(_ctx, 6);
		case 8: return precpred(_ctx, 5);
		case 9: return precpred(_ctx, 4);
		case 10: return precpred(_ctx, 3);
		case 11: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3F\u0094\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\6\2\33\n\2\r\2\16\2\34\5\2\37\n\2\3\3\3\3\3\3\7\3"+
		"$\n\3\f\3\16\3\'\13\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\5\5:\n\5\5\5<\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5b\n\5\f\5\16\5e\13\5\3\6"+
		"\3\6\3\6\3\6\3\6\5\6l\n\6\5\6n\n\6\5\6p\n\6\3\6\3\6\3\7\3\7\5\7v\n\7\3"+
		"\7\5\7y\n\7\3\b\3\b\3\b\5\b~\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\5\n\u0087"+
		"\n\n\3\n\3\n\3\13\3\13\3\f\3\f\7\f\u008f\n\f\f\f\16\f\u0092\13\f\3\f\2"+
		"\3\b\r\2\4\6\b\n\f\16\20\22\24\26\2\13\20\2\4\5\21\22\25\25\27\27\31\31"+
		"\33\33\35\35 #\'(,.\60\60\63\63\65\65@A\4\2\16\16\26\26\4\2\t\t\23\23"+
		"\4\2\b\b\17\17\20\2\3\3\5\6\n\n\f\r\20\20\24\24\30\30\32\32\37\37$$**"+
		"..\61\62\64\64\t\2\4\4\7\7\21\22!\"&&))\63\63\6\2\13\13\35\35+,\65\65"+
		"\5\2\27\27\33\33%%\3\2CD\u00a8\2\36\3\2\2\2\4 \3\2\2\2\6(\3\2\2\2\b;\3"+
		"\2\2\2\nf\3\2\2\2\fx\3\2\2\2\16z\3\2\2\2\20\u0081\3\2\2\2\22\u0086\3\2"+
		"\2\2\24\u008a\3\2\2\2\26\u0090\3\2\2\2\30\37\7E\2\2\31\33\t\2\2\2\32\31"+
		"\3\2\2\2\33\34\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\30"+
		"\3\2\2\2\36\32\3\2\2\2\37\3\3\2\2\2 %\5\b\5\2!\"\7?\2\2\"$\5\b\5\2#!\3"+
		"\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\5\3\2\2\2\'%\3\2\2\2()\7;\2\2)"+
		"*\5\4\3\2*+\7<\2\2+\7\3\2\2\2,-\b\5\1\2-.\7\34\2\2.<\5\b\5\20/\60\t\3"+
		"\2\2\60<\5\b\5\f\61\62\7\'\2\2\62<\5\b\5\3\63:\5\6\4\2\64:\5\n\6\2\65"+
		":\5\f\7\2\66:\5\22\n\2\67:\5\24\13\28:\7B\2\29\63\3\2\2\29\64\3\2\2\2"+
		"9\65\3\2\2\29\66\3\2\2\29\67\3\2\2\298\3\2\2\2:<\3\2\2\2;,\3\2\2\2;/\3"+
		"\2\2\2;\61\3\2\2\2;9\3\2\2\2<c\3\2\2\2=>\f\21\2\2>?\t\4\2\2?b\5\b\5\22"+
		"@A\f\17\2\2AB\7-\2\2Bb\5\b\5\20CD\f\16\2\2DE\t\5\2\2Eb\5\b\5\17FG\f\r"+
		"\2\2GH\7/\2\2Hb\5\b\5\16IJ\f\13\2\2JK\t\6\2\2Kb\5\b\5\fLM\f\n\2\2MN\7"+
		" \2\2Nb\5\b\5\13OP\f\t\2\2PQ\t\7\2\2Qb\5\b\5\nRS\f\b\2\2ST\t\b\2\2Tb\5"+
		"\b\5\tUV\f\7\2\2VW\7#\2\2Wb\5\b\5\bXY\f\6\2\2YZ\7\36\2\2Zb\5\b\5\7[\\"+
		"\f\5\2\2\\]\7(\2\2]b\5\b\5\6^_\f\4\2\2_`\t\t\2\2`b\5\b\5\5a=\3\2\2\2a"+
		"@\3\2\2\2aC\3\2\2\2aF\3\2\2\2aI\3\2\2\2aL\3\2\2\2aO\3\2\2\2aR\3\2\2\2"+
		"aU\3\2\2\2aX\3\2\2\2a[\3\2\2\2a^\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2"+
		"d\t\3\2\2\2ec\3\2\2\2fo\79\2\2gm\5\4\3\2hk\7@\2\2il\5\n\6\2jl\5\24\13"+
		"\2ki\3\2\2\2kj\3\2\2\2ln\3\2\2\2mh\3\2\2\2mn\3\2\2\2np\3\2\2\2og\3\2\2"+
		"\2op\3\2\2\2pq\3\2\2\2qr\7:\2\2r\13\3\2\2\2su\5\2\2\2tv\5\6\4\2ut\3\2"+
		"\2\2uv\3\2\2\2vy\3\2\2\2wy\5\6\4\2xs\3\2\2\2xw\3\2\2\2y\r\3\2\2\2z}\5"+
		"\f\7\2{|\7\23\2\2|~\5\4\3\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080"+
		"\7A\2\2\u0080\17\3\2\2\2\u0081\u0082\7\34\2\2\u0082\u0083\5\4\3\2\u0083"+
		"\u0084\7A\2\2\u0084\21\3\2\2\2\u0085\u0087\7\35\2\2\u0086\u0085\3\2\2"+
		"\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\t\n\2\2\u0089\23"+
		"\3\2\2\2\u008a\u008b\7F\2\2\u008b\25\3\2\2\2\u008c\u008f\5\16\b\2\u008d"+
		"\u008f\5\20\t\2\u008e\u008c\3\2\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3"+
		"\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\27\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\22\34\36%9;ackmoux}\u0086\u008e\u0090";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}