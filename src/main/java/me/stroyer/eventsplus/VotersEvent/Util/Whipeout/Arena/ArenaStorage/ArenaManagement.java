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

package me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.ArenaStorage;

import me.stroyer.eventsplus.PlayerInteraction.Send;
import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.WipeoutArena;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.List;

public class ArenaManagement {

    private static Location pos1 = null;
    private static Location pos2 = null;

    public static void setPos1(Location l, Player p){
        pos1 = l;
        Send.player(p, ChatColor.LIGHT_PURPLE + "Pos1 set.");
    }

    public static void setPos2(Location l, Player p){
        pos2 = l;
        Send.player(p, ChatColor.LIGHT_PURPLE + "Pos2 set.");
    }

    public static void attemptCreate(String name, Player host){
        for(WipeoutArena arena : WipeoutArena.wipeOutArenas){
            if(arena.getName().equalsIgnoreCase(name)){
                Send.player(host, ChatColor.RED + "A wipeout arena with that name already exists.");
                return;
            }
        }
        if(pos1 == null || pos2 == null){
            Send.player(host, ChatColor.RED + "Please select your arena.");
            return;
        }

        saveWipeoutArena(name, pos1, pos2, host);
        Send.player(host, ChatColor.GREEN + "Successfuly saved arena.");

    }

    public static void saveWipeoutArena(String name, Location pos1, Location pos2, Player host){
        WipeoutArena.wipeOutArenas.add(new WipeoutArena(name, pos1, pos2, pos1.getWorld()));
    }

    public static void saveArenasLocally() throws IOException {
        File file = new File("./plugins/EventsPlus/wipeoutarenas.eventsplus");
        file.createNewFile();
        FileOutputStream fOut = new FileOutputStream("./plugins/EventsPlus/wipeoutarenas.eventsplus");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);

        List<SerializableWipeoutArena> serializableWipeoutArenaList = SerializableWipeoutArena.getSerializedArenas(WipeoutArena.wipeOutArenas);

        oOut.writeObject(serializableWipeoutArenaList);

    }

    public static void loadArenasLocally() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/EventsPlus/wipeoutarenas.eventsplus");
        ObjectInputStream oIn = new ObjectInputStream(fIn);

        WipeoutArena.wipeOutArenas = SerializableWipeoutArena.getDeserializedArenas((List<SerializableWipeoutArena>) oIn.readObject());
    }

}
