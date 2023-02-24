package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.client.render.items.ItemStackTileEntityRenderer;
import io.github.originalenhancementsmain.effect.OEEffects;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.OEItems;
import io.github.originalenhancementsmain.item.RarityProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class Frostmourne extends SwordItem {

    public static final int EXTRA_DAMAGE = 100;
    public Frostmourne() {
        super(CustomItemTier.MythologicalTools,169,-1,new Properties().tab(OriginalEnhancementMain.OE3D).rarity(Rarity.create("frostmourne", ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ItemStackTileEntityRenderer();
            }
        });
    }



    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity user) {
        boolean result = super.hurtEnemy(stack, entity, user);

        if (result){
            if (entity.getMobType() != MobType.UNDEAD) {
                entity.addEffect(new MobEffectInstance(OEEffects.FROSTMOURNE_EFFECT.get(), 20 * 5, 1));
                ((ServerLevel) entity.level).getChunkSource().broadcastAndSend(entity, new ClientboundAnimatePacket(entity, 5));
            }


        }
        return result;
    }

    @Override
    public boolean isEnchantable (@NotNull ItemStack pStack){
        return false;
    }

    @Override
    public boolean isBookEnchantable (ItemStack stack, ItemStack book){
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable (ItemStack stack, Enchantment enchantment){
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> lore, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(RarityProvider.ABYSS.getRarity());
        lore.add(Component.translatable(getDescriptionId()+".lore1").withStyle(ChatFormatting.RED));

        lore.add(Component.translatable(getDescriptionId()+".lore2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> items) {
        if (tab == OriginalEnhancementMain.OE3D) {
            ItemStack stack = new ItemStack(this);
            CompoundTag tags = new CompoundTag();
            tags.putBoolean("Unbreakable", true);

            stack.setTag(tags);
            items.add(stack);
        }
    }
}
