package io.github.originalenhancementsmain.item.items.food;

import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = OriginalEnhancementsMain.MOD_ID)
public class EndFruit extends Item {


    public EndFruit (){
        super(new Item.Properties().tab(OriginalEnhancementsMain.OETab).food(new FoodProperties.Builder().nutrition(1).saturationMod(1).alwaysEat().alwaysEat().build())
                .rarity(Rarity.create("end_fruit", ChatFormatting.GOLD)));
    }

    @SubscribeEvent
    public static void onEatFinish(LivingEntityUseItemEvent.Finish event) {
        LivingEntity player = event.getEntityLiving();

        if (!player.level.isClientSide) {
            if (event.getItem().getItem() == OEItems.END_FRUIT.get()) {
                if (player.getMaxHealth() < 300){
                    Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300);
                }
            }
        }
    }

    @SubscribeEvent
    public  static  void onDeath(LivingDeathEvent event){
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof Player player && player.getMaxHealth() == 300 && !player.getTags().contains("hp_up")){
            player.addTag("hp_up");
        }
        else {
            entity.getTags().remove("hp_up");
        }
    }

    @SubscribeEvent
    public static void foreverUpHealth(PlayerEvent.PlayerRespawnEvent event) {
        Player pyr = event.getPlayer();


        if (!pyr.level.isClientSide) {

           if (pyr.getTags().contains("hp_up")){
               pyr.getTags().remove("hp_up");
               Objects.requireNonNull(pyr.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300);
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
