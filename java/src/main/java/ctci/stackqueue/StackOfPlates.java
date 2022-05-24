package ctci.stackqueue;

import java.util.*;

public class StackOfPlates {

    public static void main(String[] args) {
        SetOfStacks<String> test = new SetOfStacks<>(3);
    }
}


class SetOfStacks<V>{

    int limit;
    Stack<Stack<V>> st;

    public SetOfStacks(int limit){
        this.limit = limit;
        st = new Stack<>();
    }

    public void push(V value){
        if(st.isEmpty() || st.peek().size() >= limit)
            st.push(new Stack<V>());

        st.peek().push(value);
    }

    public V pop() throws Exception{
        while(!st.isEmpty() && st.peek().isEmpty())
            st.pop();

        if(st.isEmpty()) throw new Exception("Stack is empty");

        return st.peek().pop();
    }


    public V popAt(int index) throws Exception{
        if(st.get(index).isEmpty()) throw new Exception("Stack is empty");
        return st.get(index).pop();
    }
}
