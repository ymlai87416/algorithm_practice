package Leetcode.Contest278;

public class C {

    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int pInv = modInverse(power, modulo);
        String result = null;
        long hash = 0;
        for(int i=k-1; i>=0; --i)
            hash = (hash * power + (s.charAt(i)-'a'+1)) % modulo;

        if(hash == hashValue)
            return s.substring(0, k);

        long pp = ppow(power, k-1, modulo);

        for(int i=k; i<s.length(); ++i){

            char rc = s.charAt(i-k);
            char ac = s.charAt(i);

            hash -= (rc-'a'+1);
            while(hash< 0) hash+=modulo;
            hash = (hash * pInv) % modulo;
            hash = (hash + (ac-'a'+1) * pp) % modulo;

            //System.out.println(i + ": " + hash);

            if(hash==hashValue){
                result = s.substring(i+1-k,i+1);
                break;
            }
        }

        return result;
    }

    private long ppow(int p, int pow, int m){
        long a = p;
        for(int i=2; i<=pow; ++i){
            a = (a * p) % m;
        }
        return a;
    }

    private int modInverse(int a, int m)
    {

        for (int x = 1; x < m; x++)
            if (((a%m) * (x%m)) % m == 1)
                return x;
        return 1;
    }

    public static void main(String[] args){
        C c = new C();
        System.out.println(c.subStrHash("leetcode",
                7,
                20,
                2,
                0));

        System.out.println(c.subStrHash(
        "fbxzaad",
        31,
        100,
        3,
        32));
    }
}
