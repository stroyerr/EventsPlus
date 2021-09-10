package me.stroyer.eventsplus.PlayerInteraction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Send {

    public static String prefix = ChatColor.BLUE + "Events" + ChatColor.AQUA + "Plus" + ChatColor.GRAY + " // " + ChatColor.GOLD;

    public static void player(Player player, String message){
        player.sendMessage(prefix + message);
    }

    public static void playerMultipleLines(Player player, List<String> message, String firstLine){
        player.sendMessage(ChatColor.GRAY + "----------------------------------------");
        player(player, ChatColor.YELLOW + firstLine);
        player.sendMessage("");
        for(int i = 0; i < message.size(); i ++){
            player.sendMessage(ChatColor.GOLD + message.get(i));
        }
        player.sendMessage(ChatColor.GRAY + "----------------------------------------");
    }

    public static void allMultipleLines(List<String> message, String firstLine){
        List<Player> online = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        for(int j = 0; j < online.size(); j ++){
            player(online.get(j), ChatColor.YELLOW + firstLine);
            for(int i = 0; i < message.size(); i ++){
                online.get(j).sendMessage(ChatColor.GOLD + message.get(i));
            }
        }
    }

    public static void allPlayer(String message){
        List<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        for(int i = 0; i < players.size(); i++){
            player(players.get(i), message);
        }
    }
}
