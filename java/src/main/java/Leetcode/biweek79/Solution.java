package Leetcode.biweek79; /*
["BookMyShow","gather","scatter","gather","gather","gather"]
        [[5,9],[10,1],[3,3],[9,1],[10,2],[2,0]]

[null,[],true,[0,0],[],[]]


        [null,[0,0],[],true,true]*/
public class Solution {

    public static void main(String[] args) {
        /*
        BookMyShow test = new BookMyShow(5, 9);
        test.gather(10, 1);
        test.scatter(3, 3);
        test.gather(9, 1);
        test.gather(10, 2);
        test.gather(2, 0);*/
        BookMyShow test = new BookMyShow(3,999999999);
        test.scatter(1000000000, 2);
        test.gather(1000000000, 2);
        test.gather(1000000000, 2);
        test.gather(1000000000, 2);
    }
}


class BookMyShow {
    int n;
    int m;
    FenwickTree ft;
    int[] seat;
    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        ft = new FenwickTree(n);
        seat = new int[n];

        for(int i=0; i<n; ++i){
            seat[i] = m;
            ft.adjust(i+1, m);
        }

    }


    private void updateRowLeftSeat(int row, int seatL){
        int oldV = seat[row];
        seat[row] = seatL;
        ft.adjust(row+1, seatL - oldV);
    }

    public int[] gather(int k, int maxRow) {
        System.out.println("GG");
        for(int i=0; i<=maxRow; ++i){
            if(seat[i] >= k){
                int[] rr = new int[]{i, m-seat[i]};
                updateRowLeftSeat(i, seat[i]-k);
                return rr;
            }
        }
        return new int[0];
    }

    public boolean scatter(int k, int maxRow) {
        //now check enough seat, if so, update each row
        System.out.println("SS");

        long leftS = ft.rsq(maxRow+1);
        if(leftS < k) return false;

        //now update each line
        int kk = k;
        for(int i=0; i<=maxRow && kk> 0; ++i){
            if(seat[i] > 0){
                int seatT = kk > seat[i] ? seat[i] : kk;
                int newLeft = seat[i] - seatT;
                updateRowLeftSeat(i, newLeft);
                kk -= seatT;
            }
        }

        return true;
    }
}

class FenwickTree {
    private long[] ft; // recall that vi is: typedef vector<int> vi;
    public FenwickTree(int n) { ft = new long[n+1]; } // init n + 1 zeroes
    private int LSOne(int S){ return S & -S;}
    long rsq(int b) { // returns RSQ(1, b)
        long sum = 0; for (; b != 0; b -= LSOne(b)) sum += ft[b];
        return sum; } // note: LSOne(S) (S & (-S))
    long rsq(int a, int b) { // returns RSQ(a, b)
        return rsq(b) - (a == 1 ? 0 : rsq(a - 1)); }
    // adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
    void adjust(int k, long v) { // note: n = ft.size() - 1
        for (; k < ft.length; k += LSOne(k)) ft[k] += v; }
};