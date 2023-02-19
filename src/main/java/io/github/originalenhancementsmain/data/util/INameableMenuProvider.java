package io.github.originalenhancementsmain.data.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;

public interface INameableMenuProvider extends MenuProvider, Nameable {

    @Override
    Component getCustomName();

    Component getDefaultName();

    @Override
    default Component getDisplayName(){
        return getName();
    };

    @Override
    default Component getName() {
        Component customTitle = getCustomName();
        return customTitle != null ? customTitle : getDefaultName();
    }


    void setCustomName(Component name);
}
