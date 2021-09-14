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

package me.stroyer.eventsplus.VotersEvent.Util;

import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class VoteEventAnnouncer {
    public static void startTimer(){
        timer.runTaskTimer(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 20L);
    }

    private static BukkitRunnable timer = new BukkitRunnable() {

        int timeElapsed = 0;

        @Override
        public void run() {
            timeElapsed ++;
            if(timeElapsed == 1) {
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 60){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 120){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 180){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 240){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 270){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 285){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 299){
                announceApproaching(timeElapsed);
            }

            if(timeElapsed == 300){
                finishedCountdown();
                this.cancel();
            }
        }

        private void finishedCountdown() {

        }
    };

    private static void announceApproaching(int i){
        Send.allPlayer(ChatColor.AQUA + "Voter event starting in " + ChatColor.YELLOW + (300-i) + ChatColor.AQUA + " seconds!. There are currently " + ChatColor.YELLOW + PlayersVoted.playersVotedInLastDay().size() + ChatColor.AQUA + " players registered. Only players who have voted in the past 24 hours can play this event. Check if you are registered with " + ChatColor.YELLOW + "/ep check");
    }
}
