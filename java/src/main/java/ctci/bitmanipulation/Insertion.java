package ctci.bitmanipulation;

public class Insertion {
    public int insertion(int M, int N, int i, int j){
        int mask = 1 << (j-i + 1) -1;
        mask = mask << i;
        int r = N & ~mask;
        r = r | M << i;

        return r;
    }


    public static void main(String[] args) {
        int N= Integer.valueOf("10000000000", 2);
        int M = Integer.valueOf("10011", 2);
        Insertion test = new Insertion();
        int r = test.insertion(M, N, 2,3);
        System.out.println(Integer.toString(r, 2) );
    }
}
