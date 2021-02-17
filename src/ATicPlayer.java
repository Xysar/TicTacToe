/**
 * An abstract class that represents a generic Tic-tac-toe game player
 * @version CMPU-102
 * @author Cesar Camacho
 */
public abstract class ATicPlayer
{
    /** The game the player is playing */
    protected TicGame game;
    /** The character that represents the player's moves on the board*/
    protected char symbol;

    /** Empty constructor for objects of class ATicPlayer */
    protected ATicPlayer()
    {
    }

    /** Constructor for objects of class ATicPlayer
     * @param game the tic-tac-toe game that the player is playing.
     */

    protected ATicPlayer(TicGame game)
    {
        this.game = game;
    }

    /** Returns the symbol that represents this player
     * @return a char representing the player's symbol
     */
    public char getSymbol()
    {
        return symbol;
    }

    /** Makes player pick his next move. Each subclass of player implements this method differently.
     * @return the move picked by the player
     */
    public abstract TicMove pickMove();

}
