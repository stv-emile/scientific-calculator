package math.parser;

import adt.tree.BinaryTree;
import compute.engine.ExpressionEvaluator;
import lombok.Getter;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class MathExpression {

    private String plainTextExpression;
    private LinkedList<String> tokenizedExpressionList;
    private BinaryTree<String> tokenizedExpressionTree;
    private Double result;

    public MathExpression(String plainTextExpression){
        this.plainTextExpression = plainTextExpression;
        this.tokenizedExpressionTree = new BinaryTree<>();
        this.tokenizedExpressionList = new LinkedList<>();
        this.tokenize();
    }

    public boolean isValid(){
        return ExpressionChecker.check(this.tokenizedExpressionList);
    }
    private void tokenize(){
        //code to tokenize the expression
        String regex = "(\\d+(?:\\.\\d+)?)|[+\\-*/()]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(plainTextExpression);
        while (matcher.find()) {
            tokenizedExpressionList.add(matcher.group());
        }
        System.out.println(tokenizedExpressionList);
    }

    public LinkedList<String> getTokenizedExpressionList(){
        return this.tokenizedExpressionList;
    }

    public double getResult(){
        this.compute();
        return this.result;
    }

    //is public to be evaluated constantly by the client
    public void compute(){
        // check if valid
        if(this.isValid()){
            //build ExpressionEvaluator Tree
            this.tokenizedExpressionTree = new BinaryTree<>(ExpressionParser.parse(this.tokenizedExpressionList));

            // Create an ExpressionEvaluator by specifying the notation
            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(this.tokenizedExpressionTree, ExpressionEvaluator.INFIX);

            // Evaluate the expressionEvaluator from the Calculator Builder
            this.result = expressionEvaluator.evaluate();
        }
    }

}
