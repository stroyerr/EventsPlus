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
import me.stroyer.eventsplus.VotersEvent.Util.VoteEventAnnouncer;
import org.bukkit.ChatColor;

public class PreVoteEvent {

    public static void initialise(){
        Send.allPlayer("Voters only event starting soon! Only players who have voted in the past 24 hours will be able to join this event! The amount of current invited voters: " + ChatColor.YELLOW + PlayersVoted.playersVotedInLastDay().size());
        Send.allPlayer("Check if you have been registered for this event with " + ChatColor.YELLOW + "/ep check");
        VoteEventAnnouncer.startTimer();
    }

}