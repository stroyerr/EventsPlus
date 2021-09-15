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

package me.stroyer.eventsplus.VotersEvent.Util.Whipeout;

import me.stroyer.eventsplus.Main;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.VotersEvent.EventHandling.WipeoutEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class ConsoleVote implements CommandExecutor {

    private static Main main;
    public ConsoleVote(Main main){this.main = main;}

    private static int votesToday = 0;
    private static int minutesElapsed = 0;

    private static BukkitRunnable timer = new BukkitRunnable() {
        @Override
        public void run() {
            minutesElapsed++;
            if(minutesElapsed >= 1440){
                //24 hours
            }

            if(minutesElapsed % 1 == 0){
                Send.allPlayer("Want to be part of an awesome voters event with insane rewards? Easy! If the server reaches "+ ChatColor.YELLOW + (10-votesToday) + " more votes in the next " + ((1440-minutesElapsed)/60) + " hours," + ChatColor.GOLD +" all players who voted in the last 24 hours will get to join this awesome vote event!");
            }
        }
    };

    public static void startTimer() {
        timer.runTaskTimer(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 1200L);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){

            Send.player((Player) sender, "This command can only be called via console!");

            return true;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("force")){
                WipeoutEvent.initialise();
                return true;
            }
        }

        addVote();
        Send.allPlayer("A vote was recieved! We're getting closer to starting this awesome vote event! Only " + ChatColor.YELLOW + (10-votesToday) + ChatColor.GOLD + " more votes required in the next " + ChatColor.YELLOW + ((1440 - minutesElapsed)/60) + " hours!");
        return true;
    }



    public static void addVote(){
        votesToday ++;
        if(votesToday >= 10){
            WipeoutEvent.initialise();
            votesToday = 0;
        }
    }

    public static void save() throws IOException {
        FileOutputStream fOut = new FileOutputStream("./plugins/eventsplus/consolevote.eventsplus");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(new ConsoleVoteData(votesToday, minutesElapsed));
    }

    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/eventsplus/consolevote.eventsplus");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        ConsoleVoteData covd = (ConsoleVoteData) oIn.readObject();
        votesToday = covd.score;
        minutesElapsed = covd.minutesElapsed;
    }
}
