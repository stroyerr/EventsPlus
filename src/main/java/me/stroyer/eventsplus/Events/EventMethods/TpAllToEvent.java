package me.stroyer.eventsplus.Events.EventMethods;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.Location;

public class TpAllToEvent {
    public static void tp(Event event){
        Location l1 = event.arena.locations.get(0).location;
        double newX = l1.getX()  + 1;
        double newZ = l1.getZ()  + 1;
        double newY = l1.getWorld().getHighestBlockYAt((int) Math.round(newX), (int) Math.round(newZ));
        Location newLocation = new Location(event.arena.locations.get(0).location.getWorld(), newX, newY, newZ);
        for(int i = 0; i < event.members.size(); i++){
            event.members.get(i).teleport(newLocation);
        }
    }
}
