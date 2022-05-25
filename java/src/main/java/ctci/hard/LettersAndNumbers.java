package ctci.hard;
import java.util.*;

public class LettersAndNumbers {

    public int[] longestSubArrayEqualLetterNumber(String s){
        //create a array list of english letter
        //create a array list of number
        //create a array list of difference
        int n = s.length();
        int[] cntAlpha = new int[n];
        int[] cntNum = new int[n];
        int[] diff = new int[n];

        int alphaCnt = 0; int numCnt = 0;
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            if(Character.isAlphabetic(c)) ++alphaCnt;
            else if(Character.isDigit(c)) ++numCnt;
            cntAlpha[i] = alphaCnt;
            cntNum[i] = numCnt;
            diff[i] = alphaCnt - numCnt;
        }

        //O(N) algorithm
        HashMap<Integer, Integer> firstAppear = new HashMap<>();
        firstAppear.put(0, -1);

        int resMax = -1;
        int left = 0;
        int right = 0;

        for(int i=0; i<n; ++i){
            if(firstAppear.containsKey(diff[i])){
                int len = (i - firstAppear.get(diff[i]) +1);
                if(len > resMax) {
                    left = firstAppear.get(diff[i]) + 1;
                    right = i;
                    resMax = len;
                }
            }
	        else
                firstAppear.put(diff[i], i);
        }

        return new int[]{left, right};
    }


    public static void main(String[] args) {
        String input = "aaaa11a11aa1aa1aaaaa";
        LettersAndNumbers test = new LettersAndNumbers();
        int[] r = test.longestSubArrayEqualLetterNumber(input);
        System.out.println(r[0] + " " + r[1]);
    }
}
