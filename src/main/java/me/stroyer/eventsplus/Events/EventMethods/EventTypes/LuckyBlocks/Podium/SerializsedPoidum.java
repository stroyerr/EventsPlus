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

import org.bukkit.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SerializsedPoidum implements Serializable {

    public static SerializsedPoidum mainSerializedPodium;

    public List<SerializablePoidumLocation> serialLocations = new ArrayList<>();

    public List<Location> locations;

    public SerializsedPoidum(List<Location> locations){
        for(int i = 0; i < locations.size(); i++){
            serialLocations.add(new SerializablePoidumLocation(locations.get(i).getX(), locations.get(i).getY(), locations.get(i).getZ(), locations.get(i).getWorld().getUID()));
        }
    }

    public static void writeSerializedLocationsLocally(SerializsedPoidum mainSerializedPodium) throws IOException {
        File file = new File("./plugins/EventsPlus/podium.eventsplus");
        file.createNewFile();
        FileOutputStream fOut = new FileOutputStream("./plugins/EventsPlus/podium.eventsplus");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(mainSerializedPodium);
    }

    public static SerializsedPoidum loadSerializedLocationsLocally() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/EventsPlus/podium.eventsplus");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        SerializsedPoidum loadedSerializedPodium = (SerializsedPoidum) oIn.readObject();
        return loadedSerializedPodium;
    }

}
