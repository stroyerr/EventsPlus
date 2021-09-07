package me.stroyer.eventsplus.Events.EventMethods.EventObjects;

import org.bukkit.entity.Player;

public class EventPlayer {

    public Boolean isReady = false;
    public Player player;
    public int score = 0;

    public EventPlayer(Player player){
        this.player = player;
    }
}
