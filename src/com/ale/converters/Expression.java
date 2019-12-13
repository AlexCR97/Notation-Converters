package com.ale.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Expression
{
    private String representation;
    private Map<String, Double> variables;
    
    public Expression(String representation)
    {
        this.representation = representation;
    }

    public Expression(String representation, Map<String, Double> variables)
    {
        this.representation = representation;
        this.variables = variables;
    }

    public String getRepresentation()
    {
        return representation;
    }

    public Map<String, Double> getVariables()
    {
        return variables;
    }
    
    public List<String> toTokensList(String expression)
    {
        String[] tokens = expression.split(" ");
        List<String> tokensList = new ArrayList<>();
        
        tokensList.addAll(Arrays.asList(tokens));
        
        return tokensList;
    }
    
    @Override
    public String toString()
    {
        return representation;
    }
    
}
