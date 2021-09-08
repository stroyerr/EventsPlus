package me.stroyer.eventsplus.Events.EventMethods.EventObjects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public class EventSideBar {

    private ScoreboardManager sbm = Bukkit.getScoreboardManager();
    public Scoreboard scoreboard;
    public Objective objective;

    public EventSideBar(Event event){
        Scoreboard sb = sbm.getNewScoreboard();
        Objective objective = sb.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Lucky Blocks " + ChatColor.YELLOW + "Event");
        Score score = objective.getScore(ChatColor.BLUE + "Participants: " + ChatColor.AQUA + event.members.size());
        score.setScore(10);
        Score score1 = objective.getScore(ChatColor.BLUE + event.members.get(0).getName());
        score1.setScore(9);
        Score score2 = objective.getScore("Score8");
        score2.setScore(8);
        Score score3 = objective.getScore("§6Colors");
        score3.setScore(7);
        for(int i = 0; i < event.members.size(); i++){
            event.members.get(i).setScoreboard(sb);
        }
    }

    public static void create(EventSideBar eventSideBar){

    }
}
