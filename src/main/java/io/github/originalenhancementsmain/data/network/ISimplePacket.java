package io.github.originalenhancementsmain.data.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface ISimplePacket {
    void encode(FriendlyByteBuf buf);

    void handle(Supplier<NetworkEvent.Context> context);
}

