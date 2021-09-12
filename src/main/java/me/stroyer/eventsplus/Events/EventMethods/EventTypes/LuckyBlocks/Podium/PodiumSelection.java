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

import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.UI.Methods.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PodiumSelection {
    private static Location pos1;
    private static Location pos2;
    public static ItemStack selectionWand = NewItem.createGuiItem(Material.DIAMOND_AXE, ChatColor.AQUA + "Podium Selection Tool");

    public static void giveSelectionWant(Player p){
        p.getInventory().setItem(p.getInventory().getHeldItemSlot(), selectionWand);
    }

    public static void playerClickEvent(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            pos1 = e.getClickedBlock().getLocation();
            Send.player(e.getPlayer(), ChatColor.LIGHT_PURPLE + "Pos1 set.");
        }

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            pos2 = e.getClickedBlock().getLocation();
            Send.player(e.getPlayer(), ChatColor.LIGHT_PURPLE + "Pos2 set.");
        }
    }

    public static void createPodium(Player p){
        if(pos1 != null && pos2!= null){
            List<Location> locations = Podium.getLocations(pos1, pos2);
            Podium podium = new Podium(locations);
            Send.player(p, "Created new podium.");
        }else{
            Send.player(p, ChatColor.RED + "Failed to create podium. Make sure you select two points with the podium selection wand.");
            return;
        }
    }
}