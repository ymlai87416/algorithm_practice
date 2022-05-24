package ctci.moderate;

public class NumberSwapper {

    public void numberSwapper(){

        int a = 10;
        int b = 20;
        System.out.println("Before: " + a + " " + b);
        a = a ^ b;  //10 ^ 20
        b = a ^ b;  //10 ^ 20 ^ 20 = 10
        a = a ^ b; //10 ^ 20 ^ 10 = 20

        System.out.println("After: " + a + " " + b);
    }


    public static void main(String[] args) {
        NumberSwapper test = new NumberSwapper();
        test.numberSwapper();
    }
}
