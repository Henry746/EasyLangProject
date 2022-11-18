package ph.edu.cksc.college.translator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jomin.yu on 9/22/2017.
 */
public class InterpreterTest {

    EasyVisitor visitor;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        visitor = new EasyVisitor(printStream);
    }

    private void setInput(String input) {
        try {
            InputStream stream = new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8.name()));
            visitor.setInput(stream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Get a EasyLangParser token getter for the input String
     */
    public EasyLangParser getParser(String input) throws IOException {
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        CharStream charstream = CharStreams.fromStream(stream, StandardCharsets.UTF_8);
        EasyLangLexer lexer = new EasyLangLexer(charstream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new EasyLangParser(tokens);
    }

    @Test
    public void testInteger() throws IOException {
        EasyLangParser parser = getParser("143");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(143, result);
    }

    @Test
    public void testString() throws IOException {
        EasyLangParser parser = getParser("\"Hello\\t\\\"world\\\"\"");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof String);
        assertEquals("Hello\t\"world\"", result);
    }

    @Test
    public void testAssign() throws IOException {
        EasyLangParser parser = getParser("marvin=143");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        int value = (int) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals(143, value);

        parser = getParser("danielle=143+2");
        tree = parser.assign();
        visitor.visit(tree);
        value = (int) visitor.getMemory().getOrDefault("danielle", -1);
        assertEquals(145, value);
    }

    @Test
    public void testAssignPlus() throws IOException {
        visitor.getMemory().put("marvin", 143);
        EasyLangParser parser = getParser("marvin+=2");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        int value = (int) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals(145, value);
    }

    @Test
    public void testAssignMinus() throws IOException {
        visitor.getMemory().put("marvin", 143);
        EasyLangParser parser = getParser("marvin-=2");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        int value = (int) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals(141, value);
    }

    @Test
    public void testAssignMult() throws IOException {
        visitor.getMemory().put("marvin", 143);
        EasyLangParser parser = getParser("marvin*=10");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        int value = (int) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals(1430, value);
    }

    @Test
    public void testAssignDivide() throws IOException {
        visitor.getMemory().put("marvin", 143);
        EasyLangParser parser = getParser("marvin/=10");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        int value = (int) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals(14, value);
    }

    @Test
    public void testAssignModulus() throws IOException {
        visitor.getMemory().put("marvin", 143);
        EasyLangParser parser = getParser("marvin%=10");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        int value = (int) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals(3, value);
    }

    @Test
    public void testAssignConcat() throws IOException {
        visitor.getMemory().put("marvin", "marvelous");
        EasyLangParser parser = getParser("marvin+=2");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        String value = (String) visitor.getMemory().getOrDefault("marvin", -1);
        assertEquals("marvelous2", value);
    }

    @Test
    public void testAssignIndexedArrayPlus() throws IOException {
        List<Object> array = new ArrayList<>();
        array.add(143);
        array.add("string");
        visitor.getMemory().put("marvin", array);
        EasyLangParser parser = getParser("marvin[0]+=2");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        array = (List<Object>)  visitor.getMemory().get("marvin");
        int value = (int) array.get(0);
        assertEquals(145, value);
    }

    @Test
    public void testAssignIndexedArrayConcat() throws IOException {
        List<Object> array = new ArrayList<>();
        array.add(143);
        array.add("string");
        visitor.getMemory().put("marvin", array);
        EasyLangParser parser = getParser("marvin[1]+=2");
        ParseTree tree = parser.assign();
        visitor.visit(tree);
        array = (List<Object>)  visitor.getMemory().get("marvin");
        String value = (String) array.get(1);
        assertEquals("string2", value);
    }

    @Test
    public void testSignInteger() throws IOException {
        EasyLangParser parser = getParser("-143");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(-143, result);
    }

    @Test
    public void testSignFloat() throws IOException {
        EasyLangParser parser = getParser("-143.44");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Float);
        assertEquals(-143.44f, result);
    }

    @Test
    public void testSignMultipleFloat() throws IOException {
        EasyLangParser parser = getParser("----143.44");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Float);
        assertEquals(143.44f, result);
    }

    @Test
    public void testAddInteger() throws IOException {
        EasyLangParser parser = getParser("143+2");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(145, result);
    }

    @Test
    public void testSubtractInteger() throws IOException {
        EasyLangParser parser = getParser("143-2");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(141, result);
    }

    @Test
    public void testMultInteger() throws IOException {
        EasyLangParser parser = getParser("143*2");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(286, result);
    }

    @Test
    public void testDivideInteger() throws IOException {
        EasyLangParser parser = getParser("143/10");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(14, result);
    }

    @Test
    public void testModulusInteger() throws IOException {
        EasyLangParser parser = getParser("143%10");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Integer);
        assertEquals(3, result);
    }

    @Test
    public void testConcatenation() throws IOException {
        EasyLangParser parser = getParser("\"143\"+2");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof String);
        assertEquals("1432", result);
    }

    @Test
    public void testDuplicateString() throws IOException {
        EasyLangParser parser = getParser("\"Jomin\"*5");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof String);
        assertEquals("JominJominJominJominJomin", result);
    }

    @Test
    public void testGreaterThan() throws IOException {
        EasyLangParser parser = getParser("10>1");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertTrue((Boolean) result);

    }

    @Test
    public void testGreaterThanFalse() throws IOException {
        EasyLangParser parser = getParser("1>=10");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertTrue((Boolean) result);

        parser = getParser("1>=10");
        tree = parser.expr();
        result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertFalse((Boolean) result);
    }

    @Test
    public void testAnd() throws IOException {
        EasyLangParser parser = getParser("10>1 && 10<=20");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertTrue((Boolean) result);

    }

    @Test
    public void testAndFalse() throws IOException {
        EasyLangParser parser = getParser("1>=10 && 10<=20");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertFalse((Boolean) result);

    }

    //testOR and testOrFalse

    @Test
    public void testOr() throws IOException {
        EasyLangParser parser = getParser("1>=10 || 10<=20");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertTrue((Boolean) result);

    }

    @Test
    public void testOrFalse() throws IOException {
        EasyLangParser parser = getParser("1>=10 || 10>=20");
        ParseTree tree = parser.expr();
        Object result = visitor.visit(tree);
        assertTrue(result instanceof Boolean);
        assertFalse((Boolean) result);

    }

    @Test
    public void testOutput() throws IOException {
        EasyLangParser parser = getParser("output \"Jomin\"");
        ParseTree tree = parser.statement();
        visitor.visit(tree);
        String output = outputStream.toString(StandardCharsets.UTF_8);
        assertEquals("Jomin", output);
    }

    @Test
    public void testInput() throws IOException {
        EasyLangParser parser = getParser("input a");
        setInput("Jomin\n");    // new line is required for input
        ParseTree tree = parser.statement();
        visitor.visit(tree);
        String value = (String) visitor.getMemory().getOrDefault("a", "");
        assertEquals("Jomin", value);
    }

    @Test
    public void testForLoop() throws IOException {
        EasyLangParser parser = getParser("for i = 1 to 10 { output i + ',' }");
        ParseTree tree = parser.statement();
        visitor.visit(tree);
        String output = outputStream.toString(StandardCharsets.UTF_8);
        assertEquals("1,2,3,4,5,6,7,8,9,10,", output);
    }
}
