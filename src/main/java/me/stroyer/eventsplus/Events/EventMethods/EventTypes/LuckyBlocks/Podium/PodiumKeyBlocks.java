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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Podium;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.PlayerScore.PlayerScore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class PodiumKeyBlocks {
    /*
    This class finds and generates podium key blocks.
    Key:
    Spawn || wet sponge
    3rd || orange shulker
    2nd || light grey shulker
    1st || light blue shulker
     */

    public Block spawn;
    public Block third;
    public Block second;
    public Block first;

    public PodiumKeyBlocks(){
        this.spawn = findSpawnBlock();
        this.third = findThirdBlock();
        this.second = findSecondBlock();
        this.first = findFirstBlock();
    }

    public static Block findSpawnBlock(){
        for(int i = 0; i < Podium.podium.locations.size(); i++){
            if(Podium.podium.locations.get(i).getBlock().getType().equals(Material.WET_SPONGE)){
                return Podium.podium.locations.get(i).getBlock();
            }
        }
        return null;
    }

    public static Block findThirdBlock(){
        for(int i = 0; i < Podium.podium.locations.size(); i++){
            if(Podium.podium.locations.get(i).getBlock().getType().equals(Material.ORANGE_SHULKER_BOX)){
                return Podium.podium.locations.get(i).getBlock();
            }
        }
        return null;
    }

    public static Block findSecondBlock(){
        for(int i = 0; i < Podium.podium.locations.size(); i++){
            if(Podium.podium.locations.get(i).getBlock().getType().equals(Material.LIGHT_GRAY_SHULKER_BOX)){
                return Podium.podium.locations.get(i).getBlock();
            }
        }
        return null;
    }

    public static Block findFirstBlock(){
        for(int i = 0; i < Podium.podium.locations.size(); i++){
            if(Podium.podium.locations.get(i).getBlock().getType().equals(Material.LIGHT_BLUE_SHULKER_BOX)){
                return Podium.podium.locations.get(i).getBlock();
            }
        }
        return null;
    }

    public void destroyPlaceholders(){
        this.spawn.setType(Material.AIR);
        this.first.setType(Material.AIR);
        this.second.setType(Material.AIR);
        this.third.setType(Material.AIR);
    }

    public void rebuildPlaceholders(){
        this.spawn.setType(Material.WET_SPONGE);
        this.first.setType(Material.LIGHT_BLUE_SHULKER_BOX);
        this.second.setType(Material.LIGHT_GRAY_SHULKER_BOX);
        this.third.setType(Material.ORANGE_SHULKER_BOX);
    }

    public void tpAllToPodium(){
        for (int i = 0; i < Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).teleport(this.spawn.getLocation());
        }
    }

    public void tpTopPlayers() {
        List<PlayerScore> playerScores = Event.activeEvent.rankingScoreboard.playerScorelist;
        Player first = Bukkit.getPlayer(Event.activeEvent.rankingScoreboard.firstScoreName);
        first.teleport(this.first.getLocation());
        Player second = Bukkit.getPlayer(Event.activeEvent.rankingScoreboard.secondScoreName);
        second.teleport(this.second.getLocation());
        Player third = Bukkit.getPlayer(Event.activeEvent.rankingScoreboard.thirdScoreName);
        third.teleport(this.third.getLocation());
    }
}
