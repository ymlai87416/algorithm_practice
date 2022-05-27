package ctci.hard;
import java.util.*;

public class ContinuousMedian {

    PriorityQueue<Integer> lowHalf, highHalf;
    int n;

    public void init(){
        lowHalf = new PriorityQueue<>(Collections.reverseOrder());
        highHalf = new PriorityQueue<>();
        n = 0;
    }

    /*
    public int median(int input){
        n+=1;


        if(n % 2 == 0){
            //add it to low
            if(lowHalf.isEmpty() || input <= lowHalf.peek()){
                lowHalf.offer(input);
            }
            else{
                if(!highHalf.isEmpty())lowHalf.offer(highHalf.poll());
                highHalf.offer(input);
            }

            return (lowHalf.peek() + highHalf.peek()) / 2;
        }
        else{
            //add it to high
            if(!lowHalf.isEmpty() && input >= lowHalf.peek()){
                highHalf.offer(input);
            }
            else{
                if(!lowHalf.isEmpty())highHalf.offer(lowHalf.poll());
                lowHalf.offer(input);
            }

            return highHalf.peek();
        }
    }

     */

    public double median(int input){
        n++;
        if(highHalf.isEmpty() || input < highHalf.peek())
            lowHalf.offer(input);
        else
            highHalf.offer(input);

        if(lowHalf.size() > highHalf.size()){
            highHalf.offer(lowHalf.poll());
        }
        else if(highHalf.size() > lowHalf.size()+1){
            lowHalf.offer(highHalf.poll());
        }

        if(n % 2==0)
            return 1.0 * (lowHalf.peek() + highHalf.peek())/2;
        else
            return highHalf.peek();
    }

    public static void main(String[] args) {
        ContinuousMedian test = new ContinuousMedian();
        test.init();
        int[] data = new int[]{3,4,5,1,2,4,6,7,8};
        for(int i=0; i<data.length; ++i)
            System.out.println(test.median(data[i]));
    }
}
