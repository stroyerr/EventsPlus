package me.stroyer.eventsplus.UI.Methods;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SetNextSlot {
    public static void set(Inventory inv, ItemStack item, int searchSlots, int backupSlot){
        for(int i = 0; i < searchSlots; i ++){
            if(inv.getItem(i) != null){
                inv.setItem(i, item);
                return;
            }
        }

        inv.setItem(backupSlot, item);


    }
}
