package com.dohnpeckgames.tutorialmod.item;

import com.dohnpeckgames.tutorialmod.TutorialMod;
import com.dohnpeckgames.tutorialmod.item.custom.TutorialAdvancedItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> TUTORIAL_ITEM = ITEMS.register("tutorialitem", ()->new Item(
            new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_MOD_TAB)));

    public static final RegistryObject<Item> TUTORIAL_ADVANCED_ITEM = ITEMS.register("tutorialadvanceditem",
            ()->new TutorialAdvancedItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_MOD_TAB)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
