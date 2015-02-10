// Generated from Prolog.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PrologParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PrologVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PrologParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(@NotNull PrologParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(@NotNull PrologParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#toprule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToprule(@NotNull PrologParser.TopruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#plrule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlrule(@NotNull PrologParser.PlruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull PrologParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(@NotNull PrologParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull PrologParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#parenthesized}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesized(@NotNull PrologParser.ParenthesizedContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#argseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgseq(@NotNull PrologParser.ArgseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#predname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredname(@NotNull PrologParser.PrednameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrologParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(@NotNull PrologParser.VariableContext ctx);
}