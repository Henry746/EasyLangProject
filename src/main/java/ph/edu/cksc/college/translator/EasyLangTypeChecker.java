package ph.edu.cksc.college.translator;

import java.util.*;

public class EasyLangTypeChecker extends EasyLangBaseVisitor<Integer> {

    static final int TYPE_NA = 0;
    static final int TYPE_BOOLEAN = 1;
    static final int TYPE_INTEGER = 2;
    static final int TYPE_FLOAT = 3;
    static final int TYPE_CHAR = 4;
    static final int TYPE_STRING = 5;
    static final int TYPE_ARRAY = 6;

    private Map<String, Integer> memory = new HashMap<>();

    public EasyLangTypeChecker() {
        super();
    }

    /**
     * Get memory by checking local first before global
     *
     * @param id
     * @return
     */
    private Integer getMemory(String id) {
        Integer value = null;
        /*if (localMem != null) {
            value = localMem.get(id);
        }
        if (value == null) {*/
        value = memory.get(id);
        //}
        return value;
    }

    /**
     * For identifiers like variables
     */
    public Map<String, Integer> getMemory() {
        return memory;
    }

    /**
     * Put memory to local first if available or global
     *
     * @param id
     * @param value
     */
    private void putMemory(String id, Integer value) {
        /*if (localMem != null) {
            localMem.put(id, value);
            return;
        }*/
        memory.put(id, value);
    }

    private String typeToString(int type) {
        return switch (type) {
            case TYPE_BOOLEAN -> "boolean";
            case TYPE_CHAR -> "char";
            case TYPE_INTEGER -> "integer";
            case TYPE_FLOAT -> "float";
            case TYPE_STRING -> "string";
            case TYPE_ARRAY -> "array";
            default -> null;
        };
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitINT_TYPE(EasyLangParser.INT_TYPEContext ctx) {
        return TYPE_INTEGER;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitSTRING_TYPE(EasyLangParser.STRING_TYPEContext ctx) {
        return TYPE_STRING;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitBOOL_TYPE(EasyLangParser.BOOL_TYPEContext ctx) {
        return TYPE_BOOLEAN;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitFLOAT_TYPE(EasyLangParser.FLOAT_TYPEContext ctx) {
        return TYPE_FLOAT;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitVarDec(EasyLangParser.VarDecContext ctx) {
        String id = ctx.ID().getText();
        // TODO take care of Arrays of specific type and optional assignment
        Integer type;
        if (ctx.getChild(1).getText().charAt(0) == '[')
            type = TYPE_ARRAY;
        else
            type = visit(ctx.type());
        putMemory(id, type);
        return type;
    }

    public boolean typeInvalid = false;

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitAssign(EasyLangParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        List<EasyLangParser.ExprContext> exprs = ctx.expr();
        // with array index support
        Integer type = visit(ctx.expr(exprs.size() - 1));
        //System.out.println("assign type expr: " + type);
        if (exprs.size() == 1) {
            Integer existing = getMemory().getOrDefault(id, TYPE_NA);
            //System.out.println("existing type: " + existing);
            // TODO make it fine grain and allow promotion
            if (existing == TYPE_NA) {
                // non-declared variable with type, use the expression type
                putMemory(id, type);
            } else //noinspection StatementWithEmptyBody
                if (existing == TYPE_FLOAT && (type == TYPE_INTEGER || type == TYPE_CHAR)) {
                // possible by coercion/promotion
            } else if (!existing.equals(type)) {
                System.out.println("Invalid data type; can't assign to " +
                        typeToString(existing) + " the expression of type " +
                        typeToString(type));
                typeInvalid = true;
            }
        }
        return type;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitADD_EXPR(EasyLangParser.ADD_EXPRContext ctx) {
        Integer left = visit(ctx.expr().get(0));
        Integer right = visit(ctx.expr().get(1));
        return performAdd(left, right);
    }

    private Integer performAdd(Integer left, Integer right) {
        int result = 0;
        if (left == TYPE_INTEGER && right == TYPE_INTEGER) {
            result = TYPE_INTEGER;
        } else if (left == TYPE_FLOAT && (right == TYPE_INTEGER || right == TYPE_FLOAT) ||
                right == TYPE_FLOAT && left == TYPE_INTEGER) {
            result = TYPE_FLOAT;
        } else if (left == TYPE_STRING || right == TYPE_STRING) {
            result = TYPE_STRING;
        }
        return result;
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitEXPR_EXPR(EasyLangParser.EXPR_EXPRContext ctx) {
        return visit(ctx.expr());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitMULT_EXPR(EasyLangParser.MULT_EXPRContext ctx) {
        Integer left = visit(ctx.expr().get(0));
        Integer right = visit(ctx.expr().get(1));
        // TODO separate mult String with Int
        return performAdd(left, right);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitARRAY_EXPR(EasyLangParser.ARRAY_EXPRContext ctx) {
        // TODO implement array of specific type in the future
        return TYPE_ARRAY;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitSTRING_EXPR(EasyLangParser.STRING_EXPRContext ctx) {
        return TYPE_STRING;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitCHAR_EXPR(EasyLangParser.CHAR_EXPRContext ctx) {
        return TYPE_CHAR;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitBOOLEAN_EXPR(EasyLangParser.BOOLEAN_EXPRContext ctx) {
        return TYPE_BOOLEAN;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitINT_EXPR(EasyLangParser.INT_EXPRContext ctx) {
        return TYPE_INTEGER;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitFLOAT_EXPR(EasyLangParser.FLOAT_EXPRContext ctx) {
        return TYPE_FLOAT;
    }
}
