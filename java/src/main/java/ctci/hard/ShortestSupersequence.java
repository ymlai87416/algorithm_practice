package ctci.hard;
import java.util.*;
public class ShortestSupersequence {

    public int[] shortestSuperSubstring(int[] longS, int[] shortS){

        int rLeft = -1;
        int rRight = -1;
        int left= 0;

        HashMap<Integer, Integer> shortF = new HashMap<> ();

        for(int i=0; i<shortS.length; ++i){
            shortF.put(shortS[i], 1);
        }
        HashMap<Integer, Integer> longF = new HashMap<>();

        for(int right = 0; right < longS.length; ++right){
            if(shortF.containsKey(longS[right]) )
                longF.put(longS[right], longF.getOrDefault(longS[right], 0)+1);

//try move left until it is not compatible

            if(compareFreq(shortF, longF)){
                while(compareFreq(shortF, longF)){
                    longF.put(longS[left], longF.getOrDefault(longS[left], 0)-1);
                    left++;
                }

                //add back left and move back left
                int lastLeft= longS[--left];
                longF.put(lastLeft, longF.getOrDefault(lastLeft, 0)+1);

                if(rRight == -1 || right-left < rRight-rLeft){
                    rRight = right;
                    rLeft = left;
                }
            }
        }


        //assume it always possible to return an answer
        return new int[]{rLeft, rRight};

    }

    private boolean compareFreq(HashMap<Integer, Integer> shortF, HashMap<Integer, Integer> longF){
        //check that longF have all stuff
        for(Integer sk : shortF.keySet()){
            if(!longF.containsKey(sk))
                return false;
            if( longF.get(sk) < shortF.get(sk))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int[] s1 = new int[]{1, 5, 9};
        int[] s2 = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};

        ShortestSupersequence test = new ShortestSupersequence();
        int[] r = test.shortestSuperSubstring(s2, s1);

        System.out.printf("[%d %d]\n", r[0], r[1]);
    }
}
