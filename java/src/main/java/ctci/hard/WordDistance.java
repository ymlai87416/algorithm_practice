package ctci.hard;
import java.util.*;

public class WordDistance {

    //if only 1 pass
    public int wordDistance1(String[] input, String a, String b){
        int ptrA, ptrB;
        ptrA = -1; ptrB = -1;
        int distance = Integer.MAX_VALUE;

        for(int i=0; i<input.length; ++i){
            if(a.compareTo(input[i]) == 0){
                ptrA = i;
                if(ptrB != -1){
                    distance = Math.min(distance, ptrA-ptrB);
                }
            }
            else if(b.compareTo(input[i]) == 0){
                ptrB = i;
                if(ptrA != -1){
                    distance = Math.min(distance, ptrB-ptrA);
                }
            }
        }

        return distance;
    }

    HashMap<String, List<Integer>> wordPos;

    //if it can handle multiple query.
    public void wordDistance2Preprocess(String[] input){
        wordPos = new HashMap<>();

        for(int i=0; i<input.length; ++i){
            String cs = input[i];
            if(!wordPos.containsKey(cs))
                wordPos.put(cs, new ArrayList<Integer>());
            wordPos.get(cs).add(i);
        }

    }

    public int wordDistance2(String a, String b){
        List<Integer> posA = wordPos.get(a);
        List<Integer> posB = wordPos.get(b);

        int ptrA = 0, ptrB = 0;
        int distance = Integer.MAX_VALUE;
        if(posA == null || posB == null) return -1;

        while(ptrA < posA.size() && ptrB < posB.size()){
            distance = Math.min(distance, Math.abs(posA.get(ptrA) - posB.get(ptrB)));
            if(posA.get(ptrA) < posB.get(ptrB))
            ++ptrA;
		else
            ++ptrB;
        }

        return distance;
    }

    public static void main(String[] args) {
        WordDistance test = new WordDistance();
        String text = "Once upon a time there was an old mother pig who had three little pigs and not enough food to feed them. So when they were old enough, she sent them out into the world to seek their fortunes.\n" +
                "The first little pig was very lazy. He didn't want to work at all and he built his house out of straw. The second little pig worked a little bit harder but he was somewhat lazy too and he built his house out of sticks. Then, they sang and danced and played together the rest of the day.\n" +
                "The third little pig worked hard all day and built his house with bricks. It was a sturdy house complete with a fine fireplace and chimney. It looked like it could withstand the strongest winds.\n" +
                "The next day, a wolf happened to pass by the lane where the three little pigs lived; and he saw the straw house, and he smelled the pig inside. He thought the pig would make a mighty fine meal and his mouth began to water.";

        String[] feed = text.toLowerCase().replaceAll(",", "").replaceAll("\\.", "").split(" ");

        System.out.println(test.wordDistance1(feed, "mother", "work"));
        test.wordDistance2Preprocess(feed);
        System.out.println(test.wordDistance2("mother", "work"));

    }

}
