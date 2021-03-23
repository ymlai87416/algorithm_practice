package B;

import java.util.Random;

public class Generate {
    public static void main(String[] args) {
        String key = "TPFOXLUSHB";
        System.out.println("1");
        System.out.println("2");
        int max = 99;
        Random r  = new Random();
        for(int i=0; i<10000; ++i){
            int a = r.nextInt(max+1);
            int b = r.nextInt(a+1);
            System.out.println(a + " " + convToString(key, b));
        }
    }

    private static String convToString(String key, int N){
        String result = "";
        if(N == 0) return key.substring(0, 1);
        while(N > 0){
            int n = N % 10;
            N = N/10;
            result = key.charAt(n) + result;
        }
        return result;
    }
}
