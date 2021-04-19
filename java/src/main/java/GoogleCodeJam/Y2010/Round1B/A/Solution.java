package GoogleCodeJam.Y2010.Round1B.A;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Tom on 30/4/2016.
 *
 * Total time to implements 19 minutes.
 */
public class Solution {

    class TreeNode<T extends Comparable<T>> {
        T data;
        TreeNode<T> parent;
        List<TreeNode<T>> children;

        public TreeNode(T data) {
            this.data = data;
            this.children = new LinkedList<TreeNode<T>>();
        }

        public TreeNode<T> addChild(T child) {
            TreeNode<T> childNode = new TreeNode<T>(child);
            childNode.parent = this;
            this.children.add(childNode);
            return childNode;
        }

        public boolean containChild(T child){
            for(TreeNode<T> obj : children)
                if(obj.data.compareTo(child) == 0) return true;

            return false;
        }

        public TreeNode<T> getChild(T child){
            for(TreeNode<T> obj : children)
                if(obj.data.compareTo(child)==0) return obj;

            return null;
        }
    }

    static String   FILENAME;
    static Scanner  sc;
    static String   IN;
    static String   OUT;
    static PrintStream     out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2008\\Round1C\\A\\A-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    private void solve() {
        int ans = 0;

        int nexist = sc.nextInt();
        int ncheck = sc.nextInt();
        sc.nextLine();
        TreeNode<String> fileSys = new TreeNode<String>("root");

        for(int i=0; i<nexist; ++i){
            TreeNode<String> current = fileSys;
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s, "/");
            while(st.hasMoreTokens()){
                String b = st.nextToken();
                if(!current.containChild(b)) current.addChild(b);
                current = current.getChild(b);
            }
        }

        for(int i=0; i<ncheck; ++i){
            TreeNode<String> current = fileSys;
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s, "/");
            while(st.hasMoreTokens()){
                String b = st.nextToken();
                if(!current.containChild(b)){
                    ++ans;
                    current.addChild(b);
                }
                current = current.getChild(b);
            }
        }

        out.println(ans);
    }

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ": ");
            solve();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
