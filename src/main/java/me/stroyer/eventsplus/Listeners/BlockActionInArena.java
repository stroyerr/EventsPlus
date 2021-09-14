package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Podium.Podium;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.WipeoutArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class BlockActionInArena implements Listener {
    @EventHandler
    public static void blockBreakEvent(BlockBreakEvent e){

        for(WipeoutArena arena : WipeoutArena.wipeOutArenas){
            if(arena.arenaContainsLocation(e.getBlock().getLocation())){
                if(e.getPlayer().hasPermission("eventsplus.bypass")){
                    return;
                }else{
                    e.setCancelled(true);
                    Send.player(e.getPlayer(), ChatColor.RED + "You cannot modify a wipeout arena.");
                    return;
                }
            }
        }

        if(Podium.podium.locations.contains(e.getBlock().getLocation()) && !e.getPlayer().hasPermission("eventsplus.bypass")){
            e.setCancelled(true);
            Send.player(e.getPlayer(), ChatColor.RED + "You cannot modify a podium.");
        }

        List<Arena> arenas = Arenas.arenas;
        for(int i = 0; i < arenas.size(); i ++){
            for(int m = 0; m < arenas.get(i).locations.size(); m ++){
                if(arenas.get(i).locations.get(m).location.equals(e.getBlock().getLocation())){
                    if(arenas.get(i).locations.get(m).breakable){
                        return;
                    }else{
                        if(e.getPlayer().hasPermission("eventsplus.bypass")){
                            if(Event.activeEvent != null){
                                e.setCancelled(true);
                                Send.player(e.getPlayer(), ChatColor.RED + "You cannot mofidy an arena during an event, even though you are staff.");
                            }
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

        for(WipeoutArena arena : WipeoutArena.wipeOutArenas){
            if(arena.arenaContainsLocation(e.getBlock().getLocation())){
                if(e.getPlayer().hasPermission("eventsplus.bypass")){
                    return;
                }else{
                    e.setCancelled(true);
                    Send.player(e.getPlayer(), ChatColor.RED + "You cannot modify a wipeout arena.");
                    return;
                }
            }
        }

        if(Podium.podium.locations.contains(e.getBlock().getLocation())){
            e.setCancelled(true);
            Send.player(e.getPlayer(), ChatColor.RED + "You cannot modify a podium.");
        }

        if(Event.activeEvent == null){
            return;
        }

        if(Event.activeEvent.activeEventBlocks != null){
            Bukkit.getLogger().info("1");
            if(Event.activeEvent.activeEventBlocks.contains(e.getBlock())){
                Bukkit.getLogger().info("2");
                return;
            }
        }
        List<Arena> arenas = Arenas.arenas;
        for(int i = 0; i < arenas.size(); i ++){
            for(int m = 0; m < arenas.get(i).locations.size(); m ++){
                if(arenas.get(i).locations.get(m).location.equals(e.getBlock().getLocation())){
                    if(arenas.get(i).locations.get(m).breakable){
                        return;
                    }else{
                        if(e.getPlayer().hasPermission("eventsplus.bypass")){
                            if(Event.activeEvent != null){
                                e.setCancelled(true);
                                Send.player(e.getPlayer(), ChatColor.RED + "You cannot mofidy an arena during an event, even though you are staff.");
                            }
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
