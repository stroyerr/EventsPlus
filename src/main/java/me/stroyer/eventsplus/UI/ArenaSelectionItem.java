package me.stroyer.eventsplus.UI;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ArenaSelectionItem {

    public ItemStack guiItem;
    public Arena arena;

    public ArenaSelectionItem(Arena arena){

        this.arena = arena;
        guiItem = NewItem.createGuiItem(Material.GRASS_BLOCK, ChatColor.RED + arena.name, "");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "Arena active: " + ChatColor.YELLOW + arena.active);
        lore.add(ChatColor.GOLD + "Arena Block Volume: " + ChatColor.YELLOW + arena.locations.size());
        lore.add(ChatColor.GOLD + "Arena World: " + ChatColor.YELLOW + arena.locations.get(0).location.getWorld().getName());
        lore.add(ChatColor.GOLD + "Starting coordinates: " + ChatColor.YELLOW + Math.round(arena.locations.get(0).location.getX()) + ", " + Math.round(arena.locations.get(0).location.getY()) + ", " + Math.round(arena.locations.get(0).location.getZ()));

        ItemMeta im =  guiItem.getItemMeta();
        im.setLore(lore);
        guiItem.setItemMeta(im);

    }
}
