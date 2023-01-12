package compute.engine;
import adt.tree.BinaryTree;
import adt.tree.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExpressionEvaluator {

    //constant
    public final static int INFIX = 0;

    //notation of the expression
    private int notation;
    private BinaryTree<String> TokenizedExpressionTree;

    // With two argument
    public ExpressionEvaluator(BinaryTree<String> TokenizedExpressionTree, int notation){
        this.TokenizedExpressionTree = TokenizedExpressionTree;
        this.notation = notation;
    }

    public double evaluate(){
        double value = 0;
        if (notation == INFIX){
            value = infixCalculate(this.TokenizedExpressionTree.getRoot());
        }
        return value;
    }


    //Methode for infix Tree
    private double infixCalculate(Node<String> node){
        //it is a number for sure otherwise it's a bug
        if (node.getLeftChild()==null && node.getRightChild()==null){
            return Double.parseDouble(node.getValue());
        }

        //get the operator
        String operator = node.getValue();
        double leftValue = infixCalculate(node.getLeftChild());
        double rightValue = infixCalculate(node.getRightChild());
        double value = applyOperator(operator, leftValue, rightValue);
        return value;
    }

    //Operator applier applying supported operation
    private double applyOperator( String operator, double leftValue, double rightValue ){
        double value ;
        switch (operator){
            case "+":
                value = leftValue + rightValue;
                break;
            case "-":
                value = leftValue - rightValue;
                break;
            case "*":
                value = leftValue * rightValue;
                break;
            case "/":
                value = leftValue / rightValue;
                break;
            case "^":
                value = Math.pow(leftValue,rightValue);
                break;
            default:
                System.out.println("Error : Unsupported operator");
                value = 0;
        }
        return value;
    }

    //Here we can define new operator algorithm or import them for a library

}

