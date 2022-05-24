package ctci.recursion;

public class TowersOfHanoi {

    public void towerOfHanoi(int disk, int source, int target, int temp){
        if(disk == 0) return;

        towerOfHanoi(disk-1, source, temp, target);
        System.out.println("move disk " + disk + " from " + source + " to " + target);
        towerOfHanoi(disk-1, temp, target, source);
    }

    public static void main(String[] args) {
        TowersOfHanoi test  = new TowersOfHanoi();

        System.out.println("case 1: =======");
        test.towerOfHanoi(1, 0, 2, 1);
        System.out.println("case 2: =======");
        test.towerOfHanoi(2, 0, 2, 1);
        System.out.println("case 3: =======");
        test.towerOfHanoi(3, 0, 2, 1);
    }
}
