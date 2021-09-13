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

import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Mailbox;
import me.stroyer.eventsplus.Main;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e){

        if(Mailbox.playerMailboxExists(e.getPlayer())){
            return;
        }else{
            Mailbox.addNewMailbox(new Mailbox(e.getPlayer()));
        }

        if(e.getPlayer().hasPermission("eventsplus.staff")){
            if(Main.versionInt == 0){
                Send.player(e.getPlayer(), ChatColor.GREEN  + "Stroyer_'s EventsPlus is up to date.");
            }else if(Main.versionInt == -1){
                Send.player(e.getPlayer(), ChatColor.RED  + "Stroyer_'s EventsPlus is not up to date. Please update as this version may contain bugs and glitches! " + ChatColor.GOLD + "https://www.spigotmc.org/resources/eventsplus.96159/");
            }else if(Main.versionInt == 1){
                Send.player(e.getPlayer(),ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "You are running a dev build of Stroyer_'s EventsPlus. This build contains features not yet finalised and may contain bugs.");
            }
        }
    }
}
