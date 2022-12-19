package io.github.originalenhancementsmain.item.items.weapons;

import io.github.originalenhancementsmain.OEEffect;
import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = OriginalEnhancementsMain.MOD_ID)
public class Frostmourne extends SwordItem {
    public Frostmourne() {
        super(CustomItemTier.MythologicalTools,169,-1,new Item.Properties().tab(OriginalEnhancementsMain.OE3D).rarity(Rarity.EPIC));
    }

    @SubscribeEvent
    public static void extraDamage(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if(!entity.level.isClientSide && event.getSource().getDirectEntity() instanceof LivingEntity living) {
            ItemStack stack = living.getMainHandItem();

            if (!stack.isEmpty()){
                if (entity.getMobType() == MobType.UNDEAD && (stack.is(OEItems.FROSTMOURNE.get()))){
                    event.setAmount(event.getAmount());
                }
                else {
                    event.setAmount(event.getAmount() + 100);
                }
                ((ServerLevel) entity.level).getChunkSource().broadcastAndSend(entity, new ClientboundAnimatePacket(entity, 5));
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity user) {
        boolean result = super.hurtEnemy(stack, entity, user);

        if (entity.getMobType() == MobType.UNDEAD) {
            if (result) {
                entity.addEffect(new MobEffectInstance(OEEffect.FROSTMOURNE_EFFECT.get(), 0, 1));
            }
        }
        else {
            if (result) {
                entity.addEffect(new MobEffectInstance(OEEffect.FROSTMOURNE_EFFECT.get(), 20 * 5, 1));
            }
        }
        return result;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        lore.add(new TranslatableComponent(getDescriptionId()+".lore2").withStyle(ChatFormatting.RED));

        lore.add(new TranslatableComponent(getDescriptionId()+".lore3").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {

        if (allowdedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            CompoundTag tags = new CompoundTag();
            tags.putBoolean("Unbreakable", true);

            stack.setTag(tags);
            items.add(stack);
        }
    }

}
