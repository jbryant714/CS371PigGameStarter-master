package edu.up.cs301.pig;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by bryantja18 on 2/24/2016.
 */
public class PigGameState extends GameState {

    //Instance Variables
    private int playerId;
    private int player0Score;
    private int player1Score;
    private int runningTotal;
    private int currentValue;

    public PigGameState(){
        playerId = 0;
        player0Score = 0;
        player1Score = 0;
        runningTotal = 0;
        currentValue = 1;
    }

    public PigGameState(PigGameState state){
        playerId = state.playerId;
        player0Score = state.player0Score;
        player1Score = state.player1Score;
        runningTotal = state.runningTotal;
        currentValue = state.currentValue;
    }

    public int getPlayerId(){return playerId;}

    public int getPlayer0Score(){return player0Score;}

    public int getPlayer1Score(){return player1Score;}

    public int getRunningTotal(){return runningTotal;}

    public int getCurrentValue(){return currentValue;}

    public void setPlayerId(int newId){
        playerId = newId;
    }

    public void setPlayer0Score(int newScore){
        playerId = newScore;
    }

    public void setPlayer1Score(int newScore){
        playerId = newScore;
    }

    public void setRunningTotal(int newTotal){
        playerId = newTotal;
    }

    public void setCurrentValue(int newValue){
        playerId = newValue;
    }
}
