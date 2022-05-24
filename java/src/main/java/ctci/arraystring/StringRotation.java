package ctci.arraystring;

public class StringRotation {
    public boolean stringRotation(String s1, String s2){
        return isSubstring(s1+s1 , s2 );
    }

    private boolean isSubstring(String s1, String s2){
        return s1.indexOf(s2) != -1;
    }

    public static void main(String[] args) {
        StringRotation sr=  new StringRotation();
        System.out.println(sr.stringRotation("waterbottle", "erbottlewat"));
    }
}
