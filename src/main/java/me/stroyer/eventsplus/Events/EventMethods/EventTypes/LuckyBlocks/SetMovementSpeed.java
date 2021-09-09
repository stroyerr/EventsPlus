package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks;

import me.stroyer.eventsplus.Events.EventMethods.EventObjects.Event;

public class SetMovementSpeed {
    public static void walkSpeed(float speed){
        for(int i = 0; i <Event.activeEvent.members.size(); i++){
            Event.activeEvent.members.get(i).setWalkSpeed(speed);
        }
    }
}
