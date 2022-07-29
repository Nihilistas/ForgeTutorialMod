package com.dohnpeckgames.tutorialmod.item.custom;

import com.dohnpeckgames.tutorialmod.TutorialMod;
import com.dohnpeckgames.tutorialmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class TutorialAdvancedItem extends Item
{
    public TutorialAdvancedItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(player.level.isClientSide) return true;

        entity.moveRelative(.5f, new Vec3(0,1,0));

        return true;
    }

    @SubscribeEvent
    public static void raiseBlockUpwardsWhenLeftClickedWithTutorialAdvancedItem(PlayerInteractEvent.LeftClickBlock event)
    {
        var world = event.getWorld();
        if(world.isClientSide) return;

        var itemStack = event.getItemStack();
        if(itemStack.getItem() == ModItems.TUTORIAL_ADVANCED_ITEM.get())
        {
            var oldPos = event.getPos();
            var newPos = oldPos.above();
            var clickedBlock = world.getBlockState(oldPos);
            world.destroyBlock(oldPos, false);
            world.setBlockAndUpdate(newPos, clickedBlock);
        }
    }
}
