package Leetcode.GoogleMock;

public class PhoneQ1 {

    public boolean validMountainArray(int[] arr) {
        boolean checkUp = true;
        if(arr.length <3) return false;
        if(arr[0] > arr[1]) return false;

        for (int i = 1; i < arr.length; i++) {
            if(arr[i-1] == arr[i]) return false;
            if(checkUp && arr[i-1] < arr[i]) continue;
            else if(checkUp && arr[i-1] > arr[i]) checkUp = false;
            else if(!checkUp && arr[i-1] <= arr[i]) return false;
        }

        if(checkUp) return false;
        return true;
    }

    public static void main(String[] args){

    }
}
