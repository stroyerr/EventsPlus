package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.Voting;

import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.UI.ArenaSelectionItem;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class VoteRound {

    public static int voteRoundTimeRemaining;

    public static void start(Player p){
        openGUI(p);
        giveAllVoteItem();
    }

    public static Inventory inv;
    public static List<ArenaSelectionItem> arenaItems = new ArrayList<ArenaSelectionItem>();

    public static void openGUI(Player p){
        arenaItems = new ArrayList<ArenaSelectionItem>();
        inv = Bukkit.createInventory(null, 36, ChatColor.DARK_RED + "Vote on next arena!");
        for(int i = 0; i < Arenas.arenas.size(); i ++){
            arenaItems.add(new ArenaSelectionItem(Arenas.arenas.get(i)));
            inv.setItem(i, arenaItems.get(i).guiItem);
        }


        inv = FillBlank.updateInventory(inv);
        p.openInventory(inv);
    }

    public static ItemStack voteItem;

    public static void giveAllVoteItem(){
        voteItem = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.GREEN + "Vote");
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).getInventory().setItem(8, voteItem);
        }
    }

    public static void startVoteTimer(){
        BukkitRunnable voteTimer = new BukkitRunnable() {

            int i = 15;

            @Override
            public void run() {
                voteRoundTimeRemaining = i;
                updateVoteTimer(i);
                i--;
                if(i == 1){
                    this.cancel();
                    voteFinished();
                }
            }
        };
    }

    public static void voteFinished(){

    }

    public static void updateVoteTimer(int i){

    }
}
