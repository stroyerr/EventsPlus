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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Scoreboard;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerScore.PlayerScore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingScoreboard {

    /*
    This class handles the creation, modification and presentation of the sidebar which displays
    scores when roundplay is active. The object RankingScoreboard is an instance of a scoreboard and
    should onyl be created and deleted once.
     */

    private ScoreboardManager sbm;
    private Scoreboard sb;
    private Objective objective;
    public List<PlayerScore> playerScorelist = new ArrayList<PlayerScore>();

    public RankingScoreboard(){
        this.updatePlayerList();
        sbm = Bukkit.getScoreboardManager();
        sb = sbm.getNewScoreboard();

        objective = sb.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky Blocks " + ChatColor.YELLOW + "Event");
        Score score = objective.getScore(ChatColor.BLUE + "" + ChatColor.BOLD + "Top 3");
        score.setScore(1);
        Score score1 = objective.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "First: " + ChatColor.LIGHT_PURPLE + firstScoreName + ": " + ChatColor.YELLOW + "" + firstScore);
        score1.setScore(0);
        Score score2 = objective.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Second: " + ChatColor.LIGHT_PURPLE + secondScoreName + ": " + ChatColor.YELLOW + "" + secondScore);
        score2.setScore(0);
        Score score3 = objective.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Third: " + ChatColor.LIGHT_PURPLE + thirdScoreName + ": " + ChatColor.YELLOW + "" + thirdScore);
        score3.setScore(0);
        Score roundNumber = objective.getScore(ChatColor.GOLD + "Round: " +  ChatColor.AQUA + "" + ChatColor.BOLD + Event.activeEvent.round + "/5");
        roundNumber.setScore(-1);
    }

    public void showScoreboard(){
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).setScoreboard(sb);
        }
    }

    public String firstScoreName = "";
    public String secondScoreName = "";
    public String thirdScoreName = "";
    private int firstScore = 0;
    private int secondScore = 0;
    private int thirdScore = 0;

    public void updatePlayerList(){
        firstScore = 0;
        secondScore = 0;
        thirdScore = 0;
        firstScoreName = "";
        secondScoreName = "";
        thirdScoreName = "";
        for(int i = 0; i < PlayerScore.playerScores.size(); i++){
            if(PlayerScore.playerScores.get(i).score > thirdScore){
                if(PlayerScore.playerScores.get(i).score > secondScore){
                    if(PlayerScore.playerScores.get(i).score > firstScore){
                        firstScore = PlayerScore.playerScores.get(i).score;
                        firstScoreName = PlayerScore.playerScores.get(i).player.getName();
                    }else{
                        secondScore = PlayerScore.playerScores.get(i).score;
                        secondScoreName = PlayerScore.playerScores.get(i).player.getName();
                    }
                }else{
                    firstScore = PlayerScore.playerScores.get(i).score;
                    secondScoreName = PlayerScore.playerScores.get(i).player.getName();
                }
            }
        }
    }

    public static void updateScoreboard(){

    }
}
