package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerPerformer;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class TopPerformers {

    public static Inventory inv;

    public static void display(List<PlayerPerformer> performers){

        inv = Bukkit.createInventory(null, 45, "Top Performers in Round " + Event.activeEvent.round);

        if(performers.size() <=3){
            for(int i = 0; i < performers.size(); i++){
                inv.setItem((i+1)*9 + 5, PlayerPerformer.createPlayerHead(performers.get(i).player));
            }
            inv.setItem(12, NewItem.createGuiItem(Material.DIAMOND, ChatColor.AQUA + "" + ChatColor.BOLD + "First Place", performers.get(0).player.getName() + " got a lucky block first."));
        }else{
            for(int i = 0; i < 3; i++){
                performers.get(i);
            }
        }

        inv = FillBlank.updateInventory(inv);

        for(int i = 0; i < performers.size(); i++){
            performers.get(i).player.openInventory(inv);
        }
    }
}
