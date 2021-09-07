package me.stroyer.eventsplus.Arena.ArenaStorage;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.Arenas;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    public static void save() throws IOException {

        List<BlockData> blockData = new ArrayList<BlockData>();

        for(int i = 0; i < Arenas.arenas.size(); i ++){
            for(int j = 0; j < Arenas.arenas.get(i).locations.size(); j ++){
                Location l = Arenas.arenas.get(i).locations.get(j).location;
                Block b = l.getBlock();
                blockData.add(b.getBlockData());
            }
        }

        FileOutputStream fOut = new FileOutputStream("./plugins/EventsPlus/arenas.eventsplus");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(blockData);
        oOut.close();
    }

    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/EventsPlus/arenas.eventsplus");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        List<BlockData> blockData = (List<BlockData>) oIn.readObject();
        List<Block> blocks = new ArrayList<Block>();
        List<Location> locations = new ArrayList<Location>();
        for(int i = 0; i < blockData.size(); i ++){
            Block b = null;
            b.setBlockData(blockData.get(i));
        }
        
        Arenas.arenas = loadedArenas;
        oIn.close();
    }
}
