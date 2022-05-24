package ctci.sortsearch;

public class SortedMerge {

    void mergeB(int[] A, int Asize, int[] B, int Bsize){

        int pointerA = Asize-1;
        int pointerB = Bsize-1;

        int cur = Asize+Bsize;

        while(cur > 0){
            if(pointerB == -1)
                A[--cur] = A[pointerA--];
            else if(pointerA == -1)
                A[--cur] = B[pointerB--];
            else if(A[pointerA] > B[pointerB]){
                A[--cur] = A[pointerA--];
            }
            else{
                A[--cur] = B[pointerB--];
            }
        }
    }

    public static void main(String[] args) {
        SortedMerge test = new SortedMerge();
        int[] a = new int[]{0, 3, 5, 9, 12, -1, -1, -1};
        int[] b = new int[]{4, 7, 8};
        test.mergeB(a, 5,b, 3);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
