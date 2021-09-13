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

package me.stroyer.eventsplus.Events.EventMethods;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox.Mailbox;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayersVisibility {

    private static List<Player> playersHiddenEnabled = new ArrayList<Player>();
    private static List<Player> playersShownEnabled = new ArrayList<Player>();

    public static void toggleAllVisible(Player p){
        if(playersShownEnabled.contains(p)){
            for(int i = 0; i < Event.activeEvent.members.size(); i++){
                p.hidePlayer(Event.activeEvent.members.get(i));
                playersHiddenEnabled.add(p);
                playersShownEnabled.remove(p);
                Send.player(p, "All other players are now " + ChatColor.GREEN + "hidden");
            }
            return;
        }

        if(playersHiddenEnabled.contains(p)){
            for(int i = 0; i < Event.activeEvent.members.size(); i ++){
                p.showPlayer(Event.activeEvent.members.get(i));
                playersShownEnabled.add(p);
                playersHiddenEnabled.remove(p);
                Send.player(p, "All other players are now " + ChatColor.GREEN + "shown");
            }
            return;
        }

        playersHiddenEnabled.add(p);
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            p.hidePlayer(Event.activeEvent.members.get(i));
            playersHiddenEnabled.add(p);
            Send.player(p, "All other players are now " + ChatColor.GREEN + "hidden");
        }

    }

    public static ItemStack toggleItem;

    public static ItemStack getToggleItem(){
        toggleItem = NewItem.createGuiItem(Material.STICK, ChatColor.LIGHT_PURPLE + "Show / Hide Players");
        return toggleItem;
    }

    public static void giveAllToggleItem(){
        for(int i = 0; i < Event.activeEvent.members.size(); i++){
            Player p = Event.activeEvent.members.get(i);
            if(p.getInventory().getItem(4) != null){
                Mailbox.getMailboxByPlayer(p).addItem(p.getInventory().getItem(4));
            }
            PlayerLostItem.getPlayerLostItemObjectByPlayer(p).addLostItem(p.getInventory().getItem(4));
            p.getInventory().setItem(4, getToggleItem());
        }
    }
}
