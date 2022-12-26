// Generated from /home/student/CPL_2022/Tema2/Tema2/src/cool/CoolParser.g4 by ANTLR 4.10.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(CoolParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(CoolParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterMemberDef(CoolParser.MemberDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitMemberDef(CoolParser.MemberDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterMethodDef(CoolParser.MethodDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodDef}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitMethodDef(CoolParser.MethodDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(CoolParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(CoolParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(CoolParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(CoolParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(CoolParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(CoolParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#caseOptions}.
	 * @param ctx the parse tree
	 */
	void enterCaseOptions(CoolParser.CaseOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#caseOptions}.
	 * @param ctx the parse tree
	 */
	void exitCaseOptions(CoolParser.CaseOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(CoolParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(CoolParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dispatch}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDispatch(CoolParser.DispatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dispatch}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDispatch(CoolParser.DispatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(CoolParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(CoolParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBool(CoolParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBool(CoolParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIsvoid(CoolParser.IsvoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIsvoid(CoolParser.IsvoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code other_dispatch}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOther_dispatch(CoolParser.Other_dispatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code other_dispatch}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOther_dispatch(CoolParser.Other_dispatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhile(CoolParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhile(CoolParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterType(CoolParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitType(CoolParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(CoolParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(CoolParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code neg}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNeg(CoolParser.NegContext ctx);
	/**
	 * Exit a parse tree produced by the {@code neg}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNeg(CoolParser.NegContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(CoolParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(CoolParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(CoolParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(CoolParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multDiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDiv(CoolParser.MultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multDiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDiv(CoolParser.MultDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLet(CoolParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLet(CoolParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CoolParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CoolParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CoolParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CoolParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign2}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign2(CoolParser.Assign2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assign2}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign2(CoolParser.Assign2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf(CoolParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf(CoolParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCase(CoolParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCase(CoolParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNew(CoolParser.NewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNew(CoolParser.NewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinus(CoolParser.PlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinus(CoolParser.PlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implicitDispatch}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterImplicitDispatch(CoolParser.ImplicitDispatchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implicitDispatch}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(CoolParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(CoolParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinus(CoolParser.UnaryMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinus(CoolParser.UnaryMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relational}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelational(CoolParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relational}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelational(CoolParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CoolParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CoolParser.AssignContext ctx);
}