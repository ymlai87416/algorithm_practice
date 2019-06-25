package Leetcode;

public class WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][]
        {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };

        Solution s = new Solution();
        System.out.println(s.exist(board, "ABCCED"));
        System.out.println(s.exist(board, "SEE"));
        System.out.println(s.exist(board, "ABCB"));
    }

    static
    class Solution {
        public boolean exist(char[][] board, String word) {
            /*
            int m = board.length;
            int n = board[0].length;
            visited = new boolean[m][n];
            for(int i=0; i<m; ++i)
                for(int j=0; j<n; ++j)
                    visited[i][j] = false;
            */
            for(int i=0; i<board.length; ++i){
                for(int j=0; j<board[i].length; ++j){
                    if(board[i][j] == word.charAt(0))
                        if(track(board, i, j, word, 0))
                            return true;
                }
            }

            return false;
        }

        public boolean track(char[][] board, int x, int y, String word, int wptr){
            //System.out.format("%d %d %c %s %d\n", x, y, board[x][y], word, wptr);
            if(word.length()-1 == wptr)
                return board[x][y] == word.charAt(wptr);
            if(board[x][y] != word.charAt(wptr))
                return false;
            char save = board[x][y];
            board[x][y] = ' ';
            boolean result = (x-1 >= 0 && track(board, x-1, y, word, wptr+1)) ||
                    (y-1 >=0 &&  track(board, x, y-1, word, wptr+1)) ||
                    (x+1 < board.length &&  track(board, x+1, y, word, wptr+1)) ||
                    (y+1 < board[0].length &&  track(board, x, y+1, word, wptr+1));
            board[x][y]=save;
            return result;
        }
    }
}
