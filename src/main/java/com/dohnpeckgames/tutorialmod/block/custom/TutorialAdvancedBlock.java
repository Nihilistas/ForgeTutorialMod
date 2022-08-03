package com.dohnpeckgames.tutorialmod.block.custom;

import com.dohnpeckgames.tutorialmod.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TutorialAdvancedBlock extends Block
{
    private static final AABB TOUCH_AABB = new AABB(0.0D, 0.0D, 0.0D, 1D, 0.25D, 1D);

    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ACTIVATED);
    }

    public TutorialAdvancedBlock(Properties pProperties)
    {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, false));
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving)
    {
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
        pLevel.scheduleTick(pPos, this, 0);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(ACTIVATED) ? 10 : 0;
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand)
    {
        var isLit = pState.getValue(ACTIVATED);
        if(isLit)
        {
            checkPressed(null, pLevel, pPos, pState, true);
        }
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide)
        {
            var isLit = pState.getValue(ACTIVATED);
            if (!isLit)
            {
                this.checkPressed(pEntity, pLevel, pPos, pState, false);
            }
        }
    }

    private void checkPressed(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState, boolean wasOn) {
        boolean isOn = getIsOn(pLevel, pPos.above());
        if (wasOn != isOn)
        {
            BlockState newState = pState.setValue(ACTIVATED, isOn);
            pLevel.setBlockAndUpdate(pPos, newState);
        }

        if (!isOn && wasOn)
        {
            // stepped off
        }
        else if (isOn && !wasOn)
        {
            // stepped on
            ServerLevel sLevel = (ServerLevel) pLevel;
            for(int i=0; i<360; i+=10)
            {
                sLevel.sendParticles(ModParticles.TUTORIAL_ADVANCED_BLOCK_PARTICLES.get(), pPos.getX() + .5d,
                        pPos.getY() + 1d, pPos.getZ() + .5d, 1, Math.cos(i),
                        0.1d, Math.sin(i), 0.8f);
            }
        }

        if (isOn)
        {
            pLevel.scheduleTick(pPos, this, 10);
        }
    }

    private boolean getIsOn(Level pLevel, BlockPos pPos)
    {
        AABB aabb = TOUCH_AABB.move(pPos);
        List<? extends Entity> list = pLevel.getEntities(null, aabb);

        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return true;
                }
            }
        }

        return false;
    }
}
