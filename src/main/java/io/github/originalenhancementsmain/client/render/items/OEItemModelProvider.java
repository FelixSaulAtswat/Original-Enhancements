package io.github.originalenhancementsmain.client.render.items;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.ItemLayersModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class OEItemModelProvider extends ItemModelProvider {

    public OEItemModelProvider(DataGenerator output, ExistingFileHelper existingFileHelper){
        super(output, OriginalEnhancementMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        luminousWeapon(OEItems.WOODEN_SWORD_O, 1);
        luminousWeapon(OEItems.LADA_CRYSTASL_MAX, 0);
    }

    private ItemModelBuilder builderLuminousItems(String name, String parent, int l, ResourceLocation... layers){
        ItemModelBuilder builder = withExistingParent(name, parent);
        for (int i = 0; i < layers.length; i++){
            builder = builder.texture("layer" + i, layers[i]);
        }
        if (isLuminous(l)) builder = builder.customLoader(ItemLayersModelBuilder::begin).emissive(l).end();
        return builder;
    }

    private ItemModelBuilder luminousWeapon(RegistryObject<Item> item, int layer){
        String path = item.getId().getPath();

        return builderLuminousItems(path, "item/handheld", layer, OriginalEnhancementMain.getLocationResource("item/" + path));
    }

    private static boolean isLuminous(int l){
        return l - 1 >= -1;
    }
}
