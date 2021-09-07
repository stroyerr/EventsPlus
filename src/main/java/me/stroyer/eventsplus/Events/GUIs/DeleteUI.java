package me.stroyer.eventsplus.Events.GUIs;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.UI.ArenaSelectionItem;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DeleteUI {
    public static Inventory inv;
    public static List<ArenaSelectionItem> arenaItems = new ArrayList<ArenaSelectionItem>();
    public static ItemStack close = NewItem.createGuiItem(Material.BARRIER, ChatColor.RED + "Close");

    public static void open(Player p){
        inv = Bukkit.createInventory(null, 36, ChatColor.DARK_RED + "Delete an Arena");
        for(int i = 0; i < Arenas.arenas.size(); i ++){
            arenaItems.add(new ArenaSelectionItem(Arenas.arenas.get(i)));
            inv.setItem(i, arenaItems.get(i).guiItem);
        }

        inv.setItem(35, close);


        inv = FillBlank.updateInventory(inv);
        p.openInventory(inv);
    }

    public static void InventoryEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(close)){
            return;
        }
        Bukkit.getLogger().info("1");
        for(int i = 0; i < arenaItems.size(); i++){
            Bukkit.getLogger().info("2");
            if(arenaItems.get(i).guiItem.equals(e.getCurrentItem())){
                Bukkit.getLogger().info("3");
                Arena a = arenaItems.get(i).arena;
                Arenas.arenas.remove(a);
                open((Player) e.getWhoClicked());
                break;
            }
        }
    }
}
