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

package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects.Mailbox;

import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class Mailbox {
    /*
    A mailbox is an insatiable object which stores lost items from players with full inventories. Thic class
    contains the object constructor and methods.
     */

    public static List<Mailbox> allPlayerMailboxes;

    public Inventory inventory;
    public Player owner;

    public Mailbox(Player player){
        this.owner = player;
        this.inventory = Bukkit.createInventory(this.owner, 81, this.owner.getName() + "'s mailbox");
    }

    public Mailbox(UUID playerUUID, List<ItemStack> items){
        Player player = Bukkit.getPlayer(playerUUID);
        this.owner = player;
        this.inventory = Bukkit.createInventory(this.owner, 81, this.owner.getName() + "'s mailbox");
        for(int i = 0; i < items.size(); i++){
            this.inventory.addItem(items.get(i));
        }
    }

    public void addItem(ItemStack itemStack){
        if(this != null){
            this.inventory.addItem(itemStack);
            Send.player(this.owner, ChatColor.GREEN + "Your " + itemStack.displayName() + " was relocated to your mailbox. Access it after the event with /mailbox");
        }else{
            Send.player(this.owner, ChatColor.RED + "Your item was not safely moved. Please refer this error to your server administrator.");
        }
    }

    public static Mailbox getMailboxByPlayer(Player player){
        for(int i = 0; i < allPlayerMailboxes.size(); i++){
            if(allPlayerMailboxes.get(i).owner.equals(player)){
                return allPlayerMailboxes.get(i);
            }
        }
        return null;
    }

    public void giveItemToPlayer(ItemStack itemStack){
        if(this.inventory.contains(itemStack)){
            if(this.owner.getInventory().firstEmpty() != -1){
                this.inventory.remove(itemStack);
                this.owner.getInventory().setItem(this.owner.getInventory().firstEmpty(), itemStack);
                Send.player(this.owner, ChatColor.GREEN + "Successfully retrieved " + itemStack.displayName() + ".");
            }else{
                Send.player(this.owner, ChatColor.RED + "Your inventory is full!");
            }
        }else{
            Send.player(this.owner, ChatColor.RED + "Failed to transfer your item. Please notify your server adminstrator immediately.");
        }
    }

    public void openInventory(){
        this.owner.openInventory(this.inventory);
    }

    public static Boolean playerMailboxExists(Player player){
        for(int i = 0; i < allPlayerMailboxes.size(); i++){
            if(allPlayerMailboxes.get(i).owner.equals(player)) {
                return true;
            }
        }

        return false;

    }

    public static void addNewMailbox(Mailbox mailbox){
        allPlayerMailboxes.add(mailbox);
    }

    public static void setMailboxes(List<Mailbox> mailboxList){
        allPlayerMailboxes = mailboxList;
    }

    public static List<Mailbox> getMailboxes(){
        return allPlayerMailboxes;
    }

}
