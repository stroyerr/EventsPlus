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
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WipeoutArena {

    public static List<WipeoutArena> wipeOutArenas = new ArrayList<>();

    public Boolean arenaContainsLocation(Location location){
        if(location.getX() >= this.minX && location.getX() <= this.maxX){
            if(location.getZ() >= this.minZ && location.getZ() <= this.maxZ){
                return true;
            }
        }
        return false;
    }

    public List<Block> blocksOfTypeInArena(Material materialToSearch){
        List<Block> blocksFound = new ArrayList<Block>();
        for(int x = (int) Math.round(this.minX); x <= (int) Math.round(this.maxX); x++){
            for(int y = 0; y <= 256; y++){
                for(int z = (int) Math.round(this.minZ); z <= (int) Math.round(this.maxZ); z++){
                    if(this.world.getBlockAt(x, y, z).getType().equals(materialToSearch)){
                        blocksFound.add(this.world.getBlockAt(x, y, z));
                    }
                }
            }
        }

        return blocksFound;

    }

    public void generateTurretBlocks(){
        this.turretBlocks = blocksOfTypeInArena(Material.DISPENSER);
    }

    private String name;
    public double minX;
    public double maxX;
    public double minZ;
    public double maxZ;
    private World world;
    private List<Block> turretBlocks = new ArrayList<>();

    public WipeoutArena(String name, Location pos1, Location pos2, World world){
        this.maxX = Math.max(pos1.getX(), pos2.getX());
        this.minX = Math.min(pos1.getX(), pos2.getX());
        this.minZ = Math.min(pos1.getZ(), pos2.getZ());
        this.maxZ = Math.max(pos1.getZ(), pos2.getZ());
        this.name = name;
        this.world = world;
        wipeOutArenas.add(this);
    }

    public WipeoutArena(String name, double minX, double maxX, double minZ, double maxZ, UUID worldUUID){
        this.world = Bukkit.getWorld(worldUUID);
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.name = name;
        wipeOutArenas.add(this);
    }

    public String getName(){
        return this.name;
    }

    public World getWorld(){
        return this.world;
    }

    public void deletePlaceholders(){
        this.turretBlocks = WipeoutEvent.activeEvent.getTurretBlocks();
        for(Block block : this.turretBlocks){
            block.setType(Material.AIR);
        }
    }

    public void buildPlaceholders() {
        for (Block block : this.turretBlocks) {
            block.setType(Material.DISPENSER);
        }
    }

}
