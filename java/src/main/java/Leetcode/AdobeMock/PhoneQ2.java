package Leetcode.AdobeMock;

//digit root, the pattern can be found by looping all the number, and hence a O(1) solution.

public class PhoneQ2 {

    public int addDigits(int num) {

        while(num >= 10){

            int sum =0;
            while(num > 0){
                sum += num %10;
                num/=10;
            }

            num=sum;
        }

        return num;
    }

    public static void main(String[] args){
        PhoneQ2 s = new PhoneQ2();
        //System.out.println(s.addDigits(38));

        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ": " + s.addDigits(i));

        }
    }

}
