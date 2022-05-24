package ctci.stackqueue;

import java.util.Stack;

public class SortStack {

    public void sortStack(Stack<Integer> stack){

        Stack<Integer> temp = new Stack<Integer>();
        Integer largest = null;
        int n = stack.size();
        //now do a selection sort
        for(int i=n; i>1; --i){

            for(int j=0; j<i; ++j){
                int value = stack.pop();
                if(largest  == null || value > largest) largest = value;
                temp.push(value);
            }

            stack.push(largest);

            //now we push it back
            for(int j=0; j<i; ++j){
                int value = temp.pop();
                if(largest != null && value == largest){
                    largest = null;
                }
                else
                    stack.push(value);
            }

        }
    }

    public static void main(String[] args) {
        Stack st = new Stack<Integer>();
        st.push(3); st.push(2); st.push(4); st.push(5); st.push(1);

        SortStack test = new SortStack();
        test.sortStack(st);

        int cnt = 1;
        while(!st.isEmpty()){
            System.out.println(cnt + ": " + st.pop());
        }
    }

}
