package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.graalvm.compiler.lir.alloc.lsra.LinearScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildBlocks {

    public static List<Location> eventBlocks =  new ArrayList<Location>();

    public static void build(Event event, ItemStack itemStack){
        Arena a = event.arena;
        BlockData bd = Bukkit.createBlockData(itemStack.getType());
        for(int i = 0; i < event.members.size() + 5; i++){
            Random random = new Random();
            int j = random.nextInt(event.arena.locations.size());
            Location l = event.arena.locations.get(j).location;
            Location safeLocation = l;
            safeLocation.setY(l.getWorld().getHighestBlockYAt(l) + 1);
            safeLocation.getWorld().setBlockData(safeLocation, bd);
            eventBlocks.add(safeLocation);
        }
    }
}
