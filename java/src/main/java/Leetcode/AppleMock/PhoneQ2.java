package Leetcode.AppleMock;

//why overfloat? copy other java solution to see see

import java.util.Arrays;
import java.util.TreeSet;
import java.math.BigInteger;
public class PhoneQ2 {

    static class ProductOfNumbers{

        BigInteger[] cache = null;
        TreeSet<Integer> zeroIndexes = null;
        int size;

        public ProductOfNumbers() {
            cache = new BigInteger[40001];
            Arrays.fill(cache, 0);
            zeroIndexes = new TreeSet<Integer>();
            size = 0;
        }

        public void add(int num) {
            if(num == 0){
                zeroIndexes.add(size);
                if(size > 0) cache[size] = cache[size-1];
                else cache[size] = BigInteger.ONE;
            }
            else{
                if(size > 0) cache[size] = cache[size-1].multiply(BigInteger.valueOf(num));
                else cache[size] = BigInteger.valueOf(num);
            }

            /*
            if(cache[size] == 0){
                String bb = "";
                for (int i = 0; i< 20; i++) {
                    bb =  cache[size-i] + " " + bb;
                }
                throw new ArithmeticException("D: " + num + " " + size + " " + bb);
            }*/

            ++size;
        }

        public int getProduct(int k) {
            int fromIdx = 0, toIdx = 0;
            BigInteger denom = null, nom = null;
            try {
                fromIdx = size - 1 - k + 1;
                toIdx = size; //exclusive

                if (zeroIndexes.subSet(fromIdx, toIdx).size() > 0) return 0;
                denom = cache[size - 1];
                nom = fromIdx - 1 < 0 ? BigInteger.ONE : cache[fromIdx - 1];
                return (int) (denom.divide(nom)).intValue();
            }
            catch(ArithmeticException ex){
                throw new ArithmeticException("D " + fromIdx + " " + toIdx + " " + denom + " " +nom);
            }
        }
    }

    public static void main(String[] args){
        ProductOfNumbers p = new ProductOfNumbers();
        p.add(0);        // [3]
        System.out.println(p.getProduct(1)); // return 20.
        p.add(3);        // [3,0]
        p.add(2);        // [3,0,2]
        p.add(5);        // [3,0,2,5]
        p.add(4);        // [3,0,2,5,4]
        System.out.println(p.getProduct(2)); // return 20.
        System.out.println(p.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(p.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        p.add(8);        // [3,0,2,5,4,8]
        System.out.println(p.getProduct(2));
        System.out.println(p.getProduct(0));
    }

}
