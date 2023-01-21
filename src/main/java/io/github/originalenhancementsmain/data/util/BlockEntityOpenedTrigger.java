package io.github.originalenhancementsmain.data.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockEntityOpenedTrigger extends SimpleCriterionTrigger<BlockEntityOpenedTrigger.Instance> {

    private static final ResourceLocation ID = OriginalEnhancementMain.getLocationResource("block_container_opened");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Instance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
        ResourceLocation id = new ResourceLocation(GsonHelper.getAsString(json, "type"));
        BlockEntityType<?> type = ForgeRegistries.BLOCK_ENTITIES.getValue(id);
        if (type == null) {
            throw new JsonSyntaxException("Unknown tile entity '" + id + "'");
        }
        return new Instance(entityPredicate, type);
    }

    /** Triggers this criteria */
    public void trigger(@Nullable BlockEntity tileEntity, @Nullable Inventory inv) {
        if (tileEntity != null && inv != null && inv.player instanceof ServerPlayer) {
            this.trigger((ServerPlayer)inv.player, instance -> instance.test(tileEntity.getType()));
        }
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final BlockEntityType<?> type;
        public Instance(EntityPredicate.Composite playerCondition, BlockEntityType<?> type) {
            super(ID, playerCondition);
            this.type = type;
        }

        public static Instance container(BlockEntityType<?> type) {
            return new Instance(EntityPredicate.Composite.ANY, type);
        }

        /** Tests if this instance matches */
        public boolean test(BlockEntityType<?> type) {
            return this.type == type;
        }

        @Override
        public JsonObject serializeToJson(SerializationContext conditions) {
            JsonObject json = super.serializeToJson(conditions);
            json.addProperty("type", Objects.requireNonNull(type.getRegistryName()).toString());
            return json;
        }
    }
}
