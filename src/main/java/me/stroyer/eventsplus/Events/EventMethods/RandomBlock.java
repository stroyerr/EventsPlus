package me.stroyer.eventsplus.Events.EventMethods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RandomBlock {
    public static ItemStack get(){
        Material material = null;
        Random random = new Random();
        while(material == null)
        {
            material = material.values()[random.nextInt(material.values().length)];
            if(!(material.isBlock() || material.equals(Material.WATER) || material.equals(Material.LAVA) || material.equals(Material.BUBBLE_COLUMN)))
            {
                material = null;
            }
            if(material != null){
                if(!material.isItem()){
                    material = null;
                }
            }

        }
        ItemStack item = new ItemStack(material, 1);
        return item;
    }
}
