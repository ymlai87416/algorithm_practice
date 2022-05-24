package ctci.bitmanipulation;

public class Conversion {
    public int conversion(int A, int B){
        int x = A ^ B;
        int cnt = 0;
        while( x > 0){
            x = x & (x-1);
            ++cnt;
        }
        return cnt;
    }


    public static void main(String[] args) {
        Conversion test =  new Conversion();
        System.out.println(test.conversion(29, 15));
    }
}
