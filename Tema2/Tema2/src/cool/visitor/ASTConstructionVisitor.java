package cool.visitor;

import cool.ast.*;
import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

import java.util.stream.Collectors;

public class ASTConstructionVisitor extends CoolParserBaseVisitor<ASTNode> {
 @Override
 public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
  var classList = ctx.class_definitions
          .stream()
          .map(node -> (ASTclassDef)visitClassDef(node))
          .collect(Collectors.toList());
  return new Prog(ctx.start, classList, ctx);
 }
 @Override
 public ASTNode visitClassDef(CoolParser.ClassDefContext ctx) {
  var content = ctx.content
          .stream()
          .map(instr -> (Instruction)visit(instr))
          .collect(Collectors.toList());

  return new ASTclassNode(ctx.start, ctx.name, ctx.name2, content, ctx);
 }

 @Override
 public ASTNode visitMemberDef(CoolParser.MemberDefContext ctx) {
  var initValue = ctx.init == null ? null : (Expression)visit(ctx.init);
  return new ASTclassMemberDef(ctx.start, ctx.name, ctx.type, initValue, ctx);
 }
 @Override
 public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
  var initValue = ctx.value == null ? null : (Expression)visit(ctx.value);
  return new VarDef(ctx.type,ctx.name, initValue, ctx.start, ctx);
 }
 @Override
 public ASTNode visitMethodDef(CoolParser.MethodDefContext ctx) {
  var params = ctx.params
          .stream()
          .map(node -> (Formal)visitFormal(node))
          .collect(Collectors.toList());
  var body = ctx.body
          .stream()
          .map(node -> (Expression)node.accept(this))
          .collect(Collectors.toList());
  //  System.out.println("METH");
  return new ASTmethodDef(ctx.start, ctx.returnType, ctx.name, params, body, ctx);
 }

 @Override
 public ASTNode visitInt(CoolParser.IntContext ctx) {
  return new Int(ctx.start, ctx.INT().getSymbol(), ctx);
 }
 @Override
 public ASTNode visitId(CoolParser.IdContext ctx) {
  return new Id(ctx.start, ctx.ID().getSymbol(), ctx);
 }
 @Override
 public ASTNode visitFormal(CoolParser.FormalContext ctx) {

  return new Formal(ctx.start, ctx.type, ctx.name, ctx);
 }
 @Override
 public ASTNode visitString(CoolParser.StringContext ctx) {
  return new ASTString(ctx.start, ctx.STRING().getSymbol(), ctx);
 }
 @Override
 public ASTNode visitBool(CoolParser.BoolContext ctx) {
  return new Bool(ctx.start, ctx.BOOL().getSymbol(), ctx);
 }

 //Arithmetic
 @Override
 public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {

  var left = ctx.left == null ? null : (Expression)visit(ctx.left);
  var right = ctx.right == null ? null : (Expression)visit(ctx.right);

  if(ctx.op.getText().equals("+")== true)
   return new Plus(ctx.start, left, right, ctx);
  else return new Minus(ctx.start, left, right, ctx);
 }

 @Override
 public ASTNode visitMultDiv(CoolParser.MultDivContext ctx) {
  var left = ctx.left == null ? null : (Expression)visit(ctx.left);
  var right = ctx.right == null ? null : (Expression)visit(ctx.right);

  if(ctx.op.getText().equals("*")== true) {

   return new Mult(ctx.start, left, right, ctx);
  }
  else return new Div(ctx.start, left, right, ctx);
 }
 public ASTNode visitParen(CoolParser.ParenContext ctx) {
  var expr = ctx.e == null ? null : (Expression)visit(ctx.e);
  return new Paren(ctx.start, expr, ctx);
 }
 @Override
 public ASTNode visitNeg(CoolParser.NegContext ctx) {
  var expr = ctx.e == null ? null : (Expression)visit(ctx.e);
  return new Negation(ctx.start, ctx.op, expr, ctx);
 }
 @Override
 public ASTNode visitNot(CoolParser.NotContext ctx) {
  var expr = ctx.e == null ? null : (Expression)visit(ctx.e);
  return new Not(ctx.start, ctx.op, expr, ctx);
 }
 @Override
 public ASTNode visitRelational(CoolParser.RelationalContext ctx) {
  // System.out.println("rel");
  var left = ctx.left == null ? null : (Expression)visit(ctx.left);
  var right = ctx.right == null ? null : (Expression)visit(ctx.right);
  return new ASTRelational(ctx.start, ctx.op, left, right, ctx);
 }
 @Override
 public ASTNode visitAssign(CoolParser.AssignContext ctx) {
  var name = ctx.name == null ? null : (Expression)visit(ctx.name);
  var e = ctx.e == null ? null : (Expression)visit(ctx.e);
  return new Assign(ctx.start, name, e, ctx);
 }
 @Override
 public ASTNode visitAssign2(CoolParser.Assign2Context ctx) {
  var name = ctx.name == null ? null : (Expression)visit(ctx.name);
  var e = ctx.e == null ? null : (Expression)visit(ctx.e);
  return new Assign2(ctx.start, name, e, ctx);
 }
 @Override
 public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
  var expr = ctx.e == null? null : (Expression)visit(ctx.e);
  return new IsVoid(ctx.start, expr, ctx);
 }
 @Override
 public ASTNode visitNew(CoolParser.NewContext ctx) {
  return new New(ctx.start, ctx.TYPE().getSymbol(), ctx);
 }
 @Override
 public ASTNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
  var args = ctx.args
          .stream()
          .map(node -> (Expression)visit(node))
          .collect(Collectors.toList());
  return new ImplicitDispatch(ctx.start, ctx.name, args, ctx);
 }
 @Override
 public ASTNode visitFuncCall(CoolParser.FuncCallContext ctx) {
  var args = ctx.args
          .stream()
          .map(node -> (Expression)visit(node))
          .collect(Collectors.toList());
  return new FuncCall(ctx.start, ctx.name, args, ctx);
 }
 @Override
 public ASTNode visitIf(CoolParser.IfContext ctx) {
  var cond = ctx.cond == null ? null : (Expression)visit(ctx.cond);
  var thenBranch = ctx.thenBranch == null ? null : (Expression)visit(ctx.thenBranch);
  var elseBranch = ctx.elseBranch == null ? null : (Expression)visit(ctx.elseBranch);
  return new If(ctx.start, cond, thenBranch, elseBranch, ctx);
 }
 @Override
 public ASTNode visitWhile(CoolParser.WhileContext ctx) {
  var cond = ctx.cond == null ? null : (Expression)visit(ctx.cond);
  var body = ctx.body == null ? null : (Expression)visit(ctx.body);
  return new While(ctx.start, cond, body, ctx);

 }
 @Override
 public ASTNode visitLet(CoolParser.LetContext ctx) {
  var members = ctx.members
          .stream()
          .map(member -> (VarDef)visitVarDef(member))
          .collect(Collectors.toList());
  var body = ctx.body == null ? null : (Expression)visit(ctx.body);
  return new Let(ctx.start, members,body, ctx);
 }
 @Override
 public ASTNode visitCase(CoolParser.CaseContext ctx) {
  var name = ctx.name == null ? null : (Expression)visit(ctx.name);
  var options = ctx.options
          .stream()
          .map(member -> (CaseOption)visitCaseOptions(member))
          .collect(Collectors.toList());
  return new Case(ctx.start, name, options, ctx);
 }
 @Override
 public ASTNode visitCaseOptions(CoolParser.CaseOptionsContext ctx) {
  var value = ctx.value == null ? null : (Expression)visit(ctx.value);
  return new CaseOption(ctx.start, ctx.name, ctx.type, value, ctx);
 }
 @Override
 public ASTNode visitBlock(CoolParser.BlockContext ctx) {
  var st = ctx.statements
          .stream()
          .map(statement -> (Expression)visit(statement))
          .collect(Collectors.toList());
  return new Block(ctx.start, st, ctx);
 }
 @Override
 public ASTNode visitType(CoolParser.TypeContext ctx) {
  return new Type(ctx.start, ctx);
 }
 @Override
 public ASTNode visitDispatch(CoolParser.DispatchContext ctx) {
  var value = ctx.left == null ? null : (Expression)visit(ctx.left);
  var call = (FuncCall)visit(ctx.right);
  return new Dispatch(ctx.start, value, call, ctx);
 }
 @Override
 public ASTNode visitOther_dispatch(CoolParser.Other_dispatchContext ctx) {
  var value = ctx.left == null ? null : (Expression)visit(ctx.left);
  var call = (FuncCall)visit(ctx.rightt);
  var type = ctx.TYPE().getSymbol();
  return new OtherDispatch(ctx.start, value, type, call, ctx);
 }
}
