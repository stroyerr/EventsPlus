package me.stroyer.eventsplus.PlayerInteraction;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Send {

    public static String prefix = ChatColor.BLUE + "Events" + ChatColor.AQUA + "Plus" + ChatColor.GRAY + " // " + ChatColor.GOLD;

    public static void player(Player player, String message){
        player.sendMessage(prefix + message);
    }
}
