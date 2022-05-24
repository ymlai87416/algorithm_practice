package ctci.stackqueue;

import java.util.Stack;

public class QueueViaStacks {
    public static void main(String[] args) {
        MyQueue<String> q = new MyQueue<>();

        q.offer("data1");
        q.offer("data2");
        q.offer("data3");

        System.out.println(q.poll());
        System.out.println(q.poll());

        q.offer("data4");
        q.offer("data5");

        System.out.println(q.poll());
    }
}

class MyQueue<V>{

    Stack<V> data;
    Stack<V> fetch;
    int mode;

    public MyQueue(){
        data = new Stack<>();
        fetch = new Stack<>();
        mode = 0;
    }

    public void offer(V value){
        if(mode == 1) transfer();

        data.push(value);
    }

    public V poll(){
        //now pop all the element to the other stack
        //get the last and put it back?
        if(mode == 0) transfer();

        return fetch.pop();
    }

    public void transfer(){
        if(mode == 0){
            while(!data.isEmpty())
                fetch.push(data.pop());
            mode = 1;
        }
        else{
            while(!fetch.isEmpty())
                data.push(fetch.pop());
            mode = 0;
        }
    }
}

