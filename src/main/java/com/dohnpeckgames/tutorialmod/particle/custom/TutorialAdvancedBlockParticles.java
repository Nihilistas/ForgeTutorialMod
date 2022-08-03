package com.dohnpeckgames.tutorialmod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class TutorialAdvancedBlockParticles extends TextureSheetParticle
{
    protected TutorialAdvancedBlockParticles(ClientLevel level, double xCoord, double yCoord, double zCoord,
                                             SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        friction = 0.8F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        quadSize = .85F;
        lifetime = 20;
        setSpriteFromAge(spriteSet);
        rCol = 1f;
        gCol = 1f;
        bCol = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        alpha = 1f - (float)age/(float)lifetime;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet)
        {
            sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x,
                                       double y, double z, double dx, double dy, double dz) {
            return new TutorialAdvancedBlockParticles(level, x, y, z, sprites, dx, dy, dz);
        }
    }
}