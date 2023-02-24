package io.github.originalenhancementsmain.particle.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GrassParticle extends TextureSheetParticle {



    public GrassParticle(ClientLevel level, double x, double y, double z, double px, double py, double pz) {
        super(level, x, y, z, 0.0d, 0.0d, 0.0d);
        this.xd *= 0.1D;
        this.yd *= 0.1D;
        this.zd *= 0.1D;
        this.xd += px * 0.4D;
        this.yd += py * 0.4D;
        this.zd += pz * 0.4D;
        this.lifetime = (int) (4.0d / (Math.random() * 0.6d));
        this.quadSize *= 0.75f;
        this.rCol = 1.0f;
        this.bCol = this.rCol;
        this.gCol = this.rCol;
        this.hasPhysics = true;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getLightColor(float partialTicks) {
        return 150 | 150 << 16;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
        }

        this.move(this.xd, this.yd, this.zd);

        this.xd *= 0.7D;
        this.yd *= 0.7D;
        this.zd *= 0.7D;
        this.yd -= 0.02D;

        if (this.onGround) {
            this.xd *= 0.7D;
            this.zd *= 0.7D;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public record Factory(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            GrassParticle particle = new GrassParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }

}
