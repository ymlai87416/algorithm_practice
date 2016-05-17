package DataStructure.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 17/4/2016.
 *
 * I think this is not greedy and try to create a new row if I can put it on top on any stack, but turn out it is just greedy
 * Non greedy approach TLE, and greedy approach just 0.08...
 */
public class UVA1062 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int cnt =0;
        while(true){
            String a = sc.nextLine();
            if(a.compareTo("end")==0) break;

            ArrayList<Stack<Character>> result = new ArrayList<Stack<Character>>();
            System.out.format("Case %d: %d\n", ++cnt, gen(a, 0, result )) ;
        }
    }

    public static int gen(String a, int pa, List<Stack<Character>> result){

        if(pa == a.length()) return result.size();

        int mindiff =Integer.MAX_VALUE; int loc = -1;
        Character c = a.charAt(pa);
        for(int i=0; i<result.size(); ++i){
            char cc = result.get(i).peek();
            if(cc >= c){
                int diff = cc-c;
                if(mindiff > diff) {
                    loc = i;
                    mindiff = diff;
                }
            }
        }

        if(loc == -1){
            Stack st = new Stack<Character>();
            st.push(c);
            result.add(st);
            int r=  gen(a, pa+1, result);
            result.remove(st);
            return r;
        }
        else {
            Stack st = result.get(loc);
            st.push(c);
            int r1 = gen(a, pa+1, result);
            st.pop();
            if(st.size() == 0)result.remove(st);
            return r1;
            /*st = new Stack<Character>();
            st.push(c);
            result.add(st);
            int r2=  gen(a, pa+1, result);
            result.remove(st);

            return Math.min(r1, r2);*/
        }
    }

}
