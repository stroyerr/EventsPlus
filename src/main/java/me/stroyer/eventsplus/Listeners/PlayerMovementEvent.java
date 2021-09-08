package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovementEvent implements Listener {
    @EventHandler
    public static void playerMoves(PlayerMoveEvent e){
        Event event = Event.activeEvent;
        if(event != null){
            if(event.movementAllowed == false){
                e.setCancelled(true);
            }
        }
    }
}
