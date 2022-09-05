// Generated from C:/Users/Owner/IdeaProjects/EasyLang\EasyLang.g4 by ANTLR 4.10.1
package ph.edu.cksc.college.translator;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EasyLangParser}.
 */
public interface EasyLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(EasyLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(EasyLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(EasyLangParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(EasyLangParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(EasyLangParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(EasyLangParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#access}.
	 * @param ctx the parse tree
	 */
	void enterAccess(EasyLangParser.AccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#access}.
	 * @param ctx the parse tree
	 */
	void exitAccess(EasyLangParser.AccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(EasyLangParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(EasyLangParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ASSIGN_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterASSIGN_STMT(EasyLangParser.ASSIGN_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ASSIGN_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitASSIGN_STMT(EasyLangParser.ASSIGN_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VARDEC_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVARDEC_STMT(EasyLangParser.VARDEC_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VARDEC_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVARDEC_STMT(EasyLangParser.VARDEC_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IF_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIF_STMT(EasyLangParser.IF_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IF_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIF_STMT(EasyLangParser.IF_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SWITCH_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSWITCH_STMT(EasyLangParser.SWITCH_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SWITCH_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSWITCH_STMT(EasyLangParser.SWITCH_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FOR_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFOR_STMT(EasyLangParser.FOR_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FOR_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFOR_STMT(EasyLangParser.FOR_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WHILE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWHILE_STMT(EasyLangParser.WHILE_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WHILE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWHILE_STMT(EasyLangParser.WHILE_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DOWHILE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDOWHILE_STMT(EasyLangParser.DOWHILE_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DOWHILE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDOWHILE_STMT(EasyLangParser.DOWHILE_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code READ_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterREAD_STMT(EasyLangParser.READ_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code READ_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitREAD_STMT(EasyLangParser.READ_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WRITE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWRITE_STMT(EasyLangParser.WRITE_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WRITE_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWRITE_STMT(EasyLangParser.WRITE_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FUNC_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFUNC_STMT(EasyLangParser.FUNC_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FUNC_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFUNC_STMT(EasyLangParser.FUNC_STMTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RET_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRET_STMT(EasyLangParser.RET_STMTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RET_STMT}
	 * labeled alternative in {@link EasyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRET_STMT(EasyLangParser.RET_STMTContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(EasyLangParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(EasyLangParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLangParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(EasyLangParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLangParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(EasyLangParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INT_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterINT_TYPE(EasyLangParser.INT_TYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INT_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitINT_TYPE(EasyLangParser.INT_TYPEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code STRING_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterSTRING_TYPE(EasyLangParser.STRING_TYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code STRING_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitSTRING_TYPE(EasyLangParser.STRING_TYPEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BOOL_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBOOL_TYPE(EasyLangParser.BOOL_TYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BOOL_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBOOL_TYPE(EasyLangParser.BOOL_TYPEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FLOAT_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFLOAT_TYPE(EasyLangParser.FLOAT_TYPEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FLOAT_TYPE}
	 * labeled alternative in {@link EasyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFLOAT_TYPE(EasyLangParser.FLOAT_TYPEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FUNC_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFUNC_EXPR(EasyLangParser.FUNC_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FUNC_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFUNC_EXPR(EasyLangParser.FUNC_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MULT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMULT_EXPR(EasyLangParser.MULT_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MULT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMULT_EXPR(EasyLangParser.MULT_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code REL_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterREL_EXPR(EasyLangParser.REL_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code REL_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitREL_EXPR(EasyLangParser.REL_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SIGN_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSIGN_EXPR(EasyLangParser.SIGN_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SIGN_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSIGN_EXPR(EasyLangParser.SIGN_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOR_EXPR(EasyLangParser.OR_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOR_EXPR(EasyLangParser.OR_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CHAR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCHAR_EXPR(EasyLangParser.CHAR_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CHAR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCHAR_EXPR(EasyLangParser.CHAR_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EQ_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEQ_EXPR(EasyLangParser.EQ_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EQ_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEQ_EXPR(EasyLangParser.EQ_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code STRING_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSTRING_EXPR(EasyLangParser.STRING_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code STRING_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSTRING_EXPR(EasyLangParser.STRING_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ADD_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterADD_EXPR(EasyLangParser.ADD_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ADD_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitADD_EXPR(EasyLangParser.ADD_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AND_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAND_EXPR(EasyLangParser.AND_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AND_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAND_EXPR(EasyLangParser.AND_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterINT_EXPR(EasyLangParser.INT_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitINT_EXPR(EasyLangParser.INT_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EXPR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEXPR_EXPR(EasyLangParser.EXPR_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EXPR_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEXPR_EXPR(EasyLangParser.EXPR_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TERNARY_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTERNARY_EXPR(EasyLangParser.TERNARY_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TERNARY_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTERNARY_EXPR(EasyLangParser.TERNARY_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FLOAT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFLOAT_EXPR(EasyLangParser.FLOAT_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FLOAT_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFLOAT_EXPR(EasyLangParser.FLOAT_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ID_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterID_EXPR(EasyLangParser.ID_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ID_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitID_EXPR(EasyLangParser.ID_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HEXA_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterHEXA_EXPR(EasyLangParser.HEXA_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HEXA_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitHEXA_EXPR(EasyLangParser.HEXA_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DATE_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDATE_EXPR(EasyLangParser.DATE_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DATE_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDATE_EXPR(EasyLangParser.DATE_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BOOLEAN_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBOOLEAN_EXPR(EasyLangParser.BOOLEAN_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BOOLEAN_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBOOLEAN_EXPR(EasyLangParser.BOOLEAN_EXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ARRAY_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterARRAY_EXPR(EasyLangParser.ARRAY_EXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ARRAY_EXPR}
	 * labeled alternative in {@link EasyLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitARRAY_EXPR(EasyLangParser.ARRAY_EXPRContext ctx);
}