package ctci.treegraph;

import java.util.*;

public class Utility {

    static Random r = new Random();
    public static TreeNode createRandomTree(int d){
        if(d == 0) return null;
        TreeNode tn = getTreeNode(0);
        if(r.nextBoolean())
            tn.left = createRandomTree(d-1);
        if(r.nextBoolean())
            tn.right = createRandomTree(d-1);

        return tn;
    }

    public static TreeNode createRandomTree(){
        return createRandomTree(5);
    }

    static HashMap<Integer, Integer> cnt = new HashMap<>();
    public static TreeNode getTreeNode(int session){
        int v = cnt.getOrDefault(session, 0);
        cnt.put(session, v+1);
        return new TreeNode(v);
    }

    private static int idx = 0;
    private static Integer[] data = null;
    public static TreeNode createTree(Integer[] data){
        idx = 0;
        Utility.data =  data;
        return createTreeH();
    }
    private static TreeNode createTreeH(){
        TreeNode r = new TreeNode(data[idx++]);

        Queue<TreeNode> q  = new ArrayDeque<>();
        q.offer(r);
        while(!q.isEmpty()){
            TreeNode u = q.poll();

            u.left = data[idx] == null ? null : new TreeNode(data[idx]);
            ++idx;
            u.right = data[idx] == null ? null : new TreeNode(data[idx]);
            ++idx;

            if(u.left != null) q.offer(u.left);
            if(u.right != null) q.offer(u.right);
        }

        return r;
    }

    public static TreeNode findNode(TreeNode r, int v){
        if(r == null) return null;
        if(r.val == v)
            return r;

        TreeNode l = findNode(r.left, v);
        if(l != null) return l;
        TreeNode rr = findNode(r.right, v);
        if(rr != null) return rr;

        return null;
    }
}
