package io.github.originalenhancementsmain.data.network;

import io.github.originalenhancementsmain.OriginalEnhancementMain;
import net.minecraftforge.network.NetworkDirection;

public class OENetwork extends NetworkHelper{
    private static OENetwork instance = null;

    private OENetwork() {
        super(OriginalEnhancementMain.getLocationResource("network"));
    }

    public static OENetwork getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Attempt to call network getInstance before network is setup");
        }
        return instance;
    }


    public static void setup() {
        if (instance != null) {
            return;
        }
        instance = new OENetwork();

        instance.registerPacket(InventorySlotSyncPacket.class, InventorySlotSyncPacket::new, NetworkDirection.PLAY_TO_CLIENT);
    }
}

