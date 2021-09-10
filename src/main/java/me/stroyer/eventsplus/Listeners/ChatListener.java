package me.stroyer.eventsplus.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.stroyer.eventsplus.Events.EventMethods.CloseEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockEvent;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListener implements Listener {
    @EventHandler
    public static void commandExecuted(PlayerCommandPreprocessEvent e){
        Event event = Event.activeEvent;
        if(Event.activeEvent == null){
            return;
        }
        if(e.getPlayer().hasPermission("eventsplus.bypass") && e.getMessage().equalsIgnoreCase("/ep stop")){
            e.getPlayer().sendMessage(ChatColor.RED + "This command is not supported and is not to be used unless something went wrong. Instead, use the \"Close Event\" button in your staff controller.");
            LuckyBlockEvent.endRound();
            CloseEvent.close(Event.activeEvent);
            return;
        }
        e.setCancelled(true);
        Send.player(e.getPlayer(), ChatColor.RED + "You cannot execute commands whilst an event is active!");
    }

    @EventHandler
    public static void playerReadiesUp(AsyncPlayerChatEvent e){
        if(!LuckyBlockEvent.isWaiting){
            return;
        }
        if(Event.activeEvent == null){
            return;
        }
        if(e.getMessage().equalsIgnoreCase("ready")){
            for(int i = 0; i < Event.activeEvent.eventPlayers.size(); i++){
                if(e.getPlayer().equals(Event.activeEvent.eventPlayers.get(i).player)){
                    LuckyBlockEvent.playerReadiesUp(Event.activeEvent.eventPlayers.get(i));
                    e.setCancelled(true);
                }
            }
        }
    }
}
