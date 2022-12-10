package io.github.originalenhancementsmain.item.items;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.item.CustomItemTier;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = OriginalEnhancementsMain.MOD_ID)
public class KnightMetalSwordItemY extends SwordItem {
    public KnightMetalSwordItemY() {
        super(CustomItemTier.RareTools,14,-3, new Item.Properties().tab(OriginalEnhancementsMain.OETab));
    }

    private static final int BONUS_DAMAGE = 10;

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent evt) {
        LivingEntity target = evt.getEntityLiving();

        if (!target.level.isClientSide && evt.getSource().getDirectEntity() instanceof LivingEntity living) {
            ItemStack weapon = living.getMainHandItem();

            if (!weapon.isEmpty()) {
                if (target.getArmorValue() > 0 && (weapon.is(OEItems.KNIGHTMETAL_SWORD_ITEMY.get()))) {
                    if(target.getArmorCoverPercentage() > 0) {
                        int moreBonus = (int) (BONUS_DAMAGE * target.getArmorCoverPercentage());
                        evt.setAmount(evt.getAmount() + moreBonus);
                    } else {
                        evt.setAmount(evt.getAmount() + BONUS_DAMAGE);
                    }
                    ((ServerLevel) target.level).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(new TranslatableComponent(getDescriptionId() + ".lore1").withStyle(ChatFormatting.WHITE));
        lore.add(new TranslatableComponent(getDescriptionId()+".lore2").withStyle(ChatFormatting.GRAY));
    }


}
