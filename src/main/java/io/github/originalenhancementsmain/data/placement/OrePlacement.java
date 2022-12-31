package io.github.originalenhancementsmain.data.placement;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OrePlacement {

    public static List<PlacementModifier> orePlacement(PlacementModifier modifier_1, PlacementModifier modifier_2 ){
        return List.of(modifier_1, InSquarePlacement.spread(), modifier_2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int value, PlacementModifier modifier_3){
        return orePlacement(CountPlacement.of(value), modifier_3);
    }

    public static List<PlacementModifier> rareOrePlacement(int value, PlacementModifier modifier_4){
        return orePlacement(RarityFilter.onAverageOnceEvery(value), modifier_4);
    }
}
