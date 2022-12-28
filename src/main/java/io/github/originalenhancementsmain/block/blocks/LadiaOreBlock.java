package io.github.originalenhancementsmain.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import static net.minecraftforge.common.ForgeHooks.isCorrectToolForDrops;


public class LadiaOreBlock extends Block {

    private final UniformInt xp;
    public LadiaOreBlock(Properties properties, UniformInt uniformInt) {
        super(properties);
        this.xp = uniformInt;
    }

    @Override
    public float getDestroyProgress(@NotNull BlockState state, @NotNull Player player, @NotNull BlockGetter getter, @NotNull BlockPos pos) {
        float progress = isCorrectToolForDrops(state, player) ? 30 : 100;
        float speed = state.getDestroySpeed(getter, pos);

        Item item = player.getMainHandItem().getItem();

        if (item instanceof TieredItem tieredItem) {
            if (tieredItem.getTier() != Tiers.NETHERITE) {
                return player.getDigSpeed(state,pos) / speed / progress * 0.009F;
            }
        }
        return player.getDigSpeed(state, pos) / speed / progress;
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.xp.sample(RANDOM) : 0;
    }
}
