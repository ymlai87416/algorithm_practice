package ctci.treegraph;

import java.util.*;

public class RandomNode {
    //this is a binary search tree, duplicate insert to left tree
    RandomTreeNode root;
    Random r;

    public RandomNode(){
        root = null;
        r = new Random();
    }

    void insert(int val){
        root = insertHelper(root, val);
    }

    private RandomTreeNode insertHelper(RandomTreeNode root, int val){
        if(root == null) return new RandomTreeNode(val);
        if(root.val >= val)
            root.left = insertHelper(root.left, val);
        else
            root.right = insertHelper(root.right, val);
        root.size+=1;

        return root;
    }

    boolean find(int val){
        if(root == null) return false;
        return findHelper(root, val);
    }

    RandomTreeNode getRandomNode(){
        if(root == null) return null;
        int rand = r.nextInt(root.size);

        //now check where should I go
        return randHelper(root, rand);
    }

    private RandomTreeNode randHelper(RandomTreeNode root, int val){
        int leftS = root.left == null ? 0: root.left.size;
        int rightS = root.right == null? 0: root.right.size;

        if(val == leftS)
            return root;
        else if(val < leftS)
            return randHelper(root.left, val);
        else
            return randHelper(root.right, val-leftS-1);
    }

    boolean findHelper(RandomTreeNode root, int val){
        if(root.val == val)
            return true;
        else if(root.val > val)
            return findHelper(root.left, val);
        else
            return findHelper(root.right, val);
    }

    void delete(int val){
        //not implemented for the time being
    }

    //Hints: #42, #54, #62, #75, #89, #99, #112, #119
    public static void main(String[] args) {
        RandomNode test = new RandomNode();
        test.insert(20);
        test.insert(10);
        test.insert(30);
        test.insert(5);
        test.insert(15);
        test.insert(3);
        test.insert(7);
        test.insert(17);

        HashMap<Integer, Integer> randF = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            RandomTreeNode rn = test.getRandomNode();
            randF.put(rn.val, randF.getOrDefault(rn.val, 0)+1);
        }
        System.out.println(randF);
        //{17=13, 3=9, 20=10, 5=7, 7=13, 10=13, 30=10, 15=25}
    }
}


class RandomTreeNode{
    int val;
    RandomTreeNode left;
    RandomTreeNode right;
    int size;

    public RandomTreeNode(int val){
        this.val = val;
        this.size = 1;
    }
}