package me.stroyer.eventsplus.Commands;

import jdk.jfr.Event;
import me.stroyer.eventsplus.Arena.Arenas;
import me.stroyer.eventsplus.Arena.CreateArena.CreateArena;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Podium.PodiumSelection;
import me.stroyer.eventsplus.Events.GUIs.DeleteUI;
import me.stroyer.eventsplus.Events.GUIs.StartGUI;
import me.stroyer.eventsplus.Main;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import me.stroyer.eventsplus.VotersEvent.EventHandling.PreVoteEvent;
import me.stroyer.eventsplus.VotersEvent.EventHandling.WipeoutEvent;
import me.stroyer.eventsplus.VotersEvent.Util.PlayersVoted;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.ArenaStorage.ArenaManagement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventsPlus implements CommandExecutor {

    public static String[] commands = {
            "start",
            "help",
            "delete",
            "arena",
            "stop",
            "listarenas",
            "license",
            "tp",
            "podium",
            "version",
            "wipeout"
    };

    public static String[] arenaCommands = {
            "create",
            "save"
    };

    public static String[] podiumCommands = {
            "create",
            "save"
    };

    public static String[] wipeoutCommands = {
            "create"
    };

    private Main main;
    public EventsPlus(Main main){this.main = main;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if(args.length == 0){
            Send.player(p, "EventsPlus by Stroyer_ - Use /ep help for admin commands.");
            return true;
        }else {

            if(args[0].equalsIgnoreCase("check")){
                Boolean isRegistered = PlayersVoted.playersVotedInLastDay().contains(p);
                if(isRegistered){
                    Send.player(p, ChatColor.GREEN + "You are registered for the event! Thanks for voting in the last 24 hours!");
                }else{
                    Send.player(p, ChatColor.RED + "You are not registered for the event as you have not voted in the past 24 hours. Vote now and you will automatically be registered!");
                }
                return true;
            }

            if (!p.hasPermission("eventsplus.admin")) {
                Send.player(p, ChatColor.RED + "Insufficient permissions.");
                return true;
            }
        }

        if(args.length > 0){

            if(args[0].equalsIgnoreCase("wipeout")){
                if(args.length == 1){
                    Send.player(p, ChatColor.LIGHT_PURPLE + "Left click to set pos1. Right click to set pos2. Use /ep wipeout create <name> to create your selection.");
                    p.getInventory().setItem(p.getInventory().getHeldItemSlot(), NewItem.createGuiItem(Material.IRON_AXE, ChatColor.RED + "Wipeout Arena Selection"));
                    return true;
                }
                if(args.length == 3){
                    ArenaManagement.attemptCreate(args[2], p);
                    return true;
                }
                Send.player(p, "Incorrect syntax. Use /ep wipeout create <name>");
                return true;
            }

            if(args[0].equalsIgnoreCase("version")){
                Send.player(p, "Currently running version " + Bukkit.getPluginManager().getPlugin("EventsPlus").getDescription().getVersion() + ".");
                return true;
            }

            if(args[0].equalsIgnoreCase("license")){

                String[] MITLicense = {
                        "Copyright ?? 2021 stroyerr\n" ,
                                "\n" ,
                                "Permission is hereby granted, free of charge, to any person obtaining " +
                                "a copy of this software and associated documentation files (the \"Software\"), " +
                                "to deal in the Software without restriction, including without limitation the " +
                                "rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell " +
                                "copies of the Software, and to permit persons to whom the Software is furnished " +
                                "to do so, subject to the following conditions: " +
                                "","",
                                "The above copyright notice and this permission notice shall be included in all" +
                                "copies or substantial portions of the Software." +
                                "", "", "",
                                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, " +
                                "INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A " +
                                "PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS " +
                                "BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, " +
                                "TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE " +
                                "USE OR OTHER DEALINGS IN THE SOFTWARE."
                };

                Send.playerMultipleLines(p, Arrays.asList(MITLicense), "EventsPlus (Open Source) is licensed under the MIT license;");
                return true;
            }

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
                msg.add("/mailbox" + ChatColor.GREEN + " Opens your mailbox.");
                msg.add("/ep arena create" + ChatColor.GREEN + " Gives the arena creation selection tool");
                msg.add("/ep arena save <arena name>" + ChatColor.GREEN + " Creates an arena from your selection and names the arena <arena name>");
                msg.add("/ep podium create" + ChatColor.GREEN + " Gives the podium selection wand.");
                msg.add("/ep podium save" + ChatColor.GREEN + " Sets the active podium to the podium selected with the selection wand.");
                msg.add("/ep listarenas" + ChatColor.GREEN + " Lists all currently saved arenas.");
                msg.add("/ep start" + ChatColor.GREEN + " Start an event as host. Must have eventsplus.host permission node to host an event.");
                msg.add("/ep delete" + ChatColor.GREEN + " Delete an event.");
                msg.add("/ep license " + ChatColor.GREEN + "Get the license for EventsPlus");
                msg.add("/ep version " + ChatColor.GREEN + "Current version being run");
                msg.add("/ep wipeout" + ChatColor.GREEN + " Get wipeout arena selection tool.");
                msg.add("/ep wipeout create <name>" + ChatColor.GREEN + " Create a wipeout arena from your selection.");
                Send.playerMultipleLines(p, msg, "Admin Commands");
                return true;
            }

            if(args[0].equalsIgnoreCase("startvote")){
                if(p.hasPermission("eventsplus.bypass")){
                    PreVoteEvent.initialise();
                }
            }

            if(args[0].equalsIgnoreCase("podium")){
                if(args.length > 1){
                    if(args[1].equalsIgnoreCase("create")){
                        PodiumSelection.giveSelectionWant(p);
                        return true;
                    }else if(args[1].equalsIgnoreCase("save")){
                        PodiumSelection.createPodium(p);
                        return true;
                    }else{
                        Send.player(p, ChatColor.RED + "Unkown command.");
                        return true;
                    }
                }else{
                    Send.player(p, ChatColor.RED + "Incorrect syntax.");
                    return true;
                }
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

            if(args[0].equalsIgnoreCase("stop")){
                return true;
            }

            if(args[0].equalsIgnoreCase("tp")){
                if(args.length < 2){
                    Send.player(p, ChatColor.RED + "Specify an arena to tp to.");
                }else{
                    for(int i = 0; i < Arenas.arenas.size(); i++){
                        if(Arenas.arenas.get(i).name.equalsIgnoreCase(args[1])){
                            p.teleport(Arenas.arenas.get(i).locations.get(0).location);
                            return true;
                        }
                    }
                    Send.player(p, ChatColor.RED + "Could not find arena: " + args[1]);
                    return true;
                }
            }
        }



        Send.player(p, ChatColor.RED +"Unkown command. Use /ep help for admin commands.");

        return true;
    }

}
