package de.srendi.advancedperipherals.network;

import de.srendi.advancedperipherals.network.toclient.ToastToClientPacket;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class APNetworking {

    /**
     * Send a packet to a specific player.<p>
     * Must be called Server side.
     */
    public static void sendTo(ToastToClientPacket msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            PacketDistributor.sendToPlayer(player, msg);
        }
    }

}
