package GoogleCodeJam.Y2019.Round1B.B;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            /*
            FILENAME = "Solution-large";
            IN = FILENAME + ".in";
            OUT = FILENAME + ".out";
            sc = new Scanner(new File(IN));
            out      = new PrintStream(
                    new FileOutputStream(OUT, false));
                    */

            //IN = "A-test.in";
            IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean solve(){
        char []var = {'u', 'v', 'w', 'x', 'y', 'z'};
        //System.out.println("Enter the number of variables in the equations: ");
        int n = 6;
        //System.out.println("Enter the coefficients of each variable for each equations");
        //System.out.println("ax + by + cz + ... = d");
        double [][]mat = new double[n][n];
        double [][]constants = new double[n][1];

        mat[0][0] = 2;
        mat[0][1] = mat[0][2] =mat[0][3] =mat[0][4] =mat[0][5] =1;
        mat[1][0] = 4;
        mat[1][1] = 2;
        mat[1][2] =mat[1][3] =mat[1][4] =mat[1][5] =1;
        mat[2][0] = 8;
        mat[2][1] = mat[2][2] =2;
        mat[2][3] =mat[2][4] =mat[2][5] =1;
        mat[3][0] = 16;
        mat[3][1] = 4;
        mat[3][2] =mat[3][3] =2;
        mat[3][4] =mat[3][5] =1;
        mat[4][0] = 32;
        mat[4][1] = 4;
        mat[4][2] =mat[4][3] =mat[4][4] =2;
        mat[4][5] = 1;
        mat[5][0] = 64;
        mat[5][1] = 8;
        mat[5][2] =4;
        mat[5][3] =mat[5][4] =mat[5][5] =2;

        //input
        for(int i=0; i<n; i++)
        {
            out.println(i+1);
            out.flush();
            constants[i][0] = sc.nextInt();
        }

        /*
        //Matrix representation
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print(" "+mat[i][j]);
            }
            System.out.print("  "+ var[i]);
            System.out.print("  =  "+ constants[i][0]);
            System.out.println();
        }
        */
        //inverse of matrix mat[][]
        double inverted_mat[][] = invert(mat);

        /*
        System.out.println("The inverse is: ");
        for (int i=0; i<n; ++i)
        {
            for (int j=0; j<n; ++j)
            {
                System.out.print(inverted_mat[i][j]+"  ");
            }
            System.out.println();
        }
        */

        //Multiplication of mat inverse and constants
        double result[][] = new double[n][1];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < 1; j++)
            {
                for (int k = 0; k < n; k++)
                {
                    result[i][j] = result[i][j] + inverted_mat[i][k] * constants[k][j];
                }
            }
        }

        //System.out.println("The product is:");
        for(int i=0; i<n; i++)
        {
            long rr = Math.round(result[i][0]);
            out.print(rr + " ");
        }
        out.println();
        out.flush();

        int judge = sc.nextInt();
        return judge == 1;
    }

    private void run() throws Exception {
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= t; i++) {
            //out.print("Case #" + i + ": ");

            boolean a = solve();
            if(!a) break;
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }

    private static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    private static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = a[index[i]][j]/a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

}
