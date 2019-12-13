package com.ale.converters;

import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("===================================================================");
        System.out.println("===================================================================");
        System.out.println("===================================================================");
        
        //notations();
        //triples();
        //cuadruples();
        
        foo();
        
        System.out.println("===================================================================");
        System.out.println("===================================================================");
        System.out.println("===================================================================");
        
    }
    
    private static void foo()
    {
        String expression = "a + b * c";
        
        List<String> infix = ExpressionUtils.tokensFrom(expression);
        List<String> postfix = NotationConverters.infixToPostfix(infix);
        List<String> prefix = NotationConverters.infixToPrefix(infix);
        
        System.out.println(infix);
        System.out.println(postfix);
        System.out.println(prefix);
        System.out.println();
        
        List<String> infixBack = NotationConverters.postfixToInfix(postfix);
        List<String> postfixBack = NotationConverters.infixToPostfix(infixBack);
        List<String> prefixBack = NotationConverters.infixToPrefix(infixBack);
        
        System.out.println(infixBack);
        System.out.println(postfixBack);
        System.out.println(prefixBack);
    }
    
    private static void notations()
    {
        List<String> expressions = Arrays.asList(
                "( A + ( B * ( ( ( C * D ) - ( H + U ) ) + A ) ) )",
                "( ( ( B - ( D * H ) ) + ( A * B ) ) + ( Z - U ) )",
                "( ( ( ( Z + Y ) / ( A + B ) ) * ( U + H ) ) + C )",
                "( ( A - ( B / H ) ) + ( U * ( B + U ) ) )",
                "( ( B - ( A * D ) ) + ( U + ( H / Z ) ) )",
                "( ( ( X - ( Y + Z ) ) - Y ) + ( ( A * B ) * U ) )",
                "( ( ( A - B ) * ( U - D ) ) - Z )",
                "( ( Y / ( A - B ) ) + ( U + H ) )",
                "( X - ( ( H / U ) - ( Z + U ) ) )",
                "( ( ( A + B ) / C ) * ( Z + H ) )"
        );
        
        expressions.forEach((expression) ->
        {
            List<String> infixTokens = ExpressionUtils.tokensFrom(expression);
            List<String> prefixTokens = NotationConverters.infixToPrefix(infixTokens);
            List<String> postfixTokens = NotationConverters.infixToPostfix(infixTokens);
            
            System.out.println("Infix:   " + infixTokens);
            System.out.println("Prefix:  " + prefixTokens);
            System.out.println("Postfix: " + postfixTokens);
            System.out.println("");
        });
    }
    
    private static void triples()
    {
        List<String> expressions = Arrays.asList(
                "( A + ( B * C ) / D )",
                "( A + ( B * ( ( ( C * D ) - ( H + U ) ) + A ) ) )",
                "( ( ( B - ( D * H ) ) + ( A * B ) ) + ( Z - U ) )",
                "( ( ( ( Z + Y ) / ( A + B ) ) * ( U + H ) ) + C )",
                "( ( A - ( B / H ) ) + ( U * ( B + U ) ) )",
                "( ( B - ( A * D ) ) + ( U + ( H / Z ) ) )",
                "( ( ( X - ( Y + Z ) ) - Y ) + ( ( A * B ) * U ) )",
                "( ( ( A - B ) * ( U - D ) ) - Z )",
                "( ( Y / ( A - B ) ) + ( U + H ) )",
                "( X - ( ( H / U ) - ( Z + U ) ) )",
                "( ( ( A + B ) / C ) * ( Z + H ) )"
        );
        
        expressions.forEach((expression) ->
        {
            List<String> infixTokens = ExpressionUtils.tokensFrom(expression);
            List<String> prefixTokens = NotationConverters.infixToPrefix(infixTokens);
            
            System.out.println("Expression:  " + infixTokens);
            //System.out.println("Prefix: " + prefixTokens);
        
            Triples triples = TriplesFactory.fromPrefixTokens(prefixTokens);
            System.out.println();
            System.out.println("Triples:");
            System.out.println(triples);
        });
    }
    
    private static void cuadruples()
    {
        List<String> expressions = Arrays.asList(
                "( A + ( B * ( ( ( C * D ) - ( H + U ) ) + A ) ) )",
                "( ( ( B - ( D * H ) ) + ( A * B ) ) + ( Z - U ) )",
                "( ( ( ( Z + Y ) / ( A + B ) ) * ( U + H ) ) + C )",
                "( ( A - ( B / H ) ) + ( U * ( B + U ) ) )",
                "( ( B - ( A * D ) ) + ( U + ( H / Z ) ) )",
                "( ( ( X - ( Y + Z ) ) - Y ) + ( ( A * B ) * U ) )",
                "( ( ( A - B ) * ( U - D ) ) - Z )",
                "( ( Y / ( A - B ) ) + ( U + H ) )",
                "( X - ( ( H / U ) - ( Z + U ) ) )",
                "( ( ( A + B ) / C ) * ( Z + H ) )"
        );
        
        expressions.forEach((expression) ->
        {
            List<String> infixTokens = ExpressionUtils.tokensFrom(expression);
            List<String> prefixTokens = NotationConverters.infixToPrefix(infixTokens);
            
            System.out.println("Expression:  " + infixTokens);
            //System.out.println("Prefix: " + prefixTokens);
        
            Cuadruples cuadruples = CuadruplesFactory.fromPrefixTokens(prefixTokens);
            System.out.println();
            System.out.println("Cuadruples:");
            System.out.println(cuadruples);
        });
    }
    
}
