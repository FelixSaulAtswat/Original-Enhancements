package io.github.originalenhancementsmain.data.util;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.Nullable;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockEntityUtil {

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

    @SuppressWarnings("unchecked")
    @Nullable
    public static <HAVE extends BlockEntity, RET extends BlockEntity> BlockEntityTicker<RET> castTicker(BlockEntityType<RET> expected, BlockEntityType<HAVE> have, BlockEntityTicker<? super HAVE> ticker) {
        return have == expected ? (BlockEntityTicker<RET>)ticker : null;
    }

    @Nullable
    public static <HAVE extends BlockEntity, RET extends BlockEntity> BlockEntityTicker<RET> serverTicker(Level level, BlockEntityType<RET> expected, BlockEntityType<HAVE> have, BlockEntityTicker<? super HAVE> ticker) {
        return level.isClientSide ? null : castTicker(expected, have, ticker);
    }
}
