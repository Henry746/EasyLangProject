package ph.edu.cksc.college.translator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Get a EasyLangParser token getter for the input String
     */
    public EasyLangParser getParser (String input) throws IOException{
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        CharStream charstream = CharStreams.fromStream(stream, StandardCharsets.UTF_8);
        EasyLangLexer lexer = new EasyLangLexer(charstream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new EasyLangParser(tokens);
    }

    @Test
    public void testAssign() throws IOException {
        EasyLangParser parser = getParser("jomin=1");
        EasyLangParser.AssignContext tree = parser.assign();
        assertEquals(3, tree.getChildCount());
        // no need actually because it's already an assign tree
        assertEquals(EasyLangParser.RULE_assign, tree.getRuleIndex());
        // we cannot use getChild because only getText is available and no token info
        assertEquals(EasyLangLexer.ID, tree.ID().getSymbol().getType());
        assertEquals("jomin", tree.ID().getText());
        assertEquals("=", tree.getChild(1).getText());
        // System.out.println(tree.expr().getClass().getCanonicalName());
        assertEquals(tree.expr().size(), 1);
        EasyLangParser.INT_EXPRContext term = (EasyLangParser.INT_EXPRContext) tree.expr(0);
        assertEquals("1", term.getText());
    }

    @Test
    public void testAssignToIndexedArray() throws Exception {
        EasyLangParser parser = getParser("ar[1]=2");
        EasyLangParser.AssignContext tree = parser.assign();
        assertEquals(6, tree.getChildCount());
        // no need actually because it's already an assign tree
        assertEquals(EasyLangParser.RULE_assign, tree.getRuleIndex());
        // we cannot use getChild because only getText is available and no token info
        assertEquals(EasyLangLexer.ID, tree.ID().getSymbol().getType());
        assertEquals("ar", tree.ID().getText());
        assertEquals("[", tree.getChild(1).getText());
        assertEquals("]", tree.getChild(3).getText());
        assertEquals("=", tree.getChild(4).getText());
        // System.out.println(tree.expr().getClass().getCanonicalName());
        assertEquals(tree.expr().size(), 2);
        EasyLangParser.INT_EXPRContext term = (EasyLangParser.INT_EXPRContext) tree.expr(0);
        assertEquals("1", term.getText());
        term = (EasyLangParser.INT_EXPRContext) tree.expr(1);
        assertEquals("2", term.getText());
    }

    @Test
    public void testAssignIncrement() throws Exception {
        EasyLangParser parser = getParser("inc+=3");
        EasyLangParser.AssignContext tree = parser.assign();
        assertEquals(3, tree.getChildCount());
        // no need actually because it's already an assign tree
        assertEquals(EasyLangParser.RULE_assign, tree.getRuleIndex());
        // we cannot use getChild because only getText is available and no token info
        assertEquals(EasyLangLexer.ID, tree.ID().getSymbol().getType());
        assertEquals("inc", tree.ID().getText());
        assertEquals("+=", tree.getChild(1).getText());
        // System.out.println(tree.expr().getClass().getCanonicalName());
        assertEquals(tree.expr().size(), 1);
        EasyLangParser.INT_EXPRContext term = (EasyLangParser.INT_EXPRContext) tree.expr(0);
        assertEquals("3", term.getText());
    }

    @Test
    public void testAssignMultiply() throws Exception {
        EasyLangParser parser = getParser("mul*=4");
        EasyLangParser.AssignContext tree = parser.assign();
        assertEquals(3, tree.getChildCount());
        // no need actually because it's already an assign tree
        assertEquals(EasyLangParser.RULE_assign, tree.getRuleIndex());
        // we cannot use getChild because only getText is available and no token info
        assertEquals(EasyLangLexer.ID, tree.ID().getSymbol().getType());
        assertEquals("mul", tree.ID().getText());
        assertEquals("*=", tree.getChild(1).getText());
        // System.out.println(tree.expr().getClass().getCanonicalName());
        assertEquals(tree.expr().size(), 1);
        EasyLangParser.INT_EXPRContext term = (EasyLangParser.INT_EXPRContext) tree.expr(0);
        assertEquals("4", term.getText());
    }

    @Test
    public void testExpressionArray() throws IOException {
        EasyLangParser parser = getParser("{1,2}");
        EasyLangParser.ExprContext tree = parser.expr();
        assertEquals(5, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals("ARRAY_EXPRContext", tree.getClass().getSimpleName());
        EasyLangParser.ARRAY_EXPRContext array = (EasyLangParser.ARRAY_EXPRContext) tree;
        List<EasyLangParser.ExprContext> list = array.expr();
        assertEquals(2, list.size());
        EasyLangParser.INT_EXPRContext term = (EasyLangParser.INT_EXPRContext) array.expr(0);
        assertEquals(EasyLangLexer.INT, term.INT().getSymbol().getType());
        assertEquals("1", term.getText());
        term = (EasyLangParser.INT_EXPRContext) array.expr(1);
        assertEquals(EasyLangLexer.INT, term.INT().getSymbol().getType());
        assertEquals("2", term.getText());
    }
//Assignment
    @Test
    public void testIfNoElse() throws IOException {
        EasyLangParser parser = getParser("if jomin>1{a=1}");
        EasyLangParser.StatementContext tree = parser.statement();
        //System.out.println(tree.toStringTree());
        assertEquals(3, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals("IF_STMTContext", tree.getClass().getSimpleName());
        EasyLangParser.ExprContext expr = ((EasyLangParser.IF_STMTContext) tree).expr();
        assertEquals("REL_EXPRContext", expr.getClass().getSimpleName());
        assertEquals("jomin>1", expr.getText());

        List<EasyLangParser.StatementsContext> list = ((EasyLangParser.IF_STMTContext) tree).statements();
        assertEquals(1, list.size());
        assertEquals("{a=1}", list.get(0).getText());


    }

    @Test
    public void testSwitch() throws IOException {
        EasyLangParser parser = getParser("switch j{case 1:{output 2}}");
        EasyLangParser.StatementContext tree = parser.statement();
        //System.out.println(tree.toStringTree());
        assertEquals(8, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals("SWITCH_STMTContext", tree.getClass().getSimpleName());
        List<EasyLangParser.ExprContext> expr = ((EasyLangParser.SWITCH_STMTContext) tree).expr();
        assertEquals("ID_EXPRContext", expr.get(0).getClass().getSimpleName());
        assertEquals("j", expr.get(0).getText());

        EasyLangParser.ExprContext block = expr.get(1);
        assertEquals("INT_EXPRContext", block.getClass().getSimpleName());
        assertEquals("1", expr.get(1).getText());

        List<EasyLangParser.StatementsContext> stmts = ((EasyLangParser.SWITCH_STMTContext) tree).statements();
        assertEquals(1, stmts.size());
        assertEquals("{output2}", stmts.get(0).getText());
    }

    @Test
    public void testReadOne() throws IOException {
        EasyLangParser parser = getParser("input jomin");
        EasyLangParser.StatementContext tree = parser.statement();
        assertEquals(2, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals("READ_STMTContext", tree.getClass().getSimpleName());
        List<TerminalNode> list = ((EasyLangParser.READ_STMTContext) tree).ID();
        assertEquals(1, list.size());
        assertEquals(EasyLangLexer.ID, list.get(0).getSymbol().getType());
        assertEquals("jomin", list.get(0).getText());
    }

    @Test
    public void testReadTwo() throws IOException {
        EasyLangParser parser = getParser("input jomin, yu");
        EasyLangParser.StatementContext tree = parser.statement();
        assertEquals(4, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals("READ_STMTContext", tree.getClass().getSimpleName());
        List<TerminalNode> list = ((EasyLangParser.READ_STMTContext) tree).ID();
        assertEquals(2, list.size());
        assertEquals(EasyLangLexer.ID, list.get(0).getSymbol().getType());
        assertEquals("jomin", list.get(0).getText());
        assertEquals(EasyLangLexer.ID, list.get(1).getSymbol().getType());
        assertEquals("yu", list.get(1).getText());
    }
    //up to here
    @Test
    public void testWriteOne() throws IOException {
        EasyLangParser parser = getParser("output jomin");
        EasyLangParser.StatementContext tree = parser.statement();
        assertEquals(2, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals("WRITE_STMTContext", tree.getClass().getSimpleName());
        List<EasyLangParser.ExprContext> list = ((EasyLangParser.WRITE_STMTContext) tree).expr();
        assertEquals(1, list.size());

        assertEquals("ID_EXPRContext", list.get(0).getClass().getSimpleName());
        EasyLangParser.ID_EXPRContext term = (EasyLangParser.ID_EXPRContext) list.get(0);
        assertEquals("jomin", term.getText());
        assertEquals("WRITE_STMTContext", tree.getClass().getSimpleName());

    }

    @Test
    public void testWriteTwo() throws IOException {
        EasyLangParser parser = getParser("output jomin, 1");
        EasyLangParser.StatementContext tree = parser.statement();
        assertEquals(4, tree.getChildCount());
        assertEquals("WRITE_STMTContext", tree.getClass().getSimpleName());
        List<EasyLangParser.ExprContext> list = ((EasyLangParser.WRITE_STMTContext) tree).expr();
        assertEquals(2, list.size());

        assertEquals("ID_EXPRContext", list.get(0).getClass().getSimpleName());
        EasyLangParser.ID_EXPRContext term = (EasyLangParser.ID_EXPRContext) list.get(0);
        assertEquals("jomin", term.getText());
        assertEquals("INT_EXPRContext", list.get(1).getClass().getSimpleName());
        EasyLangParser.INT_EXPRContext term2 = (EasyLangParser.INT_EXPRContext) list.get(1);
        assertEquals("1", term2.getText());
    }

    @Test
    public void testExpressionAdd() throws IOException {
        // just expression via assign because of the int parameter for expression
        EasyLangParser parser = getParser("jomin+1");
        EasyLangParser.ExprContext tree = parser.expr();
        assertEquals(3, tree.getChildCount());
        assertEquals("ADD_EXPRContext", tree.getClass().getSimpleName());
        assertEquals("+", tree.getChild(1).getText());
        List<EasyLangParser.ExprContext> list = ((EasyLangParser.ADD_EXPRContext) tree).expr();
        assertEquals(2, list.size());
        TerminalNodeImpl term = (TerminalNodeImpl) list.get(0).getChild(0);
        assertEquals(EasyLangLexer.ID, term.symbol.getType());
        assertEquals("jomin", term.getText());
        term = (TerminalNodeImpl) list.get(1).getChild(0);
        assertEquals(EasyLangLexer.INT, term.symbol.getType());
        assertEquals("1", term.getText());
    }

    @Test
    public void testExpressionParenthesized() throws IOException {
        // just expression via assign because of the int parameter for expression
        EasyLangParser parser = getParser("(jomin+1)");
        EasyLangParser.ExprContext tree = parser.expr();
        assertEquals(3, tree.getChildCount());
        assertEquals("EXPR_EXPRContext", tree.getClass().getSimpleName());
        tree = ((EasyLangParser.EXPR_EXPRContext) tree).expr();
        assertEquals("jomin+1", tree.getText());
        assertEquals(3, tree.getChildCount());
        assertEquals("ADD_EXPRContext", tree.getClass().getSimpleName());
        // normally, you should no longer test the add expression
        // because we have a separate tester for that

    }


    @Test
    public void testExpressionChar() throws IOException {
        // just expression via assign because of the char parameter for expression
        EasyLangParser parser = getParser("'J'");
        EasyLangParser.ExprContext tree = parser.expr();
        assertEquals(1, tree.getChildCount());
        assertTrue(tree instanceof EasyLangParser.CHAR_EXPRContext);
        TerminalNodeImpl term = (TerminalNodeImpl) tree.getChild(0);
        assertEquals(EasyLangLexer.CHAR, term.symbol.getType());
        assertEquals("'J'", term.getText());
    }

    @Test
    public void testExpressionInt() throws IOException {
        // just expression via assign because of the int parameter for expression
        EasyLangParser parser = getParser("143");
        EasyLangParser.ExprContext tree = parser.expr();
        assertEquals(1, tree.getChildCount());
        assertEquals("INT_EXPRContext" ,tree.getClass().getSimpleName());
        TerminalNodeImpl term = (TerminalNodeImpl) tree.getChild(0);
        assertEquals(EasyLangLexer.INT, term.symbol.getType());
        assertEquals("143", term.getText());
    }

    @Test
    public void testExpressionFloat() throws IOException {
        // just expression via assign because of the float parameter for expression
        EasyLangParser parser = getParser("143.44");
        EasyLangParser.ExprContext tree = parser.expr();
        assertEquals(1, tree.getChildCount());
        assertEquals("FLOAT_EXPRContext" ,tree.getClass().getSimpleName());
        TerminalNodeImpl term = (TerminalNodeImpl) tree.getChild(0);
        assertEquals(EasyLangLexer.FLOAT, term.symbol.getType());
        assertEquals("143.44", term.getText());
    }

    @Test
    public void testProgram() throws IOException {
        EasyLangParser parser = getParser("program p{output 3}");
        EasyLangParser.ProgramContext tree = parser.program();
        //System.out.println(tree.toStringTree());
        assertEquals(3, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        assertEquals(EasyLangLexer.ID, tree.ID().getSymbol().getType());
        assertEquals("p", tree.ID().getSymbol().getText());
        List<EasyLangParser.StatementContext> list = tree.statements().statement();
        assertEquals(1, list.size());
        assertEquals("output3", list.get(0).getText());
    }

    @Test
    public void testStatements() throws IOException {
        EasyLangParser parser = getParser("{input a output a}");
        EasyLangParser.StatementsContext tree = parser.statements();
        //System.out.println(tree.toStringTree());
        assertEquals(4, tree.getChildCount());
        // we cannot use getChild because only getText is available and no token info
        List<EasyLangParser.StatementContext> list = tree.statement();
        assertEquals(2, list.size());
        assertEquals("inputa", list.get(0).getText());
        assertEquals("outputa", list.get(1).getText());
    }
}
