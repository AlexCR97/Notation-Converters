package com.ale.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionUtils
{
    public static boolean isOperator(String token)
    {
        return
                token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/") ||
                token.equals("(") ||
                token.equals(")");
    }
    
    public static boolean isOperand(String token)
    {
        return !isOperator(token);
    }
    
    public static int precedenceOf(String token) {
        if (token.equals("(") || token.equals(")"))
            return 1;

        if (token.equals("+") || token.equals("-"))
            return 2;

        if (token.equals("*") || token.equals("/") || token.equals("%"))
            return 3;

        if (token.equals("^"))
            return 4;

        return -1;
    }

    public static List<String> tokensFrom(String expression)
    {
        String[] tokens = expression.split(" ");
        List<String> tokensList = new ArrayList<>();
        
        tokensList.addAll(Arrays.asList(tokens));
        
        return tokensList;
    }
    
}
