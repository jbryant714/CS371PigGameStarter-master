package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState gameState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        gameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == gameState.getPlayerId()){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        int numPlayers = this.playerNames.length;
        if(action instanceof  PigHoldAction){
            int id = gameState.getPlayerId();
            int currentTotal = gameState.getRunningTotal();
            if(id == 0){
                int score = gameState.getPlayer0Score();
                score = score + currentTotal;
                gameState.setPlayer0Score(score);
                if(numPlayers > 1){
                    gameState.setPlayerId(1);
                }
                gameState.setRunningTotal(0);
                return true;
            }
            if(id == 1){
                int score = gameState.getPlayer1Score();
                score = score + currentTotal;
                gameState.setPlayer1Score(score);
                gameState.setPlayerId(0);
                gameState.setRunningTotal(0);
                return true;
            }
        }

        if(action instanceof PigRollAction){
            int dieValue = (int) (Math.random()*6) + 1;
            gameState.setCurrentValue(dieValue);
            if(dieValue == 1){
                gameState.setRunningTotal(0);
                if((gameState.getPlayerId() == 0) && (numPlayers > 1)){
                    gameState.setPlayerId(1);
                }
                else if(gameState.getPlayerId() == 1){
                    gameState.setPlayerId(0);
                }
                return true;
            }

            else{
                gameState.setRunningTotal(gameState.getRunningTotal() + dieValue);
                return true;
            }
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState currentState = new PigGameState(gameState);
        p.sendInfo(currentState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(gameState.getPlayer0Score() >= 50){
            String name = this.playerNames[0];
            int score = gameState.getPlayer0Score();
            String message = name + " won with a score of " + score;
            return message;
        }
        if(gameState.getPlayer1Score() >= 50){
            String name = this.playerNames[1];
            int score = gameState.getPlayer1Score();
            String message = name + " won with a score of " + score;
            return message;
        }
        return null;
    }

}// class PigLocalGame
