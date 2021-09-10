package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RoundTimer {

    public static BukkitRunnable backGroundTimer;

    public static void start(){
        backGroundTimer = new BukkitRunnable() {

            int i = 30;

            @Override
            public void run() {
                BuildBlocks.particles();
                if(i > 0){
                    RoundActive.timerEvent(i);
                    i--;
                }else{
                    RoundActive.timerFinished();
                    this.cancel();
                }
            }
        };

        backGroundTimer.runTaskTimerAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("EventsPlus"), 0L, 20L);
    }

    public static void cancelTimer(){
        if(backGroundTimer != null){
            backGroundTimer.cancel();
        }

    }
}
