package ctci.treegraph;
import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;  //only valid if the question specifies

    public TreeNode(){}
    public TreeNode(int value){ val = value; }

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(val);
        buffer.append('\n');
        List<TreeNode> children;
        if(left == null && right == null) children = Collections.emptyList();
        else if(left == null) children = List.of(right);
        else if(right == null) children = List.of(left);
        else children = List.of(left, right);
        for (Iterator<TreeNode> it = children.iterator(); it.hasNext();) {
            TreeNode next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }


}
