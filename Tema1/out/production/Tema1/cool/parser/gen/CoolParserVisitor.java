// Generated from /home/student/CPL_2022/Tema1/out/production/Tema1/cool/parser/CoolParser.g4 by ANTLR 4.10.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(CoolParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(CoolParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(CoolParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(CoolParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CoolParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(CoolParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinus(CoolParser.PlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(CoolParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multDiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDiv(CoolParser.MultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinus(CoolParser.UnaryMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relational}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(CoolParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CoolParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(CoolParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(CoolParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(CoolParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(CoolParser.AssignContext ctx);
}