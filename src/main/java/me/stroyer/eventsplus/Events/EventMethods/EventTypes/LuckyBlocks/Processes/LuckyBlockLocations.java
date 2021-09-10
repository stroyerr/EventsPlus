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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes;

import me.stroyer.eventsplus.Arena.Arena;
import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class LuckyBlockLocations {

    private static List<Location> luckyBlockLocations = new ArrayList<Location>();

    public static void generateLuckyBlockLocations(){
        for(int i = 0; i < Event.activeEvent.arena.locations.size(); i++){
            if(Event.activeEvent.arena.locations.get(i).location.getBlock().getType().equals(Material.GOLD_BLOCK)){
                luckyBlockLocations.add(Event.activeEvent.arena.locations.get(i).location);
            }
        }
    }

    public static List<Location> generateLuckyBlockLocationsForArena(Arena arena){
        List<Location> locationsToReturn = new ArrayList<Location>();
        for(int i = 0; i < arena.locations.size(); i++){
            if(arena.locations.get(i).location.getBlock().getType().equals(Material.GOLD_BLOCK)){
                locationsToReturn.add(arena.locations.get(i).location);
            }
        }

        return locationsToReturn;
    }

    public static void destroyerPlaceholderBlocks(){
        for(int i = 0; i < luckyBlockLocations.size(); i ++){
            luckyBlockLocations.get(i).getBlock().setType(Material.AIR);
        }
        hideSpawnBlock();
    }

    public static void clearLocations(){
        for(int i = 0; i < luckyBlockLocations.size(); i++){
            luckyBlockLocations.get(i).getBlock().setType(Material.GOLD_BLOCK);
        }
        luckyBlockLocations.clear();
        repairSpawnBlock();
    }

    public static List<Location> getLuckyBlockLocations(){
        return luckyBlockLocations;
    }

    private static Location spawnBlock;

    public static Location getSpawnLocation(Arena arena){
        for(int i = 0; i < arena.locations.size(); i++){
            if(arena.locations.get(i).location.getBlock().getType().equals(Material.SCAFFOLDING)){
                spawnBlock = arena.locations.get(i).location;
                return arena.locations.get(i).location;
            }
        }
        return null;
    }

    public static void hideSpawnBlock(){
        spawnBlock.getBlock().setType(Material.AIR);
    }

    public static void repairSpawnBlock(){
        spawnBlock.getBlock().setType(Material.SCAFFOLDING);
    }
}
