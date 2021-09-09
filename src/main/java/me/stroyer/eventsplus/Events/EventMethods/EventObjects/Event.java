package me.stroyer.eventsplus.Events.EventMethods.EventObjects;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Methods.StaffOnline;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Event {

    public static Event activeEvent = null;

    public Arena arena;
    public Player host;
    public List<Player> members;
    public List<Player> staff = new ArrayList<Player>();
    public List<PlayerEventPreLocation> originalLocations = new ArrayList<PlayerEventPreLocation>();
    public String type;
    public List<EventPlayer> eventPlayers = new ArrayList<EventPlayer>();
    public int round;
    public List<Block> activeEventBlocks;
    public Boolean movementAllowed;
    public Boolean inRound;
    public ItemStack activeItemStack;

    public Event(Arena arena, Player host, String type) {
        this.arena = arena;
        this.host = host;
        this.originalLocations = PlayerEventPreLocation.generateList();
        this.members = new ArrayList<Player>(Bukkit.getOnlinePlayers());
        this.staff = StaffOnline.get();
        this.round = 1;
        this.movementAllowed = true;
        this.inRound = false;
        for(int i = 0; i < members.size(); i ++){
            eventPlayers.add(new EventPlayer(members.get(i)));
        }
        if(type.equalsIgnoreCase("lucky_blocks")){
            this.type = "lucky_blocks";
        }
    }
}
