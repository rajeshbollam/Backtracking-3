//The approach here is to check all possibilities of placing a queen for all the columns in a row and recurse the same for all the queens for all the rows based on the conditions
//So, at each row, we place a queen at a column and check if we can complete the grid by similarly placing queens in the next rows as per the conditions, if we cannot, then we backtrack at each step and continue checking for remaining possibilities
//Time Complexity:O(n!) where n is the number of queens
//Space Complexity: O(n^2) 
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] board = new boolean[n][n];
        backtrack(board, n, 0, result);
        return result;
    }

    private void backtrack(boolean[][] board, int n, int r, List<List<String>> result){
        //base
        if(r == board.length){
            //all queens are placed
            List<String> li = new ArrayList<>();
            for(int i = 0; i<n; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j<n; j++){
                    if(board[i][j] == true){
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                li.add(sb.toString());
            }
            result.add(li);
        }

        //logic
        for(int c = 0; c<board[0].length; c++){
            if(isSafe(board, r, c)){
                //action
                board[r][c] = true;
                //recurse
                backtrack(board, n, r+1, result);
                //backtrack
                board[r][c] = false;                    
            }
        }
    }

    private boolean isSafe(boolean[][] board, int r, int c){
        //column up
        for(int i = 0; i<r; i++){
            if(board[i][c]) return false;
        }
        //diagonal left up
        int i = r; int j = c;
        while(i>=0 && j>=0){
            if(board[i][j]) return false;
            i--;
            j--;
        }
        //diagonal right up
        i = r; j = c;
        while(i>=0 && j<board[0].length){
            if(board[i][j]) return false;
            i--;
            j++;
        }
        return true;
    }
}