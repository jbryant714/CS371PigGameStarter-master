package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if( info instanceof PigGameState) {
            if(this.playerNum == ((PigGameState)info).getPlayerId()) {

                //if AI can win with the running total and its previous point accumulation
                if(((PigGameState)info).getRunningTotal()+((PigGameState)info).getPlayer1Score() >= 50)
                {
                    this.game.sendAction(new PigHoldAction(this));
                }
                //if AI is more than 12 points behind the human player with both its current and running values
                else if(((PigGameState)info).getPlayer0Score() - (((PigGameState)info).getPlayer1Score()+((PigGameState)info).getRunningTotal()) > 12)
                {
                    this.game.sendAction(new PigRollAction);
                }
                //if current running total value is greater than 12, take the value
                else if(((PigGameState)info).getRunningTotal() > 12)
                {
                 this.game.sendAction(new PigHoldAction(this));
                }
                else
                {
                    this.game.sendAction(new PigRollAction);
                }


//                double move = Math.random();
//                if (move < .5) {
//                    this.game.sendAction(new PigHoldAction(this));
//                } else {
//                    this.game.sendAction(new PigRollAction(this));
//                }
            }
        }
    }//receiveInfo

}
