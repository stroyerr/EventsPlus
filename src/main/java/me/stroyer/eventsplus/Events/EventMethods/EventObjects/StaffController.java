package me.stroyer.eventsplus.Events.EventMethods.EventObjects;

import me.stroyer.eventsplus.Events.EventMethods.CloseEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Mailbox;
import me.stroyer.eventsplus.Methods.StaffOnline;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
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
        ItemStack eventDetails = NewItem.createGuiItem(Material.PAPER, ChatColor.WHITE + "" + ChatColor.BOLD, "Event details", "");


        ItemMeta im = eventDetails.getItemMeta();
        String[] loreString = {
                ChatColor.AQUA + "Event type: " + ChatColor.DARK_AQUA + event.type,
                ChatColor.AQUA + "Event host: " + ChatColor.DARK_AQUA + event.host.getName(),
                ChatColor.AQUA + "Event staff count: " + ChatColor.DARK_AQUA + event.staff.size(),
                ChatColor.AQUA + "Event player count: " + ChatColor.DARK_AQUA + event.members.size(),
                ChatColor.AQUA + "Event Arena: " + ChatColor.DARK_AQUA + event.arena.name,
                ChatColor.AQUA + "Event Arena Volume: " + ChatColor.DARK_AQUA + event.arena.locations.size(),
                ChatColor.AQUA + "Player prior locations saved: " + ChatColor.DARK_AQUA + event.originalLocations.size()
        };
        List<String> lore = Arrays.asList(loreString);
        im.setLore(lore);
        eventDetails.setItemMeta(im);


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
            Player p = (Player) e.getWhoClicked();

        }

        if(e.getWhoClicked().equals(removeMember)){

        }

        if(e.getCurrentItem().equals(end)){
            Player p = (Player) e.getWhoClicked();
            if(p.equals(event.host)){
                Send.player(p, "Attempting to close event.");
                CloseEvent.close(event);
            }else{
                Send.player(p, ChatColor.RED + "You are not the host of this event.");
            }
        }
    }

    public static ItemStack getStaffItem(){
        staffController = NewItem.createGuiItem(Material.COMPASS, ChatColor.RED + "Event Controller");
        return staffController;
    }

    public static void giveStaff(){
        List<Player> staff = StaffOnline.get();
        for(int i = 0; i < staff.size(); i ++){
            if(staff.get(i).getInventory().getItem(0) != null){
                Mailbox.getMailboxByPlayer(staff.get(i)).addItem(staff.get(i).getInventory().getItem(0));
            }
            staff.get(i).getInventory().setItem(0, getStaffItem());
        }
    }
}
