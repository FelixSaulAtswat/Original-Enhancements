package io.github.originalenhancementsmain.data.tags;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class StringProvider {
    public static String getTranslationKey(String base, @Nullable ResourceLocation name) {
        return net.minecraft.Util.makeDescriptionId(base, name);
    }
}
