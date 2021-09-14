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

package me.stroyer.eventsplus.VotersEvent.EventHandling;


import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.VotersEvent.Util.PlayersVoted;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.WipeoutArena;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class VotingEvent {

    /*
    This class should be the central control unit for all Voting Event actions. This class should contain
    all methods and orders of the event. No other thread or class should become the primary 'storyboard' of
    the event. Other classes may be referenced to get or set properties. The object VotingEvent has central and
    only important properties. All properties are to be attained via the object call methods, not directly (hence the
    private variables).
     */

    public static VotingEvent activeEvent = null;

    private List<Player> members;
    private Boolean eventActive;
    private Player host;
    private List<Block> turretBlocks;

    public VotingEvent(Player host){
        this.members = PlayersVoted.playersVotedInLastDay();
        this.eventActive = true;
        this.host = host;
    }

    public VotingEvent(){
        this.members = PlayersVoted.playersVotedInLastDay();
        this.eventActive = true;
    }

    public static void initialise(Player host, WipeoutArena arena){
        if(!runInitialiseChecks(arena)){
            Send.player(host, ChatColor.RED + "Something went wrong, ensure you have properly configured and setup the event.");
            return;
        }

        Send.player(host, ChatColor.GREEN + "Wipeout Arena configured correctly. Initiating now.");
        activeEvent = new VotingEvent(host);

    }

    public static Boolean runInitialiseChecks(WipeoutArena arena){
        if(arena.blocksOfTypeInArena(Material.DISPENSER).size() > 0){
            if(arena.blocksOfTypeInArena(Material.SCAFFOLDING).size() == 1){
                return true;
            }
            return false;
        }else{
            return false;
        }
    }
}
