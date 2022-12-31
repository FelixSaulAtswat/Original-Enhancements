package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.oeblock.apparatusblock.interfaceprovider.INameableProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class OENameableBlockEntity extends BaseOEBlockEntity implements INameableProvider {
    private static final String TAG_CUSTOM_NAME = "CustomName";
    private final Component defaultName;
    private Component customName;

    public OENameableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, Component defaultName){
        super(type, pos, state);
        this.defaultName = defaultName;
    }

    public Component getDefaultName() {
        return this.defaultName;
    }

    public Component getCustomName() {
        return this.customName;
    }

    public void setCustomName(Component customName) {
        this.customName = customName;
    }

    public void saveSynced(CompoundTag tags) {
        super.saveSynced(tags);
        if (this.hasCustomName()) {
            tags.putString("CustomName", Component.Serializer.toJson(this.customName));
        }

    }

    public void load(CompoundTag tags) {
        super.load(tags);
        if (tags.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(tags.getString("CustomName"));
        }

    }
}
