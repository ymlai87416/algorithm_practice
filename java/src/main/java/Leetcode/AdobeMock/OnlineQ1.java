package Leetcode.AdobeMock;

public class OnlineQ1 {

    public boolean isOneBitCharacter(int[] bits) {
        int ptr = 0;
        int lastWordLen = 0;
        while(ptr < bits.length){
            if(bits[ptr] == 1)
                lastWordLen=2;
            else
                lastWordLen=1;

            ptr+=lastWordLen;
        }

        return lastWordLen == 1;
    }

    public static void main(String[] args){
        int[] bits= {1,0,0};
        OnlineQ1 s = new OnlineQ1();

        System.out.println(s.isOneBitCharacter(bits));
        bits= new int[]{1,1,1,0};
        System.out.println(s.isOneBitCharacter(bits));
    }

}
