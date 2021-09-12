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

package me.stroyer.eventsplus.Commands;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("eventsplus") || command.getName().equalsIgnoreCase("ep") || alias.equalsIgnoreCase("ep")) { // checking if my command is the one i'm after

            List<String> autoCompletes = new ArrayList<>();

            if (args.length == 1) {

                for (int i = 0; i < EventsPlus.commands.length; i++) {

                    autoCompletes.add(EventsPlus.commands[i]);

                }

                return autoCompletes;

            }

            if (args.length == 2) {

                if(args[0].equalsIgnoreCase("arena")){
                    for (int i = 0; i < EventsPlus.arenaCommands.length; i++) {

                    autoCompletes.add(EventsPlus.arenaCommands[i]);

                }

                    if(args[0].equalsIgnoreCase("podium")){
                        for(int i = 0; i < EventsPlus.podiumCommands.length; i++){
                            autoCompletes.add(EventsPlus.podiumCommands[i]);
                        }
                    }

                    return autoCompletes;

                }


            }

        }

        return null;
    }
}
