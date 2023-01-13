package com.winter.engine.math.parser;

//import lombok.AllArgsConstructor;

import java.util.*;

//@AllArgsConstructor
public class ExpressionChecker {

    public static boolean check(LinkedList<String> tokenizedExpressionToCheck){
        //check all condition
        boolean isCorrect = true;

        //parenhesis check
        isCorrect &= parenthesisCheck(tokenizedExpressionToCheck);

        // return and checker responses
        return isCorrect;
    }


    //paenthesis check
    public static boolean parenthesisCheck(LinkedList<String> tokenizedExpressionToCheck){
        Stack<String> bracketStack = new Stack<>();
        for ( String token : tokenizedExpressionToCheck) {
            // if it's an open bracket
            if(token.equals("(")){
                bracketStack.push(token);
            }
            else if (token.equals(")")){
                if(bracketStack.empty()) return false;
                bracketStack.pop();
            }
        }
        if (!bracketStack.empty()) return false;

        return true;
    }

}
