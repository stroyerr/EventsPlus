package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Arena.CreateArena.CreateArena;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Podium.PodiumSelection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectionWand implements Listener {
    
    
    @EventHandler
    public static void onClick(PlayerInteractEvent e){

        if(!e.getPlayer().getInventory().getItemInMainHand().equals(CreateArena.getWand()) && !e.getPlayer().getInventory().getItemInMainHand().equals(PodiumSelection.selectionWand)){
            return;
        }

        Player p = e.getPlayer();

        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            e.setCancelled(true);
            if(e.getPlayer().getInventory().getItemInMainHand().equals(PodiumSelection.selectionWand)){
                PodiumSelection.playerClickEvent(e);
                return;
            }
            CreateArena.setPos1(p, e.getClickedBlock());
            return;
        }

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            e.setCancelled(true);
            if(e.getPlayer().getInventory().getItemInMainHand().equals(PodiumSelection.selectionWand)){
                PodiumSelection.playerClickEvent(e);
                return;
            }
            CreateArena.setPos2(p, e.getClickedBlock());
            return;
        }
    }

    @EventHandler
    public static void onDrop(PlayerDropItemEvent e){
        if(e.getItemDrop().getItemStack().equals(CreateArena.getWand())){
            e.getItemDrop().remove();
        }
    }
    
    
}
