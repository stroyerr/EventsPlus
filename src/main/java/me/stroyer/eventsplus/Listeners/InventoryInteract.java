package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.StaffController;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.RoundItem;
import me.stroyer.eventsplus.Events.GUIs.DeleteUI;
import me.stroyer.eventsplus.Events.GUIs.SelectEventType;
import me.stroyer.eventsplus.Events.GUIs.StartGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

        if(e.getInventory().equals(SelectEventType.inv)){
            e.setCancelled(true);
            SelectEventType.InventoryEvent(e);
        }

        if(e.getInventory().equals(StaffController.inv)){
            e.setCancelled(true);
            StaffController.InventoryInteract(e);
        }

        if(e.getInventory().equals(RoundItem.inv)){
            e.setCancelled(true);
        }

    }

    @EventHandler
    public static void onUseItem(PlayerInteractEvent e){
        if(e.getItem() == null){return;}
        if(e.getItem().equals(StaffController.getStaffItem())){
            e.setCancelled(true);
            Player p = e.getPlayer();
            StaffController.open(p);
        }
    }

}
