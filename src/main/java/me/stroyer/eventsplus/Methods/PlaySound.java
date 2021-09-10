package me.stroyer.eventsplus.Methods;

import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.awt.*;
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

    public static void allCatSong() {
            Player player = Event.activeEvent.host;
//            player.getWorld().playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.MUSIC_DISC_CAT, 100000);
//            player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.MUSIC_DISC_CAT, 10000);
        player.getWorld().playSound(player.getLocation(), Sound.MUSIC_DISC_CAT, 500f, 1f);
        }
    }
