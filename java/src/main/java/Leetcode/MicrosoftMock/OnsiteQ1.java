package Leetcode.MicrosoftMock;

public class OnsiteQ1 {

    public String convertToTitle(int columnNumber) {
        String result = "";
        String map = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
        while(columnNumber > 0){
            int a = columnNumber %26;
            result = map.charAt(a) + result;
            columnNumber -= (a == 0 ? 26: a);  //no 0, only 26.
            columnNumber /= 26;
        }
        return result;
    }

    public static void main(String[] args){

    }
}
