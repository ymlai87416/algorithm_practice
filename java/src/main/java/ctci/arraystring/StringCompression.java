package ctci.arraystring;

public class StringCompression {
    public String compressString(String input){
        StringBuilder sb = new StringBuilder();

        char pc=input.charAt(0), cc;

        int cf = 1;

        for(int i=1; i<input.length(); ++i){
            cc = input.charAt(i);
            if(cc != pc){
                sb.append(pc);
                sb.append(cf);
                pc = cc;
                cf = 1;
            }
            else
                cf+= 1;
        }
        //now flush the last
        sb.append(pc);
        sb.append(cf);

        String cInput = sb.toString();
        if(input.length() <= cInput.length())
            return input;
        else
            return cInput;
    }


    public static void main(String[] args) {
        StringCompression test = new StringCompression();
        System.out.println(test.compressString("aabcccccaaa"));
    }
}
