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

package me.stroyer.eventsplus.Listeners;

import me.stroyer.eventsplus.VotersEvent.EventHandling.WipeoutEvent;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.Checkpoint;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.PlayerCheckpoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveToBlock implements Listener {
    @EventHandler
    public static void playerMovementEvent(PlayerMoveEvent e){
        if(WipeoutEvent.activeEvent == null){return;}
        PlayerCheckpoint pc = PlayerCheckpoint.getPlayerCheckpointByPlayer(e.getPlayer());

        double x = Math.round(e.getPlayer().getLocation().getX());
        double y = Math.round(e.getPlayer().getLocation().getY());
        double z = Math.round(e.getPlayer().getLocation().getZ());

        for(Checkpoint checkpoint : Checkpoint.getCheckpoints()){
            if(new Location(e.getPlayer().getLocation().getWorld(), x, y, z).equals(checkpoint.getLocation())){
                PlayerCheckpoint.getPlayerCheckpointByPlayer(e.getPlayer()).checkpointCollision(checkpoint);
                return;
            }
        }
    }
}
