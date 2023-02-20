package io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities;

import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.apparatusblock.Components.NatureConverterDiskBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.DiskBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.InteractiveBlock;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class NatureConverterBlockEntity extends DiskBlockEntity implements IAnimatable {

    private double range = 0.0d;
    @Getter
    public float f;
    public float volume = 1000.0f;

    private final AnimationFactory manager = GeckoLibUtil.createFactory(this);
    public NatureConverterBlockEntity(BlockPos pos, BlockState state){
        super(OEBlockEntities.NATURE_CONVERTER_BLOCK_ENTITY.get(), pos, state);
    }

    private <E extends IAnimatable> PlayState predicate (AnimationEvent<E> event){
        BlockState state = this.getBlockState();
        ItemStack stack = this.getItem(0);
        NatureConverterDiskBlock block = (NatureConverterDiskBlock) state.getBlock();

        if (!stack.isEmpty()) {
            if (state.getValue(InteractiveBlock.STRUCTURE_COMPOSITION)) {
                if (!block.sideActive) {
                    this.volume = 1000;
                    this.range++;
                    this.f = (float) Math.sin(0.05d * this.range);
                    if (0.05d * this.range > Math.PI * 100000.0d) {
                        this.range = 0.0d;
                    }
                } else {
                    if (this.volume <= 0.0f) {
                        this.volume = 0.0f;
                    }
                    this.volume--;

                }
            } else {
                this.volume = 1000;
                this.range = 0;
            }
        }
        AnimationBuilder builder = new AnimationBuilder().addAnimation("animation.nature_converter_disk_original", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
        if (state.getValue(InteractiveBlock.STRUCTURE_COMPOSITION)){
            builder = new AnimationBuilder().addAnimation("animation.nature_converter_disk_in_structure", ILoopType.EDefaultLoopTypes.LOOP);
        }

        event.getController().setAnimation(builder);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }
}
