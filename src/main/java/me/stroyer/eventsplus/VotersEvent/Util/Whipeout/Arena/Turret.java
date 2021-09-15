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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Turret {

    public static List<Turret> turretPreChange = new ArrayList<>();

    private Location location;
    private Directional direction;
    private Vector facingVector;
    private Dispenser dispenser;
    private Block block;
    private BlockData dispenserData;
    private BlockFace facing;

    public Turret(Block turretBlock){
        this.location = turretBlock.getLocation();
        this.block = this.location.getBlock();
        this.dispenserData = this.block.getBlockData();
        this.direction = (Directional) turretBlock.getBlockData();
        this.facingVector = this.direction.getFacing().getDirection();
        if(this.block instanceof Dispenser){
            Dispenser d = (Dispenser) this.block.getState();
            this.dispenser = d;
            this.facing = this.dispenser.getFacing();
        }else{
            Bukkit.getLogger().info("Failed to cast.");
        }


        turretPreChange.add(this);

    }

    private static BukkitRunnable particleTimer = new BukkitRunnable() {
        @Override
        public void run() {
            for(Turret t : turretPreChange){
                t.getLocation().getWorld().spawnParticle(Particle.PORTAL, t.getLocation(), 50);
            }
        }
    };

    public static void startParticleEffects(){
        particleTimer.runTaskTimer(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 10L);
    }

    public static void stopParticleEffects(){
        particleTimer.cancel();
    }

    public Block getBlock(){return this.block;}

    public BlockFace getFacing(){return this.facing;}

    public Vector getFacingVector(){
        return this.facingVector;
    }

    public Location getLocation(){
        return this.location;
    }

    public Directional getDirection(){
        return this.direction;
    }

    public BlockData getDispenserData(){return this.dispenserData;}

    public static void clearTurretBlockSaveData(){
        turretPreChange.clear();
    }
}
