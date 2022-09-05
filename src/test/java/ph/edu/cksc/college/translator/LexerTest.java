package ph.edu.cksc.college.translator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class LexerTest {

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Get a EasyLangLexer token getter for the input String
     */
    public Token getLexerToken (String input) throws IOException {
        return getLexerToken(input, 0);
    }
    public Token getLexerToken (String input, int position) throws IOException {
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        CharStream charStream = CharStreams.fromStream(stream, StandardCharsets.UTF_8);
        EasyLangLexer lexer = new EasyLangLexer(charStream);
        Token token;
        int count = 0;
        do {
            token = lexer.nextToken();
            count++;
        } while (count <= position);
        return token;
    }
    @Test
    public void testId() throws IOException {
        Token token = getLexerToken("a_1$");
        assertEquals(EasyLangLexer.ID, token.getType());
        assertEquals("a_1", token.getText());
    }
    @Test
    public void testNotId() throws IOException {
        Token token = getLexerToken("1ab");
        assertNotSame(EasyLangLexer.ID, token.getType());
        assertEquals("1", token.getText());
        assertEquals(EasyLangLexer.INT, token.getType());
    }
    @Test
    public void testBooleanTrue() throws IOException {
        Token token = getLexerToken("true");
        assertEquals(EasyLangLexer.BOOLEAN, token.getType());
        assertEquals("true", token.getText());
    }
    @Test
    public void testBooleanFalse() throws IOException {
        Token token = getLexerToken("false");
        assertEquals(EasyLangLexer.BOOLEAN, token.getType());
        assertEquals("false", token.getText());
    }
    @Test
    public void testInteger() throws IOException {
        Token token = getLexerToken("143s");
        assertEquals(EasyLangLexer.INT, token.getType());
        assertEquals("143", token.getText());
    }
	@Test
	public void testHexa() throws IOException {
		Token token = getLexerToken("0xdea4");
		assertEquals(EasyLangLexer.HEXA, token.getType());
		assertEquals("0xdea4", token.getText());
	}
    @Test
    public void testHexaShortest() throws IOException {
        Token token = getLexerToken("0xf");
        assertEquals(EasyLangLexer.HEXA, token.getType());
        assertEquals("0xf", token.getText());
    }
    @Test
    public void testHexaLongest() throws IOException {
        Token token = getLexerToken("0x12345678");
        assertEquals(EasyLangLexer.HEXA, token.getType());
        assertEquals("0x12345678", token.getText());
    }
    @Test
    public void testHexaLongestOver() throws IOException {
        String input = "0x1234567890abcdef";
        Token token = getLexerToken(input);
        assertEquals(EasyLangLexer.HEXA, token.getType());
        assertEquals("0x12345678", token.getText());
        token = getLexerToken(input, 1);
        assertEquals(EasyLangLexer.INT, token.getType());
        assertEquals("90", token.getText());
        token = getLexerToken(input, 2);
        assertEquals(EasyLangLexer.ID, token.getType());
        assertEquals("abcdef", token.getText());
    }
    @Test
    public void testFloatEndsDot() throws IOException {
        Token token = getLexerToken("1.");
        assertEquals(EasyLangLexer.FLOAT, token.getType());
        assertEquals("1.", token.getText());
    }
    @Test
    public void testFloatWithFractionalPart() throws Exception {
        Token token = getLexerToken("1.2");
        assertEquals(EasyLangLexer.FLOAT, token.getType());
        assertEquals("1.2", token.getText());
    }
    @Test
    public void testFloatWithStartDot() throws Exception {
        Token token = getLexerToken(".2");
        assertEquals(EasyLangLexer.FLOAT, token.getType());
        assertEquals(".2", token.getText());
    }
    @Test
    public void testFloatWithESmallNoSign() throws Exception {
        Token token = getLexerToken("1.2e1");
        assertEquals(EasyLangLexer.FLOAT, token.getType());
        assertEquals("1.2e1", token.getText());
    }
    @Test
    public void testFloatWithEBigNegative() throws Exception {
        Token token = getLexerToken(".2E-8");
        assertEquals(EasyLangLexer.FLOAT, token.getType());
        assertEquals(".2E-8", token.getText());
    }
    @Test
    public void testFloatWithESmallPositive() throws Exception {
        Token token = getLexerToken("1.2e+10");
        assertEquals(EasyLangLexer.FLOAT, token.getType());
        assertEquals("1.2e+10", token.getText());
    }
    @Test
    public void testDate() throws Exception {
        Token token = getLexerToken("2.29.22");
        assertEquals(EasyLangLexer.DATE, token.getType());
        assertEquals("2.29.22", token.getText());
    }
    @Test
    public void testCharRegular() throws Exception {
        Token token = getLexerToken("'c'");
        assertEquals(EasyLangLexer.CHAR, token.getType());
        assertEquals("'c'", token.getText());
    }
    @Test
    public void testCharSingleQuote() throws Exception {
        Token token = getLexerToken("'\\''");
        assertEquals(EasyLangLexer.CHAR, token.getType());
        assertEquals("'\\''", token.getText());
    }
    @Test
    public void testCharBackSlash() throws Exception {
        Token token = getLexerToken("'\\\\'");
        assertEquals(EasyLangLexer.CHAR, token.getType());
        assertEquals("'\\\\'", token.getText());
    }
    @Test
    public void testCharTab() throws Exception {
        Token token = getLexerToken("'\\t'");
        assertEquals(EasyLangLexer.CHAR, token.getType());
        assertEquals("'\\t'", token.getText());
    }
    @Test
    public void testStringEmpty() throws Exception {
        Token token = getLexerToken("\"\"");
        assertEquals(EasyLangLexer.STRING, token.getType());
        assertEquals("\"\"", token.getText());
    }
    @Test
    public void testStringRegular() throws Exception {
        Token token = getLexerToken("\"abc\"");
        assertEquals(EasyLangLexer.STRING, token.getType());
        assertEquals("\"abc\"", token.getText());
    }
    @Test
    public void testStringDoubleQuote() throws Exception {
        Token token = getLexerToken("\"\\\"Hello\\\"\"");
        assertEquals(EasyLangLexer.STRING, token.getType());
        assertEquals("\"\\\"Hello\\\"\"", token.getText());
    }
    @Test
    public void testStringBackSlash() throws Exception {
        Token token = getLexerToken("\"\\\\\"");
        assertEquals(EasyLangLexer.STRING, token.getType());
        assertEquals("\"\\\\\"", token.getText());
    }
    @Test
    public void testStringTab() throws Exception {
        Token token = getLexerToken("\"\\t\"");
        assertEquals(EasyLangLexer.STRING, token.getType());
        assertEquals("\"\\t\"", token.getText());
    }
}
