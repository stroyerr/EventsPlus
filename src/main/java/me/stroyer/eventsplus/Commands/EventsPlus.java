package me.stroyer.eventsplus.Commands;

import jdk.jfr.Event;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Arena.CreateArena.CreateArena;
import me.stroyer.eventsplus.Events.GUIs.DeleteUI;
import me.stroyer.eventsplus.Events.GUIs.StartGUI;
import me.stroyer.eventsplus.Main;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
            Send.player(p, "EventsPlus by Stroyer_ - Use /ep help for admin commands.");
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
                            if(Arenas.arenas.size() == 34){
                                Send.player(p, ChatColor.RED + "Failed to created arena - the maxmimum number of arenas (34) has been reached. Delete another arena then try again.");
                                return true;
                            }
                            CreateArena.attemptCreate(p, args[2]);
                            return true;
                        }else{
                            Send.player(p, "Incorrect syntax. Use /ep arena save <Arena Name>");
                            return true;
                        }
                    }

                    else{
                        Send.player(p, "Incorrect syntax. Use /cn arena <Create|Edit>");
                        return true;
                    }
                }else{
                    Send.player(p, "Incorrect syntax. Use /ep arena <Create|Edit>");
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("listarenas")){
                List<String> arenasString = new ArrayList<String>();
                for(int i = 0; i < Arenas.arenas.size(); i ++){
                    arenasString.add("Name: " + ChatColor.GREEN + Arenas.arenas.get(i).name);
                    arenasString.add("Active: " + ChatColor.GREEN + Arenas.arenas.get(i).active);
                    arenasString.add("Blocks in arena: " + ChatColor.GREEN + Arenas.arenas.get(i).locations.size());
                    arenasString.add("World: " + ChatColor.GREEN + Arenas.arenas.get(i).locations.get(0).location.getWorld().toString());
                    Location loc = Arenas.arenas.get(i).locations.get(0).location;
                    arenasString.add("Location: " + ChatColor.GREEN + Math.round(loc.getX()) + ", " + Math.round(loc.getY()) + ", " + Math.round(loc.getZ()));
                    Send.playerMultipleLines(p, arenasString, "Arena ID: " + i);
                    arenasString.clear();
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("help")){
                List<String> msg = new ArrayList<String>();
                msg.add("/ep arena create" + ChatColor.GREEN + " Gives the arena creation selection tool");
                msg.add("/ep arena save <arena name>" + ChatColor.GREEN + " Creates an arena from your selection and names the arena <arena name>");
                msg.add("/ep listarenas" + ChatColor.GREEN + " Lists all currently saved arenas.");
                msg.add("/ep start" + ChatColor.GREEN + " Start an event as host. Must have eventsplus.host permission node to host an event.");
                msg.add("/ep delete" + ChatColor.GREEN + " Delete an event.");
                Send.playerMultipleLines(p, msg, "Admin Commands");
                return true;
            }

            if(args[0].equalsIgnoreCase("start")){
                if(!sender.hasPermission("eventsplus.host")){Send.player((Player) sender, ChatColor.RED + "Insufficient permissions.");return true;}
                StartGUI.open(p);
                return true;
            }

            if(args[0].equalsIgnoreCase("delete")){
                DeleteUI.open(p);
                return true;
            }
        }



        Send.player(p, ChatColor.RED +"Unkown command. Use /ep help for admin commands.");

        return true;
    }
}
