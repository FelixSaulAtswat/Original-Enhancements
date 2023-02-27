package io.github.originalenhancementsmain.data.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

public final class RenderUtil {

    public static boolean checkHasSize(Entity entity) {
        CompoundTag compoundtag = entity.saveWithoutId(new CompoundTag());
        return compoundtag.contains("Size");
    }

    public static float getSize(Entity entity){
        CompoundTag compoundTag = entity.saveWithoutId(new CompoundTag());
        return (float) compoundTag.getInt("Size") + 1.0F;
    }
}
