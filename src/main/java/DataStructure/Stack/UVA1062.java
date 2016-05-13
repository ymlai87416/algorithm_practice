package DataStructure.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Tom on 17/4/2016.
 */
public class UVA1062 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            String a = sc.nextLine();
            if(a.compareTo("end")==0) break;

            ArrayList<Stack<Character>> result = new ArrayList<Stack<Character>>();
            System.out.println(gen(a, 0, result )) ;
        }
    }

    public static int gen(String a, int pa, List<Stack<Character>> result){
        if(pa == a.length()) return result.size();

        int mindiff =Integer.MAX_VALUE; int loc = -1;
        Character c = a.charAt(pa);
        for(int i=0; i<result.size(); ++i){
            char cc = result.get(i).peek();
            if(cc > c){
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
        }
        else
            result.get(loc).push(c);

        return gen(a, pa+1, result);
    }
}
