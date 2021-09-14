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

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SerializableMailbox implements Serializable {
    private UUID playerUUID;
    private List<Map<String, Object>> serializableMailboxItemsList;

    public SerializableMailbox(Mailbox mailbox){

        List<Map<String, Object>> serializedObjects = new ArrayList<>();
        byte[] serializedBytes;
        List<byte[]> objectByteArrays = new ArrayList<>();

        this.playerUUID = mailbox.ownerUUID;
        for(int i = 0; i < mailbox.inventory.getSize(); i++) {
            if(serializedObjects.get(i) == null){continue;}
                serializedObjects.add(mailbox.inventory.getItem(i).serialize());

            //if(mailbox.inventory.getItem(i) != null){
//                try{
//                    checkSerializable(mailbox.inv//entory.getItem(i));
//                    serializedObjects.add(mailbox.inventory.getItem(i).serialize());
//                }catch (NotSerializableException e){
//                    Bukkit.getLogger().info("Failed to save an unserializable itemstack.");
//                    continue;
//                } catch (RuntimeException e){
//
//                } catch (Exception e) {
//                    Bukkit.getLogger().info("Point 2");
//                } finally{
//                    Bukkit.getLogger().info("Failed to save a single itemstack.");
//                    continue;
//                }
            //}
        }

        this.serializableMailboxItemsList = serializedObjects;
    }

    public static void checkSerializable(ItemStack is) throws NotSerializableException, NullPointerException {
//        try{
        if(is != null){
            is.serialize();
        }
//        }catch(IOException e){
//
//        }
    }

    public static List<SerializableMailbox> getSerializedMailboxes(){
        List<Mailbox> mailboxes = Mailbox.getMailboxes();
        List<SerializableMailbox> convertedList = new ArrayList<>();
        for(int i = 0; i < mailboxes.size(); i++){
            convertedList.add(new SerializableMailbox(mailboxes.get(i)));
        }
        return convertedList;
    }

    public static void saveSerializedMailboxes(List<SerializableMailbox> mailboxesToSerialize) throws IOException {
        File file = new File("./plugins/EventsPlus/mailboxData.eventsplus");
        file.createNewFile();
        FileOutputStream fOut = new FileOutputStream("./plugins/EventsPlus/mailboxData.eventsplus");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(mailboxesToSerialize);
    }

    public static List<SerializableMailbox> mailboxesFromLocal() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/EventsPlus/mailboxData.eventsplus");
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        return (List<SerializableMailbox>) oIn.readObject();
    }

    public static List<Mailbox> deserializeMailboxes() throws IOException, ClassNotFoundException {
        List<SerializableMailbox> unconverted = mailboxesFromLocal();
        List<Mailbox> convertedList = new ArrayList<>();
        for(int i = 0; i < unconverted.size(); i++){
            List<ItemStack> items = new ArrayList<>();
            UUID playerUUID = unconverted.get(i).playerUUID;
            for(int j = 0; j < unconverted.get(i).serializableMailboxItemsList.size(); j++){
                items.add(ItemStack.deserialize(unconverted.get(i).serializableMailboxItemsList.get(j)));
            }
            convertedList.add(new Mailbox(playerUUID, items));
        }
        return convertedList;
    }

    public static void saveMailboxes() throws IOException {
        saveSerializedMailboxes(getSerializedMailboxes());
    }

    public static void loadMailboxes() throws IOException, ClassNotFoundException {
        List<Mailbox> mailboxes = deserializeMailboxes();
        Mailbox.setMailboxes(mailboxes);
    }
}
