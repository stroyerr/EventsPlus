package me.stroyer.eventsplus.Arena;

import org.bukkit.Location;

import java.io.Serializable;

public class LocationData implements Serializable {

    public Location location;
    public Boolean breakable;

    public LocationData(Location location, Boolean breakable){
        this.location = location;
        this.breakable = breakable;
    }
}
