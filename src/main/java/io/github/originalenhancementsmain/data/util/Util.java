package io.github.originalenhancementsmain.data.util;

import io.github.originalenhancementsmain.oeblock.OEBlocks;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities.NatureRealNameReconfigurableApparatusBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blocks.NatureRealNameReconfigurableApparatusBlock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;
import javax.swing.text.html.HTML;

import java.util.Objects;

import static net.minecraft.Util.makeDescriptionId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Util {

    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static String getTranslationKey(String base, @Nullable ResourceLocation name) {
        return makeDescriptionId(base, name);
    }

    public static BlockEntity getSideBlockEntity(String perspectiveDirection, BlockEntity entity){
        Level level = entity.getLevel();
        BlockPos east = entity.getBlockPos().east();
        BlockPos north = entity.getBlockPos().north();
        BlockPos south = entity.getBlockPos().south();
        BlockPos west = entity.getBlockPos().west();
        Direction direction = entity.getBlockState().getValue(HorizontalDirectionalBlock.FACING);

        if (level != null) {
            if (perspectiveDirection.equals(LEFT)) {
                if (direction == Direction.NORTH) {
                    return level.getBlockEntity(east);
                }
                if (direction == Direction.EAST) {
                    return level.getBlockEntity(south);
                }
                if (direction == Direction.SOUTH) {
                    return level.getBlockEntity(west);
                }
                if (direction == Direction.WEST) {
                    return level.getBlockEntity(north);
                }
            }
            if (perspectiveDirection.equals(RIGHT)) {
                if (direction == Direction.NORTH) {
                    return level.getBlockEntity(west);
                }
                if (direction == Direction.EAST) {
                    return level.getBlockEntity(north);
                }
                if (direction == Direction.SOUTH) {
                    return level.getBlockEntity(east);
                }
                if (direction == Direction.WEST) {
                    return level.getBlockEntity(south);
                }
            }
        }
        return null;
    }
}
