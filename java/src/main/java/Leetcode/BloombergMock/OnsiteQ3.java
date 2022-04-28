package Leetcode.BloombergMock;


public class OnsiteQ3 {

    int[] heights;
    int[] st;
    public int largestRectangleArea(int[] heights) {

        //find the shortest bar
        this.heights = heights;
        //it is either include it, or not include it
        int n = this.heights.length;
        this.st = new int[n*4];

        //build this segment tree
        build(1, 0, n-1);

        return helper(0, n);
    }

    private void build(int p, int L, int R){
        if(L == R){
            st[p] = L;
        }
        else{
            int mid = (L+R)/2;
            build(2*p,  L, mid);
            build(2*p+1, mid+1, R);
            if(heights[st[2*p]] < heights[st[2*p+1]])
                st[p] = st[2*p];
            else
                st[p] = st[2*p+1];
        }
    }

    private int rmq(int p, int L, int R, int i, int j){
        if(i > R || j < L) return -1;  //0 only work in RSQ, RMQ please use back -1
        if(i == L && j == R) return st[p];
        else{
            int mid = (L+R)/2;
            int left  = rmq(2*p, L, mid, i, Math.min(mid, j));
            int right  = rmq(2*p+1, mid+1, R, Math.max(i, mid+1), j);

            if(left == -1) return right;
            else if(right==-1) return left;
            else if(heights[left] < heights[right]) return left;
            else return right;
        }
    }

    public int helper(int start, int end){
        //shortest loc
        if(start == end) return 0;
        if(start + 1== end) return heights[start];

        long dpIdx = start* 100000 + end;

        int result = 0;
        int shortLoc, shortVal;
        //This is bad
        shortLoc = rmq(1, 0, heights.length-1, start, end-1);
        shortVal = heights[shortLoc];

        result = shortVal * (end - start);

        int left=  helper(start, shortLoc);
        int right = helper(shortLoc+1, end);
        result = Math.max(result, Math.max(left, right));

        return result;
    }

    public static void main(String[] args){
        int[] heights = {2,1,5,6,2,3};
        OnsiteQ3 s = new OnsiteQ3();
        System.out.println(s.largestRectangleArea(heights));
    }

}
