import java.util.Scanner;

/**
 * Defines a sudoku solver which accepts sudoku board by user input and
 * solves it to produce a board that resembles a solution.
 *
 * @author JZ-FSDev
 * @since 17.0.1
 * @version 0.0.1
 */
public class Sudoku{
    private int[][] board;

    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        System.out.println(s);
        // int[] coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        // coord = s.placeNumberInEmptyCell();
        s.solveSudoku(s.board);
        // System.out.println("Empty cell: " + s.emptyCell());
        // System.out.println("Conflict: " + s.conflict(coord[0], coord[1]));
        System.out.println(s);
        System.out.println(s.valid(1, 0, 1, s.board));
    }
    
    /**
     * Constructs a new Sudoku board based on the board defined by user input
     * in the form 2 - 7 - - 3 - 8 - - 5 - - 9 - - - 6 - - - - - - - - 9 - 4 - 
     * 2 - - - - - - - 8 5 - 1 6 - - - - - - - 4 - 3 - 4 - - - - - - - - 5 - - 
     * - 7 - - 2 - - 3 - 9 - - 4 - 5 , where dashes represent an empty cell.
     */
    public Sudoku(){
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        double n = Math.sqrt(Math.sqrt(input.length));
        if(n*n*n*n == input.length){
            board = new int[(int)(n*n)][(int)(n*n)];
            for(int i = 0; i < n*n; i++){
                for(int j = 0; j < n*n; j++){
                    if(!input[i*(int)n*(int)n+j].equals("-")){
                        board[i][j] = Integer.parseInt(input[i*(int)n*(int)n+j]);
                    }
                }
            }
        }
    }

    /**
     * Returns this sudoku board as a String.
     * 
     * @return Returns this sudoku board as a String.
     */
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                stringBuilder.append(board[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Solves this board by random brute force.
     */
    public void solve(){
        while(hasEmptyCell()){
            int[] coord = placeNumberInEmptyCell();
            if(conflictAtCell(coord[0], coord[1])){
                board[coord[0]][coord[1]] = 0;
            }
        }
    }

    /**
     * Returns true if there is a conflict at the cell at the specified coordinates.
     * A conflict is defined as the presence of a repeated number in the n x n cell,
     * row, or column.
     * 
     * @param row The row coordinate of the cell to check for conflict.
     * @param col The column coordinate of the cell to check for conflict.
     * @return Returns true if there is a conflict at the cell at the specified coordinates.
     */
    public boolean conflictAtCell(int row, int col){
        boolean conflict = false;
        int num = board[row][col];
        for(int i = 0; i < board.length && !conflict; i++){
            if(board[i][col] == num && i != row){
                conflict = true;
            }
        }
        for(int j = 0; j < board[0].length && !conflict; j++){
            if(board[row][j] == num && j != col){
                conflict = true;
            }
        }
        int n = (int)Math.sqrt(board.length);
        int partitionTopX = row / n * n;
        int partitionTopY = col / n * n;
        boolean[] cell = new boolean[board.length];
        for(int i = partitionTopX; i < partitionTopX + n && !conflict; i++){
            for(int j = partitionTopY; j < partitionTopY + n && !conflict; j++){
                if(board[i][j] != 0 && cell[board[i][j]-1] == false){
                    cell[board[i][j]-1] = true;
                }else if(board[i][j] != 0 && cell[board[i][j] - 1] == true){
                    conflict = true;
                }
            }
        }
        return conflict;
    }
    
    /**
     * Places a random number from 1 to n*n in an empty cell and returns the coordinate
     * of the cell that had an number placed as an array with the row coordinate as
     * the element at index 0 and the column element as index 1.
     * 
     * @return The array that contains the coordinates of the cell that had a number placed.
     */
    public int[] placeNumberInEmptyCell(){
        int[] coordinate = new int[2];
        boolean placed = false;
        for(int i = 0; i < board.length && !placed; i++){
            for(int j = 0; j < board[i].length && !placed; j++){
                if(board[i][j] == 0){
                    board[i][j] = (int)(Math.random()*board.length) + 1;
                    coordinate[0] = i;
                    coordinate[1] = j;
                    placed = true;
                }
            }
        }
        return coordinate;
    }

    /**
     * Returns true if this sudoku board has an empty cell.
     * 
     * @return Returns true if this sudoku board has an empty cell.
     */
    public boolean hasEmptyCell(){
        boolean empty = false;
        for(int i = 0; i < board.length && !empty; i++){
            for(int j = 0; j < board[i].length && !empty; j++){
                if(board[i][j] == 0){
                    empty = true;
                }
            }
        }
        return empty;
    }
    
    private boolean valid(int item, int row, int col, int[][] board){
        boolean valid = true;
        for(int i = 0; i < 9 && valid; i++){
            if(board[i][col] == item || board[row][i] == item){
                valid = false;
            }
        }
        for(int r = (row/3)*3; r < (row/3)*3 + 3 && valid; r++){
            for(int c = (col/3)*3; c < (col/3)*3 + 3 && valid; c++){
                if(board[r][c] == item){
                    valid = false;
                }
            }
        }
        return valid;
    }
}
