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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Podium;

import me.stroyer.eventsplus.Events.EventMethods.CloseEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.RoundTimer;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.Voting.VoteRound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PodiumRoundHandler {
    public static void initialise(){
        startRoundTimer();
        VoteRound.cancelVoteTimer();
        RoundTimer.cancelTimer();
        Podium.podium.keyBlocks.destroyPlaceholders();
        Podium.podium.keyBlocks.tpAllToPodium();
        Podium.podium.keyBlocks.tpTopPlayers();
    }

    private static BukkitRunnable backgroundTimer = new BukkitRunnable() {
        int i = 0;
        @Override
        public void run() {
            if(i < 45){
                timerUpdate(i);
            }else{
                runnableFinished();
                this.cancel();
            }
            i++;
        }
    };

    public static void runnableFinished(){
        stopRoundTimer();

        BukkitRunnable endPodium = new BukkitRunnable() {
            @Override
            public void run() {
                end();
            }
        };
        endPodium.runTask(Bukkit.getPluginManager().getPlugin("EventsPlus"));
    }

    public static void timerUpdate(int timeElapsed){

        sendTimeRemaining(timeElapsed);

        if(timeElapsed == 5){
            reward(3);
        }

        if(timeElapsed == 10){
            reward(2);
        }

        if(timeElapsed == 15){
            reward(1);
        }
    }

    public static void sendTimeRemaining(int timeElpased){
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + "" + ChatColor.BOLD + "Event ending in " + (45-timeElpased) + " seconds."));
        }
    }

    public static ItemStack[] tier1 = {
            new ItemStack(Material.DIAMOND, 8),
            new ItemStack(Material.OBSIDIAN, 16),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 2),
            new ItemStack(Material.QUARTZ, 32),
            new ItemStack(Material.IRON_BARS, 16)
    };

    public static ItemStack[] tier2 = {
            new ItemStack(Material.DIAMOND, 12),
            new ItemStack(Material.GOLD_BLOCK, 2),
            new ItemStack(Material.IRON_BLOCK, 8),
            new ItemStack(Material.GOLDEN_APPLE, 32)
    };

    public static ItemStack[] tier3 = {
            new ItemStack(Material.DIAMOND_BLOCK, 4),
            new ItemStack(Material.NETHERITE_INGOT, 4),
            new ItemStack(Material.BEACON),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 16)
    };

    public static void reward(int playerRank){

        Player first = Bukkit.getPlayer(Event.activeEvent.rankingScoreboard.firstScoreName);
        Player second = Bukkit.getPlayer(Event.activeEvent.rankingScoreboard.secondScoreName);
        Player third = Bukkit.getPlayer(Event.activeEvent.rankingScoreboard.thirdScoreName);

        Random random = new Random();

        int teir1random = random.nextInt(tier1.length);
        int teir2random = random.nextInt(tier2.length);
        int teir3random = random.nextInt(tier3.length);

        ItemStack teir1got = tier1[teir1random];
        ItemStack teir2got = tier2[teir2random];
        ItemStack teir3got = tier3[teir3random];

        if(playerRank == 1){

            first.getInventory().addItem(teir1got);
            first.getInventory().addItem(teir2got);
            first.getInventory().addItem(teir3got);
            Send.allPlayer(ChatColor.LIGHT_PURPLE + first.getName() + " got " + ChatColor.YELLOW + teir1got.getType().name() + ", " + teir2got.getType().name() + ", " + teir3got.getType().name());

        }

        if(playerRank == 2){

            second.getInventory().addItem(teir1got);
            second.getInventory().addItem(teir2got);
            Send.allPlayer(ChatColor.LIGHT_PURPLE + second.getName() + " got " + ChatColor.YELLOW + teir1got.getType().name() + ", " + teir2got.getType().name());

        }

        if(playerRank == 3){

            third.getInventory().addItem(teir1got);
            Send.allPlayer(ChatColor.LIGHT_PURPLE + third.getName() + " got " + ChatColor.YELLOW + teir3got.getType().name());

        }
    }

    public static void startRoundTimer(){
        backgroundTimer.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 20L);
    }

    public static void stopRoundTimer(){
        if(backgroundTimer != null){
            backgroundTimer.cancel();
        }
    }

    public static void end(){
        stopRoundTimer();
        Podium.podium.keyBlocks.rebuildPlaceholders();
        CloseEvent.close(Event.activeEvent);
    }
}
