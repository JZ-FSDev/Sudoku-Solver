import java.util.Scanner;

public class Sudoku{
    private int[][] board;
  
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

    public void solve(){
        while(emptyCell()){
            int[] coord = placeNumberInEmptyCell();
            if(conflict(coord[0], coord[1])){
                board[coord[0]][coord[1]] = 0;
            }
        }
    }
    
    public boolean conflict(int x, int y){
        boolean conflict = false;
        int num = board[x][y];
        for(int i = 0; i < board.length && !conflict; i++){
            if(board[i][y] == num && i != x){
                conflict = true;
            }
        }
        for(int j = 0; j < board[0].length && !conflict; j++){
            if(board[x][j] == num && j != y){
                conflict = true;
            }
        }
        int n = (int)Math.sqrt(board.length);
        int partitionTopX = x / n * n;
        int partitionTopY = y / n * n;
        boolean[] cell = new boolean[board.length];
        for(int i = partitionTopX; i < partitionTopX + n && !conflict; i++){
            for(int j = partitionTopY; j < partitionTopY + n && !conflict; j++){
                if(board[i][j] != 0 && cell[board[i][j]-1] == false){
                    cell[board[i][j]-1] = true;
                }else if(board[i][j] != 0 && cell[board[i][j]-1] == true){
                    conflict = true;
                }
            }
        }
        return conflict;
    }    
}
