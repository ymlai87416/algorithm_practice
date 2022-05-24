package ctci.bitmanipulation;

public class BinaryToString {
    public String binaryToString(double number){
        StringBuilder sb = new StringBuilder();
        sb.append("0.");

        double cur = 0.5;
        int pass = 0;
        while(number > 0 && pass < 32){
            if(number >= cur){
                sb.append("1");
                number -= cur;
            }
            else
                sb.append("0");
            cur /=2;
            ++pass;
        }

        if(pass==32 && number >= cur)  //How to terminate?
            return "ERROR";

        return sb.toString();
    }


    public static void main(String[] args) {
        BinaryToString test = new BinaryToString();
        System.out.println(test.binaryToString(0.72));
        System.out.println(test.binaryToString(0.75));
        System.out.println(test.binaryToString(0));
    }
}
