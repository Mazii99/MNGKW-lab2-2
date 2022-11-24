import java.util.Stack;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.Math;


public class MainListen extends CalculatorBaseListener {

    private final Stack<Double> stack = new Stack<>();

    public Double getResult() {
        return stack.peek();
    }

@Override
public void exitExpression(CalculatorParser.ExpressionContext ctx) {
    if(ctx.multdivexpr().size()!=1){
        double right = stack.pop();
        for(int i = ctx.getChildCount()-2; i >= 1; i = i - 2) {
            double left = stack.pop();
            if (symbolEquals(ctx.getChild(i), CalculatorParser.PLUS)) {
                right = left + right;
            } else {
                right = left - right;
            }
        }
        stack.push(right);
    }

}
@Override
public void exitMultdivexpression(CalculatorParser.MultdivexpressionContext ctx) {
    if(ctx.powexpr().size()!=1){
        double right = stack.pop();
        for(int i = ctx.getChildCount()-2; i >= 1; i = i - 2) {
            double left = stack.pop();
            if (symbolEquals(ctx.getChild(i), CalculatorParser.TIMES)) {
                right = left * right;
            } else {
                right = left / right;
            }
        }
        stack.push(right);
    }

}
@Override
public void exitPowerexpression(CalculatorParser.PowerexpressionContext ctx) {
    if(ctx.logexpr().size()!=1){
        double right = Double.valueOf(stack.pop());
        for(int i = ctx.getChildCount()-2; i >= 1; i = i - 2){
            double left = Double.valueOf(stack.pop());
            if (symbolEquals(ctx.getChild(i), CalculatorParser.POW)){
                right = Math.pow(left,right);
            }else{
                right = Math.pow(left,1.0/right);
            }
        }
        stack.push(right);
    }


}
    private boolean symbolEquals(ParseTree child, int symbol) {
        return ((TerminalNode) child).getSymbol().getType() == symbol;
    }
@Override public void exitLogexpression(CalculatorParser.LogexpressionContext ctx) {
        if(ctx.LOG() != null){
            stack.push(Math.log(stack.pop()));
        }
}
@Override public void exitNumexpression(CalculatorParser.NumexpressionContext ctx) {
     if(ctx.MINUS() != null){
         stack.push(-1 * Double.valueOf(ctx.NUMBER().getText()));
     }else{
         stack.push(Double.valueOf(ctx.NUMBER().getText()));
     }
}
public static void main(String[] args) throws Exception {
        CharStream charStreams = CharStreams.fromFileName("C:\\Users\\Mazii\\IdeaProjects\\antrl\\src\\example.txt");
        CalculatorLexer lexer = new CalculatorLexer(charStreams);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //System.out.println(tokens.getText());

        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expr(); // parse

        ParseTreeWalker walker = new ParseTreeWalker();
        MainListen listener = new MainListen();
        walker.walk(listener, tree);
        System.out.println(listener.getResult());
    }


}
