package com.dohnpeckgames.tutorialmod.util;

import com.dohnpeckgames.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags
{
    public static class Blocks
    {
        public static final Tags.IOptionalNamedTag<Block> TUTORIAL_ADVANCED_BLOCK_BLACKLISTED =
                tag("tutorialadvancedblockblacklistedblocks", false);

        private static Tags.IOptionalNamedTag<Block> tag(String name, boolean isForgeTag)
        {
            return BlockTags.createOptional(new ResourceLocation(isForgeTag ? "forge" : TutorialMod.MOD_ID, name));
        }
    }

    public static class Items
    {
        public static final Tags.IOptionalNamedTag<Item> TUTORIAL_ADVANCED_BLOCK_BLACKLISTED =
                tag("tutorialadvancedblockblacklisteditems", false);

        private static Tags.IOptionalNamedTag<Item> tag(String name, boolean isForgeTag)
        {
            return ItemTags.createOptional(new ResourceLocation(isForgeTag ? "forge" : TutorialMod.MOD_ID, name));
        }
    }

    public static class Entities
    {
        public static final Tags.IOptionalNamedTag<EntityType<?>> TUTORIAL_ADVANCED_BLOCK_BLACKLISTED =
                tag("tutorialadvancedblockblacklistedentities", false);

        private static Tags.IOptionalNamedTag<EntityType<?>> tag(String name, boolean isForgeTag)
        {
            return EntityTypeTags.createOptional(new ResourceLocation(isForgeTag ? "forge" : TutorialMod.MOD_ID, name));
        }
    }
}
