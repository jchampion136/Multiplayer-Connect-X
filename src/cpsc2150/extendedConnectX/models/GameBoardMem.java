package cpsc2150.extendedConnectX.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *This class creates (3-100)X(3-100) game board of ConnectX, verifies a winner and displays board status
 *
 * @author Jackson Champion CPSC 2150
 * @version 1.0
 *
 * @invariant represented by a map with keys of type Character and values of type List,BoardPosition
 *  the keys are either a game token char or a space (' ') which indicates an empty position
 *
 * @correspondence self = [boardMap Character, List{@code BoardPosition}]
 *
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    /**
     * creates position of board using a map
     */
    private Map<Character, List<BoardPosition>> boardMap;
    private final int row;
    /**
     * column number on game board
     */
    private final int column;
    /**
     * number of tokens in a row to win a game
     */
    private final int numToWin;

    /**
     * This constructor Creates a new game board with the specified number of rows, columns, and tokens.
     *
     * @param numRow represents the number of rows in the game board
     * @param numColumn represents the number of columns in the game board
     * @param numTokens represents the number of tokens required to win the game
     *
     * @pre
     *      numRow{@code >=} MIN_TO_WIN AND numRow{@code <=} MAX_SIZE AND
     *      numColumn{@code >=} MIN_TO_WIN  AND numColumn{@code <=} MAX_SIZE AND
     *      numTokens{@code >=} MIN_TO_WIN AND numTokens {@code <=} MAX_TO_WIN
     *
     * @post
     *      GameBoardMem = #GameBoardMem
     *      row = #numRow
     *      column = #numColumn
     *      numToWin = #numTokens
     *      boardMap character, List {@code BoardPosition} = ' '
     */
    public GameBoardMem(int numRow, int numColumn, int numTokens){
        this.row = numRow;
        this.column = numColumn;
        this.numToWin = numTokens;
        boardMap = new HashMap<>();
    }

    public void placeToken(char p, int c){
        List<BoardPosition> listPos = boardMap.computeIfAbsent(p, character -> new ArrayList<>());
        int row = getNumRows();
        boolean isOpen;

        for(int i = 0; i < row; i++) {
            BoardPosition pos = new BoardPosition(i,c);
            if(!listPos.contains(pos)) {
                isOpen = true;

                // loops through the positions occupied by other players to check if the current position is available
                for (Map.Entry<Character, List<BoardPosition>> map : boardMap.entrySet()) {
                    if (map.getKey() != p) {
                        List<BoardPosition> playerPosition = map.getValue();
                        for (BoardPosition playerPos : playerPosition) {
                            if (playerPos.getColumn() == c && playerPos.getRow() == i) {
                                isOpen = false;
                                break;
                            }
                        }
                        if (!isOpen) {
                            break;
                        }
                    }
                }

                //if the position is open, add to player map of positions
                if (isOpen) {
                    listPos.add(pos);
                    return;
                }
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        //gets column at position
        int column = pos.getColumn();

        // iterates over the boardMap to check if any player has a piece at the given position
        for (Map.Entry<Character, List<BoardPosition>> map : boardMap.entrySet()) {
            char player = map.getKey();
            List<BoardPosition> playerPosition = map.getValue();
            for (BoardPosition playerPos : playerPosition) {
                if (playerPos.getColumn() == column && playerPos.equals(pos)) {
                    return player;
                }
            }
        }
        //returns empty space
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char p) {
        if(whatsAtPos(pos) == p) {
            return true;
        }
        else {
            return false;
        }
    }
    public int getNumRows(){
        return row;
    }

    public int getNumColumns(){
        return column;
    }

    public int getNumToWin(){
        return numToWin;
    }
}
