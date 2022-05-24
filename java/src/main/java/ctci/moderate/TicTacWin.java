package ctci.moderate;

public class TicTacWin {

    public char won(char[][] matrix){
        int N = matrix.length;
        for(int i=0; i<N; ++i){
            char rc = matrix[i][0];
            char cc = matrix[0][i];

            for(int j=0; j<N; ++j){
                if(matrix[i][j] != rc) rc = ' ';
                if(matrix[j][i] != cc) cc = ' ';
            }

            if(rc != ' ') return rc;
            if(cc != ' ') return cc;
        }

        char ld = matrix[0][0];
        for(int i=0; i<N; ++i)
            if(ld != matrix[i][i]) ld= ' ';
        if(ld != ' ')
        return ld;

        char rd = matrix[0][N-1];
        for(int i=0; i<N; ++i)
            if(rd != matrix[i][N-1-i]) rd = ' ';

        if(rd != ' ')
            return rd;

        return ' ';
    }


    public static void main(String[] args) {
        TicTacWin test = new TicTacWin();
        char[][] b1 = board1();
        char[][] b2 =board2();

        System.out.println(test.won(b1));
        System.out.println(test.won(b2));
    }

    private static char[][] board1(){
        return new char[][]{
                {'O', 'O', 'O'},
                {'X', 'X', ' '},
                {' ', ' ', ' '}
        };
    }

    private static char[][] board2(){
        return new char[][]{
                {'X', 'O', 'X'},
                {'O', 'X', ' '},
                {'X', ' ', ' '}
        };
    }


}
