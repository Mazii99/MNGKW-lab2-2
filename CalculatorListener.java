// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code expression}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multdivexpression}
	 * labeled alternative in {@link CalculatorParser#multdivexpr}.
	 * @param ctx the parse tree
	 */
	void enterMultdivexpression(CalculatorParser.MultdivexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multdivexpression}
	 * labeled alternative in {@link CalculatorParser#multdivexpr}.
	 * @param ctx the parse tree
	 */
	void exitMultdivexpression(CalculatorParser.MultdivexpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powerexpression}
	 * labeled alternative in {@link CalculatorParser#powexpr}.
	 * @param ctx the parse tree
	 */
	void enterPowerexpression(CalculatorParser.PowerexpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powerexpression}
	 * labeled alternative in {@link CalculatorParser#powexpr}.
	 * @param ctx the parse tree
	 */
	void exitPowerexpression(CalculatorParser.PowerexpressionContext ctx);
}