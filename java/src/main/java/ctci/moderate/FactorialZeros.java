package ctci.moderate;

public class FactorialZeros {
    public int factorialZeros(int n){
        //count the power of2
        //count the power of 5
        //we only need to count how many 5 are there

        int cnt = 0;

        while(n > 0){
            cnt = cnt + n/5;
            n = n/5;
        }

        return cnt;
    }

    public static void main(String[] args) {
        FactorialZeros test = new FactorialZeros();

        System.out.println(factorial(18)  + ": "  + test.factorialZeros(18));
        System.out.println(factorial(12)  + ": "  + test.factorialZeros(12));
    }

    private static long factorial(int n){
        long r = 1;
        for(int i=1; i<=n; ++i){
            r *= i;
        }
        return r;
    }

}
