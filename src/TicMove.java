/**
 * Represents a move in a Tic-tac-toe game 
 * @version CMPU-102
 * @author Cesar Camacho
 */
public class TicMove
{
    /**the move's column */
    int col;
    /** The move's row */
    int row;

    /** Constructor for objects of class TicMove
     * @param row - the move's row
     * @param col - the move's column
     */
    public TicMove(int row, int col)
    {
        this.col = col;
        this.row = row;
    }
}