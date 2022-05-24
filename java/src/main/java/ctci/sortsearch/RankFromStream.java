package ctci.sortsearch;

public class RankFromStream{

    public static void main(String[] args) {
        int[] data = new int[]{5, 1, 4, 4, 5, 9, 7, 13, 3};

        RankFromStream test = new RankFromStream();
        for(int i=0; i<data.length; ++i)
            test.track(data[i]);

        System.out.println(test.getRankOfNumber(1));
        System.out.println(test.getRankOfNumber(3));
        System.out.println(test.getRankOfNumber(4));
    }
    RankTreeNode root;

    public RankFromStream(){
        root = null;
    }

    int getRankOfNumber(int x){
        RankTreeNode ptr = root;
        if(root == null) return -1;

        int rank = 0;
        while(ptr != null){
            if(ptr.val == x)
                return rank+ptr.leftSize;
            else if(ptr.val > x)
                ptr = ptr.left;
            else{
                rank = rank+1+ptr.leftSize;
                ptr = ptr.right;
            }
        }
        return -1;
    }

    void track(int x){
        if(root == null)
            root = new RankTreeNode(x);
        else{
            addHelper(root, x);
        }
    }


    void addHelper(RankTreeNode root, int val){
        if (root.val == val){
            root.leftSize += 1;
            //does not need to add it.
        }
        else if(root.val > val){
            if(root.left == null)
                root.left = new RankTreeNode(val);
            else
                addHelper(root.left, val);

            root.leftSize+=1;
        }
        else{
            if(root.right == null)
                root.right = new RankTreeNode(val);
            else
                addHelper(root.right, val);
        }
    }
}

class RankTreeNode{

    RankTreeNode left;
    RankTreeNode right;
    int val;
    int leftSize;

    public RankTreeNode(int val){
        this.val = val;
        this.leftSize = 0;
    }

}
