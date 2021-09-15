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

package me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayerCheckpoint {

    private static List<PlayerCheckpoint> playerCheckpoints = new ArrayList<>();
    private Player player;
    private Checkpoint latestCheckpoint;

    public PlayerCheckpoint(@NotNull Player player){
        this.player = player;
        this.latestCheckpoint = null;
        Bukkit.getLogger().info("created pc");
    }

    public void setLatestCheckpoint(Checkpoint checkpoint){
        this.latestCheckpoint = checkpoint;
    }

    public Location getLastCheckpointLocation(){
        if(this.latestCheckpoint == null){
            return null;
        }
        return this.latestCheckpoint.getLocation();
    }

    public static void generateCheckpoints(List<Player> members){
        for(Player p : members){
            playerCheckpoints.add(new PlayerCheckpoint(p));
        }
    }

    public static void clearCheckpoints(){
        playerCheckpoints.clear();
    }

    public void respawnAtCheckpoint(){
        this.player.teleport(this.latestCheckpoint.getLocation());
    }

    public static List<PlayerCheckpoint> getPlayerCheckpoints(){
        return playerCheckpoints;
    }

    public static PlayerCheckpoint getPlayerCheckpointByPlayer(Player player){
        for(PlayerCheckpoint pc : playerCheckpoints){
            if(pc.player.equals(player)){
                return pc;
            }
        }
        return null;
    }

    public void checkpointCollision(Checkpoint checkpoint){
        Bukkit.getLogger().info("collision");
        if(this.latestCheckpoint == null){
            this.setLatestCheckpoint(checkpoint);
            this.newCheckpointReached();
            return;
        }
        if(this.latestCheckpoint.equals(checkpoint)){
            return;
        }else{
            this.setLatestCheckpoint(checkpoint);
            this.newCheckpointReached();
        }
    }

    private static ItemStack[] checkpointRewards = {

    };

    public void newCheckpointReached(){
        player.sendTitle(ChatColor.GREEN + "Checkpoint reached!", ChatColor.DARK_GREEN + "Good work!", 10, 70, 20);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10f, 1f);
    }
}
