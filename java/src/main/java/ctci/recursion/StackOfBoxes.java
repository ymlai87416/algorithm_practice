package ctci.recursion;
import java.util.*;

public class StackOfBoxes {
    //this is the quesiton: https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
    int[] memo;
    int n;
    Box[] boxes;
    public int stackOfBoxes(Box[] boxes){
        this.boxes = boxes;
        this.n = boxes.length;

        Comparator compare = (Comparator<Box>) (b1, b2) -> {
            if(b1.height == b2.height)  //same height
                if(b1.width == b2.width)
                    return b2.depth - b1.depth;
                else
                    return b2.width - b1.width;
            else
                return b2.height - b1.height;
        };

        memo = new int[n];
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
        int h= boxes[index].height;

        for(int i=index+1; i< n; ++i){
            if(canStack(boxes[index], boxes[i]))
                h = Math.max(h, stackHelper(i) + boxes[index].height);
        }

        memo[index] = h;
        return h;
    }

    //assume strictly larger means only in each dimension...
    private boolean canStack(Box base, Box top){
        return base.width > top.width && base.height > top.height && base.depth > top.depth;
    }


    public static void main(String[] args) {
        StackOfBoxes test = new StackOfBoxes();
        StackOfBoxesRef ans = new StackOfBoxesRef();
        Box[] data = new Box[]{new Box(50, 45, 20),
                new Box(95, 37, 53), new Box(45, 23, 12)};
        ArrayList<Box> arr= new ArrayList<>();
        for(int i=0; i<data.length; ++i) arr.add(data[i]);

        System.out.println(test.stackOfBoxes(data));
        System.out.println(ans.createStack(arr));

        data = new Box[]{new Box(38,25,45),
                new Box(76,35,3)};
        arr= new ArrayList<>();
        for(int i=0; i<data.length; ++i) arr.add(data[i]);

        System.out.println(test.stackOfBoxes(data));
        System.out.println(ans.createStack(arr));

    }
}

class StackOfBoxesRef{
    int createStack(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int[] stackMap = new int[boxes.size()];
        return createStack(boxes, null, 0, stackMap);
    }
    int createStack(ArrayList<Box> boxes, Box bottom, int offset, int[] stackMap) {
        if (offset >= boxes.size()) return 0; // Base case

        /* height with this bottom*/
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if (bottom== null || newBottom .canBeAbove(bottom)) {
            if (stackMap[offset] == 0) {
                stackMap[offset] = createStack(boxes, newBottom, offset+ 1, stackMap);
                stackMap[offset] += newBottom.height;
            }
            heightWithBottom = stackMap[offset];
        }

        /* without this bottom*/
        int heightWithoutBottom=createStack(boxes, bottom, offset+ 1, stackMap);

        /* Return better of two options. */
        return Math.max(heightWithBottom, heightWithoutBottom);

    }

}

class Box{
    int height;
    int width;
    int depth;

    public Box(int w, int h, int d){
        width = w; height = h; depth = d;
    }
    public boolean canBeAbove(Box bottom){
        return height < bottom.height && width < bottom.width && depth < bottom.depth;
    }
}

class BoxComparator implements Comparator<Box>{
    @Override
 public int compare(Box x, Box y){
        return y.height - x.height;
         }
}