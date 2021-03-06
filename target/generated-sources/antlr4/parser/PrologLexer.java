// Generated from Prolog.g4 by ANTLR 4.2.2
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrologLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"' div '", "'=..'", "'=<'", "'**'", "'>>'", "'='", "'@>'", "'^'", "' rdiv '", 
		"' as '", "'@>='", "'\\=@='", "'*->'", "'$'", "'\\='", "':-'", "'=@='", 
		"'>='", "'\\+'", "'//'", "'<'", "'@'", "'=:='", "' rem '", "'-->'", "'+'", 
		"'/'", "'@<'", "'\\'", "';'", "'<<'", "' mod '", "'\\/'", "':='", "'?'", 
		"'*'", "'->'", "'not '", "' xor '", "':'", "'=\\='", "'=='", "'>'", "':<'", 
		"'>:<'", "'@=<'", "' is '", "'\\=='", "'/\\'", "'-'", "'?-'", "LINECOMM", 
		"MULTLINECOMM", "WS", "'['", "']'", "'('", "')'", "'{'", "'}'", "','", 
		"'|'", "'.'", "CHARARRAY", "INT", "FLOAT", "CON", "VAR"
	};
	public static final String[] ruleNames = {
		"T__50", "T__49", "T__48", "T__47", "T__46", "T__45", "T__44", "T__43", 
		"T__42", "T__41", "T__40", "T__39", "T__38", "T__37", "T__36", "T__35", 
		"T__34", "T__33", "T__32", "T__31", "T__30", "T__29", "T__28", "T__27", 
		"T__26", "T__25", "T__24", "T__23", "T__22", "T__21", "T__20", "T__19", 
		"T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", 
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "LINECOMM", "MULTLINECOMM", "WS", "LBRACKET", "RBRACKET", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "BAR", "DOT", "CHARARRAY", 
		"INT", "FLOAT", "CON", "VAR"
	};


	public PrologLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Prolog.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2F\u01a8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3#\3#\3"+
		"#\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3*"+
		"\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3\60\3"+
		"\60\3\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3"+
		"\64\3\65\3\65\3\65\5\65\u013c\n\65\3\65\7\65\u013f\n\65\f\65\16\65\u0142"+
		"\13\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\7\66\u014c\n\66\f\66\16"+
		"\66\u014f\13\66\3\66\3\66\3\66\3\66\3\66\3\67\6\67\u0157\n\67\r\67\16"+
		"\67\u0158\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3"+
		"@\3@\3A\3A\7A\u0171\nA\fA\16A\u0174\13A\3A\3A\3B\6B\u0179\nB\rB\16B\u017a"+
		"\3C\6C\u017e\nC\rC\16C\u017f\3C\3C\6C\u0184\nC\rC\16C\u0185\3D\3D\7D\u018a"+
		"\nD\fD\16D\u018d\13D\3D\3D\6D\u0191\nD\rD\16D\u0192\3D\7D\u0196\nD\fD"+
		"\16D\u0199\13D\3D\5D\u019c\nD\3E\6E\u019f\nE\rE\16E\u01a0\3E\7E\u01a4"+
		"\nE\fE\16E\u01a7\13E\2\2F\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087"+
		"E\u0089F\3\2\f\4\2\f\f\17\17\3\2\61\61\3\2,,\5\2\13\f\17\17\"\"\5\2\f"+
		"\f\17\17$$\3\2\62;\5\2\f\f\17\17))\3\2c|\6\2\62;C\\aac|\4\2C\\aa\u01b7"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2"+
		"{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\3\u008b\3\2\2\2\5\u0091\3\2\2"+
		"\2\7\u0095\3\2\2\2\t\u0098\3\2\2\2\13\u009b\3\2\2\2\r\u009e\3\2\2\2\17"+
		"\u00a0\3\2\2\2\21\u00a3\3\2\2\2\23\u00a5\3\2\2\2\25\u00ac\3\2\2\2\27\u00b1"+
		"\3\2\2\2\31\u00b5\3\2\2\2\33\u00ba\3\2\2\2\35\u00be\3\2\2\2\37\u00c0\3"+
		"\2\2\2!\u00c3\3\2\2\2#\u00c6\3\2\2\2%\u00ca\3\2\2\2\'\u00cd\3\2\2\2)\u00d0"+
		"\3\2\2\2+\u00d3\3\2\2\2-\u00d5\3\2\2\2/\u00d7\3\2\2\2\61\u00db\3\2\2\2"+
		"\63\u00e1\3\2\2\2\65\u00e5\3\2\2\2\67\u00e7\3\2\2\29\u00e9\3\2\2\2;\u00ec"+
		"\3\2\2\2=\u00ee\3\2\2\2?\u00f0\3\2\2\2A\u00f3\3\2\2\2C\u00f9\3\2\2\2E"+
		"\u00fc\3\2\2\2G\u00ff\3\2\2\2I\u0101\3\2\2\2K\u0103\3\2\2\2M\u0106\3\2"+
		"\2\2O\u010b\3\2\2\2Q\u0111\3\2\2\2S\u0113\3\2\2\2U\u0117\3\2\2\2W\u011a"+
		"\3\2\2\2Y\u011c\3\2\2\2[\u011f\3\2\2\2]\u0123\3\2\2\2_\u0127\3\2\2\2a"+
		"\u012c\3\2\2\2c\u0130\3\2\2\2e\u0133\3\2\2\2g\u0135\3\2\2\2i\u013b\3\2"+
		"\2\2k\u0145\3\2\2\2m\u0156\3\2\2\2o\u015c\3\2\2\2q\u015e\3\2\2\2s\u0160"+
		"\3\2\2\2u\u0162\3\2\2\2w\u0164\3\2\2\2y\u0166\3\2\2\2{\u0168\3\2\2\2}"+
		"\u016a\3\2\2\2\177\u016c\3\2\2\2\u0081\u016e\3\2\2\2\u0083\u0178\3\2\2"+
		"\2\u0085\u017d\3\2\2\2\u0087\u019b\3\2\2\2\u0089\u019e\3\2\2\2\u008b\u008c"+
		"\7\"\2\2\u008c\u008d\7f\2\2\u008d\u008e\7k\2\2\u008e\u008f\7x\2\2\u008f"+
		"\u0090\7\"\2\2\u0090\4\3\2\2\2\u0091\u0092\7?\2\2\u0092\u0093\7\60\2\2"+
		"\u0093\u0094\7\60\2\2\u0094\6\3\2\2\2\u0095\u0096\7?\2\2\u0096\u0097\7"+
		">\2\2\u0097\b\3\2\2\2\u0098\u0099\7,\2\2\u0099\u009a\7,\2\2\u009a\n\3"+
		"\2\2\2\u009b\u009c\7@\2\2\u009c\u009d\7@\2\2\u009d\f\3\2\2\2\u009e\u009f"+
		"\7?\2\2\u009f\16\3\2\2\2\u00a0\u00a1\7B\2\2\u00a1\u00a2\7@\2\2\u00a2\20"+
		"\3\2\2\2\u00a3\u00a4\7`\2\2\u00a4\22\3\2\2\2\u00a5\u00a6\7\"\2\2\u00a6"+
		"\u00a7\7t\2\2\u00a7\u00a8\7f\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7x\2\2"+
		"\u00aa\u00ab\7\"\2\2\u00ab\24\3\2\2\2\u00ac\u00ad\7\"\2\2\u00ad\u00ae"+
		"\7c\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7\"\2\2\u00b0\26\3\2\2\2\u00b1"+
		"\u00b2\7B\2\2\u00b2\u00b3\7@\2\2\u00b3\u00b4\7?\2\2\u00b4\30\3\2\2\2\u00b5"+
		"\u00b6\7^\2\2\u00b6\u00b7\7?\2\2\u00b7\u00b8\7B\2\2\u00b8\u00b9\7?\2\2"+
		"\u00b9\32\3\2\2\2\u00ba\u00bb\7,\2\2\u00bb\u00bc\7/\2\2\u00bc\u00bd\7"+
		"@\2\2\u00bd\34\3\2\2\2\u00be\u00bf\7&\2\2\u00bf\36\3\2\2\2\u00c0\u00c1"+
		"\7^\2\2\u00c1\u00c2\7?\2\2\u00c2 \3\2\2\2\u00c3\u00c4\7<\2\2\u00c4\u00c5"+
		"\7/\2\2\u00c5\"\3\2\2\2\u00c6\u00c7\7?\2\2\u00c7\u00c8\7B\2\2\u00c8\u00c9"+
		"\7?\2\2\u00c9$\3\2\2\2\u00ca\u00cb\7@\2\2\u00cb\u00cc\7?\2\2\u00cc&\3"+
		"\2\2\2\u00cd\u00ce\7^\2\2\u00ce\u00cf\7-\2\2\u00cf(\3\2\2\2\u00d0\u00d1"+
		"\7\61\2\2\u00d1\u00d2\7\61\2\2\u00d2*\3\2\2\2\u00d3\u00d4\7>\2\2\u00d4"+
		",\3\2\2\2\u00d5\u00d6\7B\2\2\u00d6.\3\2\2\2\u00d7\u00d8\7?\2\2\u00d8\u00d9"+
		"\7<\2\2\u00d9\u00da\7?\2\2\u00da\60\3\2\2\2\u00db\u00dc\7\"\2\2\u00dc"+
		"\u00dd\7t\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7o\2\2\u00df\u00e0\7\"\2"+
		"\2\u00e0\62\3\2\2\2\u00e1\u00e2\7/\2\2\u00e2\u00e3\7/\2\2\u00e3\u00e4"+
		"\7@\2\2\u00e4\64\3\2\2\2\u00e5\u00e6\7-\2\2\u00e6\66\3\2\2\2\u00e7\u00e8"+
		"\7\61\2\2\u00e88\3\2\2\2\u00e9\u00ea\7B\2\2\u00ea\u00eb\7>\2\2\u00eb:"+
		"\3\2\2\2\u00ec\u00ed\7^\2\2\u00ed<\3\2\2\2\u00ee\u00ef\7=\2\2\u00ef>\3"+
		"\2\2\2\u00f0\u00f1\7>\2\2\u00f1\u00f2\7>\2\2\u00f2@\3\2\2\2\u00f3\u00f4"+
		"\7\"\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6\7q\2\2\u00f6\u00f7\7f\2\2\u00f7"+
		"\u00f8\7\"\2\2\u00f8B\3\2\2\2\u00f9\u00fa\7^\2\2\u00fa\u00fb\7\61\2\2"+
		"\u00fbD\3\2\2\2\u00fc\u00fd\7<\2\2\u00fd\u00fe\7?\2\2\u00feF\3\2\2\2\u00ff"+
		"\u0100\7A\2\2\u0100H\3\2\2\2\u0101\u0102\7,\2\2\u0102J\3\2\2\2\u0103\u0104"+
		"\7/\2\2\u0104\u0105\7@\2\2\u0105L\3\2\2\2\u0106\u0107\7p\2\2\u0107\u0108"+
		"\7q\2\2\u0108\u0109\7v\2\2\u0109\u010a\7\"\2\2\u010aN\3\2\2\2\u010b\u010c"+
		"\7\"\2\2\u010c\u010d\7z\2\2\u010d\u010e\7q\2\2\u010e\u010f\7t\2\2\u010f"+
		"\u0110\7\"\2\2\u0110P\3\2\2\2\u0111\u0112\7<\2\2\u0112R\3\2\2\2\u0113"+
		"\u0114\7?\2\2\u0114\u0115\7^\2\2\u0115\u0116\7?\2\2\u0116T\3\2\2\2\u0117"+
		"\u0118\7?\2\2\u0118\u0119\7?\2\2\u0119V\3\2\2\2\u011a\u011b\7@\2\2\u011b"+
		"X\3\2\2\2\u011c\u011d\7<\2\2\u011d\u011e\7>\2\2\u011eZ\3\2\2\2\u011f\u0120"+
		"\7@\2\2\u0120\u0121\7<\2\2\u0121\u0122\7>\2\2\u0122\\\3\2\2\2\u0123\u0124"+
		"\7B\2\2\u0124\u0125\7?\2\2\u0125\u0126\7>\2\2\u0126^\3\2\2\2\u0127\u0128"+
		"\7\"\2\2\u0128\u0129\7k\2\2\u0129\u012a\7u\2\2\u012a\u012b\7\"\2\2\u012b"+
		"`\3\2\2\2\u012c\u012d\7^\2\2\u012d\u012e\7?\2\2\u012e\u012f\7?\2\2\u012f"+
		"b\3\2\2\2\u0130\u0131\7\61\2\2\u0131\u0132\7^\2\2\u0132d\3\2\2\2\u0133"+
		"\u0134\7/\2\2\u0134f\3\2\2\2\u0135\u0136\7A\2\2\u0136\u0137\7/\2\2\u0137"+
		"h\3\2\2\2\u0138\u0139\7\61\2\2\u0139\u013c\7\61\2\2\u013a\u013c\7\'\2"+
		"\2\u013b\u0138\3\2\2\2\u013b\u013a\3\2\2\2\u013c\u0140\3\2\2\2\u013d\u013f"+
		"\n\2\2\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144\b\65"+
		"\2\2\u0144j\3\2\2\2\u0145\u0146\7\61\2\2\u0146\u0147\7,\2\2\u0147\u014d"+
		"\3\2\2\2\u0148\u0149\7,\2\2\u0149\u014c\n\3\2\2\u014a\u014c\n\4\2\2\u014b"+
		"\u0148\3\2\2\2\u014b\u014a\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2"+
		"\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150"+
		"\u0151\7,\2\2\u0151\u0152\7\61\2\2\u0152\u0153\3\2\2\2\u0153\u0154\b\66"+
		"\2\2\u0154l\3\2\2\2\u0155\u0157\t\5\2\2\u0156\u0155\3\2\2\2\u0157\u0158"+
		"\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\3\2\2\2\u015a"+
		"\u015b\b\67\2\2\u015bn\3\2\2\2\u015c\u015d\7]\2\2\u015dp\3\2\2\2\u015e"+
		"\u015f\7_\2\2\u015fr\3\2\2\2\u0160\u0161\7*\2\2\u0161t\3\2\2\2\u0162\u0163"+
		"\7+\2\2\u0163v\3\2\2\2\u0164\u0165\7}\2\2\u0165x\3\2\2\2\u0166\u0167\7"+
		"\177\2\2\u0167z\3\2\2\2\u0168\u0169\7.\2\2\u0169|\3\2\2\2\u016a\u016b"+
		"\7~\2\2\u016b~\3\2\2\2\u016c\u016d\7\60\2\2\u016d\u0080\3\2\2\2\u016e"+
		"\u0172\7$\2\2\u016f\u0171\n\6\2\2\u0170\u016f\3\2\2\2\u0171\u0174\3\2"+
		"\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0175\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0175\u0176\7$\2\2\u0176\u0082\3\2\2\2\u0177\u0179\t\7"+
		"\2\2\u0178\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0178\3\2\2\2\u017a"+
		"\u017b\3\2\2\2\u017b\u0084\3\2\2\2\u017c\u017e\t\7\2\2\u017d\u017c\3\2"+
		"\2\2\u017e\u017f\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180"+
		"\u0181\3\2\2\2\u0181\u0183\7\60\2\2\u0182\u0184\t\7\2\2\u0183\u0182\3"+
		"\2\2\2\u0184\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0086\3\2\2\2\u0187\u018b\7)\2\2\u0188\u018a\n\b\2\2\u0189\u0188\3\2"+
		"\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c"+
		"\u018e\3\2\2\2\u018d\u018b\3\2\2\2\u018e\u019c\7)\2\2\u018f\u0191\t\t"+
		"\2\2\u0190\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0190\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193\u0197\3\2\2\2\u0194\u0196\t\n\2\2\u0195\u0194\3\2"+
		"\2\2\u0196\u0199\3\2\2\2\u0197\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u019c\3\2\2\2\u0199\u0197\3\2\2\2\u019a\u019c\7#\2\2\u019b\u0187\3\2"+
		"\2\2\u019b\u0190\3\2\2\2\u019b\u019a\3\2\2\2\u019c\u0088\3\2\2\2\u019d"+
		"\u019f\t\13\2\2\u019e\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u019e\3"+
		"\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a5\3\2\2\2\u01a2\u01a4\t\n\2\2\u01a3"+
		"\u01a2\3\2\2\2\u01a4\u01a7\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2"+
		"\2\2\u01a6\u008a\3\2\2\2\u01a7\u01a5\3\2\2\2\25\2\u013b\u0140\u014b\u014d"+
		"\u0158\u0172\u017a\u017f\u0185\u018b\u0192\u0195\u0197\u019b\u019e\u01a0"+
		"\u01a3\u01a5\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}