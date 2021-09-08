package me.stroyer.eventsplus.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaySound {
    public static void all(Sound sound){
        List<Player> online = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        for(int i = 0; i < online.size(); i ++){
            online.get(i).getWorld().playSound(online.get(i).getLocation(), sound, 1.0f, 1.0f);
            Bukkit.getLogger().info("player");
             }
    }
}
