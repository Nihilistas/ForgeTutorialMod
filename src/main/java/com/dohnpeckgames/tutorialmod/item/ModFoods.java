package com.dohnpeckgames.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods
{
    public static final FoodProperties TUTORIAL_FOOD = (new FoodProperties.Builder()).nutrition(4)
            .saturationMod(0.3F).fast()
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 40, 1), 1).build();

}
