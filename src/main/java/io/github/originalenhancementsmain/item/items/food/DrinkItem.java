package io.github.originalenhancementsmain.item.items.food;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class DrinkItem extends Item {

    private final MobEffect effect;
    private boolean isHarmful;

    public DrinkItem(MobEffect effect, boolean isHarmful) {
        super(new Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(1).tab(OriginalEnhancementMain.OETab));
        this.effect = effect;
        this.isHarmful = isHarmful;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        Player player = living instanceof Player ? (Player)living : null;

        if (living instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
        }

        if (!level.isClientSide) {
            living.addEffect(new MobEffectInstance(effect, 20 * 30, 0));
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        living.gameEvent(GameEvent.DRINK);
        return stack;
    }

    public int getUseDuration(ItemStack pStack) {
        return 40;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }
    public SoundEvent getDrinkingSound() {
        return SoundEvents.WANDERING_TRADER_DRINK_POTION;
    }
    public SoundEvent getEatingSound() {
        return SoundEvents.WANDERING_TRADER_DRINK_POTION;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag){
        lore.add(Component.translatable(OriginalEnhancementMain.getTranslationKey("item", "effect")).withStyle(ChatFormatting.DARK_BLUE));
        lore.add(Component.translatable(getDescriptionId() + ".lore1").withStyle(this.isHarmful? ChatFormatting.RED : ChatFormatting.DARK_GREEN));
    }
}
