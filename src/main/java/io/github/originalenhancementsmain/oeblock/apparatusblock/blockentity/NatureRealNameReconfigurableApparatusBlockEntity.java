package io.github.originalenhancementsmain.oeblock.apparatusblock.blockentity;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import io.github.originalenhancementsmain.oeblock.OEBlockEntities;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusControllerBlock;
import io.github.originalenhancementsmain.oeblock.apparatusblock.ApparatusNameableMenuBlockEntity;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blockmenu.NatureRealNameReconfigurableApparatusMenu;
import io.github.originalenhancementsmain.oeblock.apparatusblock.blocks.NatureRealNameReconfigurableApparatusBlock;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

public class NatureRealNameReconfigurableApparatusBlockEntity extends ApparatusNameableMenuBlockEntity {

    public static final Component NAME = OriginalEnhancementMain.getTranslationWay("gui", "nature");

    private int progress = 0;

    private int maxProgress = 20 * 10;

    protected final ContainerData data;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ItemStackHandler itemHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public static final BlockEntityTicker<NatureRealNameReconfigurableApparatusBlockEntity> TICKER = (level, pso , state, self) -> self.tick(level, pso, state, self);

    public NatureRealNameReconfigurableApparatusBlockEntity(BlockPos pos, BlockState state){
        this(OEBlockEntities.NATURE_APPARATUS_CONTROLLER_BLOCK_ENTITY.get(), pos, state);
    }

    private boolean isFormed(){
        BlockState state = this.getBlockState();
        return state.hasProperty(NatureRealNameReconfigurableApparatusBlock.STRUCTURE_COMPOSITION) && state.getValue(NatureRealNameReconfigurableApparatusBlock.STRUCTURE_COMPOSITION);
    }

    public NatureRealNameReconfigurableApparatusBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state){
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
        return new NatureRealNameReconfigurableApparatusMenu(id, inv, this);
    }

    public void neighborChanged(Direction direction){

    }

    private static boolean hasRecipe(NatureRealNameReconfigurableApparatusBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());

        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }


        Optional<NatureRealNameReconfigurableApparatusRecipes> match = level.getRecipeManager().getRecipeFor(NatureRealNameReconfigurableApparatusRecipes.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());

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
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private void tick(Level level, BlockPos pos, BlockState state, NatureRealNameReconfigurableApparatusBlockEntity entity) {

        if (isFormed()) {
            if (hasRecipe(entity)) {

                    if (!state.getValue(ApparatusControllerBlock.ACTIVE)) {
                        level.setBlockAndUpdate(pos, state.setValue(ApparatusControllerBlock.ACTIVE, true));
                        entity.progress ++;
                        setChanged(level, pos, state);
                        if (entity.progress > entity.maxProgress){
                            materialItem(entity);
                        }
                    }else {

                        level.setBlockAndUpdate(pos,state.setValue(ApparatusControllerBlock.ACTIVE, false));
                        entity.resetProgress();
                        setChanged(level, pos, state);
                }
            }
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
        tag.putInt("virus_generator.progress", progress);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
    }
}
