package StringProcessing.CipherEncodeEncryptDecodeDecryptEasier;

import java.util.Scanner;

/**
 * Created by Tom on 21/4/2016.
 * Start at 1:29 and AC at 1:40, total time 11 mins.
 *
 * problem: https://onlinejudge.org/external/112/11278.pdf
 * #UVA #Lv3 #string
 */
public class UVA11278 {
    static String qwer= "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>? \t";
    static String dkey= "`123qjlmfp/[]456.orsuyb;=\\789aehtdck-0zx,inwvg'~!@#QJLMFP?{}$%^>ORSUYB:+|&*(AEHTDCK_)ZX<INWVG\" \t";
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            String s = sc.nextLine();

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<s.length(); ++i){
                sb.append(dkey.charAt(qwer.indexOf(s.charAt(i))));
            }
            System.out.println(sb.toString());
        }
    }
}
