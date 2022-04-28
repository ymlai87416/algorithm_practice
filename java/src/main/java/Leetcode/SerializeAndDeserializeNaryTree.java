package Leetcode;

import java.util.*;

public class SerializeAndDeserializeNaryTree {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "";
        //root  (left )  (right)
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(root.val);

        if(root.children != null){

            for(int i=0; i<root.children.size(); ++i){
                String content = serialize(root.children.get(i));
                sb.append(content);
            }

        }

        sb.append(")");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data == "") return null;

        Node root = new Node();
        List<String> tt =  tokenized(data);
        Stack<Object> st = new Stack<Object>();
        st.push(root);

        for(int i=0; i<tt.size(); ++i){
            String ct = tt.get(i);
            if(Character.isDigit(ct.charAt(0)))
                ((Node)st.peek()).val = Integer.parseInt(ct);
            else if(ct.compareTo("(") == 0){
                st.push("(");
                st.push(new Node());
            }
            else if(ct.compareTo(")") == 0){
                //unpop all the Node and add all the node to the first node which sit on top of (
                List<Node> nodes = new ArrayList<Node>();
                while(!st.isEmpty()){
                    Object obj = st.pop();
                    if(obj instanceof Node)
                        nodes.add((Node)obj);
                    else{
                        Node r = nodes.get(nodes.size()-1);
                        r.children = new ArrayList<>();  //will break the judge if not add to all node
                        for(int j=nodes.size()-2; j>=0; --j) {
                            r.children.add(nodes.get(j));
                        }

                        //push it back to the stack
                        st.push(r);
                        break;
                    }
                }
            }
        }

        return (Node)st.pop();
    }

    private List<String> tokenized(String s){
        int number = -1;
        List<String> result = new ArrayList<String>();
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            if(Character.isDigit(c))
                number= number==-1 ? (c-'0') : number * 10 + (c-'0');
            else if(c == '('){
                if(number != -1){
                    result.add(String.valueOf(number));
                    number=-1;
                }
                result.add("(");
            }
            else if(c == ')'){
                if(number != -1) {
                    result.add(String.valueOf(number));
                    number=-1;
                }
                result.add(")");
            }
        }


        return result;
    }


    public static void main(String[] args){
        Node root = new Node(1);
        Node l1_3 = new Node(3);
        Node l1_2 = new Node(2);
        Node l1_4 = new Node(4);
        Node l2_5 = new Node(5);
        Node l2_6 =new Node (6);
        root.children = new ArrayList<>();
        root.children.add(l1_3);
        root.children.add(l1_2);
        root.children.add(l1_4);
        l1_3.children = new ArrayList<>();
        l1_3.children.add(l2_5);
        l1_3.children.add(l2_6);


        SerializeAndDeserializeNaryTree s = new SerializeAndDeserializeNaryTree();

        String t = s.serialize(root);
        System.out.println(t);
        Node f = s.deserialize(t);
        t = s.serialize(root);
        System.out.println(t);

        String t1 = s.serialize(null);
        System.out.println(t);

    }
}
