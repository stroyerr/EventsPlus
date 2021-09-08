package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.Countdown;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RoundActive {

    public static ArrayList<Player> ranks = new ArrayList<Player>();

    public static void begin(){
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).setGameMode(GameMode.SURVIVAL);
        }
        Countdown.start();
    }

    public static void activate(){
        Event.activeEvent.inRound = true;
    }

    public static void playerBreaksEventBlock(Player p){
        ranks.add(p);
        p.setGameMode(GameMode.SPECTATOR);
        Send.allPlayer(p.getName() + " has gotten a lucky block! They came in position " + ranks.size());
    }

    public static void clearRanks(){
        ranks.clear();
    }
}
