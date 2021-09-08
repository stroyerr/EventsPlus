package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.EventPlayer;
import me.stroyer.eventsplus.Methods.PlaySound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;

import static org.bukkit.Sound.BLOCK_AMETHYST_BLOCK_CHIME;

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
                Send.player(ep.player, ChatColor.GREEN + "Thanks for readying up! Waiting for " + remaining() + " more players to ready up.");
                Send.allPlayer(ChatColor.GREEN + "Event starting!");
                PlaySound.all(BLOCK_AMETHYST_BLOCK_CHIME);
                newRound();
            }else{
                Send.allPlayer(ep.player.getName() + " has readied up. Waiting for " + remaining() + " more players to ready up.");
                PlaySound.all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
            }
        }else{
            Send.player(ep.player, "You have already readied up!");
            ep.isReady = true;
        }

    }

    public static void newRound(){
        int newRoundNumber = e.round;
        RoundItem.open(e.round, e);
    }

    public static void endRound(){
        BlockData air = Bukkit.createBlockData(Material.AIR);
        for(int i = 0; i < BuildBlocks.eventBlocks.size(); i ++){
            BuildBlocks.eventBlocks.get(i).getWorld().setBlockData(BuildBlocks.eventBlocks.get(i), air);
        }
        BuildBlocks.eventBlocks.clear();
    }
}
