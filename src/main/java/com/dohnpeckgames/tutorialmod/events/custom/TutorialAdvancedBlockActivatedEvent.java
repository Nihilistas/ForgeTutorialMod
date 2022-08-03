package com.dohnpeckgames.tutorialmod.events.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public class TutorialAdvancedBlockActivatedEvent extends Event
{
    public BlockPos pos;
    public Level level;

    public TutorialAdvancedBlockActivatedEvent(BlockPos pPos, Level pLevel)
    {
        pos = pPos; level = pLevel;
    }
}
