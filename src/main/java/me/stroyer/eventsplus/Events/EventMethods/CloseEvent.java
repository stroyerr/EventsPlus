package me.stroyer.eventsplus.Events.EventMethods;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.PlayerEventPreLocation;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.StaffController;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockEvent;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;

public class CloseEvent {
    public static void close(Event event){
        if(event.type.equalsIgnoreCase("lucky_blocks")){
            LuckyBlockEvent.endRound();
        }

        for(int i = 0; i < event.staff.size(); i++){
            event.staff.get(i).getInventory().removeItem(StaffController.getStaffItem());
        }
        Event.activeEvent = null;
        event.arena.active = false;
        List<Player> members = event.members;
        List<PlayerEventPreLocation> teleportLocations = event.originalLocations;
        event = null;

        for(int i = 0; i < members.size(); i++){
            members.get(i).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            for(int j = 0; j < teleportLocations.size(); j++){
                if(members.get(i).equals(teleportLocations.get(j).player)){
                    members.get(i).teleport(teleportLocations.get(j).location);
                    Send.player(members.get(i), ChatColor.GREEN + "You were succesfuly returned from the event. Thanks for playing!");
                }
            }
        }


    }
}
