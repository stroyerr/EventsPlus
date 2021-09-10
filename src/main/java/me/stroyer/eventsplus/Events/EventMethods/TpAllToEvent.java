package me.stroyer.eventsplus.Events.EventMethods;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockLocations;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class TpAllToEvent {
    public static void tp(Event event){

        event = Event.activeEvent;

        if(LuckyBlockLocations.getSpawnLocation(event.arena) != null){
            for(int i = 0; i < event.members.size(); i++){
                event.members.get(i).teleport(LuckyBlockLocations.getSpawnLocation(event.arena));
            }
            return;
        }

        for(int i = 0; i < event.members.size(); i++){
            Bukkit.getLogger().info("attempting tp");
            if(event.members != null && LuckyBlockLocations.getSpawnLocation(event.arena) != null){
                event.members.get(i).teleport(LuckyBlockLocations.getSpawnLocation(event.arena));
            }
        }
    }
}
