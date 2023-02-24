package io.github.originalenhancementsmain.item;


import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public enum RarityProvider {

    TREASURE(OriginalEnhancementMain.getTranslationWay("rarity", "treasure").withStyle(ChatFormatting.DARK_BLUE)),
    ABYSS(OriginalEnhancementMain.getTranslationWay("rarity", "abyss").withStyle(ChatFormatting.LIGHT_PURPLE)),
    TRIVIAL(OriginalEnhancementMain.getTranslationWay("rarity", "trivial").withStyle(ChatFormatting.GRAY)),
    RARITY(OriginalEnhancementMain.getTranslationWay("rarity", "rarity").withStyle(ChatFormatting.DARK_GREEN));
    private final Component rarity;

    RarityProvider(Component rarity){
        this.rarity = rarity;
    }


    public Component getRarity(){return this.rarity;}

}
