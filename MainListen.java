import java.util.LinkedList;
import java.util.Stack;

import com.sun.tools.javac.Main;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.Math;


public class MainListen extends CalculatorBaseListener {

    private final LinkedList<Double> firstStack = new LinkedList<>();
    private final LinkedList<Double> secondStack = new LinkedList<>();
    private final LinkedList<Double> thirdStack = new LinkedList<>();
    private final LinkedList<Double> fourthStack = new LinkedList<>();

    public Double getResult() {

        return fourthStack.pop();
    }

@Override
public void exitExpression(CalculatorParser.ExpressionContext ctx) {
        double left = fourthStack.removeLast();
        for (int i = 1; i < ctx.getChildCount(); i = i + 2) {
            if (symbolEquals(ctx.getChild(i), CalculatorParser.PLUS)) {
                left = left + fourthStack.removeLast();
            } else {
                left = left - fourthStack.removeLast();
            }
        }
        fourthStack.push(left);
}
@Override
public void exitMultdivexpression(CalculatorParser.MultdivexpressionContext ctx) {
        double left = thirdStack.removeLast();
        for (int i = 1; i < ctx.getChildCount(); i = i + 2) {
            if (symbolEquals(ctx.getChild(i), CalculatorParser.TIMES)) {
                left = left * thirdStack.removeLast();
            } else {
                left = left / thirdStack.removeLast();
            }
        }
        fourthStack.push(left);

}
@Override
public void exitPowerexpression(CalculatorParser.PowerexpressionContext ctx) {
        double left = Double.valueOf(secondStack.removeLast());
        for (int i = 1; i < ctx.getChildCount(); i = i + 2) {
            if (symbolEquals(ctx.getChild(i), CalculatorParser.POW)){
                left = Math.pow(left,secondStack.removeLast());
            }else if(symbolEquals(ctx.getChild(i), CalculatorParser.SQRT)){
                left = Math.pow(left,1.0/secondStack.removeLast());
            }
        }
        thirdStack.push(left);
}
    private boolean symbolEquals(ParseTree child, int symbol) {
        return ((TerminalNode) child).getSymbol().getType() == symbol;
    }
@Override public void exitLogexpression(CalculatorParser.LogexpressionContext ctx) {
        if(ctx.LOG() != null){
            secondStack.push(Math.log(firstStack.removeLast()));
        }else{
            secondStack.push(firstStack.removeLast());
        }
}
@Override public void exitNumexpression(CalculatorParser.NumexpressionContext ctx) {
    double value = Double.valueOf(ctx.NUMBER().getText());
    if (ctx.NUMBER() != null) {
        if(ctx.MINUS() != null){
            firstStack.push(-1 * value);
        }
        else
        {
            firstStack.push(value) ;
        }
    }
}
    public static Double calc(CharStream charStream) {
        CalculatorLexer lexer = new CalculatorLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        System.out.println(tokens.getText());

        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expr();

        ParseTreeWalker walker = new ParseTreeWalker();
        MainListen calculatorListener = new MainListen();
        walker.walk(calculatorListener, tree);
        return calculatorListener.getResult();
    }

    public static Double calc(String expression) {
        return calc(CharStreams.fromString(expression));
    }

    public static void main(String[] args) throws Exception {
        CharStream charStreams = CharStreams.fromFileName("C:\\Users\\Mazii\\IdeaProjects\\antrl\\src\\example.txt");
        Double result = calc(charStreams);
        System.out.println("Result = " + result);
    }



}
