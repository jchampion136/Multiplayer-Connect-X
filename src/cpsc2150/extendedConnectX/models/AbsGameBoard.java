package cpsc2150.extendedConnectX.models;
import cpsc2150.extendedConnectX.models.IGameBoard;
/**
 *This class overrides the toString() function that returns a StringBuilder of the game board
 *
 *
 * @author Jackson Champion CPSC 2150
 * @version 1.0
 *
 * @invariant each position of a board must represent a player token as any char OR
 *            an empty space ' ' to represent no token is present
 *
 * @correspondence self = [A string representation of cpsc.extendedConnectX.models.GameBoard]
 *
 */
public abstract class AbsGameBoard implements IGameBoard{
    /**
     * returns a string representation of the Game Board
     *
     * @return a string representation of cpsc.extendedConnectX.models.GameBoard
     *
     * @pre
     *      NONE
     *
     * @post
     *     toString = [ String representation of the game board ] and self = #self
     *
     */
    @Override
    public String toString(){
        //creates a stringBuilder to print to and sets constant
        StringBuilder sb = new StringBuilder();
        final int SINGLE_DIGIT = 9;

        //creates spacers between tokens on the board
        sb.append("|");

        //appends columns to the sringbuilder and numbers them
        for (int i = 0; i < getNumColumns(); i++) {
            if(i <= SINGLE_DIGIT) {
                sb.append(' ');
            }
            sb.append(i).append("|");
        }
        //adds a newline after the numbers
        sb.append("\n");

        //appends game board to StringBuilder
        for(int i = getNumRows()-1; i >= 0; i--) {
            sb.append("|");
            for(int j = 0; j < getNumColumns(); j++) {
                BoardPosition pos = new BoardPosition(i,j);
                sb.append(whatsAtPos(pos));
                sb.append(" |");
            }
            sb.append("\n");
        }

        //returns string representation of game board
        return sb.toString();
    }
}
