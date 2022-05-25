package ctci.moderate;
import java.util.*;

public class MasterMind {

    public int[] masterMindHit(String result, String guess){
        int slot = 4;
        //count the hit
        HashMap<Character, Integer> pResult = new HashMap<>();
        HashMap<Character, Integer> pGuess = new HashMap<>();

        int hit = 0;
        for(int i=0; i<slot; ++i){
            char rc = result.charAt(i);
            char gc = guess.charAt(i);
            if(rc ==gc) ++hit;
            else{
                pResult.put(rc, pResult.getOrDefault(rc, 0)+1);
                pGuess.put(gc, pResult.getOrDefault(gc, 0)+1);
            }
        }


        //count the pseudo hit
        int pHit = 0;
        for(Character gc: pGuess.keySet()){
            int gCnt = pGuess.get(gc);
            int rCnt = pResult.getOrDefault(gc, 0);

            pHit += Math.min(gCnt, rCnt);
        }

        return new int[]{hit, pHit};

    }


    public static void main(String[] args) {
        MasterMind test = new MasterMind();
        int[] r = test.masterMindHit("RGBY", "GGRR");
        System.out.println("Hit: " + r[0] + " Pseudo hit: " + r[1]);
    }

}
