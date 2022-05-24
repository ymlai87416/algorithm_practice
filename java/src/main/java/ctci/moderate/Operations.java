package ctci.moderate;

public class Operations {

    public int multiply(int a, int b){
        if(b ==  1) return a;
        if(b == 0) return 0;

        if(b > a) return multiply(b, a);

        int hm = multiply(a, b/2);
        if(b %2 == 0)
            return hm+hm;
        else
            return hm+hm+a;
    }

    public int subtract(int a, int b){

        if(b == 0) return a;
        //now the 2’s complement
        int negB = b ^ 0xffffffff + 1;
        return a+b;
    }

    public int divide(int a, int b){

        int t = 0;
        int r = 0;
        while(t + b <= a){
            //find n such that b * 2^n + t <= a
            int lr = 1;
            int lb = b;

            while(lb + lb + t <= a){
                lb = lb + lb;
                lr = lr+ lr;
            }

            r = r+ lr;
            t = t+lb;
        }

        return r;

    }

    public static void main(String[] args) {
        Operations test = new Operations();
        System.out.println("14x53=" + test.multiply(14, 53));
        System.out.println("14-53=" + test.subtract(14, 53));
        System.out.println("53/14=" + test.divide(53, 14));
    }
}
