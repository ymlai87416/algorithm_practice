package Leetcode.AmazonMock;

import java.util.*;

public class PhoneQ2 {
    static class MinStack {

        Stack<Integer> normal = null;
        Stack<Integer> minSt = null; //store location of min

        public MinStack() {
            normal  = new Stack<>();
            minSt = new Stack<>();
        }

        public void push(int val) {
            normal.push(val);
            if(minSt.isEmpty() || (!minSt.isEmpty() && normal.get(minSt.peek()) > val ))
                minSt.push(normal.size()-1);
        }

        public void pop() {
            int a = normal.pop();
            if(!minSt.isEmpty() && minSt.peek() == normal.size())
                minSt.pop();
        }

        public int top() {
            return normal.peek();
        }

        public int getMin() {
            int a =  minSt.peek();
            //System.out.println("d" + a);
            return normal.get(a);
        }
    }


    public static void main(String[] args){
        MinStack s = new MinStack();
        s.push(-2);
        s.push(0);
        s.push(-1);
        System.out.println(s.getMin()); //-2
        System.out.println(s.top()); //-1
        s.pop();
        System.out.println(s.getMin());
    }
}
