package io.github.originalenhancementsmain.item.items.weapon;

import io.github.originalenhancementsmain.OESounds;
import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.entity.OEEntitiers;
import io.github.originalenhancementsmain.entity.monster.DeathKnight;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DeathKnightScepter extends Item {
    public DeathKnightScepter() {
        super(new Item.Properties().durability(4).tab(OriginalEnhancementsMain.OETab));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() == stack.getMaxDamage()) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide()) {
            BlockHitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

            if (result.getType() != HitResult.Type.MISS) {
                DeathKnight zombie = OEEntitiers.DEATH_KNIGHT_IMI.get().create(level);
                assert zombie != null;
                zombie.moveTo(result.getLocation());
                if (!level.noCollision(zombie, zombie.getBoundingBox())) {
                    return InteractionResultHolder.pass(stack);
                }
                zombie.spawnAnim();
                zombie.setTame(true);
                zombie.setOwnerUUID(player.getUUID());
                zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 1));
                level.addFreshEntity(zombie);
                level.gameEvent(player, GameEvent.ENTITY_PLACE, new BlockPos(result.getLocation()));

                stack.hurt(1, level.getRandom(), null);
                zombie.playSound(OESounds.DEATH_KNIGHTSUMMON, 1.0F, zombie.getVoicePitch());
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
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
    public void appendHoverText (@NotNull ItemStack stack, Level world, @NotNull List< Component > tooltip, @NotNull TooltipFlag flags){
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(new TranslatableComponent("oe.scepter_charges", stack.getMaxDamage() - stack.getDamageValue()).withStyle(ChatFormatting.GRAY));
        }
    }


