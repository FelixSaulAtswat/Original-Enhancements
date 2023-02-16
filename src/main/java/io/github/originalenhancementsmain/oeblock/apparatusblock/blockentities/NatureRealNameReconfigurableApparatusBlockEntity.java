package io.github.originalenhancementsmain.oeblock.apparatusblock.blockentities;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusNameableMenuBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenus.NatureRealNameReconfigurableApparatusMenu;
import io.github.originalenhancementsmain.recipe.apparatusrecipes.NatureRealNameReconfigurableApparatusRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.openjdk.nashorn.api.tree.LoopTree;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class NatureRealNameReconfigurableApparatusBlockEntity extends ApparatusNameableMenuBlockEntity implements IAnimatable {

    private static final BooleanProperty STRUCTURE_COMPOSITION = ApparatusControllerBlock.STRUCTURE_COMPOSITION;
    private static final BooleanProperty ACTIVE = ApparatusControllerBlock.ACTIVE;
    private final AnimationFactory manager = GeckoLibUtil.createFactory(this);

    public static final Component NAME = OriginalEnhancementMain.getTranslationWay("gui", "nature");

    private int progress = 0;
    private int AnimationProgress = 0;

    private int maxProgress = 20 * 100;

    protected final ContainerData data;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ItemStackHandler itemHandler = new ItemStackHandler(5){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private <E extends IAnimatable>PlayState predicate (AnimationEvent<E> event){
        BlockState state = this.getBlockState();

        AnimationBuilder builder;
        if (state.getValue(STRUCTURE_COMPOSITION) && !state.getValue(ACTIVE)) {
            builder = new AnimationBuilder().addAnimation("animation.nature_apparatus_booting", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
            this.AnimationProgress++;

            if (AnimationProgress > 13*20){
                builder = new AnimationBuilder().addAnimation("animation.nature_apparatus_standby", ILoopType.EDefaultLoopTypes.LOOP);
            }

        }else if (state.getValue(ACTIVE)){
            builder = new AnimationBuilder().addAnimation("animation.nature_apparatus_activiting", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
        } else {
            builder = new AnimationBuilder().addAnimation("animation.nature_apparatus_original", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME);
            this.resetAnimation();
        }

        event.getController().setAnimation(builder);
        return PlayState.CONTINUE;
    }

    private void resetAnimation(){
        this.AnimationProgress = 0;
    }

    public NatureRealNameReconfigurableApparatusBlockEntity(BlockPos pos, BlockState state){
        this(OEBlockEntities.NATURE_APPARATUS_CONTROLLER_BLOCK_ENTITY.get(), pos, state);
    }

    protected NatureRealNameReconfigurableApparatusBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state){
        super(type, pos, state, NAME);

        this.data = new ContainerData() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NatureRealNameReconfigurableApparatusBlockEntity.this.progress;
                    case 1 -> NatureRealNameReconfigurableApparatusBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NatureRealNameReconfigurableApparatusBlockEntity.this.progress = value;
                    case 1 -> NatureRealNameReconfigurableApparatusBlockEntity.this.maxProgress = value;
                }

            }
            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player playerEntity) {
        return new NatureRealNameReconfigurableApparatusMenu(id, inv, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side){
        if (capability == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(capability, side);
    }

    public void neighborChanged(Direction direction){}

    private static boolean hasRecipe(NatureRealNameReconfigurableApparatusBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }


        Optional<NatureRealNameReconfigurableApparatusRecipes> match = level.getRecipeManager().getRecipeFor(NatureRealNameReconfigurableApparatusRecipes.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot3(inventory) && canInsertItemIntoOutputSlot3(inventory, match.get().getResultItem()) && canInsertAmountIntoOutputSlot4(inventory) && canInsertItemIntoOutputSlot4(inventory, match.get().getResultItem());

    }

    private static void materialItem(NatureRealNameReconfigurableApparatusBlockEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<NatureRealNameReconfigurableApparatusRecipes> match = level.getRecipeManager().getRecipeFor(NatureRealNameReconfigurableApparatusRecipes.Type.INSTANCE, inventory, level);
        if (match.isPresent()){
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.extractItem(2, 1, false);

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));
            entity.itemHandler.setStackInSlot(4, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + 1));
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertAmountIntoOutputSlot3(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }

    private static boolean canInsertItemIntoOutputSlot3(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot4(SimpleContainer inventory) {
        return inventory.getItem(4).getMaxStackSize() > inventory.getItem(4).getCount();
    }

    private static boolean canInsertItemIntoOutputSlot4(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(4).getItem() == output.getItem() || inventory.getItem(4).isEmpty();
    }


    public static void tick(Level level, BlockPos pos, BlockState state, NatureRealNameReconfigurableApparatusBlockEntity entity) {
        BlockPos west = pos.west();
        BlockPos east = pos.east();
        BlockState westState = level.getBlockState(west);
        BlockState eastState = level.getBlockState(east);

        if (hasRecipe(entity) && state.getValue(STRUCTURE_COMPOSITION) && westState.is(Blocks.STONE) && eastState.is(Blocks.DIRT)) {

            level.setBlockAndUpdate(pos, state.setValue(ApparatusControllerBlock.ACTIVE, true));
            entity.progress++;
            setChanged(level, pos, state);
            if (entity.progress > entity.maxProgress) {
                materialItem(entity);
            }
        } else {

            level.setBlockAndUpdate(pos, state.setValue(ApparatusControllerBlock.ACTIVE, false));
            entity.resetProgress();
            setChanged(level, pos, state);
        }


    }

    @Override
    public void onLoad(){
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()-> itemHandler);
    }
    @Override
    public void invalidateCaps(){
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected boolean shouldSyncOnUpdate() {
        return true;
    }

    @Override
    public void load(CompoundTag nbt){
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("nature_apparatus.progress");
    }

    @Override
    public void saveSynced(CompoundTag tag){
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("nature_apparatus.progress", progress);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }
}
