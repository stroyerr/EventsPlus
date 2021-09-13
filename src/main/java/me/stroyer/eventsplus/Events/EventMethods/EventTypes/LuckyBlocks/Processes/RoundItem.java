package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Mailbox;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.ReturnBlockRound;
import me.stroyer.eventsplus.Events.EventMethods.PlayerLostItem;
import me.stroyer.eventsplus.Events.EventMethods.RandomBlock;
import me.stroyer.eventsplus.Methods.PlaySound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

public class RoundItem {

    public static BukkitRunnable br;

    public static Inventory inv;
    public static Block roundBlock;
    public static Event e;
    public static ItemStack roundStack;

    public static void open(int round, Event event){
        inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.GOLD + "Round " + Event.activeEvent.round + " block");
        ItemStack currentItem;
        e = event;
        final int[] timeRemaining = {40};


        br = new BukkitRunnable() {
            @Override
            public void run() {
                if(timeRemaining[0] >=0){
                    timeRemaining[0] --;
                    updateInventory();
                } else if(timeRemaining[0] >= -20 && timeRemaining[0] < 0){
                    if(timeRemaining[0] % 4 == 0){
                        PlaySound.all(Sound.BLOCK_NOTE_BLOCK_PLING);
                    }
                    timeRemaining[0] --;
                }
                else{
                    startItemPicked(1);
                    this.cancel();
                }
            }
        };

        br.runTaskTimer(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 2L);


}
public static void updateInventory(){
        ItemStack randomItem = RandomBlock.get();
        inv.setItem(4, randomItem);
        inv = FillBlank.updateInventory(inv);
        roundStack = randomItem;

        for(int i = 0; i < e.members.size(); i++){
            e.members.get(i).openInventory(inv);
            e.members.get(i).playSound(e.members.get(i).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
    }

    public static void startItemPicked(int round) {
        itemPicked(round);
        br.cancel();
    }

    public static void itemPicked(int round){

        ItemStack roundItem = roundStack;
        roundStack = NewItem.createGuiItem(roundStack.getType(), ChatColor.AQUA + "Round " + Event.activeEvent.round + " Item", ChatColor.GOLD + "Mine this item!");
        BuildBlocks.build(roundStack);

        for(int i = 0; i < e.members.size(); i ++){
            if(e.members.get(i).getInventory().getItem(8) != null){
                Mailbox.getMailboxByPlayer(e.members.get(i)).addItem(e.members.get(i).getInventory().getItem(8));
            }
            e.members.get(i).getInventory().setItem(8, roundStack);
            e.members.get(i).getInventory().setHeldItemSlot(8);
            e.members.get(i).getInventory().close();
            Send.allPlayer("The item this round is " + ChatColor.YELLOW + roundStack.getType().name());
            Send.allPlayer("Get ready to mine this block!");
            Event.activeEvent.activeItemStack = roundStack;

            RoundActive.begin();
        }
    }

}
