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

import me.stroyer.eventsplus.Arena.ArenaStorage.ArenaSerialization.SerializableArena;
import me.stroyer.eventsplus.PlayerInteraction.Send;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Podium {

    public static Podium podium;

    public List<Location> locations;

    public PodiumKeyBlocks keyBlocks;

    public Podium(List<Location> locations){
        this.locations = locations;
        podium = this;
        this.keyBlocks = new PodiumKeyBlocks();
        if(this.keyBlocks.first == null || this.keyBlocks.second == null || this.keyBlocks.third == null || this.keyBlocks.spawn == null){
            Send.allPlayer("Please alert the server administrators that they have not correctly configured the event podium. Events will likely fail or cause issues if this is not resolved.");
        }
    }

    public static void attemptLoadLocal() throws IOException, ClassNotFoundException {
        podium = deserializePodium();
    }

    public static Podium deserializePodium() throws IOException, ClassNotFoundException {
        SerializsedPoidum sp = SerializsedPoidum.loadSerializedLocationsLocally();
        List<Location> newLocations = new ArrayList<Location>();
        for(int i = 0; i < sp.serialLocations.size(); i++){
            Location currentLocation = new Location(Bukkit.getWorld(sp.serialLocations.get(i).worldUUID), sp.serialLocations.get(i).x, sp.serialLocations.get(i).y, sp.serialLocations.get(i).z);
            newLocations.add(currentLocation);
        }
        return new Podium(newLocations);
    }

    public static void attemptSaveLocal() throws IOException {
        SerializsedPoidum sp = new SerializsedPoidum(podium.locations);
        SerializsedPoidum.writeSerializedLocationsLocally(sp);
    }

    public static List<Location> getLocations(Location pos1, Location pos2){
        List<Location> gottenLocations = new ArrayList<Location>();
        double maxX = Math.max(pos1.getX(), pos2.getX());
        double minX = Math.min(pos1.getX(), pos2.getX());

        double maxZ = Math.max(pos1.getZ(), pos2.getZ());
        double minZ = Math.min(pos1.getZ(), pos2.getZ());

        for(int i = (int) Math.round(minX); i <= (int) Math.round(maxX); i++){
            for(int j = (int) Math.round(minZ); j <= (int) Math.round(maxZ); j++){
                for(int y = 0; y <= 256; y++){
                    gottenLocations.add(new Location(pos1.getWorld(), i, y, j));
                }
            }
        }
        return gottenLocations;
    }

}
