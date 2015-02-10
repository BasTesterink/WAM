// Generated from Prolog.g4 by ANTLR 4.2.2
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
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__47=1, T__46=2, T__45=3, T__44=4, T__43=5, T__42=6, T__41=7, T__40=8, 
		T__39=9, T__38=10, T__37=11, T__36=12, T__35=13, T__34=14, T__33=15, T__32=16, 
		T__31=17, T__30=18, T__29=19, T__28=20, T__27=21, T__26=22, T__25=23, 
		T__24=24, T__23=25, T__22=26, T__21=27, T__20=28, T__19=29, T__18=30, 
		T__17=31, T__16=32, T__15=33, T__14=34, T__13=35, T__12=36, T__11=37, 
		T__10=38, T__9=39, T__8=40, T__7=41, T__6=42, T__5=43, T__4=44, T__3=45, 
		T__2=46, T__1=47, T__0=48, LINECOMM=49, MULTLINECOMM=50, WS=51, LBRACKET=52, 
		RBRACKET=53, LPAREN=54, RPAREN=55, LBRACE=56, RBRACE=57, COMMA=58, BAR=59, 
		DOT=60, CHARARRAY=61, INT=62, FLOAT=63, CON=64, VAR=65, OP=66;
	public static final String[] tokenNames = {
		"<INVALID>", "' div '", "'=..'", "'=<'", "'**'", "'>>'", "'='", "'^'", 
		"'@>'", "' rdiv '", "'@>='", "'$'", "'\\=@='", "'*->'", "'\\='", "':-'", 
		"'=@='", "'>='", "'\\+'", "'//'", "'<'", "' rem '", "'=:='", "'-->'", 
		"'+'", "'/'", "'@<'", "';'", "' mod '", "'<<'", "'?'", "'\\/'", "':='", 
		"'*'", "'->'", "'not '", "' xor '", "':'", "'=\\='", "'=='", "'>'", "':<'", 
		"'>:<'", "'@=<'", "' is '", "'\\=='", "'/\\'", "'-'", "'?-'", "LINECOMM", 
		"MULTLINECOMM", "WS", "'['", "']'", "'('", "')'", "'{'", "'}'", "','", 
		"'|'", "'.'", "CHARARRAY", "INT", "FLOAT", "CON", "VAR", "OP"
	};
	public static final int
		RULE_argseq = 0, RULE_parenthesized = 1, RULE_expr = 2, RULE_list = 3, 
		RULE_predicate = 4, RULE_plrule = 5, RULE_query = 6, RULE_number = 7, 
		RULE_variable = 8, RULE_toprule = 9;
	public static final String[] ruleNames = {
		"argseq", "parenthesized", "expr", "list", "predicate", "plrule", "query", 
		"number", "variable", "toprule"
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
	public static class ArgseqContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrologParser.COMMA); }
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
	}

	public final ArgseqContext argseq() throws RecognitionException {
		ArgseqContext _localctx = new ArgseqContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_argseq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); expr(0);
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(21); match(COMMA);
				setState(22); expr(0);
				}
				}
				setState(27);
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
		public TerminalNode LPAREN() { return getToken(PrologParser.LPAREN, 0); }
		public ArgseqContext argseq() {
			return getRuleContext(ArgseqContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PrologParser.RPAREN, 0); }
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
	}

	public final ParenthesizedContext parenthesized() throws RecognitionException {
		ParenthesizedContext _localctx = new ParenthesizedContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parenthesized);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); match(LPAREN);
			setState(29); argseq();
			setState(30); match(RPAREN);
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
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CHARARRAY() { return getToken(PrologParser.CHARARRAY, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ParenthesizedContext parenthesized() {
			return getRuleContext(ParenthesizedContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
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
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			switch (_input.LA(1)) {
			case 48:
				{
				{
				setState(33); match(48);
				}
				setState(34); expr(14);
				}
				break;
			case 18:
			case 35:
				{
				setState(35);
				_la = _input.LA(1);
				if ( !(_la==18 || _la==35) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(36); expr(10);
				}
				break;
			case 11:
				{
				{
				setState(37); match(11);
				}
				setState(38); expr(1);
				}
				break;
			case 47:
			case LBRACKET:
			case LPAREN:
			case CHARARRAY:
			case INT:
			case FLOAT:
			case CON:
			case VAR:
			case OP:
				{
				setState(45);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(39); parenthesized();
					}
					break;

				case 2:
					{
					setState(40); list();
					}
					break;

				case 3:
					{
					setState(41); predicate();
					}
					break;

				case 4:
					{
					setState(42); number();
					}
					break;

				case 5:
					{
					setState(43); variable();
					}
					break;

				case 6:
					{
					setState(44); match(CHARARRAY);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(85);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(49);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(50);
						_la = _input.LA(1);
						if ( !(_la==15 || _la==23) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(51); expr(16);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(52);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						{
						setState(53); match(27);
						}
						setState(54); expr(14);
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(55);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(56);
						_la = _input.LA(1);
						if ( !(_la==13 || _la==34) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(57); expr(13);
						}
						break;

					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(58);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(59); match(32);
						setState(60); expr(12);
						}
						break;

					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(61);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(62);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 8) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 16) | (1L << 17) | (1L << 20) | (1L << 22) | (1L << 26) | (1L << 38) | (1L << 39) | (1L << 40) | (1L << 41) | (1L << 42) | (1L << 43) | (1L << 45))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(63); expr(10);
						}
						break;

					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(64);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(65); match(37);
						setState(66); expr(9);
						}
						break;

					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(67);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(68);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 9) | (1L << 19) | (1L << 21) | (1L << 25) | (1L << 28) | (1L << 29) | (1L << 33))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(69); expr(8);
						}
						break;

					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(70);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(71);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 24) | (1L << 31) | (1L << 36) | (1L << 46) | (1L << 47))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(72); expr(7);
						}
						break;

					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(73);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						{
						setState(74); match(30);
						}
						setState(75); expr(6);
						}
						break;

					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(76);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						{
						setState(77); match(4);
						}
						setState(78); expr(5);
						}
						break;

					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(79);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(80); match(7);
						}
						setState(81); expr(4);
						}
						break;

					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(82);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(83);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 6) | (1L << 44))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(84); expr(3);
						}
						break;
					}
					} 
				}
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
		public TerminalNode RBRACKET() { return getToken(PrologParser.RBRACKET, 0); }
		public TerminalNode BAR() { return getToken(PrologParser.BAR, 0); }
		public TerminalNode VAR() { return getToken(PrologParser.VAR, 0); }
		public ArgseqContext argseq() {
			return getRuleContext(ArgseqContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(PrologParser.LBRACKET, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
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
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(LBRACKET);
			setState(99);
			_la = _input.LA(1);
			if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & ((1L << (11 - 11)) | (1L << (18 - 11)) | (1L << (35 - 11)) | (1L << (47 - 11)) | (1L << (48 - 11)) | (1L << (LBRACKET - 11)) | (1L << (LPAREN - 11)) | (1L << (CHARARRAY - 11)) | (1L << (INT - 11)) | (1L << (FLOAT - 11)) | (1L << (CON - 11)) | (1L << (VAR - 11)) | (1L << (OP - 11)))) != 0)) {
				{
				setState(91); argseq();
				setState(97);
				_la = _input.LA(1);
				if (_la==BAR) {
					{
					setState(92); match(BAR);
					setState(95);
					switch (_input.LA(1)) {
					case LBRACKET:
						{
						setState(93); list();
						}
						break;
					case VAR:
						{
						setState(94); match(VAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				}
			}

			setState(101); match(RBRACKET);
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
		public TerminalNode CON() { return getToken(PrologParser.CON, 0); }
		public TerminalNode OP() { return getToken(PrologParser.OP, 0); }
		public ParenthesizedContext parenthesized() {
			return getRuleContext(ParenthesizedContext.class,0);
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
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_predicate);
		int _la;
		try {
			setState(108);
			switch (_input.LA(1)) {
			case CON:
			case OP:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(103);
				_la = _input.LA(1);
				if ( !(_la==CON || _la==OP) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(105);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(104); parenthesized();
					}
					break;
				}
				}
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(107); parenthesized();
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
	}

	public final PlruleContext plrule() throws RecognitionException {
		PlruleContext _localctx = new PlruleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_plrule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); predicate();
			setState(113);
			_la = _input.LA(1);
			if (_la==15) {
				{
				setState(111); match(15);
				setState(112); argseq();
				}
			}

			setState(115); match(DOT);
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
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); match(48);
			setState(118); argseq();
			setState(119); match(DOT);
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
		public TerminalNode INT() { return getToken(PrologParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(PrologParser.FLOAT, 0); }
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
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_la = _input.LA(1);
			if (_la==47) {
				{
				setState(121); match(47);
				}
			}

			setState(124);
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
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(VAR);
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
		public PlruleContext plrule(int i) {
			return getRuleContext(PlruleContext.class,i);
		}
		public List<PlruleContext> plrule() {
			return getRuleContexts(PlruleContext.class);
		}
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
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
	}

	public final TopruleContext toprule() throws RecognitionException {
		TopruleContext _localctx = new TopruleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_toprule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & ((1L << (48 - 48)) | (1L << (LPAREN - 48)) | (1L << (CON - 48)) | (1L << (OP - 48)))) != 0)) {
				{
				setState(130);
				switch (_input.LA(1)) {
				case LPAREN:
				case CON:
				case OP:
					{
					setState(128); plrule();
					}
					break;
				case 48:
					{
					setState(129); query();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(134);
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
		case 2: return expr_sempred((ExprContext)_localctx, predIndex);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3D\u008a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\60\n\4\5\4\62\n\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4X\n\4\f\4\16\4[\13\4\3\5\3\5\3\5\3\5\3\5\5\5b\n\5\5\5d\n\5\5\5f\n\5"+
		"\3\5\3\5\3\6\3\6\5\6l\n\6\3\6\5\6o\n\6\3\7\3\7\3\7\5\7t\n\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\t\5\t}\n\t\3\t\3\t\3\n\3\n\3\13\3\13\7\13\u0085\n\13"+
		"\f\13\16\13\u0088\13\13\3\13\2\3\6\f\2\4\6\b\n\f\16\20\22\24\2\13\4\2"+
		"\24\24%%\4\2\21\21\31\31\4\2\17\17$$\r\2\5\5\n\n\f\f\16\16\20\20\22\23"+
		"\26\26\30\30\34\34(-//\n\2\3\3\7\7\13\13\25\25\27\27\33\33\36\37##\6\2"+
		"\32\32!!&&\60\61\5\2\4\4\b\b..\4\2BBDD\3\2@A\u009d\2\26\3\2\2\2\4\36\3"+
		"\2\2\2\6\61\3\2\2\2\b\\\3\2\2\2\nn\3\2\2\2\fp\3\2\2\2\16w\3\2\2\2\20|"+
		"\3\2\2\2\22\u0080\3\2\2\2\24\u0086\3\2\2\2\26\33\5\6\4\2\27\30\7<\2\2"+
		"\30\32\5\6\4\2\31\27\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2"+
		"\34\3\3\2\2\2\35\33\3\2\2\2\36\37\78\2\2\37 \5\2\2\2 !\79\2\2!\5\3\2\2"+
		"\2\"#\b\4\1\2#$\7\62\2\2$\62\5\6\4\20%&\t\2\2\2&\62\5\6\4\f\'(\7\r\2\2"+
		"(\62\5\6\4\3)\60\5\4\3\2*\60\5\b\5\2+\60\5\n\6\2,\60\5\20\t\2-\60\5\22"+
		"\n\2.\60\7?\2\2/)\3\2\2\2/*\3\2\2\2/+\3\2\2\2/,\3\2\2\2/-\3\2\2\2/.\3"+
		"\2\2\2\60\62\3\2\2\2\61\"\3\2\2\2\61%\3\2\2\2\61\'\3\2\2\2\61/\3\2\2\2"+
		"\62Y\3\2\2\2\63\64\f\21\2\2\64\65\t\3\2\2\65X\5\6\4\22\66\67\f\17\2\2"+
		"\678\7\35\2\28X\5\6\4\209:\f\16\2\2:;\t\4\2\2;X\5\6\4\17<=\f\r\2\2=>\7"+
		"\"\2\2>X\5\6\4\16?@\f\13\2\2@A\t\5\2\2AX\5\6\4\fBC\f\n\2\2CD\7\'\2\2D"+
		"X\5\6\4\13EF\f\t\2\2FG\t\6\2\2GX\5\6\4\nHI\f\b\2\2IJ\t\7\2\2JX\5\6\4\t"+
		"KL\f\7\2\2LM\7 \2\2MX\5\6\4\bNO\f\6\2\2OP\7\6\2\2PX\5\6\4\7QR\f\5\2\2"+
		"RS\7\t\2\2SX\5\6\4\6TU\f\4\2\2UV\t\b\2\2VX\5\6\4\5W\63\3\2\2\2W\66\3\2"+
		"\2\2W9\3\2\2\2W<\3\2\2\2W?\3\2\2\2WB\3\2\2\2WE\3\2\2\2WH\3\2\2\2WK\3\2"+
		"\2\2WN\3\2\2\2WQ\3\2\2\2WT\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\7\3"+
		"\2\2\2[Y\3\2\2\2\\e\7\66\2\2]c\5\2\2\2^a\7=\2\2_b\5\b\5\2`b\7C\2\2a_\3"+
		"\2\2\2a`\3\2\2\2bd\3\2\2\2c^\3\2\2\2cd\3\2\2\2df\3\2\2\2e]\3\2\2\2ef\3"+
		"\2\2\2fg\3\2\2\2gh\7\67\2\2h\t\3\2\2\2ik\t\t\2\2jl\5\4\3\2kj\3\2\2\2k"+
		"l\3\2\2\2lo\3\2\2\2mo\5\4\3\2ni\3\2\2\2nm\3\2\2\2o\13\3\2\2\2ps\5\n\6"+
		"\2qr\7\21\2\2rt\5\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\7>\2\2v\r\3\2"+
		"\2\2wx\7\62\2\2xy\5\2\2\2yz\7>\2\2z\17\3\2\2\2{}\7\61\2\2|{\3\2\2\2|}"+
		"\3\2\2\2}~\3\2\2\2~\177\t\n\2\2\177\21\3\2\2\2\u0080\u0081\7C\2\2\u0081"+
		"\23\3\2\2\2\u0082\u0085\5\f\7\2\u0083\u0085\5\16\b\2\u0084\u0082\3\2\2"+
		"\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\25\3\2\2\2\u0088\u0086\3\2\2\2\20\33/\61WYacekns|\u0084"+
		"\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}