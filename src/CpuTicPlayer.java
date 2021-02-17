/** A computer-controlled Tic-tac-toe player that implements a random playing strategy
 * @version CMPU-102
 * @author Cesar Camacho
 */
public class CpuTicPlayer extends ATicPlayer
{

    /** Constructor for objects of class CpuTicPlayer
     * @param Tic-tac-toe game that the computer is playing
     */
    public CpuTicPlayer(TicGame game)
    {
        this.game = game;
    }

    /** Returns symbol that represents computer player on game board
     * @return Returns character that represents computer player
     */
    public char getSymbol()
    {
        symbol = 'O';
        return symbol;
    }

    /** Picks a move at random. Move stays within boundaries of board and only acts on unoccupied spaces.
     * @return chosen random move.
     */
    public TicMove pickMove()
    {
        int movex = (int) (Math.random() * game.boardSize);
        int movey = (int) (Math.random() * game.boardSize);
        TicMove move = new TicMove(movex, movey);
        return move;
    }
}
