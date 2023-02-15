package io.github.originalenhancementsmain.item.items.food;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.item.OEItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = OriginalEnhancementMain.MOD_ID)
public class EndFruit extends Item {


    public EndFruit (){
        super(new Properties().tab(OriginalEnhancementMain.OETab).food(new FoodProperties.Builder().nutrition(1).saturationMod(1).alwaysEat().alwaysEat().build())
                .rarity(Rarity.create("end_fruit", ChatFormatting.GOLD)));
    }

    @SubscribeEvent
    public static void onEatFinish(LivingEntityUseItemEvent.Finish event) {
        LivingEntity player = event.getEntity();

        if (!player.level.isClientSide) {
            if (event.getItem().getItem() == OEItems.END_FRUIT.get()) {
                MinecraftServer server = player.getServer();
                ResourceLocation advancement = new ResourceLocation("minecraft:end/kill_dragon");
                if (player.getMaxHealth() < 300.0f && player instanceof ServerPlayer && ((ServerPlayer) player).getAdvancements().getOrStartProgress(server.getAdvancements().getAdvancement(advancement)).isDone()){
                    Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(300.0d);

                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lore, TooltipFlag flag) {
        super.appendHoverText(stack, level, lore, flag);
        lore.add(Component.translatable(getDescriptionId() + ".lore1").withStyle(ChatFormatting.DARK_BLUE));
        lore.add(Component.translatable(getDescriptionId()+".lore2").withStyle(ChatFormatting.RED));
    }
}
