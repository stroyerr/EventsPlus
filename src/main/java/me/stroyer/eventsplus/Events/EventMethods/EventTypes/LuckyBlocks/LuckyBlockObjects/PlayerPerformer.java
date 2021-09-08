package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerPerformer {

    public static List<PlayerPerformer> playerPerformers = new ArrayList<PlayerPerformer>();

    public Player player;
    public int rank;

    public PlayerPerformer(Player player, int rank){
        this.player = player;
        this.rank = rank;
    }

    public static ItemStack createPlayerHead(Player p){
        ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(p.getName());
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        skull.setItemMeta(meta);

        return skull;
    }
}
