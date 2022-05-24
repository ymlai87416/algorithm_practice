package ctci.moderate;

public class NumberMax {

    //return 0 if positive, 1 if negative
    private int sign(int r){
        return r >>31 & 0x1;
    }

    public int numberMax(int a, int b){
        int k = sign(a-b);
        int q = 1 -k;
        return a * q + b * k;
    }


    public static void main(String[] args) {
        NumberMax t = new NumberMax();
        System.out.println(t.numberMax(30, 14));
    }
}
