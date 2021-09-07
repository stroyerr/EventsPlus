package me.stroyer.eventsplus.Events.GUIs;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Events.EventMethods.InitialiseEvent;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SelectEventType {
    public static Inventory inv;
    public static ItemStack back;
    public static ItemStack luckyBlocks;

    public static Arena a;

    public static void open(Arena arena, Player p){
        a = arena;
        inv = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + "Select event type");
        back = NewItem.createGuiItem(Material.ARROW, ChatColor.WHITE + "Back");
        luckyBlocks = NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky Blocks!", "Left click to begin event.");

        inv.setItem(13, luckyBlocks);
        inv.setItem(18, back);

        inv = FillBlank.updateInventory(inv);

        p.openInventory(inv);

    }

    public static void InventoryEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            e.getWhoClicked().openInventory(StartGUI.inv);
        }

        if(e.getCurrentItem().equals(luckyBlocks)){
            e.getWhoClicked().closeInventory();
            InitialiseEvent.start(a, (Player) e.getWhoClicked(), "lucky_blocks");
        }
    }

}
