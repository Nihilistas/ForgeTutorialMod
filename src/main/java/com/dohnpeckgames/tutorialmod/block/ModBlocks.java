package com.dohnpeckgames.tutorialmod.block;

import com.dohnpeckgames.tutorialmod.TutorialMod;
import com.dohnpeckgames.tutorialmod.block.custom.TutorialAdvancedBlock;
import com.dohnpeckgames.tutorialmod.item.ModCreativeModeTab;
import com.dohnpeckgames.tutorialmod.item.ModItems;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> TUTORIAL_BLOCK = registerBlock("tutorialblock", ()->new Block(
            BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()),
            ModCreativeModeTab.TUTORIAL_MOD_TAB);

    public static final RegistryObject<Block> TUTORIAL_BLOCK2 = registerBlock("tutorialblock2", ()->new Block(
            BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()),
            ModCreativeModeTab.TUTORIAL_MOD_TAB);

    public static final RegistryObject<Block> TUTORIAL_ADVANCED_BLOCK = registerBlock("tutorialadvancedblock",
            ()->new TutorialAdvancedBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_MOD_TAB,
            "tooltip.tutorialmod.tutorialadvancedblock");


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        return registerBlock(name, block, tab, null);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab,
                                                                     String tooltipKey)
    {
        var result = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties().tab(tab)) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                if(tooltipKey != null)
                    pTooltip.add(new TranslatableComponent(tooltipKey));
                else
                    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
            }
        });
        return result;
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
