package ph.edu.cksc.college.translator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EasyLangInterpreter {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please specify the program source file.");
            return;
        }
        FileInputStream inputStream = new FileInputStream(args[0]);
        CharStream charStream = CharStreams.fromStream(inputStream, StandardCharsets.UTF_8);
        EasyLangLexer lexer = new EasyLangLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EasyLangParser parser = new EasyLangParser(tokens);
        ParseTree tree = parser.program();
        EasyLangTypeChecker typeChecker = new EasyLangTypeChecker();
        //typeChecker.visit(tree);
        if (!typeChecker.typeInvalid) {
            EasyVisitor visitor = new EasyVisitor();
            visitor.visit(tree);
        }
    }
}
