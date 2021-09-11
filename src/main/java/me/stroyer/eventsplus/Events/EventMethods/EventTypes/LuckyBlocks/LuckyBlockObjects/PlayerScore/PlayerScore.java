/*
 * This project is licensed under the MIT license.
 *
 * Copyright (c)  2021 Stroyer.  All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerScore;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerScore {

    /*
    This class is an object that tracks player scores and contains a list of all active scores.
    Objects should only be generated at the start of the event and removed after the event. Between
    these times (during rounds), the existing scores objects for each player should only be modified,
    not created or destroyed.
     */

    public Player player;
    public int score;

    public static List<PlayerScore> playerScores = new ArrayList<PlayerScore>();

    public PlayerScore(Player player){
        this.player = player;
        this.score = 0;
    }

    public void givePoints(int points){
        this.score += points;
    }

    public void removePoints(int points){
        this.score -= points;
    }

    public static void generatePlayerScoreList(){
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            playerScores.add(new PlayerScore(Event.activeEvent.members.get(i)));
        }
    }

    public static void clearPlayerScoreList(){
        playerScores.clear();
        playerScores = new ArrayList<PlayerScore>();
    }

    //This next integer keeps track of how many players have finished a round to give an idea of
    //ranking. This should be cleared at the start of every round and set to 0.

    public static int playersFinished = 0;

    //The following events are to be called during every round

    public static void playerFinished(){
        playersFinished ++;
    }

    public static void resetPlayerFinishedCount(){
        playersFinished = 0;
    }

    public static int getPlayersFinished(){
        return playersFinished;
    }

}
