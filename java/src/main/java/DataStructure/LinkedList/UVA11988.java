package DataStructure.LinkedList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Tom on 17/4/2016.
 *
 * problem: https://onlinejudge.org/external/119/11988.pdf
 *
 * #Lv1 #linked_list #UVA
 *
 */
public class UVA11988 {

    static class Text{
        LinkedList<String> b = new LinkedList<>();
        int curIdx = 0;

        public void add(String ss, int state){
            if(state == 0) {
                b.add(curIdx, ss);
                curIdx += 1;
            }
            else if(state == 1){
                b.add(0, ss);
                curIdx = 1;
            }
            else if(state==2){
                b.offerLast(ss);
                curIdx = b.size() -1;
            }
        }


        public String toString(){
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = b.iterator();
            while(it.hasNext()) {
                sb.append(it.next());
            }
            return sb.toString();
        }
    }


    public static void main(String[] args){
        Scanner sc=  new Scanner(System.in);

        while(true){
            if(!sc.hasNext()) break;
            String str = sc.nextLine();

            Text t = new Text();
            int i, s=-1, state = 0;
            for (i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '['){
                    //process
                    if(s!=-1){
                        String ss= str.substring(s, i);
                        t.add(ss, state);
                    }
                    state = 1;
                    s = -1;
                }
                else if(str.charAt(i) == ']'){
                    //process
                    if(s!=-1){
                        String ss= str.substring(s, i);
                        t.add(ss, state);
                    }
                    state = 2;
                    s = -1;
                }
                else if(s == -1) s = i;

            }

            //process
            if(s!=-1){
                String ss= str.substring(s, i);
                t.add(ss, state);
            }

            System.out.println(t.toString());
        }
    }
}
