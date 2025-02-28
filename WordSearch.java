//The idea here is to find the first character of the given word in the board and recurse through all the directions to check if all the characters in the word are as neighbors.
//In this process, when we visit a neighbor and find the correct character, we mark it visited by replacing it with a "#", and if we do not find the next characters in the same path, we backtrack and replace the "#" back with the letter there
//Time Complexity: O((m+n)*(3^l)), where m and n are the number of rows and columns in the matrix and l is the length of the word
//Space Complexity: O(l) recursive stack space
class Solution {
    int[][] dirs = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    public boolean exist(char[][] board, String word) {
        Character firstChar = word.charAt(0);
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] == firstChar){
                    if(backtrack(board, i, j, 0, word)) return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int r, int c, int idx, String word){
        //base
        if(idx == word.length()){
            return true;
        }
        if(r<0 || r==board.length || c < 0 || c==board[0].length || board[r][c] == '#'){
            return false;
        }
        //logic
        if(board[r][c] == word.charAt(idx)){
            //action
            board[r][c] = '#';
            //recurse
            for(int[] dir: dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(backtrack(board, nr, nc, idx+1, word)) return true;
            }
            //backtrack
            board[r][c] = word.charAt(idx);
        }
        return false;
    }
}