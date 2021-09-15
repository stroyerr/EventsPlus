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

import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
import me.stroyer.eventsplus.VotersEvent.EventHandling.WipeoutEvent;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.TurretHandler;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArrowEvent implements Listener {
    @EventHandler
    public static void arrowCollision(ProjectileCollideEvent e){
        if(WipeoutEvent.activeEvent == null){
            return;
        }
        if(e.getEntity() instanceof Arrow){
            e.setCancelled(true);
            e.getEntity().remove();
        }
        if(WipeoutEvent.activeEvent.getMembers().contains(e.getCollidedWith())){
            e.setCancelled(true);
            TurretHandler.playerCollision((Player) e.getCollidedWith());
            e.getEntity().remove();
            e.getEntity().getLocation().getWorld().spawnParticle(Particle.SMALL_FLAME, e.getEntity().getLocation(), 500);
        }
    }
}
