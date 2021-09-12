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

import me.stroyer.eventsplus.Methods.StaffOnline;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerLostItem {
    /*
    This class is a TEMPORARY solution to players losing items as a result of forced inventory items.
    The method (attemptSafeRelocation) is called to attempt to safely reolcate a players item before the item
    is overwritten. If this fails, the item will be added to the Player's PlayerLostItem object. At the end of the
    event, staff are sent a list of all players who lost items and what they lost. This is in the (notifyStaff) method.
     */

    public static List<PlayerLostItem> lostPlayerItemObjects = new ArrayList<PlayerLostItem>();

    public List<ItemStack> itemsLost;
    public Player player;

    public PlayerLostItem(Player player){
        this.player = player;
        this.itemsLost = new ArrayList<ItemStack>();
        lostPlayerItemObjects.add(this);
    }

    public static PlayerLostItem getPlayerLostItemObjectByPlayer(Player p){
        for(int i = 0; i < lostPlayerItemObjects.size(); i++){
            if(lostPlayerItemObjects.get(i).player.equals(p)){
                return lostPlayerItemObjects.get(i);
            }
        }
        return null;
    }

    public void addLostItem(ItemStack itemLost){
        this.itemsLost.add(itemLost);
    }

    public static void notifyStaff(){
        for(int i = 0; i < StaffOnline.get().size(); i++){

            for(int j = 0; j < lostPlayerItemObjects.size(); j++){

                List<String> itemsLost = new ArrayList<String>();

                if(lostPlayerItemObjects.get(j).itemsLost == null){
                    return;
                }

                if(lostPlayerItemObjects.get(j).itemsLost.size() == 0){
                    return;
                }

                if(lostPlayerItemObjects.get(j).itemsLost.get(0) == null){
                    return;
                }

                for(int m = 0; m < lostPlayerItemObjects.get(j).itemsLost.size(); m++){
                    itemsLost.add(lostPlayerItemObjects.get(j).itemsLost.get(m).displayName().toString() + " (x" + lostPlayerItemObjects.get(j).itemsLost.get(m).getAmount()+")");
                }

                Send.playerMultipleLines(StaffOnline.get().get(i), itemsLost, "Player " + lostPlayerItemObjects.get(j).player.getName() + " lost the following items (these should be returned to them): ");

            }
        }
    }
}
