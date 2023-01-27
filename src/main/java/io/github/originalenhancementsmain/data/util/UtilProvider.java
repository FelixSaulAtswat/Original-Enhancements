package io.github.originalenhancementsmain.data.util;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import static net.minecraft.Util.makeDescriptionId;

public class UtilProvider {
    public static String getTranslationKey(String base, @Nullable ResourceLocation name) {
        return makeDescriptionId(base, name);
    }

    public static Direction directionFromOffset(BlockPos pos, BlockPos neighbor) {
        BlockPos offset = neighbor.subtract(pos);
        for (Direction direction : Direction.values()) {
            if (direction.getNormal().equals(offset)) {
                return direction;
            }
        }
        OriginalEnhancementMain.LOG.error("No direction for position {} and neighbor {}", pos, neighbor);
        return Direction.DOWN;
    }
}
