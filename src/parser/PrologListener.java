// Generated from Prolog.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrologParser}.
 */
public interface PrologListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrologParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull PrologParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull PrologParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull PrologParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull PrologParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#toprule}.
	 * @param ctx the parse tree
	 */
	void enterToprule(@NotNull PrologParser.TopruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#toprule}.
	 * @param ctx the parse tree
	 */
	void exitToprule(@NotNull PrologParser.TopruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#plrule}.
	 * @param ctx the parse tree
	 */
	void enterPlrule(@NotNull PrologParser.PlruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#plrule}.
	 * @param ctx the parse tree
	 */
	void exitPlrule(@NotNull PrologParser.PlruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull PrologParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull PrologParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull PrologParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull PrologParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull PrologParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull PrologParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#parenthesized}.
	 * @param ctx the parse tree
	 */
	void enterParenthesized(@NotNull PrologParser.ParenthesizedContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#parenthesized}.
	 * @param ctx the parse tree
	 */
	void exitParenthesized(@NotNull PrologParser.ParenthesizedContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#argseq}.
	 * @param ctx the parse tree
	 */
	void enterArgseq(@NotNull PrologParser.ArgseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#argseq}.
	 * @param ctx the parse tree
	 */
	void exitArgseq(@NotNull PrologParser.ArgseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#predname}.
	 * @param ctx the parse tree
	 */
	void enterPredname(@NotNull PrologParser.PrednameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#predname}.
	 * @param ctx the parse tree
	 */
	void exitPredname(@NotNull PrologParser.PrednameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrologParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull PrologParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrologParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull PrologParser.VariableContext ctx);
}