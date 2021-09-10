package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.EventPlayer;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.Voting.VoteRound;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.SetMovementSpeed;
import me.stroyer.eventsplus.Methods.PlaySound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;

import static org.bukkit.Sound.BLOCK_AMETHYST_BLOCK_CHIME;

public class LuckyBlockEvent {
    public static Event e;
    public static boolean isWaiting = true;
    public static void initialise(Event event){
        Event.activeEvent = event;
        LuckyBlockLocations.generateLuckyBlockLocations();
        LuckyBlockLocations.destroyerPlaceholderBlocks();
        e = event;
        Send.allPlayer("Type " + ChatColor.GREEN + "ready" + ChatColor.GOLD +" to ready up. Waiting for " + remaining() + " more players to ready up.");
    }

    public static double amountReady;

    public static Boolean isReady(){
        amountReady = 0;
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
                amountReady = 0;
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
        int newRoundNumber = Event.activeEvent.round;
        RoundItem.open(Event.activeEvent.round, Event.activeEvent);
    }

    public static void endRound(){
        RoundTimer.cancelTimer();
        VoteRound.cancelVoteTimer();
        BlockData air = Bukkit.createBlockData(Material.AIR);
        SetMovementSpeed.walkSpeed(0.2f);
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).setGameMode(GameMode.SURVIVAL);
            if(Event.activeEvent.activeItemStack == null){}else{
                Event.activeEvent.members.get(i).getInventory().removeItem(Event.activeEvent.activeItemStack);
            }

        }
        e = Event.activeEvent;
        if(e.activeEventBlocks == null){

        }else{
            for(int i = 0; i < e.activeEventBlocks.size(); i ++){
                e.activeEventBlocks.get(i).getWorld().setBlockData(e.activeEventBlocks.get(i).getLocation(), air);
                RoundActive.clearRanks();
            }
            e.activeEventBlocks.clear();

        }

        if(e.eventPlayers != null && e.eventPlayers.size() > 0){
            for(int i = 0 ; i < Event.activeEvent.eventPlayers.size(); i ++){
                Event.activeEvent.eventPlayers.get(i).isReady = false;
            }
        }

    }

    public static void preRoundCountdown(){
        int round = e.round;

    }
}
