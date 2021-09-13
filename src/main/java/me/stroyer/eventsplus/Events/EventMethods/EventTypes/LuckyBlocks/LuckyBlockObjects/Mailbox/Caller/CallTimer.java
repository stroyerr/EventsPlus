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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Caller;

import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Mailbox;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class CallTimer {


    public static BukkitRunnable twoMinuteTimer = new BukkitRunnable() {
        @Override
        public void run() {
       //     call.runTask(Bukkit.getPluginManager().getPlugin("EventsPlus"));
        }
    };

    public static void initiate(){
        BukkitRunnable br = new BukkitRunnable(){
            @Override
            public void run(){
                List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
                for(int i = 0; i < onlinePlayers.size(); i++){
                    if(!Mailbox.getMailboxByPlayer(onlinePlayers.get(i)).isEmpty()){
                        Send.player(onlinePlayers.get(i), ChatColor.RED + "You currently have items waiting in your " + ChatColor.YELLOW + "/mailbox" + ChatColor.RED + "... claim these items soon or they may be gone forever! If you fail to retrieve these items before they're gone, you may not be able to get them back.");
                    }
                }
            }
        };

        br.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 1200L);
    }

    public static void cancel(){
            twoMinuteTimer.cancel();
        }
    }
