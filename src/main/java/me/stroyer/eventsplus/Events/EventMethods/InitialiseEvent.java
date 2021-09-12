package me.stroyer.eventsplus.Events.EventMethods;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerScore.PlayerScore;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockLocations;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Scoreboard.EventSideBar;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.StaffController;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Scoreboard.RankingScoreboard;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InitialiseEvent {
    public static void start(Arena arena, Player host, String type){
        Event event = new Event(arena, host, type);
        arena.active = true;
        Event.activeEvent = event;
        Event.activeEvent.rankingScoreboard = new RankingScoreboard();
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            PlayerLostItem.lostPlayerItemObjects.add(new PlayerLostItem(Event.activeEvent.members.get(i)));
        }

        if(event.type.equals("lucky_blocks")){


            EventSideBar sideBar = new EventSideBar(event);
            event.activeEventBlocks = new ArrayList<Block>();
            Event.activeEvent = event;
            TpAllToEvent.tp(event);
            if(LuckyBlockLocations.generateLuckyBlockLocationsForArena(arena).size() < 10 || LuckyBlockLocations.getSpawnLocation(arena) == null){
                Send.player(host, ChatColor.RED + "This arena needs " + (10 - LuckyBlockLocations.generateLuckyBlockLocationsForArena(arena).size()) + " more block spawn locations. Create a block spawn location by placing a GOLD BLOCK inside the arena and or you failed to set a spawn location in the arena with a SCAFFOLD block.");
                LuckyBlockEvent.endRound();
                CloseEvent.close(event);
                return;
            }

            PlayersVisibility.giveAllToggleItem();

            List<String> notification = new ArrayList<String>();
            notification.add("Each round, you will be assigned a random block to break.");
            notification.add("Breaking this block in the time limit before another player gives you a point. The sooner you break your block, the more points you are rewarded.");
            notification.add("If you fail to break the block within the time limit, you will not get a point");
            notification.add("At the end of each round, the player with the most points that round recieves a random reward.");
            notification.add("At the end of all rounds, the top 3 players recieve special rewards!");
            notification.add(ChatColor.GRAY + "Brought to you by Stroyer_ for LonelyMC");

            Send.allMultipleLines(notification, ChatColor.BOLD + "LuckyBlocks" + ChatColor.WHITE + "    Participants: " + event.members.size());
            PlayerScore.generatePlayerScoreList();
            StaffController.giveStaff();
            LuckyBlockEvent.initialise(event);

        }
    }
}
