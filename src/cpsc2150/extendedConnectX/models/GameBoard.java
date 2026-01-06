package cpsc2150.extendedConnectX.models;

/**
 *This class creates (3-100)X(3-100) game board of ConnectX, verifies a winner and displays
 * board status
 *
 * @Author Jackson Champion CPSC 2150
 * @version 1.0
 *
 * @invariant board[][](@code == char) AND board[][](@code == ' ')
 *
 * @correspondence self = board[0...row][0...column]
 *
 */

public class GameBoard extends AbsGameBoard implements IGameBoard {

    /**
     * character position of game board
     */
    private char[][] board;
    /**
     * row number on game board
     */
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
     * This constructor creates an object of a (3-100)X(3-100) game board of ConnectX
     *
     * @param numRow represents the number of rows in the game board
     *
     * @param numColumn represents the number of columns in the game board
     *
     * @param numTokens represents the number of tokens in a row needed to win
     *
     * @pre
     *      numRow{@code >=} 3 AND numRow{@code <=} MAX_SIZE AND
     *       numColumn{@code >=} MIN_SIZE AND numColumn{@code <=} MAX_SIZE AND
     *       numTokens{@code >=} MIN_TO_WIN AND numTokens {@code <=} MAX_TO_WIN
     *
     * @post
     *      GameBoard = #GameBoard AND
     *      row = #numRow AND
     *      column = #numColumn AND
     *      numToWin  = #numTokens AND
     *      board = #board[numRow][numColumn] AND
     *      board[0...numRow][0...numColumn] = ' '
     *
     */
    public GameBoard(int numRow, int numColumn, int numTokens){
        this.row = numRow;
        this.column = numColumn;
        this.numToWin = numTokens;
        this.board = new char[numRow][numColumn];

        //creates an empty board with spaces
        for(int i = 0; i < numRow; i++) {
            for(int j = 0; j < numColumn; j++) {
                board[i][j] = ' ';
            }
        }
    }


    public void placeToken(char p, int c) {
        int row = getNumRows();

        //places character token to chosen space on board
        for(int r = 0; r < row; r++) {
            if(board[r][c] == ' ') {
                board[r][c] = p;
                return;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        char val = board[row][col];

        //returns space if found
        if(val == ' ') {
            return ' ';
        }
        else {
            return val;
        }

    }
    public int getNumRows() {
        return row;
    }
    public int getNumColumns() {
        return column;
    }
    public int getNumToWin() {
        return numToWin;
    }
}
