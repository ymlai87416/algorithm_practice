package ctci.hard;

public class AddWithoutPlus {

    public int addWithoutPlus(int a, int b){
        int p = a; int q = b;
        while(true){
            int r = p ^ q;
            int c = (p & q) << 1;

            p = r;
            q = c;

            if(q == 0) break;
        }

        return p;
    }


    public static void main(String[] args) {
        AddWithoutPlus sol = new AddWithoutPlus();
        System.out.println(sol.addWithoutPlus(759, 674));
    }
}
