package ctci.stackqueue;

import java.util.*;

public class StackMin{

    Stack<Integer> minStack;
    Stack<Integer> stack;
    public StackMin(){
        minStack = new Stack<Integer>();
        stack = new Stack<Integer>();
    }

    public void push(int value){
        if(minStack.isEmpty() || value <= minStack.peek())
            minStack.push(value);
        stack.push(value);
    }

    public int pop() throws Exception {
        if(stack.isEmpty()) throw new Exception("stack is empty");
        int v = stack.pop();
        if(v == minStack.peek())
            minStack.pop();

        return v;
    }

    public int min() throws Exception {
        if(minStack.isEmpty()) throw new Exception("stack is empty");
        return minStack.peek();
    }


    public static void main(String[] args) throws Exception {
        StackMin stack = new StackMin();

        stack.push(5);
        System.out.println("min: " + stack.min());
        stack.push(6);
        System.out.println("min: " + stack.min());
        stack.push(3);
        System.out.println("min: " + stack.min());
        stack.push(7);
        System.out.println("min: " + stack.min());
        System.out.println("pop: " + stack.pop() + " min: " + stack.min());
        System.out.println("pop: " + stack.pop()+ " min: " + stack.min());
    }
}
