package me.stroyer.eventsplus.Arena;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Arena implements Serializable {

    public Boolean active;
    public List<LocationData> locations = new ArrayList<LocationData>();
    public String name;

    public Arena(String name, List<LocationData> locations){


        this.locations = new ArrayList<LocationData>();
        this.locations = locations;


        this.name = name;

        active = false;

    }
}
