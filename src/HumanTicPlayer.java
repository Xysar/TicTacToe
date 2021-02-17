import java.util.Scanner;

/** A human Tic-tac-toe player that inputs moves from the keyboard
 * @version CMPU-102
 * @author Cesar Camacho
 */
public class HumanTicPlayer extends ATicPlayer
{
    /**Constructor for objects of class HumanTicPlayer
     * @param Tic-tac-toe game that the player is playing
     */
    public HumanTicPlayer(TicGame game)
    {
        this.game = game;
    }

    /** returns symbol that represents human player on game board
     * @return character that represents human player
     */
    public char getSymbol()
    {
        symbol = 'X';
        return symbol;
    }

    /** This method asks user to pick a tic-tac-toe move, in the form of two characters, rc. r represents letter of row and c is digit representing column. If user writes quit, the method returns null.
     * @return the move the user chose or null if user wants to exit
     */
    public TicMove pickMove ()
    {
        Scanner scanner = new Scanner(System.in);
        String rc;
        rc = scanner.next().toUpperCase();
        if (rc.equals("QUIT"))
            return null;
        else{
            int r = (int) rc.charAt(0) - 65;
            int c = Character.digit(rc.charAt(1), 10) - 1;
            TicMove n = new TicMove(r, c);
            return n;
        }
    }
}
