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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public class PreRoundSidebar {
    private static final ScoreboardManager sbm = Bukkit.getScoreboardManager();
    private static Scoreboard sb;

    public static void showSideBar(){

        Event event = Event.activeEvent;

        Scoreboard sb = sbm.getNewScoreboard();
        Objective objective = sb.registerNewObjective("waiting", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky Blocks " + ChatColor.YELLOW + "Waiting");
        Score score = objective.getScore(ChatColor.BLUE + "Participants: " + ChatColor.AQUA + event.members.size());
        score.setScore(0);
        Score score1 = objective.getScore(ChatColor.BLUE + "Arena: ");
        score1.setScore(0);
        Score score2 = objective.getScore("Waiting for players to ready up");
        score2.setScore(0);
        Score score3 = objective.getScore("Players Ready:");
        score3.setScore(0);
        Score score4 = objective.getScore("Players ready to start event: ");
    }
}
