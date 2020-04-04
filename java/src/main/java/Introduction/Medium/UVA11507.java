package Introduction.Medium;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Tom on 13/4/2016.
 * Start at 15: 21, formulat  and then AC at 15:56, total time = 25 minutes.
 */
public class UVA11507 {
    static class Pair{
        String a; String b;
        public Pair(String a, String b){this.a =a; this.b=b;}
        public boolean equals(Object obj){
            Pair p = (Pair) obj;
            if( p == null) return false;
            return a.compareTo(p.a) == 0  && b.compareTo(p.b)  == 0;
        }
        public int hashCode(){
            return a.hashCode() * b.hashCode();
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        HashMap<Pair, String> lookup = new HashMap<Pair, String>();
        lookup.put(new Pair("+y", "+y"), "-x");
        lookup.put(new Pair("+y", "-y"), "+x");
        lookup.put(new Pair("+y", "+z"), "+z");
        lookup.put(new Pair("+y", "-z"), "-z");
        lookup.put(new Pair("+y", "-x"), "-y");
        lookup.put(new Pair("+y", "+x"), "+y");

        lookup.put(new Pair("-y", "+y"), "+x");
        lookup.put(new Pair("-y", "-y"), "-x");
        lookup.put(new Pair("-y", "+z"), "+z");
        lookup.put(new Pair("-y", "-z"), "-z");
        lookup.put(new Pair("-y", "-x"), "+y");
        lookup.put(new Pair("-y", "+x"), "-y");

        lookup.put(new Pair("+z", "+y"), "+y");
        lookup.put(new Pair("+z", "-y"), "-y");
        lookup.put(new Pair("+z", "+z"), "-x");
        lookup.put(new Pair("+z", "-z"), "+x");
        lookup.put(new Pair("+z", "-x"), "-z");
        lookup.put(new Pair("+z", "+x"), "+z");

        lookup.put(new Pair("-z", "+y"), "+y");
        lookup.put(new Pair("-z", "-y"), "-y");
        lookup.put(new Pair("-z", "+z"), "+x");
        lookup.put(new Pair("-z", "-z"), "-x");
        lookup.put(new Pair("-z", "-x"), "+z");
        lookup.put(new Pair("-z", "+x"), "-z");

        while(true){
            int a =sc.nextInt();
            if(a==0) break;
            sc.nextLine();

            String pos = "+x";
            for(int i=a-1; i>0; --i){
                String t = sc.next();
                if(t.compareTo("No") == 0){
                    //pos = pos;
                } else {
                    pos = lookup.get(new Pair(t, pos));
                }
            }

            System.out.println(pos);
        }
    }
}
