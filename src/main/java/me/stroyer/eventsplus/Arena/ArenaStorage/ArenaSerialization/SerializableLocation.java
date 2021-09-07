package me.stroyer.eventsplus.Arena.ArenaStorage.ArenaSerialization;

import me.stroyer.eventsplus.Arena.LocationData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SerializableLocation implements Serializable {

    public UUID worldUUID;
    public double x;
    public double y;
    public double z;

    public Boolean breakable;

    public SerializableLocation (Location l, Boolean breakable){
        worldUUID = l.getWorld().getUID();
        x = l.getX();
        y = l.getY();
        z = l.getZ();

        this.breakable = breakable;


    }

    public static List<LocationData> deserializeList(List<SerializableLocation> list){

        List<LocationData> convertedList = new ArrayList<LocationData>();

        for(int i = 0; i < list.size(); i++){
            World world = Bukkit.getWorld(list.get(i).worldUUID);
            Location newLocation = new Location(world, list.get(i).x, list.get(i).y, list.get(i).z);
            Boolean breakable = list.get(i).breakable;
            LocationData ld = new LocationData(newLocation, breakable);
            convertedList.add(ld);
        }

        return convertedList;
    }
}
