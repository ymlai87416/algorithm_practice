package Leetcode.UberMock;

import java.util.HashMap;

public class PhoneQ1 {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        HashMap<Integer, int[]> hm = new HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            hm.put(pieces[i][0], pieces[i]);
        }

        int ptr = 0;
        while(ptr < arr.length){
            int x = arr[ptr];
            int[] nextPiece = hm.getOrDefault(x, null);
            if(nextPiece == null) return false;
            //we also check if this piece work
            for (int i = 0; i < nextPiece.length; i++) {
                if(arr[ptr+i] != nextPiece[i]) return false;
            }
            ptr += nextPiece.length;
        }

        return true;
    }

    public static void main(String[] args){

    }
}
