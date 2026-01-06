package cpsc2150.extendedConnectX.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(org.junit.runners.JUnit4.class)

public class TestGameBoard {

    private IGameBoard makeABoard(int numRows, int numCols, int numToWin) {
        IGameBoard gb = new GameBoard(numRows, numCols, numToWin);
        return gb;

    }

    private char[][] makeEmptyBoard(int row, int col) {
        char[][] board = new char[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                board[r][c] = ' ';
            }
        }
        return board;
    }

    private String makeBoardWithTokens(char[][] board) {
        StringBuilder sb = new StringBuilder();

        sb.append("|");

        for (int i = 0; i < board[0].length; i++) {
            if (i <= 9) {
                sb.append(' ');
            }
            sb.append(i).append("|");
        }

        sb.append("\n");

        for (int i = (board.length) - 1; i >= 0; i--) {
            sb.append("|");
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
                sb.append(" |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Test
    public void testConstructor_six_by_ten() {
        IGameBoard gb = makeABoard(6, 10, 0);
        char[][] board = makeEmptyBoard(6, 10);
        String str = makeBoardWithTokens(board);


        assertEquals(str, gb.toString());
    }

    @Test
    public void testConstructor_one_hundred_by_one_hundred() {
        IGameBoard gb = makeABoard(100, 100, 0);
        char[][] board = makeEmptyBoard(100, 100);
        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
    }

    @Test
    public void testConstructor_three_by_three() {
        IGameBoard gb = makeABoard(3, 3, 0);
        char[][] board = makeEmptyBoard(3, 3);
        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
    }

    @Test
    public void testCheckIfFree_column_one_full_second_marker() {
        IGameBoard gb = makeABoard(5, 6, 0);
        char[][] board = makeEmptyBoard(5, 6);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        board[0][0] = 'X';
        board[1][0] = 'O';
        board[0][1] = 'O';
        board[1][1] = 'O';
        board[2][1] = 'X';
        board[3][1] = 'O';
        board[4][1] = 'O';

        for (int i = 0; i <= 2; i++) {
            gb.placeToken('X', 3);
            board[i][3] = 'X';
        }

        gb.placeToken('X', 5);
        board[0][5] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkIfFree(1));
    }

    @Test
    public void testCheckIfFree_column_five_open_first_marker() {
        IGameBoard gb = makeABoard(7, 6, 0);
        char[][] board = makeEmptyBoard(7, 6);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        board[0][0] = 'X';
        board[1][0] = 'O';

        for (int i = 0; i <= 1; i++) {
            gb.placeToken('O', 1);
            gb.placeToken('X', 3);
            gb.placeToken('O', 4);
            gb.placeToken('X', 5);
            board[i][1] = 'O';
            board[i][3] = 'X';
            board[i][4] = 'O';
            board[i][5] = 'X';
        }

        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 3);
        board[2][1] = 'X';
        board[3][1] = 'O';
        board[4][1] = 'X';
        board[2][3] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkIfFree(5));
    }

    @Test
    public void testCheckIfFree_column_three_full_first_marker() {
        IGameBoard gb = makeABoard(5, 6, 0);
        char board[][] = makeEmptyBoard(5, 6);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        board[0][0] = 'X';
        board[1][0] = 'O';

        for (int i = 0; i <= 1; i++) {
            gb.placeToken('O', 1);
            gb.placeToken('X', 3);
            gb.placeToken('X', 5);
            board[i][1] = 'O';
            board[i][3] = 'X';
            board[i][5] = 'X';
        }

        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        board[0][2] = 'O';
        board[1][2] = 'X';
        board[2][3] = 'O';
        board[3][3] = 'O';
        board[4][3] = 'X';
        board[0][4] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkIfFree(3));
    }

    @Test
    public void testCheckHorizWin_win_six_marker_middle() {
        IGameBoard gb = makeABoard(9, 10, 6);
        BoardPosition pos = new BoardPosition(1, 2);
        char[][] board = makeEmptyBoard(9, 10);

        gb.placeToken('O', 0);
        gb.placeToken('N', 1);
        gb.placeToken('E', 2);
        gb.placeToken('N', 3);
        gb.placeToken('P', 4);
        gb.placeToken('P', 5);
        gb.placeToken('P', 6);
        gb.placeToken('E', 7);
        gb.placeToken('E', 9);
        board[0][0] = 'O';
        board[0][1] = 'N';
        board[0][2] = 'E';
        board[0][3] = 'N';
        board[0][4] = 'P';
        board[0][5] = 'P';
        board[0][6] = 'P';
        board[0][7] = 'E';
        board[0][9] = 'E';

        for (int i = 0; i <= 5; i++) {
            gb.placeToken('O', i);
            board[1][i] = 'O';
        }

        gb.placeToken('P', 6);
        gb.placeToken('N', 7);
        gb.placeToken('N', 9);
        board[1][6] = 'P';
        board[1][7] = 'N';
        board[1][9] = 'N';
        gb.placeToken('E', 0);
        gb.placeToken('P', 4);
        gb.placeToken('E', 6);
        gb.placeToken('E', 7);
        gb.placeToken('P', 9);
        gb.placeToken('N', 0);
        gb.placeToken('N', 6);
        board[2][0] = 'E';
        board[2][4] = 'P';
        board[2][6] = 'E';
        board[2][7] = 'E';
        board[2][9] = 'P';
        board[3][0] = 'N';
        board[3][6] = 'N';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkHorizWin(pos, 'O'));

    }

    @Test
    public void checkHorizWin_win_five_marker_right_end() {
        IGameBoard gb = makeABoard(14, 8, 5);
        BoardPosition pos = new BoardPosition(5, 5);
        char[][] board = makeEmptyBoard(14, 8);

        gb.placeToken('X', 0);
        gb.placeToken('Y', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('Z', 4);
        gb.placeToken('Z', 5);
        gb.placeToken('X', 6);
        gb.placeToken('X', 7);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 6);
        gb.placeToken('Y', 7);
        board[0][0] = 'X';
        board[0][1] = 'Y';
        board[0][2] = 'X';
        board[0][3] = 'X';
        board[0][4] = 'Z';
        board[0][5] = 'Z';
        board[0][6] = 'X';
        board[0][7] = 'X';
        board[1][0] = 'X';
        board[1][1] = 'X';
        board[1][6] = 'X';
        board[1][7] = 'Y';

        for (int i = 2; i <= 5; i++) {
            gb.placeToken('Y', i);
            board[1][i] = 'Y';
        }

        gb.placeToken('Y', 0);
        gb.placeToken('Z', 1);
        gb.placeToken('Z', 2);
        gb.placeToken('X', 3);
        board[2][0] = 'Y';
        board[2][1] = 'Z';
        board[2][2] = 'Z';
        board[2][3] = 'X';

        for (int i = 4; i <= 7; i++) {
            gb.placeToken('Z', i);
            board[2][i] = 'Z';
        }

        gb.placeToken('Y', 0);
        gb.placeToken('Y', 1);
        gb.placeToken('X', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('Z', 4);
        gb.placeToken('Z', 5);
        gb.placeToken('X', 6);
        gb.placeToken('X', 7);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('X', 4);
        gb.placeToken('Y', 5);
        gb.placeToken('Y', 6);
        gb.placeToken('X', 7);
        board[3][0] = 'Y';
        board[3][1] = 'Y';
        board[3][2] = 'X';
        board[3][3] = 'Y';
        board[3][4] = 'Z';
        board[3][5] = 'Z';
        board[3][6] = 'X';
        board[3][7] = 'X';
        board[4][1] = 'X';
        board[4][2] = 'Y';
        board[4][3] = 'Y';
        board[4][4] = 'X';
        board[4][5] = 'Y';
        board[4][6] = 'Y';
        board[4][7] = 'X';

        for (int i = 1; i <= 5; i++) {
            gb.placeToken('Z', i);
            board[5][i] = 'Z';
        }

        gb.placeToken('X', 6);
        gb.placeToken('X', 7);
        gb.placeToken('Y', 7);
        board[5][6] = 'X';
        board[5][7] = 'X';
        board[6][7] = 'Y';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkHorizWin(pos, 'Z'));
    }

    @Test
    public void testCheckHorizWin_no_win_four_marker_left_end() {
        IGameBoard gb = makeABoard(7, 6, 4);
        BoardPosition pos = new BoardPosition(1, 3);
        char[][] board = makeEmptyBoard(7, 6);

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 5);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'O';
        board[0][3] = 'X';
        board[0][4] = 'O';
        board[0][5] = 'X';

        for (int i = 0; i <= 2; i++) {
            gb.placeToken('O', i);
            board[1][i] = 'O';
        }

        for (int i = 3; i <= 5; i++) {
            gb.placeToken('X', i);
            board[1][i] = 'X';
        }

        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 4);
        gb.placeToken('O', 5);
        board[2][0] = 'O';
        board[2][1] = 'X';
        board[2][4] = 'X';
        board[2][5] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testCheckHorizWin_win_eight_marker_middle() {
        IGameBoard gb = makeABoard(10, 10, 6);
        BoardPosition pos = new BoardPosition(2, 4);
        char[][] board = makeEmptyBoard(10, 10);

        gb.placeToken('X', 0);
        gb.placeToken('Z', 1);
        gb.placeToken('X', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('Y', 4);
        gb.placeToken('Z', 5);
        gb.placeToken('Y', 6);
        gb.placeToken('X', 7);
        gb.placeToken('Y', 8);
        gb.placeToken('Z', 9);
        gb.placeToken('Y', 0);
        gb.placeToken('Z', 1);
        gb.placeToken('X', 2);
        gb.placeToken('Z', 3);
        gb.placeToken('Z', 4);
        gb.placeToken('X', 5);
        gb.placeToken('Z', 6);
        gb.placeToken('X', 7);
        gb.placeToken('X', 8);
        gb.placeToken('Z', 9);
        gb.placeToken('X', 9);
        board[0][0] = 'X';
        board[0][1] = 'Z';
        board[0][2] = 'X';
        board[0][3] = 'Y';
        board[0][4] = 'Y';
        board[0][5] = 'Z';
        board[0][6] = 'Y';
        board[0][7] = 'X';
        board[0][8] = 'Y';
        board[0][9] = 'Z';
        board[1][0] = 'Y';
        board[1][1] = 'Z';
        board[1][2] = 'X';
        board[1][3] = 'Z';
        board[1][4] = 'Z';
        board[1][5] = 'X';
        board[1][6] = 'Z';
        board[1][7] = 'X';
        board[1][8] = 'X';
        board[1][9] = 'Z';
        board[2][9] = 'X';

        for (int i = 2; i <= 4; i++) {
            gb.placeToken('X', 0);
            board[i][0] = 'X';
        }

        for (int i = 1; i <= 8; i++) {
            gb.placeToken('Y', i);
            board[2][i] = 'Y';
        }

        for (int i = 1; i <= 3; i++) {
            gb.placeToken('Z', i);
            board[3][i] = 'Z';
        }

        gb.placeToken('X', 7);
        gb.placeToken('X', 1);
        gb.placeToken('Z', 0);
        board[3][7] = 'X';
        board[4][1] = 'X';
        board[5][0] = 'Z';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkHorizWin(pos, 'Y'));
    }

    @Test
    public void testCheckVertWin_win_five_column_four() {
        IGameBoard gb = makeABoard(8, 8, 5);
        BoardPosition pos = new BoardPosition(6, 4);
        char[][] board = makeEmptyBoard(8, 8);

        gb.placeToken('Y', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('X', 4);
        gb.placeToken('X', 6);
        gb.placeToken('Y', 7);
        gb.placeToken('Z', 0);
        gb.placeToken('Z', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('Z', 3);
        gb.placeToken('Y', 4);
        gb.placeToken('Y', 6);
        gb.placeToken('X', 7);
        gb.placeToken('Y', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('Y', 2);
        gb.placeToken('X', 3);
        board[0][0] = 'Y';
        board[0][1] = 'X';
        board[0][2] = 'X';
        board[0][3] = 'Y';
        board[0][4] = 'X';
        board[0][6] = 'X';
        board[0][7] = 'Y';
        board[1][0] = 'Z';
        board[1][1] = 'Z';
        board[1][2] = 'Y';
        board[1][3] = 'Z';
        board[1][4] = 'Y';
        board[1][6] = 'Y';
        board[1][7] = 'X';
        board[2][1] = 'Y';
        board[2][2] = 'X';
        board[2][3] = 'X';
        board[3][2] = 'Y';
        board[3][3] = 'X';

        for (int i = 2; i <= 6; i++) {
            gb.placeToken('Z', 4);
            board[i][4] = 'Z';
        }

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkVertWin(pos, 'Z'));
    }

    @Test
    public void testCheckVertWin_no_win_five_column_two() {
        IGameBoard gb = makeABoard(8, 6, 5);
        BoardPosition pos = new BoardPosition(6, 2);
        char[][] board = makeEmptyBoard(8, 6);

        gb.placeToken('X', 0);
        board[0][0] = 'X';

        for (int i = 1; i <= 3; i++) {
            gb.placeToken('Y', i);
            board[0][i] = 'Y';
        }

        gb.placeToken('Z', 1);
        board[1][1] = 'Z';

        for (int i = 0; i <= 1; i++) {
            for (int j = 4; j <= 5; j++) {
                gb.placeToken('Z', j);
                board[i][j] = 'Z';
            }
        }

        for (int i = 1; i <= 4; i++) {
            gb.placeToken('X', 2);
            board[i][2] = 'X';
        }

        gb.placeToken('Y', 2);
        gb.placeToken('Y', 4);
        board[5][2] = 'Y';
        board[2][4] = 'Y';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_win_seven_column_zero() {
        IGameBoard gb = makeABoard(11, 7, 7);
        BoardPosition pos = new BoardPosition(9, 0);
        char[][] board = makeEmptyBoard(11, 7);

        for (int i = 0; i <= 2; i++) {
            gb.placeToken('X', 0);
            board[i][0] = 'X';
        }

        gb.placeToken('O', 0);
        board[3][0] = 'O';

        for (int i = 4; i <= 10; i++) {
            gb.placeToken('X', 0);
            board[i][0] = 'X';
        }
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        board[0][1] = 'O';
        board[0][2] = 'X';
        board[0][3] = 'X';


        for (int i = 0; i <= 2; i++) {
            gb.placeToken('O', 4);
            board[i][4] = 'O';
        }

        gb.placeToken('X', 4);
        board[3][4] = 'X';

        for (int i = 4; i <= 6; i++) {
            gb.placeToken('O', 4);
            board[i][4] = 'O';
        }

        for (int i = 0; i <= 2; i++) {
            gb.placeToken('O', 6);
            board[i][6] = 'O';
        }

        gb.placeToken('O', 5);
        board[0][5] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkVertWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_no_win_four_full_column() {
        IGameBoard gb = makeABoard(7, 4, 4);
        BoardPosition pos = new BoardPosition(6, 2);
        char[][] board = makeEmptyBoard(7, 4);

        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        board[0][0] = 'O';
        board[0][1] = 'O';
        board[0][2] = 'X';
        board[0][3] = 'O';
        board[1][1] = 'X';


        for (int i = 1; i <= 3; i++) {
            gb.placeToken('X', 0);
            board[i][0] = 'X';
        }

        for (int i = 1; i <= 4; i++) {
            for (int j = 2; j <= 3; j++) {
                if (i % 2 == 0) {
                    gb.placeToken('X', j);
                    board[i][j] = 'X';
                } else {
                    gb.placeToken('O', j);
                    board[i][j] = 'O';
                }
            }
        }

        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        board[5][2] = 'O';
        board[6][2] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkVertWin(pos, 'O'));
    }

    @Test
    public void testCheckDiagWin_no_win_left_diagonal() {
        IGameBoard gb = makeABoard(8, 6, 5);
        BoardPosition pos = new BoardPosition(3, 2);
        char[][] board = makeEmptyBoard(8, 6);

        gb.placeToken('Y', 0);
        gb.placeToken('Z', 1);
        gb.placeToken('X', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('Z', 4);
        gb.placeToken('X', 5);
        board[0][0] = 'Y';
        board[0][1] = 'Z';
        board[0][2] = 'X';
        board[0][3] = 'Y';
        board[0][4] = 'Z';
        board[0][5] = 'X';

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 2; j++) {
                if (i % 3 == 1) {
                    gb.placeToken('Y', j);
                    board[i][j] = 'Y';
                } else if (i % 3 == 2) {
                    gb.placeToken('Z', j);
                    board[i][j] = 'Z';
                } else {
                    gb.placeToken('X', j);
                    board[i][j] = 'X';
                }
            }
        }

        gb.placeToken('Y', 1);
        gb.placeToken('X', 3);
        gb.placeToken('X', 4);
        gb.placeToken('Y', 5);
        gb.placeToken('X', 3);
        gb.placeToken('Z', 4);
        gb.placeToken('Z', 5);

        board[4][1] = 'Y';
        board[1][3] = 'X';
        board[1][4] = 'X';
        board[1][5] = 'Y';
        board[2][3] = 'X';
        board[2][4] = 'Z';
        board[2][5] = 'Z';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkDiagWin(pos, 'X'));

    }

    @Test
    public void testCheckDiagWin_win_right_diagonal_extra_tokens() {
        IGameBoard gb = makeABoard(9, 10, 6);
        BoardPosition pos = new BoardPosition(4, 7);
        char[][] board = makeEmptyBoard(9, 10);

        gb.placeToken('P',0);
        gb.placeToken('P',0);
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('N',2);
        gb.placeToken('E',3);
        gb.placeToken('P',4);
        gb.placeToken('E',4);
        gb.placeToken('O',5);
        gb.placeToken('N',5);
        gb.placeToken('E',5);
        gb.placeToken('O',5);
        gb.placeToken('P',6);
        gb.placeToken('P',6);
        gb.placeToken('N',6);
        gb.placeToken('E',6);
        gb.placeToken('P',6);
        gb.placeToken('P',6);
        gb.placeToken('O',7);
        gb.placeToken('N',7);
        gb.placeToken('O',7);
        gb.placeToken('N',7);
        gb.placeToken('E',7);
        gb.placeToken('E',8);
        gb.placeToken('O',8);
        gb.placeToken('P',8);
        gb.placeToken('N',8);
        gb.placeToken('O',8);
        gb.placeToken('E',8);
        gb.placeToken('E',8);
        gb.placeToken('O',8);
        gb.placeToken('N',8);
        gb.placeToken('E',9);
        gb.placeToken('P',9);
        gb.placeToken('N',9);
        gb.placeToken('O',9);
        gb.placeToken('P',9);
        gb.placeToken('N',9);
        gb.placeToken('E',9);
        board[0][0] = 'P';
        board[1][0] = 'P';
        board[0][1] = 'O';
        board[1][1] = 'O';
        board[0][2] = 'N';
        board[0][3] = 'E';
        board[0][4] = 'P';
        board[1][4] = 'E';
        board[0][5] = 'O';
        board[1][5] = 'N';
        board[2][5] = 'E';
        board[3][5] = 'O';
        board[0][6] = 'P';
        board[1][6] = 'P';
        board[2][6] = 'N';
        board[3][6] = 'E';
        board[4][6] = 'P';
        board[5][6] = 'P';
        board[0][7] = 'O';
        board[1][7] = 'N';
        board[2][7] = 'O';
        board[3][7] = 'N';
        board[4][7] = 'E';
        board[0][8] = 'E';
        board[1][8] = 'O';
        board[2][8] = 'P';
        board[3][8] = 'N';
        board[4][8] = 'O';
        board[5][8] = 'E';
        board[6][8] = 'E';
        board[7][8] = 'O';
        board[8][8] = 'N';
        board[0][9] = 'E';
        board[1][9] = 'P';
        board[2][9] = 'N';
        board[3][9] = 'O';
        board[4][9] = 'P';
        board[5][9] = 'N';
        board[6][9] = 'E';

        String str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());
        assertTrue(gb.checkDiagWin(pos,'E'));
    }

    @Test
    public void testCheckDiagWin_win_right_diagonal_marker_middle() {
        IGameBoard gb = makeABoard(7, 4, 4);
        BoardPosition pos = new BoardPosition(2, 2);
        char[][] board = makeEmptyBoard(7, 4);

        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 0);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 3);
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'O';
        board[0][3] = 'O';
        board[1][0] = 'O';
        board[1][1] = 'X';
        board[1][2] = 'O';
        board[1][3] = 'X';
        board[2][0] = 'O';
        board[2][2] = 'X';
        board[2][3] = 'O';
        board[3][0] = 'X';
        board[3][3] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testCheckDiagWin_left_diagonal_marker_right_end() {
        IGameBoard gb = makeABoard(6, 6, 5);
        BoardPosition pos = new BoardPosition(0, 5);
        char[][] board = makeEmptyBoard(6, 6);

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                gb.placeToken('X', i);
                board[0][i] = 'X';
            } else {
                gb.placeToken('O', i);
                board[0][i] = 'O';
            }
        }

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        board[1][0] = 'X';
        board[1][1] = 'O';
        board[1][2] = 'O';
        board[1][3] = 'X';
        board[1][4] = 'O';
        board[2][1] = 'X';
        board[2][2] = 'X';
        board[2][3] = 'O';
        board[3][1] = 'O';
        board[3][2] = 'O';
        board[4][1] = 'O';
        board[5][1] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'O'));
    }

    @Test
    public void testCheckDiagWin_win_left_diagonal_marker_left_end() {
        IGameBoard gb = makeABoard(5, 6, 4);
        BoardPosition pos = new BoardPosition(3, 0);
        char[][] board = makeEmptyBoard(5, 6);

        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 5);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 5);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 0);

        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';
        board[0][3] = 'O';
        board[0][5] = 'O';
        board[1][0] = 'X';
        board[1][1] = 'X';
        board[1][2] = 'O';
        board[1][5] = 'X';
        board[2][0] = 'O';
        board[2][1] = 'O';
        board[3][0] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'O'));
    }

    @Test
    public void testCheckDiagWin_no_win_left_diagonal_missing_token() {
        IGameBoard gb = makeABoard(5, 6, 4);
        BoardPosition pos = new BoardPosition(3, 1);
        char[][] board = makeEmptyBoard(5, 6);

        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 5);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 1);

        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'O';
        board[0][3] = 'O';
        board[0][5] = 'X';
        board[1][1] = 'X';
        board[1][2] = 'O';
        board[2][1] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkDiagWin(pos, 'O'));

    }

    @Test
    public void testCheckDiagWin_no_win_right_diagonal_board_end() {
        IGameBoard gb = makeABoard(7, 6, 4);
        BoardPosition pos = new BoardPosition(2, 5);
        char[][] board = makeEmptyBoard(7, 6);

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                gb.placeToken('X', i);
                board[0][i] = 'X';
            } else {
                gb.placeToken('O', i);
                board[0][i] = 'O';
            }
        }

        gb.placeToken('X', 2);
        gb.placeToken('O', 4);
        gb.placeToken('X', 5);
        gb.placeToken('O', 5);
        board[1][2] = 'X';
        board[1][4] = 'O';
        board[1][5] = 'X';
        board[2][5] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkDiagWin(pos, 'O'));
    }

    @Test
    public void testCheckTie_full_three_players(){
        IGameBoard gb = makeABoard(8, 5, 0);
        char[][] board = makeEmptyBoard(8, 5);

        gb.placeToken('Y',0);
        gb.placeToken('X',0);
        gb.placeToken('Y',0);
        gb.placeToken('Z',0);
        gb.placeToken('Z',0);
        gb.placeToken('Y',1);
        gb.placeToken('Z',1);
        gb.placeToken('X',1);
        gb.placeToken('Y',1);
        gb.placeToken('X',1);
        board[0][0] = 'Y';
        board[1][0] = 'X';
        board[2][0] = 'Y';
        board[3][0] = 'Z';
        board[4][0] = 'Z';
        board[0][1] = 'Y';
        board[1][1] = 'Z';
        board[2][1] = 'X';
        board[3][1] = 'Y';
        board[4][1] = 'X';

        for(int i = 0; i <= 1; i++) {
            gb.placeToken('Y', i);
            gb.placeToken('Z', i);
            gb.placeToken('X', i);
            board[5][i] ='Y';
            board[6][i] ='Z';
            board[7][i] ='X';
        }

        gb.placeToken('Z',2);
        gb.placeToken('Y',2);
        gb.placeToken('X',2);
        gb.placeToken('Y',2);
        gb.placeToken('Z',2);
        gb.placeToken('Z',2);
        gb.placeToken('Y',2);
        gb.placeToken('Y',2);
        board[0][2] = 'Z';
        board[1][2] = 'Y';
        board[2][2] = 'X';
        board[3][2] = 'Y';
        board[4][2] = 'Z';
        board[5][2] = 'Z';
        board[6][2] = 'Y';
        board[7][2] = 'Y';

        for(int i = 0; i <= 2; i++) {
            gb.placeToken('Z',3);
            gb.placeToken('X',4);
            board[i][3] = 'Z';
            board[i][4] = 'X';
        }

        gb.placeToken('X',3);
        gb.placeToken('X',3);
        gb.placeToken('Y',3);
        gb.placeToken('X',3);
        gb.placeToken('Z',3);
        gb.placeToken('Y',4);
        gb.placeToken('X',4);
        gb.placeToken('Y',4);
        gb.placeToken('Z',4);
        gb.placeToken('X',4);
        board[3][3] = 'X';
        board[4][3] = 'X';
        board[5][3] = 'Y';
        board[6][3] = 'X';
        board[7][3] = 'Z';
        board[3][4] = 'Y';
        board[4][4] = 'X';
        board[5][4] = 'Y';
        board[6][4] = 'Z';
        board[7][4] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());
        assertTrue(gb.checkTie());

    }
    @Test
    public void testCheckTie_not_full_two_players() {
        IGameBoard gb = makeABoard(4, 4, 0);
        char[][] board = makeEmptyBoard(4, 4);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        board[0][0] = 'X';
        board[1][0] = 'O';
        board[0][1] = 'X';
        board[0][2] = 'O';
        board[1][2] = 'X';
        board[0][3] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkTie());
    }

    @Test
    public void testCheckTie_not_full_two_players_column_one() {
        IGameBoard gb = makeABoard(5, 6, 0);
        char[][] board = makeEmptyBoard(5, 6);

        gb.placeToken('X', 0);
        gb.placeToken('Y', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('Y', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        board[0][0] = 'X';
        board[0][1] = 'Y';
        board[0][2] = 'Y';
        board[1][0] = 'Y';
        board[1][1] = 'X';
        board[1][2] = 'X';

        for (int i = 3; i <= 5; i++) {
            gb.placeToken('X', i);
            gb.placeToken('Y', i);
            board[0][i] = 'X';
            board[1][i] = 'Y';
        }

        gb.placeToken('X', 0);
        gb.placeToken('Y', 1);
        gb.placeToken('X', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('X', 4);
        gb.placeToken('X', 5);
        gb.placeToken('Y', 0);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('Y', 3);
        gb.placeToken('X', 4);
        gb.placeToken('Y', 5);
        gb.placeToken('Y', 0);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('Y', 4);
        gb.placeToken('X', 5);
        board[2][0] = 'X';
        board[2][1] = 'Y';
        board[2][2] = 'X';
        board[2][3] = 'Y';
        board[2][4] = 'X';
        board[2][5] = 'X';
        board[3][0] = 'Y';
        board[3][1] = 'X';
        board[3][2] = 'Y';
        board[3][3] = 'Y';
        board[3][4] = 'X';
        board[3][5] = 'Y';
        board[4][0] = 'Y';
        board[4][2] = 'X';
        board[4][3] = 'X';
        board[4][4] = 'Y';
        board[4][5] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
        assertTrue(!gb.checkTie());
    }

    @Test
    public void testCheckTie_full_four_players(){
        IGameBoard gb = makeABoard(9, 7, 0);
        char[][] board = makeEmptyBoard(9, 7);

        gb.placeToken('O',0);
        gb.placeToken('N',0);
        gb.placeToken('O',0);
        gb.placeToken('E',0);
        gb.placeToken('N',0);
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('P',0);
        gb.placeToken('E',0);
        gb.placeToken('P',1);
        gb.placeToken('E',1);
        gb.placeToken('N',1);
        gb.placeToken('P',1);
        gb.placeToken('P',1);
        gb.placeToken('O',1);
        gb.placeToken('P',1);
        gb.placeToken('E',1);
        gb.placeToken('N',1);
        gb.placeToken('P',2);
        gb.placeToken('E',2);
        gb.placeToken('O',2);
        gb.placeToken('P',2);
        gb.placeToken('E',2);
        gb.placeToken('N',2);
        gb.placeToken('O',2);
        gb.placeToken('P',2);
        gb.placeToken('E',2);
        gb.placeToken('N',3);
        gb.placeToken('O',3);
        gb.placeToken('P',3);
        gb.placeToken('O',3);
        gb.placeToken('E',3);
        gb.placeToken('E',3);
        gb.placeToken('N',3);
        gb.placeToken('E',3);
        gb.placeToken('N',3);
        board[0][0] = 'O';
        board[1][0] = 'N';
        board[2][0] = 'O';
        board[3][0] = 'E';
        board[4][0] = 'N';
        board[5][0] = 'O';
        board[6][0] = 'O';
        board[7][0] = 'P';
        board[8][0] = 'E';
        board[0][1] = 'P';
        board[1][1] = 'E';
        board[2][1] = 'N';
        board[3][1] = 'P';
        board[4][1] = 'P';
        board[5][1] = 'O';
        board[6][1] = 'P';
        board[7][1] = 'E';
        board[8][1] = 'N';
        board[0][2] = 'P';
        board[1][2] = 'E';
        board[2][2] = 'O';
        board[3][2] = 'P';
        board[4][2] = 'E';
        board[5][2] = 'N';
        board[6][2] = 'O';
        board[7][2] = 'P';
        board[8][2] = 'E';
        board[0][3] = 'N';
        board[1][3] = 'O';
        board[2][3] = 'P';
        board[3][3] = 'O';
        board[4][3] = 'E';
        board[5][3] = 'E';
        board[6][3] = 'N';
        board[7][3] = 'E';
        board[8][3] = 'N';

        for(int i = 0; i <= 2; i++) {
            gb.placeToken('E',4);
            board[i][4] = 'E';
        }

        for(int i = 3; i <= 5; i++) {
            gb.placeToken('N',4);
            board[i][4] = 'N';
        }

        gb.placeToken('O',4);
        gb.placeToken('P',4);
        gb.placeToken('E',4);
        gb.placeToken('N',5);
        gb.placeToken('O',5);
        gb.placeToken('P',5);
        gb.placeToken('E',5);
        gb.placeToken('N',5);
        gb.placeToken('P',5);
        gb.placeToken('E',5);
        gb.placeToken('N',5);
        gb.placeToken('N',5);
        board[6][4] = 'O';
        board[7][4] = 'P';
        board[8][4] = 'E';
        board[0][5] = 'N';
        board[1][5] = 'O';
        board[2][5] = 'P';
        board[3][5] = 'E';
        board[4][5] = 'N';
        board[5][5] = 'P';
        board[6][5] = 'E';
        board[7][5] = 'N';
        board[8][5] = 'N';

        for(int i = 0; i <= 8; i++) {
            if(i % 2 == 0) {
                gb.placeToken('O',6);
                board[i][6] = 'O';
            }
            else{
                gb.placeToken('P',6);
                board[i][6] = 'P';
            }
        }
        String str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());
        assertTrue(gb.checkTie());
    }

    @Test
    public void testWhatsAtPos_player_two_stacked_token() {
        IGameBoard gb = makeABoard(5, 6, 0);
        BoardPosition pos = new BoardPosition(1, 3);
        char[][] board = makeEmptyBoard(5, 6);

        board[0][2] = 'O';
        board[0][3] = 'X';
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);


        for (int i = 0; i <= 1; i++) {
            board[i + 1][3] = 'O';
            board[i][1] = 'X';
            gb.placeToken('X', 1);
            gb.placeToken('O', 3);
        }

        board[0][5] = 'X';
        board[1][5] = 'O';
        board[2][5] = 'X';
        gb.placeToken('X', 5);
        gb.placeToken('O', 5);
        gb.placeToken('X', 5);

        String str = makeBoardWithTokens(board);

        assertEquals(gb.whatsAtPos(pos), 'O');
        assertEquals(str, gb.toString());
    }

    @Test
    public void testWhatsAtPos_player_two_solo_column_token() {
        IGameBoard gb = makeABoard(5, 5, 0);
        BoardPosition pos = new BoardPosition(0, 0);
        char[][] board = makeEmptyBoard(5, 5);

        board[0][0] = 'O';
        board[0][2] = 'X';
        board[0][3] = 'O';
        board[1][3] = 'X';
        board[2][3] = 'O';
        board[0][4] = 'X';
        gb.placeToken('O', 0);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);

        String str = makeBoardWithTokens(board);

        assertEquals(gb.whatsAtPos(pos), 'O');
        assertEquals(str, gb.toString());
    }

    @Test
    public void testWhatsAtPos_player_one_between_tokens() {
        IGameBoard gb = makeABoard(6, 7, 0);
        BoardPosition pos = new BoardPosition(2, 4);
        char[][] board = makeEmptyBoard(6, 7);

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 5);
        gb.placeToken('O', 5);
        gb.placeToken('X', 6);
        board[0][1] = 'X';
        board[0][2] = 'O';
        board[1][2] = 'X';
        board[0][3] = 'O';
        board[1][3] = 'X';
        board[2][3] = 'X';
        board[0][4] = 'O';
        board[1][4] = 'O';
        board[2][4] = 'X';
        board[3][4] = 'O';
        board[0][5] = 'X';
        board[1][5] = 'O';
        board[0][6] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(gb.whatsAtPos(pos), 'X');
        assertEquals(str, gb.toString());
    }

    @Test
    public void testWhatsAtPos_empty_board() {
        IGameBoard gb = makeABoard(5, 4, 0);
        BoardPosition pos = new BoardPosition(3, 2);
        char[][] board = makeEmptyBoard(5, 4);

        String str = makeBoardWithTokens(board);

        assertEquals(gb.whatsAtPos(pos), ' ');
        assertEquals(str, gb.toString());
    }

    @Test
    public void testWhatsAtPos_space_above_tokens() {
        IGameBoard gb = makeABoard(6, 7, 0);
        BoardPosition pos = new BoardPosition(4, 1);
        char[][] board = makeEmptyBoard(6, 7);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        board[0][0] = 'X';
        board[1][0] = 'O';
        board[2][0] = 'X';

        for (int i = 0; i <= 1; i++) {
            gb.placeToken('X', 1);
            gb.placeToken('O', 4);
            board[i][1] = 'X';
            board[i][4] = 'O';

        }

        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 5);
        board[2][1] = 'O';
        board[3][1] = 'X';
        board[0][2] = 'O';
        board[0][3] = 'O';
        board[1][3] = 'X';
        board[0][5] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(gb.whatsAtPos(pos), ' ');
        assertEquals(str, gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_player_one_found_above_token() {
        IGameBoard gb = makeABoard(6, 8, 0);
        BoardPosition pos = new BoardPosition(0, 1);
        char[][] board = makeEmptyBoard(6, 8);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[1][0] = 'O';
        board[0][2] = 'X';
        board[1][2] = 'X';
        board[0][3] = 'O';
        board[1][3] = 'X';
        board[2][3] = 'O';


        for (int i = 5; i <= 7; i++) {
            gb.placeToken('X', i);
            gb.placeToken('O', i);
            board[0][i] = 'X';
            board[1][i] = 'O';
        }

        String str = makeBoardWithTokens(board);

        assertTrue(!gb.isPlayerAtPos(pos, 'X'));
        assertEquals(str, gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_player_one_not_found() {
        IGameBoard gb = makeABoard(6, 6, 0);
        BoardPosition pos = new BoardPosition(0, 1);
        char[][] board = makeEmptyBoard(6, 6);

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('O', 5);
        gb.placeToken('X', 5);

        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'X';
        board[1][2] = 'O';
        board[0][3] = 'O';
        board[1][3] = 'X';
        board[2][3] = 'X';
        board[0][4] = 'X';
        board[1][4] = 'O';
        board[0][5] = 'O';
        board[1][5] = 'X';


        String str = makeBoardWithTokens(board);

        assertTrue(!gb.isPlayerAtPos(pos, 'X'));
        assertEquals(str, gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_player_two_incorrect_token_found() {
        IGameBoard gb = makeABoard(4, 6, 0);
        BoardPosition pos = new BoardPosition(2, 2);
        char[][] board = makeEmptyBoard(4, 6);

        for (int i = 0; i <= 2; i++) {
            gb.placeToken('X', i);
            board[0][i] = 'X';
        }
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        board[1][0] = 'X';
        board[2][0] = 'O';
        board[3][0] = 'X';
        board[1][1] = 'O';
        board[2][1] = 'O';
        board[1][2] = 'O';
        board[2][2] = 'X';
        board[0][3] = 'O';

        String str = makeBoardWithTokens(board);

        assertTrue(!gb.isPlayerAtPos(pos, 'O'));
        assertEquals(str, gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_player_three_not_found() {
        IGameBoard gb = makeABoard(6, 5, 0);
        BoardPosition pos = new BoardPosition(0, 4);
        char[][] board = makeEmptyBoard(6, 5);

        gb.placeToken('X', 1);
        gb.placeToken('Y', 4);
        board[0][1] = 'X';
        board[0][4] = 'Y';
        String str = makeBoardWithTokens(board);

        assertTrue(!gb.isPlayerAtPos(pos, 'Z'));
        assertEquals(str, gb.toString());
    }

    @Test
    public void testIsPlayerAtPos_player_one_found_full_board() {
        IGameBoard gb = makeABoard(9, 7, 0);
        BoardPosition pos = new BoardPosition(7, 4);
        char[][] board = makeEmptyBoard(9, 7);
        int[] row = new int[3];

        for (int i = 0; i <= 2; i++) {
            gb.placeToken('Z', 0);
            board[row[0]++][0] = 'Z';
            gb.placeToken('X', 0);
            board[row[0]++][0] = 'X';
            gb.placeToken('Y', 0);
            board[row[0]++][0] = 'Y';
        }

        gb.placeToken('Y', 1);
        gb.placeToken('Z', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 1);
        gb.placeToken('Z', 1);
        board[0][1] = 'Y';
        board[1][1] = 'Z';
        board[2][1] = 'X';
        board[3][1] = 'X';
        board[4][1] = 'Y';
        board[5][1] = 'X';
        board[6][1] = 'X';
        board[7][1] = 'Y';
        board[8][1] = 'Z';

        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Z', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Y', 2);
        gb.placeToken('Y', 2);
        board[0][2] = 'X';
        board[1][2] = 'X';
        board[2][2] = 'Z';
        board[3][2] = 'X';
        board[4][2] = 'X';
        board[5][2] = 'Y';
        board[6][2] = 'Y';

        gb.placeToken('Z', 3);
        gb.placeToken('Z', 3);
        gb.placeToken('Y', 3);
        gb.placeToken('Y', 3);
        gb.placeToken('Z', 3);
        gb.placeToken('X', 3);
        gb.placeToken('Z', 3);
        board[0][3] = 'Z';
        board[1][3] = 'Z';
        board[2][3] = 'Y';
        board[3][3] = 'Y';
        board[4][3] = 'Z';
        board[5][3] = 'X';
        board[6][3] = 'Z';

        gb.placeToken('X', 4);
        gb.placeToken('Z', 4);
        gb.placeToken('Y', 4);
        gb.placeToken('Y', 4);
        gb.placeToken('Z', 4);
        gb.placeToken('Y', 4);
        gb.placeToken('Z', 4);
        board[0][4] = 'X';
        board[1][4] = 'Z';
        board[2][4] = 'Y';
        board[3][4] = 'Y';
        board[4][4] = 'Z';
        board[5][4] = 'Y';
        board[6][4] = 'Z';

        for (int i = 7; i < 8; i++) {
            gb.placeToken('Y', 2);
            gb.placeToken('Z', 3);
            gb.placeToken('X', 4);
            board[i][2] = 'Y';
            board[i][3] = 'Z';
            board[i][4] = 'X';
        }

        for (int i = 5; i <= 6; i++) {
            gb.placeToken('Y', i);
            gb.placeToken('X', i);
            gb.placeToken('Z', i);
            gb.placeToken('X', i);
            gb.placeToken('Z', i);
            gb.placeToken('Y', i);
            gb.placeToken('Y', i);
            gb.placeToken('X', i);
            gb.placeToken('Z', i);
            board[0][i] = 'Y';
            board[1][i] = 'X';
            board[2][i] = 'Z';
            board[3][i] = 'X';
            board[4][i] = 'Z';
            board[5][i] = 'Y';
            board[6][i] = 'Y';
            board[7][i] = 'X';
            board[8][i] = 'Z';
        }

        gb.placeToken('Y', 2);
        gb.placeToken('Z', 3);
        gb.placeToken('X', 4);
        board[8][2] = 'Y';
        board[8][3] = 'Z';
        board[8][4] = 'X';

        String str = makeBoardWithTokens(board);

        assertTrue(gb.isPlayerAtPos(pos, 'X'));
        assertEquals(str, gb.toString());
    }

    @Test
    public void testPlaceToken_empty_board() {
        IGameBoard gb = makeABoard(5, 7, 0);
        char[][] board = makeEmptyBoard(5, 7);
        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());

        gb.placeToken('X', 4);
        board[0][4] = 'X';

        str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
    }

    @Test
    public void testPlaceToken_full_board(){
        IGameBoard gb = makeABoard(6, 5, 0);
        char[][] board = makeEmptyBoard(6, 5);

        for(int i = 0; i <= 5; i++) {
            if(i % 2 == 0) {
                gb.placeToken('X',0);
                board[i][0] = 'X';
            }
            else {
                gb.placeToken('O',0);
                board[i][0] = 'O';
            }
        }

        for(int i = 0; i <= 2; i++) {
            gb.placeToken('X',1);
            board[i][1] = 'X';
        }

        for(int i = 3; i <= 5; i++) {
            gb.placeToken('O',1);
            board[i][1] = 'O';
        }

        for(int i = 0; i <= 2; i++) {
            gb.placeToken('O',2);
            board[i][2] = 'O';
        }

        for(int i = 3; i <= 5; i++) {
            gb.placeToken('X',2);
            board[i][2] = 'X';
        }

        gb.placeToken('X',3);
        gb.placeToken('X',3);
        gb.placeToken('O',3);
        gb.placeToken('X',3);
        gb.placeToken('O',3);
        gb.placeToken('X',3);
        gb.placeToken('X',4);
        gb.placeToken('O',4);
        gb.placeToken('O',4);
        gb.placeToken('X',4);
        gb.placeToken('O',4);
        board[0][3] = 'X';
        board[1][3] = 'X';
        board[2][3] = 'O';
        board[3][3] = 'X';
        board[4][3] = 'O';
        board[5][3] = 'X';
        board[0][4] = 'X';
        board[1][4] = 'O';
        board[2][4] = 'O';
        board[3][4] = 'X';
        board[4][4] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());

        gb.placeToken('X',4);
        board[5][4] = 'X';

        str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());
    }

    @Test
    public void testPlaceToken_three_players_col_not_empty(){
        IGameBoard gb = makeABoard(8, 8, 0);
        char[][] board = makeEmptyBoard(8, 8);

        gb.placeToken('Z',1);
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('Z',4);
        gb.placeToken('Y',5);
        gb.placeToken('X',6);
        gb.placeToken('Z',7);
        gb.placeToken('Z',1);
        gb.placeToken('Y',2);
        gb.placeToken('Y',3);
        gb.placeToken('X',4);
        gb.placeToken('X',5);
        gb.placeToken('Y',6);
        gb.placeToken('Y',7);
        gb.placeToken('Z',3);
        gb.placeToken('Y',5);
        gb.placeToken('X',6);
        gb.placeToken('Y',7);
        gb.placeToken('Z',5);
        gb.placeToken('X',7);
        board[0][1] = 'Z';
        board[0][2] = 'X';
        board[0][3] = 'X';
        board[0][4] = 'Z';
        board[0][5] = 'Y';
        board[0][6] = 'X';
        board[0][7] = 'Z';
        board[1][1] = 'Z';
        board[1][2] = 'Y';
        board[1][3] = 'Y';
        board[1][4] = 'X';
        board[1][5] = 'X';
        board[1][6] = 'Y';
        board[1][7] = 'Y';
        board[2][3] = 'Z';
        board[2][5] = 'Y';
        board[2][6] = 'X';
        board[2][7] = 'Y';
        board[3][5] = 'Z';
        board[3][7] = 'X';

        String str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());

        gb.placeToken('Z',6);
        board[3][6] = 'Z';

        str = makeBoardWithTokens(board);

        assertEquals(str,gb.toString());
    }

    @Test
    public void testPlaceToken_three_players_col_empty() {
        IGameBoard gb = makeABoard(8, 5, 0);
        char[][] board = makeEmptyBoard(8, 5);

        gb.placeToken('X', 0);
        gb.placeToken('Y', 0);
        gb.placeToken('Z', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 4);
        gb.placeToken('Y', 4);
        gb.placeToken('Z', 4);
        board[0][0] = 'X';
        board[1][0] = 'Y';
        board[0][3] = 'Z';
        board[1][3] = 'X';
        board[0][4] = 'X';
        board[1][4] = 'Y';
        board[2][4] = 'Z';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());

        gb.placeToken('Y', 2);
        board[0][2] = 'Y';

        str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
    }

    @Test
    public void testPlaceToken_col_full() {
        IGameBoard gb = makeABoard(6, 7, 0);
        char[][] board = makeEmptyBoard(6, 7);

        gb.placeToken('X',0);
        gb.placeToken('O',0);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',2);
        board[0][0] = 'X';
        board[1][0] = 'O';
        board[0][2] = 'O';
        board[1][2] = 'X';
        board[2][2] = 'X';

        for(int i = 0; i <= 2; i++) {
            gb.placeToken('X',3);
            board[i][3] = 'X';
        }

        gb.placeToken('O',3);
        gb.placeToken('O',3);
        gb.placeToken('O',5);
        gb.placeToken('O',5);
        gb.placeToken('X',5);
        gb.placeToken('O',6);
        board[3][3] = 'O';
        board[4][3] = 'O';
        board[0][5] = 'O';
        board[1][5] = 'O';
        board[2][5] = 'X';
        board[0][6] = 'O';

        String str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());

        gb.placeToken('X',3);
        board[5][3] = 'X';

        str = makeBoardWithTokens(board);

        assertEquals(str, gb.toString());
    }
}
