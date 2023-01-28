package io.github.originalenhancementsmain.client.component;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.data.util.ScreenComponentUtil;
import net.minecraft.resources.ResourceLocation;

public interface ApparatusGuiComponents {

    int imageW = 256;
    int imageH = 256;

    ResourceLocation Component = OriginalEnhancementMain.getLocationResource("textures/gui/apparatus_components.png");

    ScreenComponentUtil forestRightArrow = new ScreenComponentUtil(0, 0, 67, 23, imageW, imageH);
    ScreenComponentUtil forestLeftArrow= new ScreenComponentUtil(0, 24, 66, 23, imageW, imageH);
}
