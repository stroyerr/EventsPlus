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

import me.stroyer.eventsplus.VotersEvent.Util.Whipeout.Arena.WipeoutArena;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.UUID;

public class SerializableWipeoutLocation implements Serializable {

    public double minX;
    public double maxX;
    public double minZ;
    public double maxZ;
    public UUID worldUUID;

    public SerializableWipeoutLocation(WipeoutArena arena){
        this.minX = arena.minX;
        this.maxX = arena.maxX;
        this.minZ = arena.minZ;
        this.maxZ = arena.maxZ;
        this.worldUUID = arena.getWorld().getUID();
    }
}
