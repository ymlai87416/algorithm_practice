package ctci.recursion;
import java.util.*;

public class StackOfBoxes {

    int[] memo;
    int n;
    int[][] boxes;
    public int stackOfBoxes(int[][] boxes){
        this.boxes = boxes;
        this.n = boxes.length;

        Comparator compare = new Comparator<int[]>(){
            public int compare(int[] b1,int[] b2){
                if(b1[1] == b2[1])  //same height
                    if(b1[0] == b2[0])
                        return b2[2] - b1[2];
                    else
                        return b2[0] - b1[0];
                else
                    return b2[0] - b1[0];
            }
        };

        Arrays.sort(boxes, compare);
        Arrays.fill(memo, -1);
        int result = 0;

        for(int i=0; i<boxes.length; ++i){
            if(memo[i] == -1)
                stackHelper(i);

            if(memo[i] > result)
                result = memo[i];
        }

        return result;
    }

    private int stackHelper(int index){
        if(index == n) return 0;
        if(memo[index] != -1)
            return memo[index];

        //find the next one?
        int h= 0;

        for(int i=index+1; i< n; ++i){
            if(canStack(boxes[index], boxes[i]))
                h = Math.max(h, stackHelper(i) + boxes[index][1]);
        }

        memo[index] = h;
        return h;
    }

    //assume strictly larger means only in each dimension...
    private boolean canStack(int[] base, int[] top){
        return base[0] > top[0] && base[1] > top[1] && base[2] > top[2];
    }


    public static void main(String[] args) {

    }
}
