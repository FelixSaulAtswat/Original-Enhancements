package io.github.originalenhancementsmain.data.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackList extends NonNullList<ItemStack> {

    protected ItemStackList() {
        this(new ArrayList<>());
    }
    protected ItemStackList(List<ItemStack> delegate) {
        super(delegate, ItemStack.EMPTY);
    }

    public static ItemStackList withSize(int size) {
        ItemStack[] aobject = new ItemStack[size];
        Arrays.fill(aobject, ItemStack.EMPTY);
        return new ItemStackList(Arrays.asList(aobject));
    }
}
