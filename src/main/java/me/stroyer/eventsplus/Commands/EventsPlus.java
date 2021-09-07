package me.stroyer.eventsplus.Commands;

import jdk.jfr.Event;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Arena.CreateArena.CreateArena;
import me.stroyer.eventsplus.Main;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventsPlus implements CommandExecutor {

    private Main main;
    public EventsPlus(Main main){this.main = main;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if(args.length == 0){
            Send.player(p, "EventsPlus by Stroyer_ - Use /ep help for commands.");
            return true;
        }else {

            if (!p.hasPermission("eventsplus.admin")) {
                Send.player(p, ChatColor.RED + "Insufficient permissions.");
                return true;
            }
        }

        if(args.length > 0){
            if(args[0].equalsIgnoreCase("arena")){
                if(args.length >= 2){
                    if(args[1].equalsIgnoreCase("create")){
                        CreateArena.start(p);
                    }if(args[1].equalsIgnoreCase("save")){
                        if(args.length == 3){
                            for(int i = 0; i < Arenas.arenas.size(); i ++){
                                if(Arenas.arenas.get(i).name.equalsIgnoreCase(args[2])){
                                    Send.player(p, ChatColor.RED + "An arena with this name already exists.");
                                    return true;
                                }
                            }
                            CreateArena.attemptCreate(p, args[2]);
                        }else{
                            Send.player(p, "Incorrect syntax. Use /ep arena save <Arena Name>");
                        }
                    }

                    else{
                        Send.player(p, "Incorrect syntax. Use /cn arena <Create|Edit>");
                    }
                }else{
                    Send.player(p, "Incorrect syntax. Use /cn arena <Create|Edit>");
                }
            }
        }

        return true;
    }
}
