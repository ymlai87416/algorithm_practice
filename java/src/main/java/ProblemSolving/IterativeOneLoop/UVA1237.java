package ProblemSolving.IterativeOneLoop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Tom on 17/4/2016.
 */
public class UVA1237 {

    // Structure to represent an interval
    class Interval
    {
        int low, high;
        String band;
        public Interval(int low, int high, String band){
            this.low = low;
            this.high = high;
            this.band = band;
        }
    };

    // Structure to represent a node in Interval Search Tree
    class ITNode
    {
        Interval i;  // 'i' could also be a normal variable
        int max;
        ITNode left, right;
    };

// A utility function to create a new Interval Search Tree Node
    ITNode newNode(Interval i)
    {
        ITNode temp = new ITNode();
        temp.i = new Interval(i.low, i.high, i.band);
        temp.max = i.high;
        temp.left = temp.right = null;

        return temp;
    }

// A utility function to insert a new Interval Search Tree Node
// This is similar to BST Insert.  Here the low value of interval
// is used tomaintain BST property
    ITNode insert(ITNode root, Interval i)
    {
        // Base case: Tree is empty, new node becomes root
        if (root == null)
            return newNode(i);

        // Get low value of interval at root
        int l = root.i.low;

        // If root's low value is smaller, then new interval goes to
        // left subtree
        if (i.low < l)
            root.left = insert(root.left, i);

            // Else, new node goes to right subtree.
        else
            root.right = insert(root.right, i);

        // Update the max value of this ancestor if needed
        if (root.max < i.high)
            root.max = i.high;

        return root;
    }

    // A utility function to check if given two intervals overlap
    boolean doOVerlap(Interval i1, Interval i2)
    {
        if (i1.low <= i2.high && i2.low <= i1.high)
            return true;
        return false;
    }

// The main function that searches a given interval i in a given
// Interval Tree.
    List<Interval> overlapSearch(ITNode root, Interval i)
    {
        // Base Case, tree is empty
        if (root == null) return new ArrayList<Interval>();

        ArrayList<Interval> result = new ArrayList<Interval>();
        // If given interval overlaps with root
        if (doOVerlap(root.i, i))
            result.add(root.i);

        // If left child of root is present and max of left child is
        // greater than or equal to given interval, then i may
        // overlap with an interval is left subtree
        if (root.left != null && root.left.max >= i.low) {
            result.addAll(overlapSearch(root.left, i));
            result.addAll(overlapSearch(root.right, i));
        }
        else
            result.addAll(overlapSearch(root.right, i));

        // Else interval can only overlap with right subtree
        return result;
    }

    void inorder(ITNode root, String path)
    {
        if (root == null) return;
        inorder(root.left, path+"L");
        System.out.format("%s [%d, %d] max = %d (%s)\n", path, root.i.low, root.i.high, root.max, root.i.band);

        inorder(root.right, path+"R");
    }

    public void run(){
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        for(int i=0; i<c; ++i){

            int b = sc.nextInt();

            ITNode root = null;
            for(int j=0; j<b; ++j){
                String band = sc.next();
                int ll = sc.nextInt();
                int hh = sc.nextInt();
                root = insert(root, new Interval(ll, hh, band));
            }

            //inorder(root, "");
            int q = sc.nextInt();
            for(int j=0; j<q; ++j){
                int qq = sc.nextInt();
                Interval ii = new Interval(qq, qq, null);
                List<Interval> pos = overlapSearch(root, ii);
                if(pos.size() > 1 || pos.size() == 0)
                    System.out.println("UNDETERMINED");
                else
                    System.out.println(pos.get(0).band);
            }

            if(i != c-1) System.out.println();
        }
    }


    public static void main(String[] args){
        UVA1237 obj= new UVA1237();
        obj.run();
    }
}
