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
}
