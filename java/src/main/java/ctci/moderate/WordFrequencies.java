package ctci.moderate;
import java.util.*;

public class WordFrequencies {

    HashMap<String, Integer> freq;

    public void preprocess(String[] words){
        freq = new HashMap<>();
        for(int i=0; i<words.length; ++i){
            freq.put(words[i], freq.getOrDefault(words[i], 0)+1);
        }
    }

    public int query(String s){
        return freq.getOrDefault(s, 0);
    }


    public static void main(String[] args) {
        WordFrequencies test = new WordFrequencies();
        String s = "The villagers of Little Hangleton still called it \"the Riddle House,\" even though it had been many years since the Riddle family had lived there. It stood on a hill overlooking the village, some of its windows boarded, tiles missing from its roof, and ivy spreading unchecked over its face. Once a fine-looking manor, and easily the largest and grandest building for miles around, the Riddle House was now damp, derelict, and unoccupied. \n" +
                "\n" +
                "The Little Hangletons all agreed that the old house was \"creepy. \" Half a century ago, something strange and horrible had happened there, something that the older inhabitants of the village still liked to discuss when topics for gossip were scarce. The story had been picked over so many times, and had been embroidered in so many places, that nobody was quite sure what the truth was anymore. Every version of the tale, however, started in the same place: Fifty years before, at daybreak on a fine summer's morning when the Riddle House had still been well kept and impressive, a maid had entered the drawing room to find all three Riddles dead. ";
        test.preprocess(toToken(s));
        System.out.println(test.query("the"));
    }

    private static String[] toToken(String s){
        s = s.toLowerCase();
        s.replaceAll(",", " ");
        s.replaceAll("\\.", " ");
        s.replaceAll("\"", " ");
        s.replaceAll(":", " ");
        return s.split(" ");


    }
}
