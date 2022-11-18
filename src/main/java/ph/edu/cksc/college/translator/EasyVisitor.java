/*
 * Copyright (c) 2004-2021 Engr. Jomin N Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ph.edu.cksc.college.translator;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Mini/Simple/Matrix/Easy Language Interpreter formerly via ANTLR AST Walking
 * but now it is ANTLR 4.0 Visitor Pattern-based
 * <p>
 *
 * Date Created: September 2004
 * Date Modified: January/February 2016/October 2018/November 2021
 * Upgraded to use ANTLR V.3.0
 * January 2010
 * 		Code clean up
 * 		August 2010
 * 		Modified for new language
 * 15 August 2011, batch of Lester Hao and company
 * 		Modified for Unit testing, private walkers are made public
 * 17 August 2011
 * 		Added more tests and changed expr ARREXPR to ARRAY
 * 19 August 2011
 * 		Made output and input testable
 * 		Fix a forloop bug, before the increment you should lookup again counter
 * 		added also a fix for wrap around to negative when max is reached
 * 		added break for null causing assign and max
 * 12-16 September 2011
 * 		Added function call
 * 14 August to 11 September 2013
 * 		Upgraded to use ANTLR V.4.0
 * 		Implementation of ANTLR 4.0 Visitor Pattern-based interpreter
 * 1 January to February 2015
 * 		Minor upgrade
 * 18 January to Februabry 2016
 * 		No longer use Value but just Object return value
 * 4 September 2017 resued for Matrix Language
 * 31 August 2018 reused for EasyLang
 * 12 November 2021 updated EasyLang
 * 
 * Created by jomin.yu on 9/4/2017.
 */
public class EasyVisitor extends EasyLangBaseVisitor<Object> {

    private InputStream input = System.in;
    private PrintStream output = System.out;

    private Map<String, Object> memory = new HashMap<>();
    private Map<String, Object> localMem;
    private Stack<Map<String, Object>> stack = new Stack<>();

    public EasyVisitor() {
        super();
    }

    public EasyVisitor(PrintStream output) {
        super();
        this.output = output;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitProgram(EasyLangParser.ProgramContext ctx) {
        for (EasyLangParser.FunctionContext fctx : ctx.function()) {
            visit(fctx);
        }
        return visit(ctx.statements());
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFunction(EasyLangParser.FunctionContext ctx) {
        String id = ctx.ID(0).getText();
        memory.put(id, ctx);
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFUNC_STMT(EasyLangParser.FUNC_STMTContext ctx) {
        String id = ctx.ID().getText();
        List<EasyLangParser.ExprContext> pctx = ctx.expr();
        try {
            return funcCall(id, pctx);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Process function call
     * @param id
     * @param pctx
     * @return
     */
    public Object funcCall(String id, List<EasyLangParser.ExprContext> pctx) throws Exception {
        returnValue = null;
        EasyLangParser.FunctionContext ctx = (EasyLangParser.FunctionContext) memory.get(id);
        if (ctx == null){
            switch (id){
                case "capitalize":
                    return capitalize(pctx);
                case "read":
                    return read(pctx);
                case "write":
                    return write(pctx);
                case "grayscale":
                    return grayscale(pctx);
            }
            String message = "function" + id + "not defined";
            System.out.println(message);
            throw new Exception(message);
        }
        EasyLangParser.StatementsContext sctx = ctx.statements();
        List<TerminalNode> vctx = ctx.ID();
        List<Object> params = new ArrayList<>();
        // skip the first ID as it is the function name
        for (int i = 1; i < vctx.size(); i++) {
            TerminalNode node = vctx.get(i);
            String vid = node.getText();
            if (pctx.size() <= i - 1)
                throw new Exception("Incomplete parameters ");
            Object value = visit(pctx.get(i - 1));
            params.add(value);
        }
        stack.push(localMem);
        localMem = new HashMap<>();
        // skip the first ID as it is the function name
        for (int i = 1; i < vctx.size(); i++) {
            TerminalNode node = vctx.get(i);
            String vid = node.getText();
            Object value = params.get(i - 1);
            putMemory(vid, value);
            //System.out.println("Funct param " + vid + " " + value + " ..." + localMem.get(vid));
        }
        visit (sctx);
        localMem = stack.pop();
        return returnValue;
    }

    private Object capitalize(List<EasyLangParser.ExprContext> pctx) throws Exception {
        if(pctx.size() == 0)
            throw new Exception("capitalize: invalid number of parameters");
        Object value = visit(pctx.get(0));
        return value.toString().toUpperCase(Locale.ROOT);
    }

    private Object read(List<EasyLangParser.ExprContext> pctx) {
        return null;
    }

    private Object write(List<EasyLangParser.ExprContext> pctx) {
        return null;
    }

    private Object grayscale(List<EasyLangParser.ExprContext> pctx) {
        return null;
    }

    /**
     * Get memory by checking local first before global
     * @param id
     * @return
     */
    private Object getMemory(String id) {
        Object value = null;
        if (localMem != null) {
            value = localMem.get(id);
        }
        if (value == null) {
            value = memory.get(id);
        }
        return value;
    }

    /**
     * Put memory to local first if available or global
     * @param id
     * @param value
     */
    private void putMemory(String id, Object value) {
        if (localMem != null) {
            localMem.put(id, value);
            return;
        }
        memory.put(id, value);
    }

    // for the mean time it's global
    Object returnValue;

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFUNC_EXPR(EasyLangParser.FUNC_EXPRContext ctx) {
        String id = ctx.ID().getText();
        List<EasyLangParser.ExprContext> pctx = ctx.expr();
        try {
            return funcCall(id, pctx);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitRET_STMT(EasyLangParser.RET_STMTContext ctx) {
        Object value = visit(ctx.expr());
        returnValue = value;
        return value;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitWRITE_STMT(EasyLangParser.WRITE_STMTContext ctx) {
        StringBuilder builder = new StringBuilder();
        for (EasyLangParser.ExprContext item : ctx.expr()) {
            Object value = visit(item);
            if (value instanceof Date) {
                value = simpleDateFormat.format((Date) value);
            }
            builder.append(value);
        }
        output.print(builder);
        return null;
    }

    BufferedReader reader;

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitREAD_STMT(EasyLangParser.READ_STMTContext ctx) {
        Object value = null;
        List<TerminalNode> list = ctx.ID();
        if (reader == null)
            reader = new BufferedReader(new InputStreamReader(input));
        for (TerminalNode id : list) {
            String datastr = "";
            try {
                datastr = reader.readLine();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Object data = datastr;
            // consider first as string
            try { // try to convert to int
                data = Integer.parseInt(datastr, 10);
            } catch (Exception e) {
                try { // try to convert to double
                    data = Double.parseDouble(datastr);
                } catch (Exception ex) {
                }
            }
            value = data;
            putMemory(id.getText(), value);
            //System.err.print(value+",");
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitIF_STMT(EasyLangParser.IF_STMTContext ctx) {
        Object retval = null;
        Object condition = visit(ctx.expr());
        List<EasyLangParser.StatementsContext> statements = ctx.statements();
        boolean itstrue = false;
        if (condition instanceof Boolean)
            itstrue = (Boolean) condition;
        else if (condition instanceof Integer)
            itstrue = (Integer) condition != 0;
        else if (condition instanceof Float)
            itstrue = (Float) condition != 0;
        else if (condition instanceof String)
            itstrue = !((String) condition).isEmpty();
        if (itstrue) {
            retval = visit(statements.get(0));
        } else {
            if (statements.size() > 1)
                retval = visit(statements.get(1));
        }
        return retval;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitWHILE_STMT(EasyLangParser.WHILE_STMTContext ctx) {
        // condition
        EasyLangParser.ExprContext condition = ctx.expr();
        while ((Boolean) visit(condition)) {
            // statement portion
            visit(ctx.statements());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitDOWHILE_STMT(EasyLangParser.DOWHILE_STMTContext ctx) {
        // condition
        EasyLangParser.ExprContext condition = ctx.expr();
        do {
            // statement portion
            visit(ctx.statements());
        } while ((Boolean) visit(condition));
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFOR_STMT(EasyLangParser.FOR_STMTContext ctx) {
        Object assignval = visit(ctx.assign());
        String id = (String) assignval;
        Number counter = (Number) getMemory(id);
        Number max = (Number) visit(ctx.expr().get(0));
        Number step = 1;
        if (ctx.expr().size() > 1)
            step = (Number) visit(ctx.expr().get(1));
        // condition portion
        while (counter.floatValue() <= max.floatValue()) {
            // statement portion
            visit(ctx.statements());
            // increment portion
            counter = (Number) getMemory(id); // read again so that it can be changed inside the loop
            if (counter instanceof Integer && step instanceof Integer)
                counter = counter.intValue() + step.intValue();
            else
                counter = counter.floatValue() + step.floatValue();
            putMemory(id, counter);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitAssign(EasyLangParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        String op = ctx.getChild(ctx.getChildCount() - 2).getText();
        List<EasyLangParser.ExprContext> exprs = ctx.expr();
        // with array index support
        Object value = visit(ctx.expr(exprs.size() - 1));
        if (exprs.size() == 1) {
            if (op.length() > 1) {
                // TODO: implement modulus
                Object old = getMemory().getOrDefault(id, 0);
                char operation = op.charAt(0);
                switch (operation) {
                    case '+' -> value = performAdd(old, value, true);
                    case '-' -> value = performAdd(old, value, false);
                    case '*' -> value = performMult(old, value, OPERATOR_MULT);
                    case '/' -> value = performMult(old, value, OPERATOR_DIVISION);
                    case '%' -> value = performMult(old, value, OPERATOR_MODULUS);
                }
            }
            putMemory(id, value);
        } else {
            // += etc not yet implemented here
            List<Object> list = (List<Object>) getMemory(id);
            int index = 0;
            Object val;
            for (int i = 0; i < exprs.size() - 1; i++) {
                EasyLangParser.ExprContext item = exprs.get(i);
                index = (int) visit(item);
                val = list.get(index);     // zero based index
                if (val instanceof List && i < exprs.size() - 2)
                    list = (List<Object>) val;
            }

            if (op.length() > 1) {
                Object old = list.get(index);
                char operation = op.charAt(0);
                switch (operation) {
                    case '+' -> value = performAdd(old, value, true);
                    case '-' -> value = performAdd(old, value, false);
                    case '*' -> value = performMult(old, value, OPERATOR_MULT);
                    case '/' -> value = performMult(old, value, OPERATOR_DIVISION);
                    case '%' -> value = performMult(old, value, OPERATOR_MODULUS);
                }
            }
            list.set(index, value);
        }

        return id;    // return the id as String
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitID_EXPR(EasyLangParser.ID_EXPRContext ctx) {
        String id = ctx.ID().getText();
        Object value = getMemory(id);
        if (value == null) {
            throw new RuntimeException("no such variable: " + id);
        }
        // check if indexed and array
        if (value instanceof List && ctx.expr() != null) {
            List<Object> list = (List<Object>) value;
            List<EasyLangParser.ExprContext> indices = ctx.expr();
            if (indices.size() > 0) {
                // support multiple array index
                Object val = null;
                for (EasyLangParser.ExprContext item : indices) {
                    int index = (int) visit(item);
                    val = list.get(index);     // zero based index
                    if (val instanceof List)
                        list = (List<Object>) val;
                }
                return val;
            } else {
                return list;
            }
        }
        return value;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitSTRING_EXPR(EasyLangParser.STRING_EXPRContext ctx) {
        String str = ctx.getText();
        // strip quotes
        str = str.substring(1, str.length() - 1);
        str = str.replace("\\n", "\n");
        str = str.replace("\\t", "\t");
        str = str.replace("\\'", "'");
        str = str.replace("\\\"", "\"");
        str = str.replace("\\\\", "\\");
        return str;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitCHAR_EXPR(EasyLangParser.CHAR_EXPRContext ctx) {
        String str = ctx.getText();
        // strip quotes
        str = str.substring(1, str.length() - 1);
        str = str.replace("\\n", "\n");
        str = str.replace("\\t", "\t");
        str = str.replace("\\'", "'");
        str = str.replace("\\\\", "\\");
        return str;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitBOOLEAN_EXPR(EasyLangParser.BOOLEAN_EXPRContext ctx) {
        return Boolean.valueOf(ctx.getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitINT_EXPR(EasyLangParser.INT_EXPRContext ctx) {
        return Integer.valueOf(ctx.getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitFLOAT_EXPR(EasyLangParser.FLOAT_EXPRContext ctx) {
        return Float.valueOf(ctx.getText());
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M.d.yyyy");

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitDATE_EXPR(EasyLangParser.DATE_EXPRContext ctx) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(ctx.getText());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitEXPR_EXPR(EasyLangParser.EXPR_EXPRContext ctx) {
        Object value = visit(ctx.expr());
        boolean negative = ctx.getChild(0).getText().equals("-");
        if (negative) {
            if (value instanceof  Integer)
                value = (Integer)value * -1;
            else if (value instanceof Float)
                value = (Float)value * -1;
            else if (value instanceof Boolean)
                value = !(Boolean)value;
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public Object visitSIGN_EXPR(EasyLangParser.SIGN_EXPRContext ctx) {
        Object value = visit(ctx.expr());
        char sign = ctx.getChild(0).getText().charAt(0);
        if (sign == '-') {
            if (value instanceof Integer) {
                value = ((Integer) value) * -1;
            }else if (value instanceof Float){
                value = ((Float)value) *-1;
            }

            //TODO how about strings and other types
        }
        return value;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitMULT_EXPR(EasyLangParser.MULT_EXPRContext ctx) {
        Object left = visit(ctx.expr().get(0));
        Object right = visit(ctx.expr().get(1));
        char multop = ctx.getChild(1).getText().charAt(0);
        int op = switch (multop) {
            case '*' -> OPERATOR_MULT;
            case '/' -> OPERATOR_DIVISION;
            case '%' -> OPERATOR_MODULUS;
            default -> -1;
        };
        return performMult(left, right, op);
    }

    private static final int OPERATOR_MULT = 0;
    private static final int OPERATOR_DIVISION = 1;
    private static final int OPERATOR_MODULUS = 2;

    private Object performMult(Object left, Object right, int mult) {
        Object result = null;
        if (left instanceof Number && right instanceof Number) {
            if (left instanceof Float || right instanceof Float) {
                result = switch (mult) {
                    case OPERATOR_MULT -> ((Number) left).floatValue() * ((Number) right).floatValue();
                    case OPERATOR_DIVISION -> ((Number) left).floatValue() / ((Number) right).floatValue();
                    case OPERATOR_MODULUS -> ((Number) left).floatValue() % ((Number) right).floatValue();
                    default -> 0;
                };
            } else {
                result = switch (mult) {
                    case OPERATOR_MULT -> ((Number) left).intValue() * ((Number) right).intValue();
                    case OPERATOR_DIVISION -> ((Number) left).intValue() / ((Number) right).intValue();
                    case OPERATOR_MODULUS -> ((Number) left).intValue() % ((Number) right).intValue();
                    default -> 0;
                };
            }
        } else if (left instanceof String && right instanceof Integer && mult == OPERATOR_MULT) {
            // repeat the string int times
            StringBuilder s = new StringBuilder();
            String op1 = (String) left;
            int op2 = (Integer) right;
            for (int i = 0; i < op2; i++)
                s.append(op1);
            result = s.toString();
        } else if (left instanceof Integer && right instanceof String && mult == OPERATOR_MULT) {
            // repeat the string int times
            StringBuilder s = new StringBuilder();
            String op1 = (String) right;
            int op2 = (Integer) left;
            for (int i = 0; i < op2; i++)
                s.append(op1);
            result = s.toString();
        } else if (left instanceof List && right instanceof Number ||
                left instanceof Number && right instanceof List) {
            // scalar multiplication
            // create new list so that list1 will not be altered
            List<Object> list;
            Number scalar;
            if (left instanceof List) {
                list = (List<Object>) left;
                scalar = (Number) right;
            } else {
                list = (List<Object>) right;
                scalar = (Number) left;
            }
            List<Object> list1 = new ArrayList<>((List<Object>) list);
            multList(list1,scalar);
            result = list1;
        } else if (left instanceof List && right instanceof List) {
            List<Object> list1 = (List<Object>) left;
            List<Object> list2 = (List<Object>) right;
            // check for dimensions
            if (!(list1.get(0) instanceof List)) {
                output.println("Left matrix must be 2 dimensional");
                result = list1;
            } else if (!(list2.get(0) instanceof List)) {
                output.println("Right matrix must be 2 dimensional");
                result = list1;
            } else if (((List<Object>)list1.get(0)).size() != list2.size()) {
                output.println("Left matrix number columns must match the number of rows in Right matrix");
                result = list1;
            } else {
                result = multList(list1, list2);
            }
        }
        return result;
    }

    /**
     * Scalar matrix multiplication
     * @param left
     * @param scalar
     */
    private void multList(List<Object> left, Number scalar) {

        for (int i = 0; i < left.size(); i++) {
            Object leftItem = left.get(i);
            if (leftItem instanceof List) {
                multList((List<Object>) leftItem, scalar);
            } else if (leftItem instanceof String) {
                left.set(i, String.valueOf(leftItem) + String.valueOf(scalar));
            } else if (leftItem instanceof Integer && scalar instanceof Integer) {
                left.set(i, ((Integer) leftItem) * ((Integer) scalar));
            } else if (leftItem instanceof Float || scalar instanceof Float) {
                left.set(i, ((Number) leftItem).floatValue() * ((Number) scalar).floatValue());
            }
        }
    }

    /**
     * 2 Matrices multiplication
     * @param left
     * @param right
     */
    private List<Object> multList(List<Object> left, List<Object> right) {

        List<Object> list = new ArrayList<>();
        int rightCols = ((List<Object>) right.get(0)).size();
        for (int i = 0; i < left.size(); i++) {
            Object leftItem = left.get(i);
            if (!(leftItem instanceof List)) {
                output.println("Left matrix must be 2 dimensional");
                return list;
            }
            List<Object> leftList = (List<Object>) leftItem;
            List<Object> line =  new ArrayList<>();
            for (int k = 0; k < rightCols; k++) {
                Number sumprod = 0;
                for (int j = 0; j < leftList.size(); j++) {
                    Object item = leftList.get(j);
                    if (!(item instanceof Number)) {
                        output.println("Elements in left must be number only: " + item);
                        return list;
                    }
                    Object rightItem = right.get(j);
                    if (!(rightItem instanceof List)) {
                        output.println("Right matrix must be 2 dimensional");
                        return list;
                    }
                    List<Object> rightList = (List<Object>) rightItem;
                    Object mult = rightList.get(k);
                    if (!(mult instanceof Number)) {
                        output.println("Elements in right must be number only: " + mult);
                        return list;
                    }
                    Number result = 0;
                    if (item instanceof Integer && mult instanceof Integer) {
                        result = ((Integer) item) * ((Integer) mult);
                    } else if (item instanceof Float || mult instanceof Float) {
                        result = ((Number) item).floatValue() * ((Number) mult).floatValue();
                    }
                    if (sumprod instanceof Integer && result instanceof Integer)
                        sumprod = ((Integer) sumprod) + ((Integer) result);
                    else
                        sumprod = sumprod.floatValue() + result.floatValue();
                }
                line.add(sumprod);
            }
            list.add(line);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitADD_EXPR(EasyLangParser.ADD_EXPRContext ctx) {
        Object left = visit(ctx.expr().get(0));
        Object right = visit(ctx.expr().get(1));
        boolean add = ctx.getChild(1).getText().equals("+");
        return performAdd(left, right, add);
    }

    private Object performAdd(Object left, Object right, boolean add) {
        Object result = null;
        if (left instanceof Number && right instanceof Number) {
            if (left instanceof Float || right instanceof Float) {
                if (add)
                    result = ((Number) left).floatValue() + ((Number) right).floatValue();
                else
                    result = ((Number) left).floatValue() - ((Number) right).floatValue();
            } else {
                if (add)
                    result = ((Number) left).intValue() + ((Number) right).intValue();
                else
                    result = ((Number) left).intValue() - ((Number) right).intValue();
            }
        } else if (left instanceof Date && right instanceof Integer) {
            // add number of days to date
            Date date = (Date) left;
            int days = (Integer) right;
            if (!add)
                days = -1 * days;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            result = calendar.getTime();
        } else if (left instanceof String || right instanceof String) {
            result = String.valueOf(left) + right;
        } else if (left instanceof List && right instanceof List) {
            // create new list so that list1 will not be altered
            List<Object> list1 = new ArrayList<>((List<Object>) left);
            List<Object> list2 = (List<Object>) right;
            addList(list1, list2, add);
            result = list1;
        }
        return result;
    }

    /**
     * Add/subtract right to/from left
     * @param left
     * @param right
     */
    public void addList(List<Object> left, List<Object> right, boolean add) {
        if (left.size() != right.size()) {
            output.println("Arrays must be of the same size");
            return;
        }
        for (int i = 0; i < left.size(); i++) {
            Object leftItem = left.get(i);
            Object rightItem = right.get(i);
            if (leftItem instanceof List && rightItem instanceof List) {
                addList((List<Object>) leftItem, (List<Object>) rightItem, add);
            } else if (leftItem instanceof List || rightItem instanceof List) {
                output.println("Array item must be of the same type");
                return;
            } else if (leftItem instanceof String || rightItem instanceof String) {
                left.set(i, String.valueOf(leftItem) + String.valueOf(rightItem));
            } else if (leftItem instanceof Integer && rightItem instanceof Integer) {
                left.set(i, ((Integer) leftItem) + ((Integer) rightItem) * (add ? 1 : -1));
            } else if (leftItem instanceof Float || rightItem instanceof Float) {
                left.set(i, ((Number) leftItem).floatValue() + ((Number) rightItem).floatValue() * (add ? 1 : -1));
            }
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitREL_EXPR(EasyLangParser.REL_EXPRContext ctx) {
        List<EasyLangParser.ExprContext> operands = ctx.expr();
        Object left = visit(operands.get(0));
        Object right = visit(operands.get(1));
        String opr = ctx.getChild(1).getText();
        if (left instanceof Number && right instanceof Number) {
            float op1 = ((Number) left).floatValue();
            float op2 = ((Number) right).floatValue();
            return switch (opr) {
                case "<" -> op1 < op2;
                case "<=" -> op1 <= op2;
                case ">" -> op1 > op2;
                default -> op1 >= op2;
            };
        } else if (left instanceof String && right instanceof String) {
            String op1 = (String) left;
            String op2 = (String) right;
            int compare = op1.compareTo(op2);
            return switch (opr) {
                case "<" -> compare < 0;
                case "<=" -> compare <= 0;
                case ">" -> compare > 0;
                default -> compare >= 0;
            };
        } else if (left instanceof Boolean && right instanceof Boolean) {
            // compare boolean
            int op1 = (Boolean) left ? 1 : 0;
            int op2 = (Boolean) right ? 1 : 0;
            return switch (opr) {
                case "<" -> op1 < op2;
                case "<=" -> op1 <= op2;
                case ">" -> op1 > op2;
                default -> op1 >= op2;
            };
        } else if (left instanceof Date && right instanceof Date) {
            Date op1 = (Date) left;
            Date op2 = (Date) right;
            int compare = op1.compareTo(op2);
            return switch (opr) {
                case "<" -> compare < 0;
                case "<=" -> compare <= 0;
                case ">" -> compare > 0;
                default -> compare >= 0;
            };
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Object visitEQ_EXPR(EasyLangParser.EQ_EXPRContext ctx) {
        List<EasyLangParser.ExprContext> operands = ctx.expr();
        Object left = visit(operands.get(0));
        Object right = visit(operands.get(1));
        boolean equal = ctx.getChild(1).getText().equals("=");
        if (left instanceof Number && right instanceof Number) {
            float op1 = ((Number) left).floatValue();
            float op2 = ((Number) right).floatValue();
            if (equal)
                return op1 == op2;
            else
                return op1 != op2;
        } else if (left instanceof String && right instanceof String) {
            String op1 = (String) left;
            String op2 = (String) right;
            if (equal)
                return op1.equals(op2);
            else
                return !op1.equals(op2);
        } else if (left instanceof Boolean && right instanceof Boolean) {
            // compare boolean
            boolean op1 = (Boolean) left;
            boolean op2 = (Boolean) right;
            if (equal)
                return op1 == op2;
            else
                return op1 != op2;
        } else if (left instanceof Date && right instanceof Date) {
            if (equal)
                return left.equals(right);
            else
                return !left.equals(right);
        } /*else if (left.isSet() && right.isSet()) {
            Set<Value> set1 = left.asSet();
            Set<Value> set2 = right.asSet();
            if (equal)
                return new Value(set1.equals(set2));
            else
                return new Value(!set1.equals(set2));
        }*/
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitAND_EXPR(EasyLangParser.AND_EXPRContext ctx) {

        List<EasyLangParser.ExprContext> operands = ctx.expr();
        Object left = visit(operands.get(0));
        // short circuit logic
        if (left instanceof Boolean && ((Boolean)left) == false)
            return left;
        Object right = visit(operands.get(1));
        if (left instanceof Boolean && right instanceof Boolean) {
            return right;
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitOR_EXPR(EasyLangParser.OR_EXPRContext ctx) {

        List<EasyLangParser.ExprContext> operands = ctx.expr();
        Object left = visit(operands.get(0));
        // short circuit logic
        if (left instanceof Boolean && ((Boolean)left) == true)
            return left;
        Object right = visit(operands.get(1));
        if (left instanceof Boolean && right instanceof Boolean) {
            return right;
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitARRAY_EXPR(EasyLangParser.ARRAY_EXPRContext ctx) {
        List<EasyLangParser.ExprContext> list = ctx.expr();
        List<Object> array = new ArrayList<>();
        for (EasyLangParser.ExprContext item : list) {
            array.add(visit(item));
        }
        return array;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitTERNARY_EXPR(EasyLangParser.TERNARY_EXPRContext ctx) {

        List<EasyLangParser.ExprContext> operands = ctx.expr();
        Object condition = visit(operands.get(0));
        if (condition instanceof Boolean) {
            if ((Boolean) condition)
                return visit(operands.get(1));
            return visit(operands.get(2));
        }
        return visitChildren(ctx);
    }

    /**
     * For identifiers like variables
     */
    public Map<String, Object> getMemory() {
        return memory;
    }

    public void setMemory(Map<String, Object> memory) {
        this.memory = memory;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }
}
