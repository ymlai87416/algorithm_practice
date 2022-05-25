package ctci.hard;

class BiNodeTest {

    public BiNode[] convertTreeToList(BiNode root){
        if(root ==  null) return null;
        if(root.node1 == null && root.node2 == null)
            return new BiNode[]{root, root};

        BiNode resultH, resultT, ptr;
        if(root.node1 != null){
            //do on left tree
            BiNode[] leftList = convertTreeToList(root.node1);
            BiNode leftHead = leftList[0];
            BiNode leftTail = leftList[1];

            resultH = leftHead;
            leftHead.node1 =null;

            leftTail.node2 = root;
            root.node1 = leftTail;
            resultT = root;
        }
        else{
            resultH = root;
            root.node1= null;
            resultT = root;
        }

        if(root.node2 != null){

            //append result of right tree
            BiNode[] rightList = convertTreeToList(root.node2);
            BiNode rightHead = rightList[0];
            BiNode rightTail = rightList[1];

            root.node2 = rightHead;
            rightHead.node1 = root;

            resultT = rightTail;
        }

        //return the head of left tree as head
        return new BiNode[]{resultH, resultT};
    }


    public static void main(String[] args) {

        BiNode b4 = new BiNode(4);
        BiNode b2 = new BiNode(2);
        BiNode b5 = new BiNode(5);
        BiNode b1 = new BiNode(1);
        BiNode b3 = new BiNode(3);
        BiNode b6 = new BiNode(6);
        BiNode b0 = new BiNode(0);

        b4.node1=  b2; b4.node2 = b5;
        b2.node1 = b1; b2.node2 = b3;
        b1.node1 = b0;
        b5.node2 = b6;

        BiNodeTest test = new BiNodeTest();
        BiNode[] list = test.convertTreeToList(b4);
        BiNode head = list[0];
        BiNode tail = list[1];

        while(head != null){
            System.out.print(head.data + " ");
            head= head.node2;
        }

        System.out.println();

        while(tail != null){
            System.out.print(tail.data + " ");
            tail= tail.node1;
        }

        System.out.println();

    }
}

public class BiNode {
    public BiNode node1, node2;
    public int data;

    public BiNode(){}
    public BiNode(int data){
        this.data = data;
    }
}


