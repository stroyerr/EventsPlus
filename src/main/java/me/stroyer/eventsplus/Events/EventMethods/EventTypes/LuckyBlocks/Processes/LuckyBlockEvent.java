package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.EventPlayer;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;

public class LuckyBlockEvent {
    public static Event e;
    public static boolean isWaiting = true;
    public static void initialise(Event event){
        e = event;
        Send.allPlayer("Type " + ChatColor.GREEN + "ready" + ChatColor.GOLD +" to ready up. Waiting for " + remaining() + " more players to ready up.");
    }

    public static Boolean isReady(){
        double amountReady = 0;
        double amountRequired;
        amountRequired = Math.ceil(e.eventPlayers.size() / 2);
        for(int i = 0; i < e.eventPlayers.size(); i++){
            if(e.eventPlayers.get(i).isReady){
                amountReady += 1;
            }
        }
        return(amountReady >= amountReady);
    }

    public static double remaining(){
        double amountReady = 0;
        double amountRequired;
        amountRequired = Math.ceil(e.eventPlayers.size() / 2);
        for(int i = 0; i < e.eventPlayers.size(); i++){
            if(e.eventPlayers.get(i).isReady){
                amountReady += 1;
            }
        }
        return(amountRequired - amountReady);
    }

    public static void playerReadiesUp(EventPlayer ep){
        if(!ep.isReady){
            ep.isReady = true;
            if(isReady()){
                Send.allPlayer("Starting");
            }else{
                Send.allPlayer(ep.player.getName() + " has readied up. Waiting for " + remaining() + " more players to ready up.");
            }
        }else{
            Send.player(ep.player, "You have already readied up!");
            ep.isReady = true;
        }

    }
}
