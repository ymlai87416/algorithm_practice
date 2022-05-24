package ctci.recursion;
import java.util.*;

public class BooleanEvaluation {
    //seems to me the best use is the linked list
    int[][][] memo;
    boolean target;
    List<String> expr;
    public int booleanEvaluation(String expression, boolean target){
        expr = generateToken(expression);
        this.target = target;
        int n = expr.size();
        memo = new int[n+1][n+1][2];
        for(int i=0; i<=n; ++i)
            for(int j=0; j<=n; ++j){
                memo[i][j][0] = -1;
                memo[i][j][1] = -1;
            }
        countHelper(0, n-1);

        /*
        printH(expression,6);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d:%2d ",  memo[i][j][0], memo[i][j][1]);
            }
            System.out.println();
        }
        */
        return memo[0][n-1][target? 1: 0];
    }

    private void printH(String a, int space){
        for(int i=0; i<a.length(); ++i){
            char c= a.charAt(i) ;
            System.out.printf("%" + space+ "s", ""+c);

        }
        System.out.println();
    }

    private void countHelper(int i, int j){
        if(memo[i][j][0] != -1)
            return;
        if(i == j){
            int result = Integer.valueOf(expr.get(i));
            memo[i][j][result] = 1;
            memo[i][j][1-result] = 0;
            return;
        }
        if(j -i == 2){
            int a = Integer.valueOf(expr.get(i));
            int b = Integer.valueOf(expr.get(j) );
            int result = operate(expr.get(i+1), a, b) ;
            memo[i][j][result] = 1;
            memo[i][j][1-result] = 0;
            return;
        }
        memo[i][j][1] = 0;
        memo[i][j][0] = 0;

        for(int p=i+1; p<j; p+=2){
            //do this operation
            countHelper(i, p-1);
            countHelper(p+1, j);


            for(int u=0; u<2; ++u){

                for(int v=0; v<2; ++v){

                    int result = operate(expr.get(p), u, v);
                    int time = memo[i][p-1][u] * memo[p+1][j][v];

                    memo[i][j][result] += time;
                }
            }
        }
    }

    private int operate(String operator, int operand1, int operand2){
        if(operator.compareTo("&") == 0)
            return operand1 & operand2;
        else if(operator.compareTo("|") == 0)
            return operand1 |operand2;
        else if(operator.compareTo("^") == 0)
            return operand1 ^ operand2;
        else
            return 0;
    }

    private List<String> generateToken(String expression){
        List<String> result = new ArrayList<String>();

        int number = 0;
        for(int i=0; i<expression.length(); ++i){
            char cs = expression.charAt(i);
            if(cs == '0' || cs == '1')
                number = number * 2 + (cs-'0');
            else{
                result.add(String.valueOf(number));
                number =0;
                result.add(""+cs);
            }
        }
        result.add(String.valueOf(number));

        return result;
    }


    public static void main(String[] args) {
        BooleanEvaluation eval = new BooleanEvaluation();
        //System.out.println(eval.booleanEvaluation("1|0", true));
        System.out.println(eval.booleanEvaluation("1^0|0|1", false));
        System.out.println(eval.booleanEvaluation("0&0&0&1^1|0", true));
    }
}
