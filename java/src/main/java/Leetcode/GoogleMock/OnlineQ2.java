package Leetcode.GoogleMock;

import Maths.Primes.Primes;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OnlineQ2 {

    public int deepestLeavesSum(TreeNode root) {

        PriorityQueue<Object[]> pq = new PriorityQueue<>(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                int d1 = (int)o1[1];
                int d2 = (int)o2[1];

                return d1-d2;
            }
        });

        pq.offer(new Object[]{root, 0});
        int r = 0;
        int curLvl = 0;

        while(!pq.isEmpty()){
            Object[] ul = pq.poll();
            TreeNode u = (TreeNode) ul[0];
            int du = (int)ul[1];

            if(curLvl == du)
                r +=  u.val;
            else {
                r = u.val;
                curLvl = du;
            }

            if(u.right != null) pq.offer(new Object[]{u.right, du+1});
            if(u.left != null) pq.offer(new Object[]{u.left, du+1});
        }

        return r;
    }

    public static void main(String[] args){
        OnlineQ2 q = new OnlineQ2();
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
