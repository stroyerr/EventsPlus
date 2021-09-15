/*
 * This project is licensed under the MIT license.
 *
 * Copyright (c)  2021 Stroyer.  All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.stroyer.eventsplus.VotersEvent.EventHandling;


import me.stroyer.eventsplus.Methods.StaffOnline;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.VotersEvent.Util.PlayersVoted;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.*;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.PlayerPrelocation;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WipeoutEvent {

    /*
    This class should be the central control unit for all Voting Event actions. This class should contain
    all methods and orders of the event. No other thread or class should become the primary 'storyboard' of
    the event. Other classes may be referenced to get or set properties. The object WipeoutEvent has central and
    only important properties. All properties are to be attained via the object call methods, not directly (hence the
    private variables).
     */

    public static WipeoutEvent activeEvent = null;

    private List<Player> members = new ArrayList<>();
    private Boolean eventActive;
    private Player host;
    private List<Block> turretBlocks;
    private WipeoutArena arena;
    private List<PlayerPrelocation> preLocations = new ArrayList<>();
    private Location spawn;

    public WipeoutEvent(Player host, WipeoutArena arena){
        this.members = PlayersVoted.playersVotedInLastDay();
        this.eventActive = true;
        this.host = host;
        this.arena = arena;
        this.turretBlocks = this.arena.blocksOfTypeInArena(Material.DISPENSER);
    }

    public List<Block> getTurretBlocks(){
        return this.turretBlocks;
    }

    public Boolean eventActive(){
        return this.eventActive;
    }

    public Player getHost(){
        return this.host;
    }

    public WipeoutArena getArena(){
        return this.arena;
    }

    public WipeoutEvent(WipeoutArena arena){
        this.members = PlayersVoted.playersVotedInLastDay();
        this.eventActive = true;
        this.arena = arena;
        this.turretBlocks = this.arena.blocksOfTypeInArena(Material.DISPENSER);
    }

    public static void initialise(Player host, WipeoutArena arena){
        if(activeEvent != null){
            return;
        }
        if(!runInitialiseChecks(arena)){
            Send.player(host, ChatColor.RED + "Something went wrong, ensure you have properly configured and setup the event.");
            return;
        }

        Send.player(host, ChatColor.GREEN + "Wipeout Arena configured correctly. Initiating now.");
        activeEvent = new WipeoutEvent(host, arena);
        for(Block block : activeEvent.getTurretBlocks()){
            Turret.turretPreChange.add(new Turret(block));
        }
        Turret.startParticleEffects();
        activeEvent.savePlayerPreLocations();
        activeEvent.tpAllToEvent();
        activeEvent.arena.deletePlaceholders();
        activeEvent.startEventActive();

    }

    public static void initialise(){
        if(activeEvent != null){
            return;
        }
        for(Player player : StaffOnline.get()){
            player.sendMessage(ChatColor.GREEN + "Console has recieved 10 votes in 24 hours. Attempting to begin event.");
        }
        Random random = new Random();
        int index = random.nextInt(WipeoutArena.wipeOutArenas.size()-1);
        WipeoutArena randomArena = WipeoutArena.wipeOutArenas.get(index);
        if(!runInitialiseChecks(randomArena)){
            Bukkit.getLogger().info("One or more of your Wipeout Arenas are not configured properly.");
            return;
        }
        activeEvent = new WipeoutEvent(randomArena);
        for(Block block : activeEvent.getTurretBlocks()){
            Turret.turretPreChange.add(new Turret(block));
        }
        Turret.startParticleEffects();
        activeEvent.savePlayerPreLocations();
        activeEvent.tpAllToEvent();
        activeEvent.arena.deletePlaceholders();
        activeEvent.startEventActive();
    }

    public void endEvent(){
        PlayerCheckpoint.clearCheckpoints();
        Checkpoint.clearCheckpoints();
        activeEvent.tpToPrelocations();
        activeEvent.arena.buildPlaceholders();
        Turret.stopParticleEffects();
        Turret.clearTurretBlockSaveData();
        TurretHandler.endTurretTimer();

        nullActiveWipeoutEvent();
    }

    public List<Player> getMembers(){
        return this.members;
    }

    public void tpToPrelocations(){
        for(PlayerPrelocation playerPrelocation : this.getPreLocations()){
            playerPrelocation.player.teleport(playerPrelocation.location);
        }
    }

    public void tpAllToEvent(){
        for(Player player: this.members){
            Bukkit.getLogger().info("found " + arena.blocksOfTypeInArena(Material.SCAFFOLDING).size() + " scaffolds");
            player.teleport( new Location(arena.blocksOfTypeInArena(Material.SCAFFOLDING).get(0).getLocation().getWorld(), arena.blocksOfTypeInArena(Material.SCAFFOLDING).get(0).getLocation().getX(), arena.blocksOfTypeInArena(Material.SCAFFOLDING).get(0).getLocation().getY() + 2, arena.blocksOfTypeInArena(Material.SCAFFOLDING).get(0).getLocation().getZ()));
            this.spawn = arena.blocksOfTypeInArena(Material.SCAFFOLDING).get(0).getLocation();
        }
    }

    public static void nullActiveWipeoutEvent(){
        WipeoutEvent.activeEvent = null;
    }

    public static Boolean runInitialiseChecks(WipeoutArena arena){
        Bukkit.getLogger().info("Disp: " + arena.blocksOfTypeInArena(Material.DISPENSER).size() + ", Scaff: " + arena.blocksOfTypeInArena(Material.SCAFFOLDING).size());
        if(arena.blocksOfTypeInArena(Material.DISPENSER).size() > 0){
            if(arena.blocksOfTypeInArena(Material.SCAFFOLDING).size() == 1){
                return true;
            }
            return false;
        }else{
            return false;
        }
    }

    public void savePlayerPreLocations(){
        for(Player player : members){
            preLocations.add(new PlayerPrelocation(player, player.getLocation()));
        }
    }

    public List<PlayerPrelocation> getPreLocations(){
        return this.preLocations;
    }

    public void startEventActive(){
        Checkpoint.generateCheckpoints();
        PlayerCheckpoint.generateCheckpoints(this.members);
        TurretHandler.initiate();
    }

    public void respawnPlayer(Player player){
        if(PlayerCheckpoint.getPlayerCheckpointByPlayer(player) == null){
            player.teleport(this.spawn);
        }else
        if(PlayerCheckpoint.getPlayerCheckpointByPlayer(player).getLastCheckpointLocation() == null){
            player.teleport(this.spawn);
        }else
        if(PlayerCheckpoint.getPlayerCheckpointByPlayer(player) != null){
            PlayerCheckpoint.getPlayerCheckpointByPlayer(player).respawnAtCheckpoint();
        }

        player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 10, 1);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "You got hit! Respawning at your last checkpoint..."));
    }
}
