package me.stroyer.eventsplus.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffOnline {
    public static List<Player> get(){
        List<Player> online = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        List<Player> staff = new ArrayList<Player>();
        for(int i = 0; i < online.size(); i ++){
            if(online.get(i).hasPermission("eventsplus.staff")){
                staff.add(online.get(i));
            }
        }
        return staff;
    }
}
