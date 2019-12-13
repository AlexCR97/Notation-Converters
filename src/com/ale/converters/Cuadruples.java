package com.ale.converters;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cuadruples
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
            return operator + ", " + operand1 + ", " + operand2;
        }
    }
    
    private Map<String, Item> cuadruples = new LinkedHashMap<>();
    public Map<String, Item> getCuadruples()
    {
        return cuadruples;
    }
    
    public Cuadruples() {}
    
    public boolean addCuadruple(String id, Item cuadruple)
    {
        if (containsCuadruple(id))
            return false;
        
        cuadruples.put(id, cuadruple);
        
        return true;
    }
    
    public boolean containsCuadruple(String id)
    {
        return cuadruples.containsKey(id);
    }
    
    public String findId(Item cuadruple)
    {
        for (Map.Entry<String, Item> entry : cuadruples.entrySet())
        {
            Item temp = entry.getValue();
            
            if (temp.equals(cuadruple))
                return entry.getKey();
        }
        
        return null;
    }
    
    public Item getCuadruple(String id)
    {
        if (!containsCuadruple(id))
            return null;
        
        return cuadruples.get(id);
    }
    
    public void removeTriple(String id)
    {
        cuadruples.remove(id);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        cuadruples.forEach((id, cuadruple) -> {
            sb.append("{").append(cuadruple).append(", ").append(id).append("}").append('\n');
        });
        
        return sb.toString();
    }
    
}
