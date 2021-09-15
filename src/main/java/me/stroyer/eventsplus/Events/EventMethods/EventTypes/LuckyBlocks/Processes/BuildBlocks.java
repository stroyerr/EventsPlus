package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildBlocks {


    public static void build(ItemStack itemStack){
        Event event = Event.activeEvent;
        event.activeEventBlocks = new ArrayList<Block>();
        Arena a = event.arena;
        BlockData bd = Bukkit.createBlockData(itemStack.getType());
        for(int i = 0; i < 10; i++){
            Random random = new Random();
            int j = random.nextInt(LuckyBlockLocations.getLuckyBlockLocations().size());
            Location l = LuckyBlockLocations.getLuckyBlockLocations().get(j);
            Location safeLocation = l;
            safeLocation.getWorld().setBlockData(safeLocation, bd);
            event.activeEventBlocks.add(safeLocation.getBlock());
            Event.activeEvent.activeEventBlocks = event.activeEventBlocks;
        }
    }

    public static void particles(){
        if(Event.activeEvent == null){
            return;
        }
        for(int i = 0; i < Event.activeEvent.activeEventBlocks.size(); i++){
            Location location = Event.activeEvent.activeEventBlocks.get(i).getLocation();
            location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location.getBlockX() + 0.5, location.getBlockY() + 0.5, location.getBlockZ() + 0.5, 10);
        }
    }
}
