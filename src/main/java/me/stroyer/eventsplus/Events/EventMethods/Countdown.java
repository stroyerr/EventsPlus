package me.stroyer.eventsplus.Events.EventMethods;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Methods.PlaySound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

public class Countdown {
    public static void start(){
        Event.activeEvent.movementAllowed = false;
        final int[] i = {0};

        BukkitRunnable countdownTimer = new BukkitRunnable() {
            @Override
            public void run() {
                if(i[0] < 3){
                    Send.allPlayer("" + ChatColor.AQUA + (3-i[0]) + "...");
                    i[0]++;
                    PlaySound.all(ENTITY_EXPERIENCE_ORB_PICKUP);
                }else if(i[0] >= 3){
                    Send.allPlayer(ChatColor.GREEN + "GO!");
                    PlaySound.all(Sound.BLOCK_AMETHYST_BLOCK_BREAK);
                    allowMovement();
                    Event.activeEvent.inRound = true;
                    this.cancel();
                }
            }
        };

        countdownTimer.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("EventsPlus"), 0L, 20L);
    }

    public static void allowMovement(){
        Event.activeEvent.movementAllowed = true;
    }
}
