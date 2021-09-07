package me.stroyer.eventsplus.PlayerInteraction;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Send {

    public static String prefix = ChatColor.BLUE + "Events" + ChatColor.AQUA + "Plus" + ChatColor.GRAY + " // " + ChatColor.GOLD;

    public static void player(Player player, String message){
        player.sendMessage(prefix + message);
    }

    public static void playerMultipleLines(Player player, List<String> message, String firstLine){
        player(player, ChatColor.YELLOW + firstLine);
        for(int i = 0; i < message.size(); i ++){
            player.sendMessage(ChatColor.GOLD + message.get(i));
        }
    }
}
