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
import me.stroyer.eventsplus.Methods.PlaySound;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;

public class Music {

    private static Boolean musicPlaying = false;
    private static Boolean finishMusic = false;
    private static final Long songDuration = 3720L;
    private static int timeElapsed = 0;
    private static final BukkitRunnable musicChecker = new BukkitRunnable() {
        @Override
        public void run() {
            musicPlaying = false;

            if(finishMusic){
                this.cancel();
                eventShouldBeCancelled();
            }

//            if(timeElapsed % 186 == 0){
//                if(musicPlaying == true){
//                    this.cancel();
//                    return;
//                }
//                updateMusic(musicPlaying);
//            }

            timeElapsed ++;

        }
    };

    private static void eventShouldBeCancelled(){
        stopSong();
    }

    private static void stopSong(){
    }

    private static void updateMusic(Boolean isPlaying){
        if(!isPlaying){
            startSongAgain();
        }
    }

    private static void startSongAgain(){
        startSong();
    }

    public static void cancelSong(){
        if(musicChecker == null){
            return;
        }
        musicChecker.cancel();
        musicPlaying = false;
    }

    public static void startSong(){
        PlaySound.allCatSong();
    }

    public static void initialiseSong(){
        if(musicChecker != null){return;}
        musicChecker.runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("EventsPlus"), 0L, 20L);
    }
}
