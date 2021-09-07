package me.stroyer.eventsplus.Events.EventMethods.EventObjects;

import me.stroyer.eventsplus.Methods.StaffOnline;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class StaffController {
    public static Inventory inv;
    public static ItemStack close;
    public static ItemStack end;
    public static ItemStack removeMember;
    public static ItemStack staffController;
    public static Event event;

    public static void open(Player p){
        inv = Bukkit.createInventory(null, 27, ChatColor.BLACK + "Control Event");

        event = Event.activeEvent;

        close = NewItem.createGuiItem(Material.BARRIER, ChatColor.RED + "Close");
        end = NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "End event");
        removeMember = NewItem.createGuiItem(Material.RED_STAINED_GLASS_PANE, ChatColor.GOLD + "Remove player");
        ItemStack eventDetails = NewItem.createGuiItem(Material.PAPER, ChatColor.WHITE + "Event details", "");

        inv.setItem(26, close);
        inv.setItem(13, eventDetails);
        inv.setItem(11, removeMember);
        inv.setItem(15, end);

        inv = FillBlank.updateInventory(inv);

        p.openInventory(inv);
    }

    public static void InventoryInteract(InventoryClickEvent e){

        if(e.getCurrentItem().equals(close)){
            e.getWhoClicked().closeInventory();
        }

        if(e.getWhoClicked().equals(removeMember)){

        }

        if(e.getWhoClicked().equals(end)){

        }
    }

    public static ItemStack getStaffItem(){
        staffController = NewItem.createGuiItem(Material.COMPASS, ChatColor.RED + "Event Controller");
        return staffController;
    }

    public static void giveStaff(){
        List<Player> staff = StaffOnline.get();
        for(int i = 0; i < staff.size(); i ++){
            staff.get(i).getInventory().setItem(0, getStaffItem());
        }
    }
}
