package com.winter.engine.math.parser;

import com.winter.engine.adt.tree.Node;

import java.util.LinkedList;
import java.util.ListIterator;

public class ExpressionParser {
    private static final String UNARY_OPERATORS = "+-";
    private static final String BINARY_OPERATORS = "+-*/";

    public static Node<String> parse(LinkedList<String> textExpressionList) {

        //root of the expression tree
        Node<String> root = new Node<>();

        //if the expression is null
        if(textExpressionList.size()==0){
            return null;
        }

        //if it's a single node create a leaf node with the number as a value
        //and return it
        if(textExpressionList.size()==1){
            root.addValue(textExpressionList.get(0));
            return root;
        }

        //look for the middle operator
        textExpressionList.removeFirst();
        textExpressionList.removeLast();
        System.out.println("--> "+textExpressionList);
        int index = getMiddleIndex(textExpressionList);

        //Create a node operator
        root.addValue(textExpressionList.get(index));

        //parse left side expression
        LinkedList leftExpression = new LinkedList<>(textExpressionList.subList(0,index));
        System.out.println(leftExpression);
        root.addLeft(parse(leftExpression));

        //parse right side expression
        LinkedList rightExpression = new LinkedList<>(textExpressionList.subList(index+1,textExpressionList.size()));
        System.out.println(rightExpression);
        root.addright(parse(rightExpression));

        //link the node and return it
        return root;

    }

    public static int getMiddleIndex(LinkedList<String> expList){
        ListIterator<String> expListIterator = expList.listIterator();
        LinkedList<String> parenthesis = new LinkedList<>();
        String token="?";
        while (expListIterator.hasNext()){
            int index = expListIterator.nextIndex();
            token = expListIterator.next();
            System.out.print(token+" ");
            if ("(".contains(token)){
                //System.out.print(token);
                parenthesis.push(token);
            } else if (")".contains(token)) {
                //System.out.print(token);
                parenthesis.pop();
            } else if (BINARY_OPERATORS.contains(token) && parenthesis.isEmpty()) {
                //System.out.print(token);
                return index;
            }
        }
        System.out.print("error");
        return -1;
    }

    public static  String toStringExpressionTree(Node<String> node){
        //it is a number for sure otherwise it's a bug
        if (node.getLeftChild()==null && node.getRightChild()==null){
            return node.getValue();
        }

        //get the operator
        String operator = node.getValue();
        String leftExpression = toStringExpressionTree(node.getLeftChild());
        String rightExpression = toStringExpressionTree(node.getRightChild());
        String expression = operator+"("+leftExpression+","+rightExpression+")";
        return  expression;

    }

}
