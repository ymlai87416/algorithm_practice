package Introduction.Palindrome;

import java.util.Scanner;

/**
 * Created by Tom on 15/4/2016.
 *
 * This suppose to be a easy question, but you not following the instruction... Started at 0:33 and end at 1:21, total time 48 mins.
 * problem: https://onlinejudge.org/external/112/11221.pdf
 *
 * #palindrome
 */
public class UVA11221 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<a; ++i){
            System.out.println("Case #"+(i+1)+":");
            String sr = sc.nextLine();
            sr = sr.replaceAll("[^a-z]", "");

            int sl = sr.length();
            int sqrt =(int) Math.sqrt(sl);
            if(sqrt * sqrt != sl) {
                System.out.println("No magic :(");
            }
            else{
                char[][] result = new char[sqrt][sqrt];
                int cnt = 0;
                for(int s=0; s<sqrt; ++s){
                    for(int t=0; t<sqrt; ++t){
                        result[s][t] = sr.charAt(cnt++);
                    }
                }

                int posx=0, posy=0;
                String[] sx = new String[4];
                StringBuilder sb=  new StringBuilder();
                for(int u=0; u< sl; ++u){
                    sb.append(result[posx][posy]);
                    posx = posx+1;
                    if(posx >= sqrt) { posy++; posx=0;}
                }
                sx[0] = sb.toString();

                posx=0; posy=0;
                sb=  new StringBuilder();
                for(int u=0; u< sl; ++u){
                    sb.append(result[posx][posy]);
                    posy = posy+1;
                    if(posy >= sqrt) { posx++; posy=0;}
                }
                sx[1] = sb.toString();

                posx=sqrt-1; posy=sqrt-1;
                sb=  new StringBuilder();
                for(int u=0; u< sl; ++u){
                    sb.append(result[posx][posy]);
                    posx = posx-1;
                    if(posx < 0 ) { posy--; posx=sqrt-1;}
                }
                sx[2] = sb.toString();

                posx=sqrt-1; posy=sqrt-1;
                sb=  new StringBuilder();
                for(int u=0; u< sl; ++u){
                    sb.append(result[posx][posy]);
                    posy = posy-1;
                    if(posy < 0 ) { posx--; posy=sqrt-1;}
                }
                sx[3] = sb.toString();

                boolean isp = true;
                for(int ii=0; ii<4; ++ii){
                    isp = isp && (sr.compareTo(sx[ii])  == 0);
                }

                if(isp)
                    System.out.println(sqrt);
                else
                    System.out.println("No magic :(");
            }
        }
    }
}
