package Leetcode.FacebookMock;


public class PhoneQ1 {

    public int bulbSwitch(int n) {
        //n = 10^9
        //prime

        /*
            11 -> 10
            111 -> 101 -> 100
            1111 -> 1010 -> 1000 -> 1001
            11111 -> 10101 -> 10001 -> 10011 -> 10010
            111111 -> 101010 -> 100011 -> 100111 -> 100101 -> 100100
            1111111 -> 1001001 - 1001000
            for prime number, can only toggle once => prime number always off
            for non prime, we can only toggle
            for k where k > n/2, can only toggle one light, but on and off we don't know
            the last n round only toggle 1 lightbulb

            15 = 3x5, 1x15, they will flip 2n time
            36 = 6x6, 3x12, 4x9, 1x36, ... flip (2n+1) time
         */
        if(n==0) return 0;
        if(n==1) return 1;

        //we hope we can make use of sparse to help use reduce the memory usage
        boolean[] s = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            s[i] = true;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; i*j <=n; j++) {
                s[i*j] = !s[i*j];
            }
        }
        int turnOn = 0;
        for (int i = 1; i <= n; i++) {
            if(s[i]) turnOn++;
        }



        return turnOn;
    }

    public static void main(String[] args){
        PhoneQ1 s = new PhoneQ1();

        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ": " + s.bulbSwitch(i));
        }
    }
}
