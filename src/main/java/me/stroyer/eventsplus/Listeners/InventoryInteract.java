package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Events.GUIs.DeleteUI;
import me.stroyer.eventsplus.Events.GUIs.StartGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryInteract implements Listener {

    @EventHandler
    public static void ItemClick(InventoryClickEvent e){
        if(e.getInventory().equals(DeleteUI.inv)){
            e.setCancelled(true);
            DeleteUI.InventoryEvent(e);
        }

        if(e.getInventory().equals(StartGUI.inv)){
            e.setCancelled(true);
            StartGUI.InventoryEvent(e);
        }

        if(e.getCurrentItem().equals(DeleteUI.close)){
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        }

    }

}
