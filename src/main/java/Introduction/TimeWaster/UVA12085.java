package Introduction.TimeWaster;

import java.util.Scanner;

/**
 * Created by Tom on 14/4/2016.
 *
 * Start: 1:44 and End at 2:10 with 1 Presentation error for line of every output... total time: 26 mins.
 */
public class UVA12085 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int cnt = 1;
        while(true){
            int a = sc.nextInt();
            if(a==0) break;

            int prev = sc.nextInt(); int length=0;

            System.out.format("Case %d:\n", cnt++);
            for(int i=1; i<a; ++i){
                int cur = sc.nextInt();
                if(cur != prev+1){
                    if(length==0) System.out.println("0"+prev);
                    else{
                        int start = prev-length;
                        int end = prev;
                        String ss = "0"+String.valueOf(start);
                        String se = "0"+String.valueOf(end);
                        int si;
                        for(si=0; si<se.length(); ++si){
                            if(ss.charAt(si) == se.charAt(si))
                                System.out.print(ss.charAt(si));
                            else
                                break;
                        }
                        for(int ssi=si; ssi<ss.length(); ++ssi){
                            System.out.print(ss.charAt(ssi));
                        }
                        System.out.print("-");
                        for(int ssi=si; ssi<se.length(); ++ssi){
                            System.out.print(se.charAt(ssi));
                        }
                        System.out.println();
                    }
                    length=0;
                }
                else{
                    ++length;
                }
                prev =cur;
            }

            if(length==0) System.out.println("0"+prev);
            else{
                int start = prev-length;
                int end = prev;
                String ss = "0"+String.valueOf(start);
                String se = "0"+String.valueOf(end);
                int si;
                for(si=0; si<se.length(); ++si){
                    if(ss.charAt(si) == se.charAt(si))
                        System.out.print(ss.charAt(si));
                    else
                        break;
                }
                for(int ssi=si; ssi<ss.length(); ++ssi){
                    System.out.print(ss.charAt(ssi));
                }
                System.out.print("-");
                for(int ssi=si; ssi<se.length(); ++ssi){
                    System.out.print(se.charAt(ssi));
                }
                System.out.println();
            }
            //do the final output
            System.out.println();
        }
    }
}
