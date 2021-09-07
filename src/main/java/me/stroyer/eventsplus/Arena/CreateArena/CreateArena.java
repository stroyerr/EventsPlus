package me.stroyer.eventsplus.Arena.CreateArena;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Arena.LocationData;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import me.stroyer.eventsplus.UI.Methods.SetNextSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CreateArena {

    public static Location pos1 = null;
    public static Location pos2 = null;

    public static void start(Player p){
        ItemStack boundsWand = getWand();
        SetNextSlot.set(p.getInventory(), boundsWand, 9, 0);
        Send.player(p, "Left click to set the first corner, right click to set the second corner.");
    }
    public static ItemStack getWand(){
        ItemStack boundsWand = NewItem.createGuiItem(Material.DIAMOND_HOE, ChatColor.BLUE + "Arena Selection Tool");
        return boundsWand;
    }

    public static void setPos1(Player player, Block block){
        pos1 = block.getLocation();
        Send.player(player, "Set pos1 to " + pos1.getX() + ", " + pos1.getZ());
    }

    public static void setPos2(Player player, Block block){
        pos2 = block.getLocation();
        Send.player(player, "Set pos2 to " + pos2.getX() + ", " + pos2.getZ());
    }

    public static void attemptCreate(Player p, String name){
        if(pos1 != null && pos2 != null){
            List<LocationData> locations = new ArrayList<LocationData>();
            Block block1 = pos1.getBlock();
            Block block2 = pos2.getBlock();
            locations = generateArenaLocations(block1, block2, p);
            if(locations != null){
                Arenas.arenas.add(new Arena(name, locations));
                Send.player(p, ChatColor.GREEN + "Successfully created arena " + name);
            }else{
                Send.player(p, ChatColor.RED + "Internal error when creating arena (block locations returned null). If you are sure you followed the instructions, contact Stroyer_");
            }
        }else{
            Send.player(p, ChatColor.RED + "Failed. Make sure you specify the corners of your arena with /ep arena create");
        }
    }

    public static List<LocationData> generateArenaLocations(Block block1, Block block2, Player p){
        Location l1 = block1.getLocation();
        Location l2 = block2.getLocation();

        double maxX = Math.max(l1.getX(), l2.getX()) + 1;
        double minX = Math.min(l1.getX(), l2.getX()) - 1;

        double maxZ = Math.max(l1.getZ(), l2.getZ()) + 1;
        double minZ = Math.min(l1.getZ(), l2.getZ()) - 1;

        double differenceXDouble = maxX - minX;
        int differenceX = (int) differenceXDouble;

        double differenceZDouble = maxZ - minZ;
        int differenceZ = (int) differenceZDouble;

        List<LocationData> locations = new ArrayList<LocationData>();
        locations.add(new LocationData(p.getLocation(), false));

        for(int i = (int) minX; i < ((int) minX) + differenceX; i ++){
            for(int j = (int) minZ; j < (int) minZ + differenceZ; j++){
                for(int y = 0; y < 256; y++){

                    LocationData ld = new LocationData((new Location(p.getWorld(), (double) i, (double) y, (double) j)), false);

                    locations.add(ld);

                }
            }
        }

        return(locations);
    }
}
