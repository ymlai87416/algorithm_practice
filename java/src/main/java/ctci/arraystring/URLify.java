package ctci.arraystring;

public class URLify {
    public void toHtmlSpace(char[] buffer, int trueLength){
        //find the length of string
        //find the number of space
        int spaceCnt = 0;
        int length = 0;

        for(int i=0; i<trueLength; ++i){
            if(buffer[i] == ' ') spaceCnt++;
        }

        int newLen = trueLength + spaceCnt * 2;
        int counter = newLen-1;


        for(int i=trueLength-1; i>=0; --i){
            if(buffer[i] != ' ')
                buffer[counter--] = buffer[i];
            else{
                    buffer[counter--] = '0';
                    buffer[counter--] = '2';
                    buffer[counter--] = '%';
                }
        }
    }

    public static void main(String[] args) {
        URLify sol = new URLify ();
        char[] input=  "Mr John Smith    ".toCharArray();
        sol.toHtmlSpace(input, 13);
        System.out.println(input);
    }
}
