package me.stroyer.eventsplus;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.ArenaStorage.StorageManager;
import me.stroyer.eventsplus.Commands.EventsPlus;
import me.stroyer.eventsplus.Commands.MailboxCommand;
import me.stroyer.eventsplus.Commands.TabCompleter;
import me.stroyer.eventsplus.Events.EventMethods.CloseEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Caller.CallTimer;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.SerializableMailbox;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Podium.Podium;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockEvent;
import me.stroyer.eventsplus.Events.EventMethods.Metrics;
import me.stroyer.eventsplus.Events.EventMethods.UpdateChecker;
import me.stroyer.eventsplus.Listeners.*;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.ArenaStorage.ArenaManagement;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.ConsoleVote;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static int versionInt;

    @Override
    public void onEnable() {
        // Plugin startup logic

        int pluginId = 12782; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        Logger logger = this.getLogger();

        new UpdateChecker(this, 96159).getVersion(version -> {
            double latestOnlineVersion;
            double currentVersionRun;
            latestOnlineVersion = Double.parseDouble(version);
            currentVersionRun = Double.parseDouble(this.getDescription().getVersion());
            if (latestOnlineVersion == currentVersionRun) {
                logger.info("There is not a new update available.");
                versionInt = 0;
            } else if (latestOnlineVersion > currentVersionRun){
                logger.info("There is a new update available.");
                versionInt = -1;
            } else if (latestOnlineVersion < currentVersionRun){
                logger.info("You are running a dev version of EventsPlus");
                versionInt = 1;
            }
        });

//        Bukkit.getServer().getPluginManager().getPlugin("EventsPlus").saveDefaultConfig();
        getCommand("ep_addvote").setExecutor(new ConsoleVote(this));
        getCommand("EventsPlus").setExecutor(new EventsPlus(this));
        getCommand("Mailbox").setExecutor(new MailboxCommand(this));
        getCommand("EventsPlus").setTabCompleter(new TabCompleter());
        getServer().getPluginManager().registerEvents(new ArrowEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveToBlock(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnect(), this);
        getServer().getPluginManager().registerEvents(new SelectionWand(), this);
        getServer().getPluginManager().registerEvents(new BlockActionInArena(), this);
        getServer().getPluginManager().registerEvents(new InventoryInteract(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMovementEvent(), this);
        getServer().getPluginManager().registerEvents(new ArenaActiveBlockInteraction(), this);
        getServer().getPluginManager().registerEvents(new PlayerHealthHunger(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        CallTimer.initiate();


        File f2 = new File("./plugins/EventsPlus/mailboxData.eventsplus");
        try{
            Path path = Paths.get("./plugins/EventsPlus");
            Path path2 = Paths.get("./plugins/EventsPlus/mailboxData.eventsplus");
            Files.createDirectories(path);
            File file3 = new File("./plugins/EventsPlus/wipeoutarenas.eventsplus");
            File consoleVotes = new File("./plugins/eventsplus/consolevote.eventsplus");
            try {
                f2.createNewFile();
                file3.createNewFile();
                consoleVotes.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().info("Creating EventsPlus data files...");
                Bukkit.getLogger().info("Success!");
            }
        } catch (IOException e) {
            Bukkit.getLogger().info("Creating EventsPlus data files...");
            Bukkit.getLogger().info("Success!");
        }

        BukkitRunnable loadArenasPodiumsPostStart =  new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    StorageManager.load();
                    Podium.attemptLoadLocal();
                    SerializableMailbox.loadMailboxes();
                    ArenaManagement.loadArenasLocally();
                    ConsoleVote.load();
                    ConsoleVote.startTimer();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        loadArenasPodiumsPostStart.runTaskLater(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        CallTimer.cancel();

        try {
            StorageManager.save();
            Podium.attemptSaveLocal();
            //if(SerializableMailbox.getSerializedMailboxes().size() != 0){
                //SerializableMailbox.saveMailboxes();
            //}
            ArenaManagement.saveArenasLocally();
            ConsoleVote.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Event.activeEvent != null){
            CloseEvent.close(Event.activeEvent);
        }
    }
}
