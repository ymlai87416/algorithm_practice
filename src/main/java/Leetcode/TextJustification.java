package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void main(String[] args){
        String[] words= new String[]{"What","must","be","acknowledgment","shall","be"};

        Solution s = new Solution();
        List<String> ss = s.fullJustify(words, 16);
        for(String sss: ss) {
            System.out.println(sss);
        }
    }

    static
    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            //create line
            List<String> result = new ArrayList<String>();
            List<List<String>> intrim = new ArrayList<>();
            int ptr = 0;
            while(ptr < words.length){
                int line = 0;
                List<String> cline = new ArrayList<>();

                while(ptr < words.length){
                    String curWord = words[ptr];
                    int addLength = line += (line == 0 ? 0 : 1) + curWord.length();

                    if(addLength <= maxWidth || cline.size() == 0){
                        cline.add(words[ptr]);
                        ptr++;
                    }
                    else
                        break;
                }

                intrim.add(cline);
            }

            //pad line
            for(int i=0; i<intrim.size()-1; ++i){
                //int normalLength = intrim.get(i).stream().mapToInt(x -> x.length()).sum() + intrim.get(i).size()-1;
                int normalLength= expectedSize(intrim.get(i));
                int additionalSpace = maxWidth - normalLength;

                int noOfSpace = intrim.get(i).size()-1;
                int sp = noOfSpace > 0 ? additionalSpace / noOfSpace : 0;
                int spm = noOfSpace > 0 ? additionalSpace % noOfSpace : 0;

                StringBuilder sb = new StringBuilder();
                sb.append(intrim.get(i).get(0));
                for(int j=1; j<intrim.get(i).size(); ++j){
                    sb.append(space(1+sp+(j<=spm?1:0)));
                    sb.append(intrim.get(i).get(j));
                }

                int paddingSpace = maxWidth - sb.length();
                sb.append(space(paddingSpace));
                result.add(sb.toString());
            }

            StringBuilder sb = new StringBuilder();
            sb.append(intrim.get(intrim.size()-1).get(0));
            for(int i=1; i<intrim.get(intrim.size()-1).size(); ++i) {
                sb.append(" ");
                sb.append(intrim.get(intrim.size() - 1).get(i));
            }
            int paddingSpace = maxWidth - sb.length();
            sb.append(space(paddingSpace));
            result.add(sb.toString());

            //output result
            return result;
        }

        private int expectedSize(List<String> a){
            //intrim.get(i).stream().mapToInt(x -> x.length()).sum() + intrim.get(i).size()-1;
            int b = 0;
            for(String s: a)
                b += s.length();
            return b+a.size()-1;
        }


        private String space(int w){
            StringBuilder r = new StringBuilder();
            for(int i=0; i<w; ++i)
                r.append(' ');
            return r.toString();
        }
    }
}
