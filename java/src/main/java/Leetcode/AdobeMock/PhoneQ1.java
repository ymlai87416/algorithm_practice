package Leetcode.AdobeMock;

public class PhoneQ1 {

    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;

        int p1=1; int p2=2;
        for (int i = 3; i <= n; i++) {
            int x = p1+p2;
            p1=p2;
            p2 =x;
        }

        return p2;
    }

    public static void main(String[] args){

    }
}
