package me.stroyer.eventsplus.Events.EventMethods.EventTypes.LuckyBlocks.LuckyBlockObjects;

import org.bukkit.block.Block;

public class ReturnBlockRound {
    public int round;
    public Block block;


    public static Block activeBlock;

    public ReturnBlockRound(int round, Block block){
        this.round = round;
        this.block = block;
    }

    public static void setActiveBlock(Block block){
        activeBlock = block;
    }
}
