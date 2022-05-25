package ctci.moderate;

public class PatternMatching {

    private String invertPattern(String p){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            sb.append(p.charAt(i) == 'a' ? 'b': 'a');
        }
        return sb.toString();
    }
    public boolean patternMatching(String pattern, String value){

//assume the first symbol have the length from 1 to n, check

//if only a or b appear in the pattern, then we just simply do the trick
        if(pattern.startsWith("b"))
            pattern = invertPattern(pattern);
        int aCount = 0;
        int bCount = 0;
        for(int i=0; i<pattern.length(); ++i){
            if(pattern.charAt(i) == 'a') aCount++;
	        else bCount++;
        }

        if(aCount > 0 && bCount > 0){
//assume the letter must match at least 1 characters
            for(int i=1; i<=value.length(); ++i){
                int aSize=  i;
                int bSize = (value.length() - aSize * aCount) / bCount;
                if(aSize * aCount + bSize * bCount != value.length())
                    continue;

                char[] aPattern = null;
                char[] bPattern = null;

                int vPtr = 0;
                boolean match = true;
                for(int k=0; k<pattern.length() && match; ++k){
                    char pc = pattern.charAt(k);
                    if(pc == 'a'){
                        if(aPattern == null){
                            aPattern = new char[aSize];
                            for(int l=0; l<aSize; ++l)
                                aPattern[l] = value.charAt(vPtr++);
                        }
                        else{
                            for(int l=0; l<aSize; ++l){
                                if(aPattern[l] != value.charAt(vPtr++)){
                                    match = false;
                                    break;
                                }
                            }
                        }
                    }
                    else{
                            if(bPattern == null){
                                bPattern = new char[bSize];
                                for(int l=0; l<bSize; ++l)
                                    bPattern[l] = value.charAt(vPtr++);
                            }
                            else{
                                for(int l=0; l<bSize; ++l){
                                    if(bPattern[l] != value.charAt(vPtr++)){
                                        match = false;
                                        break;
                                    }
                                }
                            }

                        }
                    }

                //now we find a match

                if(match){
                    System.out.println("Match at a:" + String.valueOf(aPattern) + " b: " + String.valueOf(bPattern));
                    return true;
                }
            }

            return false;
        }
        else{
            //this can be only aa…aa or bb…b
            if(value.length() % pattern.length() != 0) return false;
            int aLen =  value.length() / pattern.length();

            String ap = value.substring(0, aLen);
            int pPtr = 0;
            for(int i=0; i<value.length(); ++i){
                if(value.charAt(i) != pattern.charAt(pPtr))
                    return false;
                pPtr = (pPtr+1) % aLen;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        PatternMatching test = new PatternMatching();
        String text ="catcatgocatgo";
        String pattern = "aabab";
        System.out.println(test.patternMatching(pattern, text));
    }
}
