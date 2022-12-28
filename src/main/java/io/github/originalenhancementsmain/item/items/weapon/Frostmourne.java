package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.effects.OEEffect;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = OriginalEnhancementsMain.MOD_ID)
public class Frostmourne extends SwordItem {

    public static final int EXTRA_DAMAGE = 100;
    public Frostmourne() {
        super(CustomItemTier.MythologicalTools,169,-1,new Item.Properties().tab(OriginalEnhancementsMain.OE3D).rarity(Rarity.EPIC));
    }

    @SubscribeEvent
    public static void extraDamage(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        MobType type = entity.getMobType();

        if(!entity.level.isClientSide && event.getSource().getDirectEntity() instanceof LivingEntity player) {
            ItemStack stack = player.getMainHandItem();

            if (!stack.isEmpty()){
                if ((stack.is(OEItems.FROSTMOURNE.get()))) {
                    if (type != MobType.UNDEAD) {
                        event.setAmount(event.getAmount() + EXTRA_DAMAGE);
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public static void extraHeal(LivingDeathEvent deathEvent){
        LivingEntity death = deathEvent.getEntityLiving();
        MobType type = death.getMobType();

        if(!death.level.isClientSide && deathEvent.getSource().getDirectEntity() instanceof LivingEntity player){
            ItemStack item = player.getMainHandItem();

            if (!item.isEmpty()) {
                if ((item.is(OEItems.FROSTMOURNE.get()))) {
                    if (type != MobType.UNDEAD) {
                        player.heal(30);
                    }
                }
            }
        }
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity user) {
        boolean result = super.hurtEnemy(stack, entity, user);

        if (result){
            if (entity.getMobType() != MobType.UNDEAD) {
                entity.addEffect(new MobEffectInstance(OEEffect.FROSTMOURNE_EFFECT.get(), 20 * 5, 1));
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
        lore.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.GOLD));
        lore.add(new TranslatableComponent(getDescriptionId()+".lore2").withStyle(ChatFormatting.RED));

        lore.add(new TranslatableComponent(getDescriptionId()+".lore3").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab tab, @NotNull NonNullList<ItemStack> items) {

        if (allowdedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            CompoundTag tags = new CompoundTag();
            tags.putBoolean("Unbreakable", true);

            stack.setTag(tags);
            items.add(stack);
        }
    }

}
