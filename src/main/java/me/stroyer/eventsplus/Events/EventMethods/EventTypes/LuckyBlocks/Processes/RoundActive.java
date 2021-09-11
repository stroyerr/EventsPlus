package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Events.EventMethods.Countdown;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerPerformer;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerScore.PlayerScore;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.Voting.VoteRound;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.SetMovementSpeed;
import me.stroyer.eventsplus.Methods.PlaySound;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RoundActive {

    public static List<PlayerPerformer> performers = new ArrayList<PlayerPerformer>();

    public static ArrayList<Player> ranks = new ArrayList<Player>();

    public static void begin(){
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).setGameMode(GameMode.SURVIVAL);
            Event.activeEvent.members.get(i).setHealth(20);
            Event.activeEvent.members.get(i).setFlying(false);
            Event.activeEvent.members.get(i).setFoodLevel(20);
            SetMovementSpeed.walkSpeed(0.5f);
            activate();
        }
        Countdown.start();
    }

    public static void activate(){
        Event.activeEvent.inRound = true;
    }

    public static void playerBreaksEventBlock(Player p){
        ranks.add(p);
        Send.allPlayer(p.getName() + " has gotten a lucky block! They came in position " + ranks.size());
        Reward.basic(p);
        for(int i = 0; i < PlayerScore.playerScores.size(); i++){
            if(p.equals(PlayerScore.playerScores.get(i).player)){
                PlayerScore.playerScores.get(i).givePoints(Event.activeEvent.members.size() - PlayerScore.getPlayersFinished());
                PlayerScore.playerFinished();
                p.sendMessage("You now have " + PlayerScore.playerScores.get(i).score + " points!");
                break;
            }
        }
        performers.add(new PlayerPerformer(p, ranks.size()));
        p.setGameMode(GameMode.SPECTATOR);
        if(checkRoundShouldFinish()){
            roundPlayFinished();
            RoundTimer.cancelTimer();
            timerFinished();
        }
    }

    public static void clearRanks(){
        PlayerScore.resetPlayerFinishedCount();
        ranks.clear();
    }

    public static Boolean checkRoundShouldFinish(){
        if(ranks.size() >= Event.activeEvent.members.size()){
            Bukkit.getLogger().info("testing 1");
            return true;
        }else{
            return false;
        }
    }

    public static void roundPlayFinished(){
        TopPerformers.display(performers);
        RoundTimer.cancelTimer();
        Event.activeEvent.inRound = false;
        VoteRound.startVoteTimer();
        performers.clear();
    }

    public static void timerFinished(){
        for(int j = 0; j < Event.activeEvent.members.size(); j++){
            PlaySound.all(Sound.ENTITY_PLAYER_LEVELUP);
            Event.activeEvent.members.get(j).sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Round finished!"));
        }
        roundPlayFinished();
    }

    public static void timerEvent(int i){

        String message = ChatColor.AQUA + "Time remaining: " + ChatColor.YELLOW + i + " seconds";
        if (i < 4) {
            PlaySound.all(Sound.BLOCK_NOTE_BLOCK_PLING);
        }
        for(int j = 0; j < Event.activeEvent.members.size(); j ++){
            Player player = Event.activeEvent.members.get(j);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }
}
