package Leetcode;

import java.util.Stack;

/**
number: 155
url: https://leetcode.com/problems/min-stack/
level: easy
solution: just 2 stack

#stack

 **/

public class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> num, min;
    public MinStack() {
        num = new Stack<Integer>();
        min = new Stack<Integer>();
    }

    public void push(int x) {
        num.push(x);
        min.push(Math.min(min.size() == 0 ? x :min.peek(), x));
    }

    public void pop() {
        num.pop();
        min.pop();
    }

    public int top() {
        return num.peek();
    }

    public int getMin() {
        return min.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //--> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());      //--> Returns 0.
        System.out.println(minStack.getMin());   //--> Returns -2.
    }
}
