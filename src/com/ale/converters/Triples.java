package com.ale.converters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Triples
{
    public static class Item
    {
        public String operator;
        public String operand1;
        public String operand2;
        
        public Item() {}

        public Item(String operator, String operand1, String operand2)
        {
            this.operator = operator;
            this.operand1 = operand1;
            this.operand2 = operand2;
        }

        @Override
        public boolean equals(Object obj)
        {
            try
            {
                Item item = (Item) obj;
                return item.operator.equals(operator) && item.operand1.equals(operand1) && item.operand2.equals(operand2);
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        @Override
        public String toString()
        {
            return "{" + operator + ", " + operand1 + ", " + operand2 + "}";
        }
    }
    
    private Map<String, Item> triples = new LinkedHashMap<>();
    public Map<String, Item> getTriples()
    {
        return triples;
    }
    
    public Triples() {}
    
    public boolean addTriple(String id, Item triple)
    {
        if (containsTriple(id))
            return false;
        
        triples.put(id, triple);
        
        return true;
    }
    
    public boolean containsTriple(String id)
    {
        return triples.containsKey(id);
    }
    
    public String findId(Item triple)
    {
        for (Map.Entry<String, Item> entry : triples.entrySet())
        {
            Item temp = entry.getValue();
            
            if (temp.equals(triple))
                return entry.getKey();
        }
        
        return null;
    }
    
    public Item getTriple(String id)
    {
        if (!containsTriple(id))
            return null;
        
        return triples.get(id);
    }
    
    public void removeTriple(String id)
    {
        triples.remove(id);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        triples.forEach((id, triple) -> {
            sb.append(id).append(" = ").append(triple).append('\n');
        });
        
        return sb.toString();
    }
    
}
