
import java.util.Scanner;
/**
 * Represents a Tic-Tac-Toe board
 * @author Cesar Camacho
 * @version CMPU 102
 */
public class TicGame
{
    /** Represents a blank spot on the board. */
    protected final char BLANK;
    /** Represents the board, spaces are represented as an index in the matrix */
    protected char[][] board;
    /** Represents width and height of board */
    protected final int boardSize;
    /** Represents result of game:
     * '?' means game is currently in play
     * 'T' means game ended in tie
     * 'X' means player won
     * 'O' means computer won
     */
    protected char gameResult;

    /** Constructor for TicGame objects */
    public TicGame(int boardSize)
    {
        this.boardSize = boardSize;
        gameResult = '?';
        BLANK = ' ';
        board = new char [boardSize] [boardSize];
        for (int i=0;i<boardSize; i++){
            for (int j=0;j<boardSize; j++)
                board[i][j] = BLANK;
        }
    }

    /** Executes moves onto board, returns false if move is invalid
     * @param move the move that's to be executed
     * @param symbol the symbol of the player making the move
     * @return true if the move is actually executed
     */
    protected boolean executeMove(TicMove move, char symbol)
    {
        if(this.board[move.row][move.col] == BLANK) {
            this.board[move.row][move.col] = symbol;
            return true;
        }
        else {
            return false;
        }
    }

    /** Returns the game board size
     * @return game board size
     */
    public int getBoardSize()
    {
        return boardSize;
    }

    /** Returns game result as a character. */
    public char getGameResult()
    {
        return gameResult;
    }

    /** Returns true if game is over, game is over if either player has completed a line, or diagonal. Game is also over if game board is full.
     * @return boolean indicating whether game is over or not
     */
    public boolean isGameOver(){
        int full = 0;
        int pline = 0;
        int cline = 0;
        int pdiag = 0;
        int cdiag = 0;
        for(int i=0; i<boardSize;i++){
            cline = 0;
            pline = 0;
            if(this.board[i][i] == 'X')
                pdiag++;
            if(this.board[i][i] == 'O')
                cdiag++;
            for(int j=0;j<boardSize;j++){
                if (this.board[i][j] == 'X')
                    pline++;
                if (this.board[i][j] == 'O')
                    cline++;
            }
            if ((pdiag == boardSize) || (pline == boardSize)){
                this.gameResult = 'X';
                return true;
            }
            if ((cdiag == boardSize) || (cline == boardSize)){
                this.gameResult = 'O';
                return true;
            }
        }
        pdiag = 0;
        cdiag = 0;
        for(int i=0; i<boardSize;i++){
            cline = 0;
            pline = 0;
            if(this.board[i][(boardSize - 1) - i] == 'X')
                pdiag++;
            if(this.board[i][(boardSize - 1) - i] == 'O')
                cdiag++;
            for(int j=0;j<boardSize;j++){
                if (this.board[j][i] == 'X')
                    pline++;
                if (this.board[j][i] == 'O')
                    cline++;
            }
            if ((pdiag == boardSize) || (pline == boardSize)){
                this.gameResult = 'X';
                return true;
            }
            if ((cdiag == boardSize) || (cline == boardSize)){
                this.gameResult = 'O';
                return true;
            }
        }
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                if (this.board[i][j] != BLANK)
                    full++;
            }
        }
        if (full == (boardSize*boardSize)){
            this.gameResult = 'T';
            return true;
        }
        return false;
    }

    /** Resets game state */
    protected void resetGame()
    {
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++)
                this.board[i][j] = BLANK;
        }
        this.gameResult = '?';
    }

    /** Creates textual representation of game board in actual tictactoe format.
     * @return String representing game board
     */
    public String toString(){
        String ac = "";
        for (int i=1; i<=boardSize; i++){
            ac = ac + "   " + i;
            if (i == boardSize)
                ac = ac + "\n";
        }
        for (int i=0; i<boardSize; i++){
            char c = (char) ('A' + i);
            ac = ac + c + "  ";
            for (int j=0; j<boardSize; j++){
                if (j == boardSize - 1)
                    ac = ac + board[i][j] + " \n  ";
                else
                    ac = ac + board[i][j] + " | ";
            }
            if (i != boardSize - 1){
                for (int k=0; k<boardSize; k++){
                    if (k == boardSize - 1){
                        if (i != boardSize - 1)
                            ac = ac + "---\n";
                        else ac = ac + "---";
                    }
                    else
                        ac = ac + "---|";
                }
            }
        }
        return ac;
    }

    /** Runs a text-based game of Tic-tac-toe. Repeatedly creates games until user quits. Win rate is then printed at end.
     * @param args - First argument represents size of game board, ranging from [1,9]. If none is present, default size is 3x3.
     */
    public static void main(String[] args)
    {
        int bsize;
        try {
            try{
                if (1 <= Integer.parseInt(args[0]) &&  Integer.parseInt(args[0]) <= 9)
                    bsize = Integer.parseInt(args[0]);
                else
                    bsize = 3;
            }
            catch(java.lang.NumberFormatException e){
                bsize = 3;
            }
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            bsize = 3;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------New Game----------");
        TicGame a = new TicGame(bsize);
        System.out.println(a);
        HumanTicPlayer player = new HumanTicPlayer(a);
        CpuTicPlayer cpu = new CpuTicPlayer(a);
        TicMove m;
        char s;
        double turn = Math.random();
        int games = 0;
        int wins = 0;
        while (a.gameResult == '?'){
            if(turn >= .5){
                System.out.printf("Your move (quit to exit):");
                m = player.pickMove();
                s = player.getSymbol();
                if(m == null)
                    break;
                try{
                    if(a.executeMove(m, s))
                        a.executeMove(m,s);

                    else {
                        System.out.println("Invalid move: position already taken.");
                        continue;
                    }
                }
                catch(java.lang.ArrayIndexOutOfBoundsException c){
                    System.out.println("Invalid move: Out of Bounds");
                    continue;
                }
                System.out.println(a);
                turn = 0;
            }
            else{
                m = cpu.pickMove();
                s = cpu.getSymbol();
                if(a.executeMove(m, s))
                    a.executeMove(m,s);
                else continue;
                System.out.println("CPU's move");
                System.out.println(a);
                turn = .9;
            }
            a.isGameOver();
            if(a.gameResult == 'O' || a.gameResult == 'T' || a.gameResult =='X'){
                if(a.gameResult == 'X'){
                    wins++;
                    System.out.println("You won! Play again?");
                }
                if(a.gameResult == 'O')
                    System.out.println("You lost! Try again?");
                if(a.gameResult == 'T')
                    System.out.println("It's a tie! Try again?");
                games++;
                a.resetGame();
                turn = Math.random();
                System.out.println(a);
            }
        }
        if (games >= 1){
            int percent = (int)((wins * 100.0f) / games);
            System.out.println("You won " + wins + " out of " + games + " games (" + percent + "%). Goodbye!");
        }
        else System.out.println("Goodbye!");
        return;
    }
}

