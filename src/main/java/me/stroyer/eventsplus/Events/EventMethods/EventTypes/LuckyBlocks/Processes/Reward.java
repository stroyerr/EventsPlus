package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Reward {

    public static List<ItemStack> basicRewards;

    public static void basic(Player player){
        ItemStack[] basicRewardsArray = {
                new ItemStack(Material.DIAMOND, 2),
                new ItemStack(Material.IRON_INGOT, 8),
                new ItemStack(Material.GOLD_INGOT, 4),
                new ItemStack(Material.OAK_LOG, 32),
                new ItemStack(Material.GOLDEN_APPLE, 1),
                new ItemStack(Material.BLAZE_ROD, 2)
        };

        basicRewards = Arrays.asList(basicRewardsArray);
        Random random = new Random();
        int l = random.nextInt(basicRewards.size() - 1);
        player.getInventory().addItem(basicRewards.get(l));
        Send.allPlayer(ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " got " + ChatColor.YELLOW + basicRewards.get(l).getAmount() + " " + basicRewards.get(l).getType().name() + ChatColor.AQUA + "!");

    }
}
