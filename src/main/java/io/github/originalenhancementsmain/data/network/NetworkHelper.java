package io.github.originalenhancementsmain.data.network;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class NetworkHelper {

    public final SimpleChannel network;
    private int id = 0;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public NetworkHelper(ResourceLocation channelName) {
        this.network = NetworkRegistry.ChannelBuilder
                .named(channelName)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .simpleChannel();
    }

    public void sendToClientsAround(Object msg, ServerLevel serverWorld, BlockPos position) {
        LevelChunk chunk = serverWorld.getChunkAt(position);
        network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msg);
    }

    public <MSG extends ISimplePacket> void registerPacket(Class<MSG> clazz, Function<FriendlyByteBuf, MSG> decoder, @Nullable NetworkDirection direction) {
        registerPacket(clazz, ISimplePacket::encode, decoder, ISimplePacket::handle, direction);
    }
    public <MSG> void registerPacket(Class<MSG> clazz, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer, @Nullable NetworkDirection direction) {
        this.network.registerMessage(this.id++, clazz, encoder, decoder, consumer, Optional.ofNullable(direction));
    }
}
