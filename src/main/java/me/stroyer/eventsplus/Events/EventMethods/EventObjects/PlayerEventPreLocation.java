package me.stroyer.eventsplus.Events.EventMethods.EventObjects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerEventPreLocation {

    public Player player;
    public Location location;

    public PlayerEventPreLocation(Player p, Location l){
        this.player = p;
        this.location = l;
    }

    public static List<PlayerEventPreLocation> generateList(){
        List<PlayerEventPreLocation> list = new ArrayList<PlayerEventPreLocation>();
        List<Player> online = new ArrayList<Player>(Bukkit.getOnlinePlayers());

        for(int i = 0; i < online.size(); i ++){
            PlayerEventPreLocation pepl = new PlayerEventPreLocation(online.get(i), online.get(i).getLocation());
            list.add(pepl);
        }

        return list;
    }
}
