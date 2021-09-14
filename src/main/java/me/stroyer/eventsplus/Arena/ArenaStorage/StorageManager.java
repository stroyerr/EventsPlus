package me.stroyer.eventsplus.Arena.ArenaStorage;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.ArenaStorage.ArenaSerialization.SerializableArena;
import me.stroyer.eventsplus.Arena.Arenas;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {

    public static List<SerializableArena> loadedArenas = new ArrayList<>();

    public static void save() throws IOException {

        List<SerializableArena> arenasToSave = SerializableArena.convertToSerializable(Arenas.arenas);

        if(arenasToSave.equals(loadedArenas)){
            Bukkit.getLogger().info("No arenas were modified, skipping save process.");
            return;
        }

        File f = new File("./plugins/EventsPlus/arenas.eventsplus");
        f.createNewFile();


        FileOutputStream fOut = new FileOutputStream("./plugins/EventsPlus/arenas.eventsplus");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(arenasToSave);
        oOut.close();
    }

    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/EventsPlus/arenas.eventsplus");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        List<SerializableArena> serializedArenas = (List<SerializableArena>) oIn.readObject();
        loadedArenas = serializedArenas;
        Bukkit.getLogger().info("read serialized arena list of size " + serializedArenas.size());
        List<Arena> loadedArenas = SerializableArena.deserialize(serializedArenas);
        Bukkit.getLogger().info("deserialized arenas to list of size " + loadedArenas.size());
        Arenas.arenas = loadedArenas;
        Bukkit.getLogger().info("Finished deserialisation with new arena size of " + loadedArenas.size());
        oIn.close();
    }
}
