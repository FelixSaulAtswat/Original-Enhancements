package io.github.originalenhancementsmain.data.util;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockEntityGetter {

    public static <T>Optional<T> get(Class<T> tClass, @Nullable BlockGetter getter, BlockPos pos) {
        return get(tClass, getter, pos, false);
    }

    public static <T> Optional<T> get(Class<T> tClass, @Nullable BlockGetter getter, BlockPos pos, boolean logWrongType) {
        if (!isBlockLoaded(getter, pos)) {
            return Optional.empty();
        }
        BlockEntity tile = getter.getBlockEntity(pos);
        if (tile == null) {
        return Optional.empty();
        }

        if (tClass.isInstance(tile)) {
            return Optional.of(tClass.cast(tile));
        } else if (logWrongType) {
        OriginalEnhancementsMain.LOG.warn("Unexpected TileEntity class at {}, expected {}, but found: {}", pos, tClass, tile.getClass());
        }

        return Optional.empty();
    }


    @SuppressWarnings("deprecation")
    public static boolean isBlockLoaded(@Nullable BlockGetter getter, BlockPos pos) {
        if (getter == null) {
            return false;
        }
        if (getter instanceof LevelReader) {
            return ((LevelReader) getter).hasChunkAt(pos);
        }
        return true;
    }
}
