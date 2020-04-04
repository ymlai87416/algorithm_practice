package DataStructure.Stack;

import java.util.*;

/**
 * Created by Tom on 17/4/2016.
 *
 * Start at 10:13, and AC at 10:49. total time = 36 minutes.
 */
public class UVA732 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            if(!sc.hasNext()) break;
            String a = sc.nextLine();
            String b = sc.nextLine();

            Stack<Character> temp = new Stack<Character>();
            List<String> result = gen(a, b, temp, 0, 0, "");

            Collections.sort(result);

            System.out.println("[");
            for(int i=0; i<result.size(); ++i){
                System.out.println(result.get(i).substring(1));
            }
            System.out.println("]");
        }
    }

    public static List<String> gen(String a, String b, Stack<Character> temp, int pa, int pb, String ops){
        if(pa == a.length() && pb == b.length()) {
            List<String> result=  new ArrayList<String>();
            result.add(ops);
            return result;
        }
        if(temp.size() > 0){
            if(pa == a.length()){
                Character c = temp.pop();
                List<String> result = null;
                if(c != b.charAt(pb))
                    result = new ArrayList<String>();
                else{
                    result = gen(a, b, temp, pa, pb+1, ops + " o");
                }
                temp.push(c);
                return result;
            } else {
                List<String> resulti = null, resulto = null;
                Character c = (a.charAt(pa));
                temp.push(c);
                resulti = gen(a, b, temp, pa+1, pb, ops + " i");
                temp.pop();

                c = temp.pop();
                if(c == b.charAt(pb))
                    resulto = gen(a, b, temp, pa, pb+1, ops+" o");
                temp.push(c);

                if(resulto != null){
                    resulti.addAll(resulto);
                    return resulti;
                }
                else
                    return resulti;
            }
        } else if(temp.size() == 0){
            if(pa < a.length()) {
                char c = a.charAt(pa);
                temp.push(c);
                List<String> result = gen(a, b, temp, pa+1, pb, ops + " i");
                temp.pop();
                return result;
            }
            else return new ArrayList<String>();
        }
        else{
            return new ArrayList<String>();
        }
    }
}
