package com.ale.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExpressionTree
{
    public static class Node
    {
        public String value;
        public Node left;
        public Node right;

        public Node(String value)
        {
            this.value = value;
        }
        
        public Node(String value, Node left, Node right)
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString()
        {
            return value;
        }
    }

    private Node root;
    public Node getRoot() { return root; }

    private List<String> postfixTokens;

    public ExpressionTree(List<String> postfixTokens)
    {
        this.postfixTokens = postfixTokens;
    }

    public Node constructTree()
    {
        root = constructTreeHelper();
        return root;
    }

    private Node constructTreeHelper()
    {
        Node rootTemp;
        Stack<Node> stack = new Stack<>();

        for (String token : postfixTokens)
        {
            rootTemp = new Node(token);

            if (!ExpressionUtils.isOperator(token))
                stack.push(rootTemp);

            else
            {
                rootTemp.right = stack.pop();
                rootTemp.left = stack.pop();
                stack.push(rootTemp);
            }
        }
        return stack.pop();
    }

    public List<Node> traverseInorder()
    {
        List<Node> nodes = new ArrayList<>();
        traverseInorder(root, nodes);
        return nodes;
    }

    private void traverseInorder(Node root, List<Node> nodes)
    {
        if (root != null)
        {
            traverseInorder(root.left, nodes);
            nodes.add(root);
            traverseInorder(root.right, nodes);
        }
    }

    public List<Node> traversePostorder()
    {
        List<Node> nodes = new ArrayList<>();
        traversePostorder(root, nodes);
        return nodes;
    }

    private void traversePostorder(Node root, List<Node> nodes)
    {
        if (root != null)
        {
            traversePostorder(root.left, nodes);
            traversePostorder(root.right, nodes);
            nodes.add(root);
        }
    }

    public List<Node> traversePreorder()
    {
        List<Node> nodes = new ArrayList<>();
        traversePreorder(root, nodes);
        return nodes;
    }

    private void traversePreorder(Node root, List<Node> nodes)
    {
        if (root != null)
        {
            nodes.add(root);
            traversePreorder(root.left, nodes);
            traversePreorder(root.right, nodes);
        }
    }
}
