package de.srendi.advancedperipherals.client;

import de.srendi.advancedperipherals.network.base.IPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import org.jetbrains.annotations.NotNull;

public final class ClientNetworking {
    private ClientNetworking() {
    }

    public static void sendToServer(IPacket<?> message) {
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection != null) {
            connection.send(new Packet<>() {
                @Override
                public void write(@NotNull FriendlyByteBuf pBuffer) {
                    message.encode(pBuffer);
                }

                @Override
                public void handle(@NotNull PacketListener pHandler) {
                }
            });
        }

    }
}
