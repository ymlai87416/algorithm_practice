package Leetcode;

import java.util.*;

public class P1597 {

    public Node expTree(String s) {
        Stack<Node> stNum = new Stack<Node>();
        Stack<Character> stOp = new Stack<Character>();

        int number = 0;
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                stNum.push(new Node(c));
                //now check the top
                if(stOp.isEmpty()) continue;

                char cOp = stOp.peek();
                if(cOp == '*' || cOp == '/'){
                    Node right =stNum.pop();
                    Node left = stNum.pop();
                    Node op = new Node(stOp.pop());
                    op.left = left;
                    op.right = right;
                    stNum.push(op);
                }

            }
            else if(c == '('){
                stOp.push(c);
            }
            else if(c == ')'){
                Stack<Character> rStOp = new Stack<>();
                Stack<Node> rStNum = new Stack<>();
                while(true){
                    Character op = stOp.pop();
                    Node num = stNum.pop();

                    rStOp.add(op);
                    rStNum.add(num);
                    if(op == '(') {
                        rStOp.pop();
                        break;
                    }
                }
                //now sum it up until rStNum have one element and add back to the stNum;
                while(rStNum.size() > 1){
                    Node left= rStNum.pop();
                    Node right = rStNum.pop();
                    Node nop = new Node(rStOp.pop());
                    nop.left= left;
                    nop.right = right;
                    rStNum.push(nop);
                }

                stNum.push(rStNum.pop());

                if(stOp.isEmpty()) continue;

                char cOp = stOp.peek();
                if(cOp == '*' || cOp == '/'){
                    Node right =stNum.pop();
                    Node left = stNum.pop();
                    Node op = new Node(stOp.pop());
                    op.left = left;
                    op.right = right;
                    stNum.push(op);
                }

            }
            else{
                stOp.push(c);
            }
        }

        //now we continue to pop until only one element left
        Stack<Character> rStOp = new Stack<>();
        Stack<Node> rStNum = new Stack<>();
        while(!stNum.isEmpty()) rStNum.push(stNum.pop());
        while(!stOp.isEmpty()) rStOp.push(stOp.pop());


        while(rStNum.size() > 1){
            Node left = rStNum.pop();
            Node right = rStNum.pop();

            Node op = new Node(rStOp.pop());
            op.left= left;
            op.right = right;
            rStNum.push(op);
        }

        return rStNum.pop();
    }

    public static void main(String[] args){
        P1597 p = new P1597();
        p.expTree("2-3/(5*2)+1");
    }

    static class Node{
      char val;
     Node left;
      Node right;
      Node() {this.val = ' ';}
      Node(char val) { this.val = val; }
      Node(char val, Node left, Node right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
