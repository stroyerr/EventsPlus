package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.Countdown;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerPerformer;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoundActive {

    public static List<PlayerPerformer> performers = new ArrayList<PlayerPerformer>();

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
        performers.add(new PlayerPerformer(p, ranks.size()));
        p.setGameMode(GameMode.SPECTATOR);
        Send.allPlayer(p.getName() + " has gotten a lucky block! They came in position " + ranks.size());
        if(checkRoundShouldFinish()){
            roundPlayFinished();
        }
    }

    public static void clearRanks(){
        ranks.clear();
    }

    public static Boolean checkRoundShouldFinish(){
        if(ranks.size() >= Event.activeEvent.members.size()){
            Bukkit.getLogger().info("testing 1");
            return true;
        }else{
            return false;
        }
    }

    public static void roundPlayFinished(){
        TopPerformers.display(performers);
    }
}
