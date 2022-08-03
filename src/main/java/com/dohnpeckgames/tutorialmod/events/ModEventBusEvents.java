package com.dohnpeckgames.tutorialmod.events;

import com.dohnpeckgames.tutorialmod.TutorialMod;
import com.dohnpeckgames.tutorialmod.particle.ModParticles;
import com.dohnpeckgames.tutorialmod.particle.custom.TutorialAdvancedBlockParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{
    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particleEngine.register(ModParticles.TUTORIAL_ADVANCED_BLOCK_PARTICLES.get(),
                TutorialAdvancedBlockParticles.Provider::new);
    }
}
