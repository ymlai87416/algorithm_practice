package Leetcode.AppleMock;

import java.util.*;

public class OnsiteQ3 {
    public int calculate(String s) {
        ArrayList<Object> tokens = tokenized(s);
        return calculate(tokens, 0, tokens.size());
    }

    private ArrayList<Object> tokenized(String s){
        Integer number=  null;
        ArrayList<Object> result = new ArrayList<Object>();
        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);
            if('0' <= cc && cc <='9')
                number = number == null ? cc-'0' : number*10 + cc-'0';
            else{
                if(number != null) {
                    result.add(number);
                    number = null;
                }
                if(cc!=' ')
                    result.add(cc);
            }
        }

        if(number != null)
            result.add(number);

        return result;
    }

    private int calculate(ArrayList<Object> tokens, int start, int end){
        /*
        for (int i = start; i < end; i++) {
            System.out.print(tokens.get(i));
        }
        System.out.println("DDD");
         */

        //only 1 number
        if(tokens.size() == 1)
            return (int)(tokens.get(0));

        //we have ()
        int bracketStart = -1, bracketEnd = -1;
        int bracketCount = 0;

        ArrayList<Object> newList = new ArrayList<>();
        ArrayList<Object> newList2 = new ArrayList<>();
        for(int i=start; i<end; ++i){
            Object obj = tokens.get(i);

            if(obj instanceof Character){
                char c = (char)obj;
                if(c == '(') {
                    if(bracketCount == 0)
                        bracketStart = i;
                    bracketCount += 1;
                }
                else if(c == ')') {
                    bracketEnd = i;
                    bracketCount -=1;

                    if(bracketCount == 0) {
                        //evaluate the bracket content
                        int a = calculate(tokens, bracketStart+1, bracketEnd);
                        newList.add(a);

                        bracketStart = -1;
                        bracketEnd = -1;
                        bracketCount = 0;
                    }

                    continue;
                }
            }

            if(bracketStart == -1)
                newList.add(obj);
        }

        //now we have no bracket
        Stack<Integer> opdStack = new Stack<Integer>();
        Stack<Character> opStack = new Stack<Character>();

        Integer op1 = null;
        Integer op2 = null;
        Character op = null;


        for(int i=0; i<newList.size(); ++i){
            Object c = newList.get(i);
            if(c instanceof Character){
                op = (char)c;
                opStack.push((char) c);
            }
            else{
                opdStack.push((int) c);
                if(op != null){
                    if(op == '*' || op == '/'){
                        op2 = opdStack.pop();
                        op1 = opdStack.pop();
                        opStack.pop();
                        if(op == '*')
                            opdStack.push(op1 * op2);
                        else
                            opdStack.push(op1 / op2);
                    }
                    else if(op == '-')
                        opdStack.push(1);
                    else if(op == '+')
                        opdStack.push(1);
                }
            }
        }

        while(!opStack.isEmpty()){
            //op2 = opd
        }

        return 0;
    }

    private void debug(List<Object> x){
        //System.out.println(x);
    }

    public static void main(String[] args){
        String s =  "1-1+1";
        OnsiteQ3 q = new OnsiteQ3();
        System.out.println(q.calculate(s));

        s= "( (3/2 ) * (2+4)    ) ";
        System.out.println(q.calculate(s));

        s= " 3+5 / 2 ";
        System.out.println(q.calculate(s));
    }
}
