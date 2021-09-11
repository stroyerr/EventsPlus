package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerHealthHunger  implements Listener {
    @EventHandler
    public static void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            if(Event.activeEvent == null){
                return;
            }
            if(Event.activeEvent.members == null){
                return;
            }

            if(Event.activeEvent.members.size() == 0){
                return;
            }
            if(Event.activeEvent.members.contains((Player) e.getEntity())){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void onHunger(FoodLevelChangeEvent e){
        if(Event.activeEvent == null){
            return;
        }
        if(Event.activeEvent.members.size() == 0){
            return;
        }
        if(Event.activeEvent.members.contains((Player) e.getEntity())){
            e.setCancelled(true);
        }
    }
}
