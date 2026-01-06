package cpsc2150.extendedConnectX.models;
/**
 *Creates a position on the (3-100)X(3-100) game board using rows and columns
 *
 * @Author Jackson Champion CPSC 2150
 * @version 1.0
 *
 * @invariant row {@code <=} 100 AND row {@code >=} 3 AND column {@code <=} 100 AND column {@code >=} 3
 *
 * @correspondence self row = #row AND column = #column
 *
 */

public class BoardPosition {
    private int row;
    private int column;

    /**
     * Obtain the current position on the board by rows and columns
     *
     * @param row represents the row position on the board
     * @param column represents the column position on the board
     *
     * @pre
     *      row {@code >=} MIN_SIZE AND row {@code <=} MAX_SIZE AND
     *      column {@code >=} MIN_SIZE AND column {@code <=} MAX_SIZE
     *
     * @post
     *       row = #row
     *       column = #column
     */
    public BoardPosition(int row, int column){
        this.row = row;
        this.column = column;
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
     *      getRow = row AND
     *      row = #row
     */
    public int getRow() {
        return row;
    }

    /**
     * returns the column position on the board
     *
     * @return the column position
     *
     * @pre
     *      NONE
     *
     * @post
     *      getColumn = column AND
     *      column = #column
     */
    public int getColumn() {
        return column;
    }

    /**
     * indicates whether an object is equal to the referenced object
     *
     * @param obj represents an instance of a class the method is comparing
     *
     * @return true if the object is the same as the argument. False otherwise
     *
     * @pre
     *      NONE
     *
     * @post
     *      equals = this == obj AND
     *      obj = #obj
     */
    @Override
    public  boolean equals(Object obj) {
        if(obj instanceof BoardPosition ) {
            return ((BoardPosition) obj).row == row && ((BoardPosition) obj).column == column;
        }
        else {
            return false;
        }
    }

    /**
     * returns a string representation of the BoardPosition
     *
     * @return a string representation of rows and columns
     *
     * @pre
     *      NONE
     *
     * @post
     *     toString = [A string representation of cpsc.extendedConnectX.models.BoardPosition]
     *
     */
    @Override
    public String toString(){
        return  row + "," + column;
    }
}
