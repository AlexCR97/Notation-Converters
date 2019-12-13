package com.ale.converters;

import java.util.List;
import java.util.Stack;

public class TriplesFactory
{
    public static Triples fromPrefixTokens(List<String> prefixTokens)
    {
        Triples table = new Triples();
        int idCounter = 0;
        
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
            // check if triples table has the current entry, if so, add entry id to table
            else
            {
                String operand1 = operands.pop();
                String operand2 = operands.pop();
                
                Triples.Item item1 = fromString(operand1);
                Triples.Item item2 = fromString(operand2);
                
                if (item1 == null && item2 == null) 
                {
                    table.addTriple("T" + idCounter, new Triples.Item(currentToken, operand1, operand2));
                    
                    String newOperand = String.format("( %s %s %s )", operand1, currentToken, operand2);
                    operands.push(newOperand);
                }
                else if (item1 != null && item2 != null)
                {
                    String id1 = table.findId(item1);
                    String id2 = table.findId(item2);
                    table.addTriple("T" + idCounter, new Triples.Item(currentToken, id1, id2));
                    
                    String newOperand = String.format("( %s %s %s )", id1, currentToken, id2);
                    operands.push(newOperand);
                }
                else if (item1 != null && item2 == null)
                {
                    String id1 = table.findId(item1);
                    table.addTriple("T" + idCounter, new Triples.Item(currentToken, id1, operand2));
                    
                    String newOperand = String.format("( %s %s %s )", id1, currentToken, operand2);
                    operands.push(newOperand);
                }
                else if (item1 == null && item2 != null)
                {
                    String id2 = table.findId(item2);
                    table.addTriple("T" + idCounter, new Triples.Item(currentToken, operand1, id2));
                    
                    String newOperand = String.format("( %s %s %s )", operand1, currentToken, id2);
                    operands.push(newOperand);
                }
                
                idCounter++;
            }
        }
        
        return table;
    }
    
    private static Triples.Item fromString(String expression)
    {
        try
        {
            // ( A + B )
            String[] tokens = expression.split(" ");
            String operator = tokens[2];
            String operand1 = tokens[1];
            String operand2 = tokens[3];
            return new Triples.Item(operator, operand1, operand2);
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
