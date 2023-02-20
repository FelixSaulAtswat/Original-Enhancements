package io.github.originalenhancementsmain.data.util;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.resources.DirectAssetIndex;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BlockEntityUtil {

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
        OriginalEnhancementMain.LOG.warn("Unexpected TileEntity class at {}, expected {}, but found: {}", pos, tClass, tile.getClass());
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

    public static boolean checkFaceBlock(BlockEntity entity, BlockEntity self){
        Level level = entity.getLevel();
        BlockPos pos = entity.getBlockPos();
        Direction direction = entity.getBlockState().getValue(HorizontalDirectionalBlock.FACING);

        if (level != null){
            if (direction == Direction.NORTH){
                return level.getBlockEntity(pos.north()) == self;
            }
            if (direction == Direction.EAST){
                return level.getBlockEntity(pos.east()) == self;
            }
            if (direction == Direction.SOUTH){
                return level.getBlockEntity(pos.south()) == self;
            }
            if (direction == Direction.WEST){
                return level.getBlockEntity(pos.west()) == self;
            }
        }
        return false;
    }
    public static boolean checkState(BlockEntity entity, BooleanProperty property){
        BlockState state = entity.getBlockState();
        return state.getValue(property);
    }
}
