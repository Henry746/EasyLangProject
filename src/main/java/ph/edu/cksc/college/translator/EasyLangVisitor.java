// Generated from C:/Users/Owner/IdeaProjects/EasyLang\EasyLang.g4 by ANTLR 4.10.1
package ph.edu.cksc.college.translator;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EasyLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EasyLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(EasyLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(EasyLangParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(EasyLangParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#access}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess(EasyLangParser.AccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(EasyLangParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ASSIGN_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitASSIGN_STMT(EasyLangParser.ASSIGN_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VARDEC_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVARDEC_STMT(EasyLangParser.VARDEC_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IF_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIF_STMT(EasyLangParser.IF_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SWITCH_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSWITCH_STMT(EasyLangParser.SWITCH_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FOR_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFOR_STMT(EasyLangParser.FOR_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WHILE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWHILE_STMT(EasyLangParser.WHILE_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DOWHILE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDOWHILE_STMT(EasyLangParser.DOWHILE_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code READ_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitREAD_STMT(EasyLangParser.READ_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WRITE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWRITE_STMT(EasyLangParser.WRITE_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FUNC_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFUNC_STMT(EasyLangParser.FUNC_STMTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RET_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRET_STMT(EasyLangParser.RET_STMTContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(EasyLangParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLangParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(EasyLangParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INT_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINT_TYPE(EasyLangParser.INT_TYPEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code STRING_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSTRING_TYPE(EasyLangParser.STRING_TYPEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BOOL_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBOOL_TYPE(EasyLangParser.BOOL_TYPEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FLOAT_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFLOAT_TYPE(EasyLangParser.FLOAT_TYPEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FUNC_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFUNC_EXPR(EasyLangParser.FUNC_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MULT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMULT_EXPR(EasyLangParser.MULT_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code REL_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitREL_EXPR(EasyLangParser.REL_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SIGN_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSIGN_EXPR(EasyLangParser.SIGN_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOR_EXPR(EasyLangParser.OR_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CHAR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCHAR_EXPR(EasyLangParser.CHAR_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EQ_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEQ_EXPR(EasyLangParser.EQ_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code STRING_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSTRING_EXPR(EasyLangParser.STRING_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ADD_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitADD_EXPR(EasyLangParser.ADD_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AND_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAND_EXPR(EasyLangParser.AND_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINT_EXPR(EasyLangParser.INT_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EXPR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEXPR_EXPR(EasyLangParser.EXPR_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TERNARY_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTERNARY_EXPR(EasyLangParser.TERNARY_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FLOAT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFLOAT_EXPR(EasyLangParser.FLOAT_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ID_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitID_EXPR(EasyLangParser.ID_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HEXA_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHEXA_EXPR(EasyLangParser.HEXA_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DATE_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDATE_EXPR(EasyLangParser.DATE_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BOOLEAN_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBOOLEAN_EXPR(EasyLangParser.BOOLEAN_EXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ARRAY_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitARRAY_EXPR(EasyLangParser.ARRAY_EXPRContext ctx);
}