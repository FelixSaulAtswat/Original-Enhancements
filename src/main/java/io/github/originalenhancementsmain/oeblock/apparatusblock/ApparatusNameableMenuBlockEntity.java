package io.github.originalenhancementsmain.oeblock.apparatusblock;

import io.github.originalenhancementsmain.data.util.INameableMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ApparatusNameableMenuBlockEntity extends BaseApparatusBlockEntity implements INameableMenuProvider {
    private static final String TAG_CUSTOM_NAME = "CustomName";
    private final Component defaultName;
    private Component customName;

    public ApparatusNameableMenuBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, Component defaultName){
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
            tags.putString(TAG_CUSTOM_NAME, Component.Serializer.toJson(this.customName));
        }

    }

    public void load(CompoundTag tags) {
        super.load(tags);
        if (tags.contains(TAG_CUSTOM_NAME, 8)) {
            this.customName = Component.Serializer.fromJson(tags.getString(TAG_CUSTOM_NAME));
        }

    }
}
