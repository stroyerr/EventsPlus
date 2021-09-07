package me.stroyer.eventsplus.Arena.ArenaStorage.ArenaSerialization;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.LocationData;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializableArena implements Serializable {

    public List<SerializableLocation> serializedLocations = new ArrayList<SerializableLocation>();
    public String name;
    public Boolean enabled;

    public SerializableArena(Arena arena){
        this.name = arena.name;
        this.enabled = arena.active;

        for(int i = 0; i < arena.locations.size(); i++){
            this.serializedLocations.add(new SerializableLocation(arena.locations.get(i).location, arena.locations.get(i).breakable));
        }
    }

    public static List<SerializableArena> convertToSerializable(List<Arena> arenas){

        List<SerializableArena> converted = new ArrayList<SerializableArena>();

        for(int i = 0; i < arenas.size(); i ++){
            converted.add(new SerializableArena(arenas.get(i)));
        }
        return converted;
    }

    public static List<Arena> deserialize(List<SerializableArena> arenas){
        List<Arena> converted = new ArrayList<Arena>();
        for(int i = 0; i < arenas.size(); i++){
            SerializableArena a = arenas.get(i);
            String name = a.name;
            Boolean enabled = a.enabled;
            List<LocationData> newLocations = SerializableLocation.deserializeList(a.serializedLocations);
            Arena newArena = new Arena(name, newLocations);
            converted.add(newArena);
        }

        return converted;
    }
}
