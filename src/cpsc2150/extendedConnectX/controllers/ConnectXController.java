package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * The hard coded Tokens for all 10 players playing the game
     * </p>
     */
    private char[] player = {0x0D9E , '\u263A' ,'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * <p>
     * the current player number whos turn it is. used as an element number in the player char array
     * </p>
     */
    private int currPlayer;

    /**
     * <p>
     * the first available row the token will be placed in the chosen column
     * </p>
     */
    private int row;

    /**
     * <p>
     * checks if the game is over and starts a new game if true
     * </p>
     */
    private boolean isGameOver;
    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        //initializes the number of players, model and view objects, and sets the boolean to false by default
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        this.isGameOver = false;

        //initializes current player and row to 0
        currPlayer = 0;
        row = 0;

        //registers controller as an observer of view
        view.registerObserver(this);

    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        for (int r = 0; r < curGame.getNumRows(); r++) {
            if (curGame.whatsAtPos(new BoardPosition(r, col)) == ' ') {
                row = r;
                break;
            }
        }
        //starts a new game on next click if the game ends
        if(isGameOver) {
            newGame();
        }
        else {
            //checks if the selected column picked is full
            if (!curGame.checkIfFree(col)) {
                screen.setMessage("Column is full");
                return;
            }
            else {
                //places token on board and updates the game state
                screen.setMarker(row, col, player[currPlayer]);
                curGame.placeToken(player[currPlayer], col);
            }
            //check if player has won the game, displays the player that won and sets that next click by user creates a new game
            if (curGame.checkForWin(col)) {
                screen.setMessage("Player " + player[currPlayer] + " Wins! Press any button to play again.");
                isGameOver = true;
                return;
            }
            //checks if game is a tie, displays a tie and sets that next click by user creates a new game
            if (curGame.checkTie()) {
                screen.setMessage("Tie! Press any button to play again.");
                isGameOver = true;
                return;
            }
            //increments to next player and returns back to first player if all players have taken their turn
            if (currPlayer < numPlayers - 1) {
                currPlayer++;
            } else {
                currPlayer = 0;
            }
            //displays which player's turn it is
            screen.setMessage("It is " + player[currPlayer] + "\'s turn.");
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}