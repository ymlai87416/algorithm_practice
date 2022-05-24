package ctci.moderate;

public class DivingBoard {

    public int[] divingBoard(int shorter, int longer, int k){
        int[] result = new int[k+1];
        for(int i=0; i<=k; ++i){
            result[i] = longer * i + shorter * (k-i);
        }
        return result;
    }

    public static void main(String[] args) {
        DivingBoard test = new DivingBoard();
        var r = test.divingBoard(3, 5, 6);

        for(int i=0; i<r.length; ++i)
            System.out.print(r[i] + " ");

    }
}
