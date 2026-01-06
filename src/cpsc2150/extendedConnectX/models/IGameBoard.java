package cpsc2150.extendedConnectX.models;

/**
 * A game board of ConnectX
 * A cpsc.extendedConnectX.models.GameBoard is a (3-100)X(3-100) that places tokens verifies position,
 * displays board statuses, and verifies a winner or tie.

 Initialization:
 cpsc.extendedConnectX.models.GameBoard contains up to 100 rows and 100 columns and no less than 3 rows and 3 columns of blank characters
 Separated by "|" {@code <>}

 Defines:
 number_of_rows: 3-100
 number_of_columns: 3-100
 number_to_win: 3-25

 Constraints:
 0 {@code <=} number_of_rows {@code <} MAX_SIZE
 0 {@code <=}  number_of_columns {@code <} MAX_SIZE
 MIN_TO_WIN {@code <=}  number_to_win {@code <=}  MAX_TO_WIN
 */
public interface IGameBoard {
    /**
     * The minimum number of players allowed in the game.
     */
    int MIN_PLAYERS = 2;
    /**
     * The maximum number of players allowed in the game.
     */
    int MAX_PLAYERS = 10;
    /**
     * The minimum number of rows and columns allowed on a board
     */
    int MIN_SIZE = 3;
    /**
     * The maximum number of rows and columns allowed on a board
     */
    int MAX_SIZE = 100;
    /**
     * The minimum number of rows needed to win a game
     */
    int MIN_TO_WIN = 3;
    /**
     * The maximum number of rows needed to win a game
     */
    int MAX_TO_WIN = 25;

    /**
     * Returns True if column can fit another token; otherwise, returns False if full
     *
     * @return True if chosen column can fit another token; otherwise, returns false
     *
     * @param c the column the token is placed
     *
     * @pre
     *      c{@code >=} 0 AND c{@code <} MAX_SIZE
     *
     * @post
     *      checkIfFree iff [column fits tokens] AND c = #c
     *
     */
    default boolean checkIfFree(int c) {
        BoardPosition pos;

        for(int r = 0; r < getNumRows(); r++) {
            pos = new BoardPosition(r,c);
            if(whatsAtPos(pos) == ' ') {
                return true;
            }
        }
        return false;
    }
    /**
     * places the character p token in chosen column c. The token is placed in the
     * lowest available row in column.
     *
     * @param p The character of the last player who placed a token
     *
     * @param c the column the token is placed
     *
     * @pre
     *      p {@code ==} char
     *      c {@code >=} 0 AND c {@code <} MAX_SIZE
     *
     * @post
     *      p = #p AND c = #c
     */

    public void placeToken(char p, int c);
    /**
     * checks to see if the last token placed has resulted in the player winning
     * the game. If so, returns True; otherwise, returns False
     *
     * @return True if last token placed results in win; otherwise returns False
     *
     * @param c the column the token is placed
     *
     * @pre
     *      c {@code >=} 0 AND c{@code <} MAX_SIZE
     *
     * @post
     *      checkForWin iff checkDiagWin(pos,p) OR checkVertWin(pos,p) OR
     *      checkHorizWin(pos,p)
     *
     */
    default boolean checkForWin(int c){
        int row = getNumRows();
        char p;

        //creates a new instance of BoardPosition, if space is found on board continue through loop
        for(int r = 0; r < row; r++) {
            BoardPosition pos = new BoardPosition(r, c);
            if (whatsAtPos(pos) == ' ') {
                continue;

            }

            //set player token to the position
            p = whatsAtPos(pos);

            //checks if each win condition is true returns false if not
            if(checkHorizWin(pos,p)) {
                return true;
            }
            else if (checkVertWin(pos,p)) {
                return true;
            }
            else if (checkDiagWin(pos,p)) {
                return true;
            }

        }
        return false;
    }
    /**
     * checks to see if the last token placed has left no free board positions remaining,
     * resulting in a tie. If conditions for a tie is met, Returns true; otherwise, return false
     *
     * @return True if there are no free board positions remaining; otherwise, returns false
     *
     * @pre
     *      NONE
     *
     * @post
     *      checkTie iff [no free board positions remaining]
     */
    default boolean checkTie() {
        //sets row and column to the appropriate method and creates instance of BoardPosition
        int row = getNumRows();
        int col = getNumColumns();
        BoardPosition pos;

        //checks the board for an open position, if not return true
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                pos = new BoardPosition(i,j);
                if(whatsAtPos(pos) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * checks to see if the last token placed has resulted in a number_to_win in a row win horizontally.
     * returns true if conditions are met; otherwise, return false
     *
     * @return True if latest placed token results in number_to_win in a row horizontally; otherwise,
     * returns false
     *
     * @param pos The position of the game board
     *
     * @param p The character of the last player who placed a token
     *
     * @pre
     *      p {@code ==} char
     *      pos.getRow() {@code >=} 0 AND pos.getRow(){@code <=} MAX_SIZE AND
     *      pos.getColumn() {@code >=} 0 AND pos.getColumn(){@code <=} MAX_SIZE
     *
     * @post
     *      checkHorizWin iff [latest player token placed number_to_win in a row horizontally] AND
     *      p = #p
     */
    default boolean checkHorizWin(BoardPosition pos, char p) {
        //sets row and column to the appropriate method, sets counter to 0 and creates instance of BoardPosition
        int row = pos.getRow();
        int col = pos.getColumn();
        int count = 0;
        BoardPosition newPos;

        //checks for tokens to the left of current position
        for(int i = col; i >= 0; i--) {
            newPos = new BoardPosition(row,i);
            if(whatsAtPos(newPos) == p) {
                count++;
            }
            else {
                break;
            }
        }

        //checks for tokens to the right of the current position
        for(int i = col + 1 ; i < getNumColumns(); i++) {
            newPos = new BoardPosition(row,i);
            if(whatsAtPos(newPos)== p) {
                count++;
            }
            else {
                break;
            }
        }

        //if count is greater than or equal to number needed to win, return true, else return false
        if(count >= getNumToWin()) {
            return true;
        }

        return false;
    }
    /**
     * checks to see if the last token placed has resulted in a number_to_win in a row win vertically.
     * returns true if conditions are met; otherwise, return false
     *
     * @return True if latest placed token results in number_to_win in a row vertically; otherwise,
     * returns false
     *
     * @param pos The position of the game board
     *
     * @param p The character of the last player who placed a token
     *
     * @pre
     *      p{@code ==} char AND
     *      pos.getRow(){@code >=} 0 AND pos.getRow(){@code <=} MAX_SIZE AND
     *      pos.getColumn(){@code >=} 0 AND pos.getColumn(){@code <=} MAX_SIZE
     *
     * @post
     *      checkVertWin iff [latest player token placed number_to_win in a row vertically] AND
     *      p = #p
     *
     */
    default boolean checkVertWin(BoardPosition pos, char p) {
        //sets row and column to the appropriate method, sets counter to 0 and creates instance of BoardPosition
        int row = pos.getRow();
        int col = pos.getColumn();
        int count = 0;
        BoardPosition newPos;

        //checks for tokens above current position
        for (int i = row; i < getNumRows(); i++) {
            newPos = new BoardPosition(i,col);
            if (whatsAtPos(newPos) == p) {
                count++;
            }
            else {
                break;
            }
        }

        //checks for tokens below current position
        for (int i = row - 1; i >= 0; i--) {
            newPos = new BoardPosition(i,col);
            if (whatsAtPos(newPos) == p) {
                count++;
            }
            else {
                break;
            }
        }

        //if count is greater than or equal to number needed to win, return true, else return false
        if (count >= getNumToWin()) {
            return true;
        }

        return false;
    }
    /**
     * checks to see if the last token placed has resulted in a number_to_win in a row win diagonally.
     * returns true if conditions are met; otherwise, return false
     *
     * @return True if latest player token results in number_to_win in a row diagonally; otherwise,
     * returns false
     *
     * @param pos The position of the game board
     *
     * @param p The character of the last player who placed a token
     *
     * @pre
     *       p {@code ==} char AND
     *      pos.getRow(){@code >=} 0 AND pos.getRow(){@code <} MAX_SIZE AND
     *      pos.getColumn(){@code >=} 0 AND pos.getColumn(){@code <} MAX_SIZE
     *
     * @post
     *      checkDiagWin iff [latest player token placed number_to_win in a row diagonally] AND
     *      p = #p
     */
    default boolean checkDiagWin(BoardPosition pos, char p) {
        //sets row and column to the appropriate method, sets counter to 1 and creates instance of BoardPosition
        int row = pos.getRow();
        int col = pos.getColumn();
        int count = 1;
        BoardPosition newPos;

        //checks bottom left diagonal for tokens
        for(int i = row - 1 ,j = col - 1; i >= 0 && j >= 0; i--, j--) {
            newPos = new BoardPosition(i,j);
            if(whatsAtPos(newPos)== p) {
                count++;
            }
            //break loop if none are found
            else {
                break;
            }
        }

        //checks top right diagonal for tokens
        for(int i = row + 1,  j = col + 1 ; i < getNumRows() && j < getNumColumns(); i++, j++) {
            newPos = new BoardPosition(i,j);
            if(whatsAtPos(newPos)== p) {
                count++;
            }
            //break loop if none are found
            else {
                break;
            }
        }

        //if count is greater than or equal to number needed to win, return true
        if(count >= getNumToWin()) {
            return true;
        }

        //resets count to check other diagonal
        count = 1;

        //checks bottom right diagonal for tokens
        for(int i = row - 1 ,j = col + 1; i >= 0 && j < getNumColumns(); i--, j++) {
            newPos = new BoardPosition(i,j);
            if(whatsAtPos(newPos) == p) {
                count++;
            }
            //break loop if none are found
            else {
                break;
            }
        }

        //checks top left diagonal for tokens
        for(int i = row + 1, j = col - 1; i < getNumRows() && j >= 0; i++, j--) {
            newPos = new BoardPosition(i,j);
            if(whatsAtPos(newPos) == p) {
                count++;
            }
            //break loop if none are found
            else {
                break;
            }
        }

        //if count is greater than or equal to number needed to win, return true, else return false
        if (count >= getNumToWin()) {
            return true;
        }

        return false;
    }

    /**
     * return Checks what is in pos and returns the character. If no character is found,
     * returns a blank space char
     *
     * @return char in position; otherwise, return blank space char
     *
     * @param pos The position of the game board
     *
     * @pre
     *      pos.getRow() {@code >=} 0 AND pos.getRow(){@code <} MAX_SIZE AND
     *      pos.getColumn(){@code >=} 0 AND pos.getColumn(){@code <} MAX_SIZE
     *
     * @post
     *      whatsAtPos = ' ' OR char
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * return true if player is at parameter position; otherwise, return false
     *
     * @return True if player is at position; returns false if not
     *
     * @param pos The position of the game board
     *
     * @param p The character of the last player who placed a token
     *
     * @pre
     *      p{@code ==} char AND
     *      pos.getRow(){@code >=} 0 AND pos.getRow(){@code <} MAX_SIZE AND
     *      pos.getColumn(){@code >=} 0 AND pos.getColumn(){@code <} MAX_SIZE
     *
     * @post
     *      isPLayerAtPos iff [player at position] AND p = #p
     */
    default boolean isPlayerAtPos(BoardPosition pos, char p) {
        if(whatsAtPos(pos) == p) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * returns the row position on the board
     *
     * @return the row position
     *
     * @pre
     *      NONE
     *
     * @post
     *      self = row AND
     *      row = #row
     */
    public int getNumRows();

    /**
     * returns the column position on the board
     *
     * @return the column position
     *
     * @pre
     *      NONE
     *
     * @post
     *      self = column AND
     *      column = #column
     */
    public int getNumColumns();
    /**
     * returns the number of tokens in a row needed to win the game
     *
     * @return number of tokens in a row to win game
     *
     * @pre
     *      NONE
     *
     * @post
     *      self = [number of tokens in row to win]
     */
    public int getNumToWin();
}