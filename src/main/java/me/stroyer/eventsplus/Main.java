package me.stroyer.eventsplus;

import me.stroyer.eventsplus.Arena.ArenaStorage.StorageManager;
import me.stroyer.eventsplus.Commands.EventsPlus;
import me.stroyer.eventsplus.Listeners.BlockActionInArena;
import me.stroyer.eventsplus.Listeners.SelectionWand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("EventsPlus").setExecutor(new EventsPlus(this));
        getServer().getPluginManager().registerEvents(new SelectionWand(), this);
        getServer().getPluginManager().registerEvents(new BlockActionInArena(), this);

        File f = new File("./plugins/EventsPlus/arenas.eventsplus");
        try{
            Path path = Paths.get("./plugins/EventsPlus");
            Files.createDirectories(path);
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            StorageManager.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            StorageManager.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
