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

import me.stroyer.eventsplus.VotersEvent.EventHandling.WipeoutEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Checkpoint {

    private static List<Checkpoint> checkpoints = new ArrayList<>();

    private Location location;

    public Checkpoint(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    public void addToCheckpointsList(){
        checkpoints.add(this);
    }

    public static List<Checkpoint> getCheckpoints(){
        return checkpoints;
    }

    public static void generateCheckpoints(){
        for(Block block : WipeoutEvent.activeEvent.getArena().blocksOfTypeInArena(Material.LIME_CARPET)){
            checkpoints.add(new Checkpoint(new Location(block.getLocation().getWorld(), block.getLocation().getX() + 1, block.getLocation().getY(), block.getLocation().getZ() + 1)));
            Bukkit.getLogger().info("Checkpoint Location; " + block.getLocation().getX() + ", " + block.getLocation().getY() + ", " + block.getLocation().getZ());
        }
    }

    public static Boolean checkpointContainsLocation(Location l){
        for(Checkpoint cp : checkpoints){
            if(cp.getLocation().equals(l)){
                return true;
            }
        }
        return false;
    }

    public static Checkpoint getCheckpointByLocation(Location location){
        for(Checkpoint cp : checkpoints){
            if(location.equals(cp.getLocation())){
                return cp;
            }
        }
        return null;
    }

    public static void clearCheckpoints(){
        checkpoints.clear();
    }
}
