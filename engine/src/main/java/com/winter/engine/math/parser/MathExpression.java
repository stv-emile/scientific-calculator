package com.winter.engine.math.parser;

import com.winter.engine.adt.tree.BinaryTree;
import com.winter.engine.compute.engine.ExpressionEvaluator;

//import lombok.Getter;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Getter
public class MathExpression {

    private String plainTextExpression;
    private LinkedList<String> tokenizedExpressionList;
    private BinaryTree<String> tokenizedExpressionTree;
    private Double result = Double.NaN;

    public MathExpression(){
        this.plainTextExpression=null;
        this.tokenizedExpressionTree=null;
        this.tokenizedExpressionList=null;
        this.result=0.0;
    }

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
        //String adRegex = "(\\-?\\d+(?:\\.\\d+)?|[\\+\\-\\*\\/\\(\\)])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.plainTextExpression);
        while (matcher.find()) {
            this.tokenizedExpressionList.add(matcher.group());
        }
        if(this.tokenizedExpressionList.size() > 1){
            this.tokenizedExpressionList=this.removeUnnecessaryBrackets(this.getTokenizedExpressionList());
            this.tokenizedExpressionList.addFirst("(");
            this.tokenizedExpressionList.addLast(")");
        }

        System.out.println(this.tokenizedExpressionList);
    }

    private LinkedList<String> removeUnnecessaryBrackets(LinkedList<String> tokenizedExpressionListToVerify){
        LinkedList<String> innerTokenizedExpressionListCopy = new LinkedList<>(tokenizedExpressionListToVerify);
        String firstToken = innerTokenizedExpressionListCopy.removeFirst();
        String lastToken = innerTokenizedExpressionListCopy.removeLast();

        //if true Expression is exactly Enclosed by Brackets
        if(!(firstToken.equals("(") || !lastToken.equals(")"))
                && ExpressionChecker.check(innerTokenizedExpressionListCopy)){
            return  tokenizedExpressionListToVerify;
        }
        if (firstToken.equals("(") && lastToken.equals(")")
                &&  !ExpressionChecker.check(innerTokenizedExpressionListCopy)){
            return  tokenizedExpressionListToVerify;
        }
        if (!firstToken.equals("(") || !lastToken.equals(")")
                &&  !ExpressionChecker.check(innerTokenizedExpressionListCopy)){
            return  tokenizedExpressionListToVerify;
        }
        /*if (firstToken.equals("(") && lastToken.equals(")") && ExpressionChecker.check(innerTokenizedExpressionListCopy)){
            return removeUnnecessaryBrackets(innerTokenizedExpressionListCopy);
        }*/
        return removeUnnecessaryBrackets(innerTokenizedExpressionListCopy);
    }

    public LinkedList<String> getTokenizedExpressionList(){
        return this.tokenizedExpressionList;
    }

    public Double getResult(){
        try{
            this.compute();
            return this.result;
        }catch(Exception e){
            return Double.NaN;
        }
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
