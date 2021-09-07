package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Arena.CreateArena.CreateArena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectionWand implements Listener {
    
    
    @EventHandler
    public static void onClick(PlayerInteractEvent e){

        if(!e.getPlayer().getInventory().getItemInMainHand().equals(CreateArena.getWand())){
            return;
        }

        Player p = e.getPlayer();

        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            e.setCancelled(true);
            CreateArena.setPos1(p, e.getClickedBlock());
        }

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            e.setCancelled(true);
            CreateArena.setPos2(p, e.getClickedBlock());
        }
    }

    @EventHandler
    public static void onDrop(PlayerDropItemEvent e){
        if(e.getItemDrop().getItemStack().equals(CreateArena.getWand())){
            e.getItemDrop().remove();
        }
    }
    
    
}
