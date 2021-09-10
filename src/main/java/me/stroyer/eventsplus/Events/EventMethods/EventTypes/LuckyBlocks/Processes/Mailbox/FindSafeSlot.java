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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.Processes.Mailbox;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FindSafeSlot {

    /*
    This class handles a number of proccesses.
    The class tries to find a "safe spot" such that the indicated inventory slot can be used for a different itemstack
    (the indicated slot is an input). If the inventory finds a safe slot, it will return the integer of
    that slot. If it doesn't return a safe slot, it returns -1. If it fails to find a spot and returns -1, it
    automatically calls a method in a player's mailbox to send that item to the mailbox. This is also where
    a message is sent to the player.
     */

    public static void moveToSafeSpot(int desiredIndex, Player player, ItemStack item){
        if(player.getInventory().getItem(desiredIndex) == null){
            player.getInventory().setItem(desiredIndex, item);
            return;
        }
        for(int i = 0; i < 35; i++){
            if(player.getInventory().getItem(i) == null){
                player.getInventory().setItem(i, player.getInventory().getItem(desiredIndex));
                player.getInventory().setItem(desiredIndex, item);
                return;
            }
        }
        slotOverrideForced(player, player.getInventory().getItem(desiredIndex));
        return;
    }

    private static void slotOverrideForced(Player player, ItemStack itemStack){
        Send.player(player, "You lost an item (" + itemStack.getType().name() + ") because your inventory is full. You can access this in your /mailbox after the round.");
        for(int i = 0; i < Mailbox.playerMailboxes.size(); i++){
            if(Mailbox.playerMailboxes.get(i).player.equals(player)){
                Mailbox.playerMailboxes.get(i).addItem(itemStack);
                player.getInventory().removeItem(itemStack);
            }
        }
    }
}
