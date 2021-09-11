package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.Voting;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockEvent;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.LuckyBlockLocations;
import me.stroyer.eventsplus.Events.EventMethods.TpAllToEvent;
import me.stroyer.eventsplus.Events.GUIs.SelectEventType;
import me.stroyer.eventsplus.Methods.PlaySound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.ArenaSelectionItem;
import me.stroyer.eventsplus.UI.Methods.FillBlank;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VoteRound {

    public static int voteRoundTimeRemaining;

    public static void start(Player p){
        openGUI(p);
        giveAllVoteItem();
        arenaVotes.clear();
    }

    public static Inventory inv;
    public static List<ArenaSelectionItem> arenaItems = new ArrayList<ArenaSelectionItem>();

    public static void openGUI(Player p){
        arenaItems = new ArrayList<ArenaSelectionItem>();
        inv = Bukkit.createInventory(null, 36, ChatColor.DARK_RED + "Vote on round " + (Event.activeEvent.round) + " arena!");
        for(int i = 0; i < Arenas.arenas.size(); i ++){
            arenaItems.add(new ArenaSelectionItem(Arenas.arenas.get(i)));
            inv.setItem(i, arenaItems.get(i).guiItem);
        }


        inv = FillBlank.updateInventory(inv);
        p.openInventory(inv);
    }

    private static List<Arena> arenaVotes = new ArrayList<Arena>();

    public static void InventoryEvent(InventoryClickEvent e){
        for(int i = 0; i < arenaItems.size(); i ++){
            if(e.getCurrentItem().equals(arenaItems.get(i).guiItem)){
                Arena arena = arenaItems.get(i).arena;
                Player p = (Player) e.getWhoClicked();
                arenaVotes.add(arena);
                e.getWhoClicked().getInventory().removeItem(voteItem);
                score.setScore(score.getScore() + 1);
                e.getWhoClicked().closeInventory();
                break;
            }
        }
    }

    public static ItemStack voteItem;

    public static void giveAllVoteItem(){
        voteItem = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.GREEN + "Vote");
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).getInventory().setItem(8, voteItem);
        }
    }

    private static BukkitRunnable voteTimer;

    public static void startVoteTimer(){
        voteTimerScoreboardDisplay();
        voteTimer = new BukkitRunnable() {

            int i = 30;

            @Override
            public void run() {
                voteRoundTimeRemaining = i;
                updateVoteTimer(i);
                if(i > 5 && i <=30){
                    i--;
                }
                if(i <=5 && i > 0){
                    PlaySound.all((Sound.BLOCK_NOTE_BLOCK_PLING));
                    i--;
                }

                if(i == 0){
                    PlaySound.all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
                    this.cancel();
                    voteFinished();
                }
            }
        };

        voteTimer.runTaskTimer(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 20L );
    }

    public static void cancelVoteTimer(){
        Bukkit.getLogger().info("vote cancel called");
        if(voteTimer != null){
            voteTimer.cancel();
        }
    }

    private static Arena mostPopularArena = null;
    private static int votesOf = 0;

    public static void voteFinished(){
        if(Event.activeEvent == null){
            return;
        }

        voteTimer.cancel();
        Bukkit.getLogger().info("vote finished");
        mostPopularArena = null;
        votesOf = 0;
        for(int i = 0; i < Arenas.arenas.size(); i ++){
            int currentVotes = 0;
            for(int j = 0; j < arenaVotes.size(); j++){
                if(arenaVotes.get(j).equals(Arenas.arenas.get(i))){
                    currentVotes ++;
                }
                if(currentVotes > votesOf){
                    votesOf = currentVotes;
                    mostPopularArena = arenaVotes.get(j);
                }
            }
        }
        LuckyBlockEvent.endRound();

        if(mostPopularArena == null){
            Random random = new Random();
            int index = random.nextInt(Arenas.arenas.size());
            mostPopularArena = Arenas.arenas.get(index);
            Send.allPlayer("Vote finished and it was a tie! " + mostPopularArena.name + " was randomly chosen as the next arena!");
        }else{
            Send.allPlayer("Vote finished and the vote was won by " + mostPopularArena.name + " with " + votesOf + " votes!");
        }

        arenaVotes.clear();
        beginNewRound(mostPopularArena);

    }

    public static void updateVoteTimer(int i){
        timeRemaining = i;
        score3.setScore(i);
    }

    public static ScoreboardManager sbm = Bukkit.getScoreboardManager();
    public static Scoreboard sb;
    public static int timeRemaining = 30;
    public static Objective objective;
    public static Score score3;
    public static Score score;

    public static void voteTimerScoreboardDisplay(){
        sbm = Bukkit.getScoreboardManager();
        Scoreboard sb = sbm.getNewScoreboard();
        objective = sb.registerNewObjective("voteBoard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Voting " + ChatColor.YELLOW + "Round");
        score = objective.getScore(ChatColor.DARK_GREEN + "Votes: " + ChatColor.AQUA + "");
        score.setScore(0);
        score3 = objective.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Time remaining    > ");
        score3.setScore(timeRemaining);

        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).setScoreboard(sb);
        }
    }

    public static void beginNewRound(Arena arena){

        LuckyBlockLocations.clearLocations();

        LuckyBlockLocations.repairSpawnBlock();
        mostPopularArena = null;
        votesOf = 0;

        Event.activeEvent.arena = null;
        Event.activeEvent.activeEventBlocks.clear();
        Event.activeEvent.movementAllowed = true;
        Event.activeEvent.inRound = false;

        Event.activeEvent.arena = arena;
        LuckyBlockLocations.generateLuckyBlockLocations();
        TpAllToEvent.tp(Event.activeEvent);

        LuckyBlockEvent.initialise(Event.activeEvent);
    }
}
