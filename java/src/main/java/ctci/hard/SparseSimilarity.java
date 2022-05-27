package ctci.hard;
import java.util.*;
public class SparseSimilarity {
    public HashMap<DocPair, Double> sparseSimilarity(HashMap<Integer, List<Integer>> documents){
        HashMap<Integer, List<Integer>> word2Doc = new HashMap<>();
        HashMap<DocPair, Double> similarity = new HashMap<>();
        for(Integer doc: documents.keySet()){
            for(Integer word: documents.get(doc)){
                if(!word2Doc.containsKey(word))
                    word2Doc.put(word, new ArrayList<Integer>());

                word2Doc.get(word).add(doc);
            }
        }

        for(Integer word: word2Doc.keySet()){

            List<Integer> docList = word2Doc.get(word);
            for(int i=0; i<docList.size(); ++i){
                for(int j=i+1; j<docList.size(); ++j){
                    DocPair pair = new DocPair(docList.get(i), docList.get(j));
                    similarity.put(pair, similarity.getOrDefault(pair, 0.0)+1);
                }
            }
        }

        //now we just do for each pair of doc
        for(DocPair pair : similarity.keySet()){
            int i = pair.first;
            int j = pair.second;
            double intersect = similarity.get(pair);
            double unionSize = documents.get(i).size() + documents.get(j).size() - intersect;
            double result = intersect / unionSize;
            similarity.put(pair, result);
        }

        return similarity;
    }


    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> documents = new HashMap<>();
        documents.put(13, List.of(14, 15, 100, 9, 3));
        documents.put(16, List.of(32, 1, 9, 3, 5));
        documents.put(19, List.of(15, 29, 2, 6, 8, 7));
        documents.put(24, List.of(7, 10));

        SparseSimilarity test = new SparseSimilarity();
        HashMap<DocPair, Double>  result = test.sparseSimilarity(documents);

        for(DocPair dp: result.keySet()){
            System.out.printf("%2d %2d: %f\n", dp.first, dp.second, result.get(dp));
        }
    }
}

class DocPair{
    int first;
    int second;

    public DocPair(int f, int s){
        this.first = f; second = s;
    }

    public boolean equals(Object obj){
        if(obj instanceof DocPair){
            DocPair other = (DocPair) obj;
            return other.first == first && other.second == second;
        }
        return false;
    }

    public int hashCode(){
        //What we do in defining simple hash
        long hash = (first * 8191L + second) % 1_000_000_007;
        return (int) hash;
    }
}
