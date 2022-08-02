package com.dohnpeckgames.tutorialmod.block;

import com.dohnpeckgames.tutorialmod.TutorialMod;
import com.dohnpeckgames.tutorialmod.block.custom.TutorialAdvancedBlock;
import com.dohnpeckgames.tutorialmod.item.ModCreativeModeTab;
import com.dohnpeckgames.tutorialmod.item.ModItems;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_MOD_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        var result = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(result.get(), new Item.Properties().tab(tab)));
        return result;
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
