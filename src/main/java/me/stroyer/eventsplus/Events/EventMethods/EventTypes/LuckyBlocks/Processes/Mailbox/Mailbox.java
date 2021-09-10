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

/*
At this time mailboxes are unfinished as they are low priority and serialization would require manual
serialization of certain members of mailboxes.
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mailbox implements Serializable {

    /*
    Mailbox object class. Handles Mailbox methods. Only internal methods should be called within this class.
    Mailboxes should be initialised on a player join.
     */

    public static List<Mailbox> playerMailboxes = new ArrayList<Mailbox>();

    public List<ItemStack> itemsPending;
    public Player player;
    public transient Inventory inventory;

    public Mailbox(Player player){
        this.player = player;
        this.inventory = Bukkit.createInventory(player, 81, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mailbox");
        this.itemsPending = new ArrayList<ItemStack>();
    }

    public void addItem(ItemStack itemStack){
        this.itemsPending.add(itemStack);
        this.inventory.addItem(itemStack);
    }

    public void openPlayerGUI(){
        this.player.openInventory(this.inventory);
    }

    public void removeItemFromInventory(ItemStack itemStack){
        this.inventory.remove(itemStack);
        this.itemsPending.remove(itemStack);
    }

    public void playerTakeItemStack(ItemStack itemStack){
        this.removeItemFromInventory(itemStack);
        this.player.getInventory().addItem(itemStack);
    }


    public static void loadedMailboxes(List<Mailbox> loadedMailboxList){
        playerMailboxes = loadedMailboxList;
    }

    public static List<Mailbox> savedMailedBoxes(){
        return playerMailboxes;
    }
}
