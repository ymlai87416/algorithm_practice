package Introduction.TimeWaster;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Tom on 14/4/2016.
 * Start at 1:19 and AC 1:43 and total time: 24 minutes
 */
public class UVA12060 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int cnt=0;
        while(true){
            ++cnt;
            int a = sc.nextInt();
            if(a == 0) break;

            int sum = 0;
            for(int i=0; i<a; ++i){
                sum += sc.nextInt();
            }

            boolean sign = sum >= 0;
            int ra = Math.abs(sum / a);
            int rb = Math.abs(sum % a);
            int rc = a;

            if(rb != 0){
                int d =  gcd(rb, rc);
                rb /= d;
                rc /= d;
            }

            String srb = String.valueOf(rb);
            String src = String.valueOf(rc);
            String sra = String.valueOf(ra);

            System.out.format("Case %d:\n", cnt);
            if(rb == 0){
                if(!sign){
                    System.out.print("- ");
                    System.out.println(sra);
                }
                else{
                    System.out.println(sra);
                }
            } else{
                if(!sign){
                    if(ra == 0){
                        for(int tt=0; tt<+2; ++tt) System.out.print(" ");
                        System.out.format("%"+src.length()+"s", srb);
                        System.out.println("");
                        System.out.print("- ");
                        for(int tt=0; tt< src.length(); ++tt) System.out.print("-");
                        System.out.println();
                        for(int tt=0; tt<+2; ++tt) System.out.print(" ");
                        System.out.println(src);
                    }
                    else{
                        for(int tt=0; tt<sra.length()+2; ++tt) System.out.print(" ");
                        System.out.format("%"+src.length()+"s", srb);
                        System.out.println("");
                        System.out.print("- ");
                        System.out.print(ra);
                        for(int tt=0; tt< src.length(); ++tt) System.out.print("-");
                        System.out.println();
                        for(int tt=0; tt<sra.length()+2; ++tt) System.out.print(" ");
                        System.out.println(src);
                    }

                }
                else{
                    if(ra == 0){
                        System.out.format("%"+src.length()+"s", srb);
                        System.out.println("");
                        for(int tt=0; tt< src.length(); ++tt) System.out.print("-");
                        System.out.println();
                        System.out.println(src);
                    }
                    else{
                        for(int tt=0; tt<sra.length(); ++tt) System.out.print(" ");
                        System.out.format("%"+src.length()+"s", srb);
                        System.out.println("");
                        System.out.print(ra);
                        for(int tt=0; tt< src.length(); ++tt) System.out.print("-");
                        System.out.println();
                        for(int tt=0; tt<sra.length(); ++tt) System.out.print(" ");
                        System.out.println(src);
                    }
                }
            }
        }
    }

    public static int gcd(int b, int c){
        BigInteger bb = BigInteger.valueOf(b);
        BigInteger bc = BigInteger.valueOf(c);
        BigInteger r = bb.gcd(bc);
        return r.intValue();
    }
}
