package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.RoundActive;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArenaActiveBlockInteraction implements Listener {
    @EventHandler
    public static void playerInteractBlock(PlayerInteractEvent e){
        if(Event.activeEvent == null){
            return;
        }

        if(!e.getPlayer().getWorld().equals(Event.activeEvent.arena.locations.get(0).location.getWorld())){return;}

        if(Event.activeEvent.inRound){
            if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                if(!Event.activeEvent.activeEventBlocks.contains(e.getClickedBlock())){
                    return;
                }

                if(e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)){
                    return;
                }
                e.setCancelled(true);
                e.getClickedBlock().setType(Material.AIR);
                RoundActive.playerBreaksEventBlock(e.getPlayer());
                return;
            }
        }
    }
}
