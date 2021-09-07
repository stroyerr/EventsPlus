package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class BlockActionInArena implements Listener {
    @EventHandler
    public static void blockBreakEvent(BlockBreakEvent e){
        List<Arena> arenas = Arenas.arenas;
        for(int i = 0; i < arenas.size(); i ++){
            for(int m = 0; m < arenas.get(i).locations.size(); m ++){
                if(arenas.get(i).locations.get(m).location.equals(e.getBlock().getLocation())){
                    if(arenas.get(i).locations.get(m).breakable){
                        return;
                    }else{
                        if(e.getPlayer().hasPermission("eventsplus.bypass")){
                            return;
                        }else{
                            e.setCancelled(true);
                            Send.player(e.getPlayer(), "You do not have sufficient permissions to break blocks in event arenas!");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public static void blockPlaceEvent(BlockPlaceEvent e){
        List<Arena> arenas = Arenas.arenas;
        for(int i = 0; i < arenas.size(); i ++){
            for(int m = 0; m < arenas.get(i).locations.size(); m ++){
                if(arenas.get(i).locations.get(m).location.equals(e.getBlock().getLocation())){
                    if(arenas.get(i).locations.get(m).breakable){
                        return;
                    }else{
                        if(e.getPlayer().hasPermission("eventsplus.bypass")){
                            return;
                        }else{
                            e.setCancelled(true);
                            Send.player(e.getPlayer(), "You do not have sufficient permissions to build blocks in event arenas!");
                        }
                    }
                }
            }
        }
    }
}
