package ctci.arraystring;

public class OneEditAway {

    boolean oneEditAway(String a, String b){
        if(b.length() > a.length())
            return oneEditAway(b, a);

        int diffCount = 0;
        for(int i=0, j=0; i<a.length(); ++i){
            char ca = a.charAt(i);
            char cb = j == b.length() ? '\0' : b.charAt(j);

            if(ca != cb){
//if same length
                if(a.length() == b.length()){
                    diffCount++;
                    j++;
                }
                else{
                    diffCount++;
                    //only advance i
                }
            }
            else
                j++;
        }

        return diffCount == 1;
    }


    public static void main(String[] args) {
        OneEditAway sol = new OneEditAway();
        System.out.println(sol.oneEditAway("pale", "ple" ));// -> true
        System.out.println(sol.oneEditAway("pales", "pale"));  //-> true
        System.out.println(sol.oneEditAway("pale", "bale" )); //-> true
        System.out.println(sol.oneEditAway("pale", "bake" )); //-> false
        System.out.println(sol.oneEditAway("tom", "mot" )); //-> false
    }
}
