package com.ale.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NotationConverters
{
    public static List<String> infixToPostfix(List<String> infixTokens)
    {
        List<String> postfixTokens = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String popped;
        
        for (String currentToken : infixTokens)
        {
            if (!ExpressionUtils.isOperator(currentToken))
                postfixTokens.add(currentToken);
            
            else if (currentToken.equals(")"))
                while (!(popped = stack.pop()).equals("("))
                    postfixTokens.add(popped);
            
            else
            {
                while (!stack.isEmpty() && !currentToken.equals("(") && ExpressionUtils.precedenceOf(stack.peek()) >= ExpressionUtils.precedenceOf(currentToken))
                    postfixTokens.add(stack.pop());
                
                stack.push(currentToken);
            }
        }

        while (!stack.isEmpty())
            postfixTokens.add(stack.pop());

        return postfixTokens;
    }
    
    public static List<String> infixToPrefix(List<String> infixTokens)
    {
        // flip expression
        Stack<String> tokenStack = new Stack<>();
        infixTokens.forEach((token) -> tokenStack.push(token));
        
        // iterate over flipped tokens
        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        
        while (!tokenStack.isEmpty())
        {
            String currentToken = tokenStack.pop();
            
            // is operator
            if (ExpressionUtils.isOperator(currentToken))
            {
                operators.push(currentToken);
                
                if (operators.contains("(") && operators.contains(")"))
                {
                    boolean foundOpened = false;
                    boolean foundClosed = false;
                    
                    while (!foundOpened || !foundClosed)
                    {
                        String token = operators.pop();
                        
                        if (token.equals("("))
                        {
                            foundOpened = true;
                            continue;
                        }
                        
                        if (token.equals(")"))
                        {
                            foundClosed = true;
                            continue;
                        }
                        
                        operands.push(token);
                    }
                }
            }
            // is operand
            else
                operands.push(currentToken);
        }
        
        // flip operands
        List<String> prefixTokens = new ArrayList<>();
        
        while (!operands.isEmpty())
        {
            String token = operands.pop();
            prefixTokens.add(token);
        }
        
        return prefixTokens;
    }
    
    public static List<String> postfixToInfix(List<String> postfixTokens)
    {
        Stack<String> operands = new Stack<>();
        
        postfixTokens.forEach((token) -> {
            
            if (ExpressionUtils.isOperand(token))
                operands.push(token);
            
            else
            {
                String operand2 = operands.pop();
                String operand1 = operands.pop();
                String operator = token;
                String newOperand = String.format("( %s %s %s )", operand1, operator, operand2);
                operands.push(newOperand);
            }
        });
        
        String expression = operands.pop();
        List<String> infixTokens = ExpressionUtils.tokensFrom(expression);
        
        return infixTokens;
    }
    
    public static List<String> prefixToInfix(List<String> prefixTokens)
    {
        // flip tokens
        Stack<String> tokenStack = new Stack<>();
        prefixTokens.forEach((token) -> tokenStack.push(token));
        
        // iterate over tokens
        Stack<String> operands = new Stack<>();
        
        while (!tokenStack.isEmpty())
        {
            String currentToken = tokenStack.pop();
            
            // if token is operand, add to operand stack
            if (ExpressionUtils.isOperand(currentToken))
                operands.push(currentToken);
            
            // if token is operator, apply operation to last two elements in stack and add to operands stack
            else
            {
                String a = operands.pop();
                String b = operands.pop();
                String c = String.format("( %s %s %s )", a, currentToken, b);
                operands.push(c);
            }
        }
        
        List<String> infixTokens = new ArrayList<>();
        
        while (!operands.isEmpty())
        {
            String expression = operands.pop();
            List<String> tokens = ExpressionUtils.tokensFrom(expression);
            tokens.forEach((token) -> infixTokens.add(token));
        }
        
        return infixTokens;
    }
}
