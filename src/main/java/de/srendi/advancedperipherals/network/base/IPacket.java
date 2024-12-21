package de.srendi.advancedperipherals.network.base;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public interface IPacket<T extends IPacket<?> & CustomPacketPayload> extends IPayloadHandler<T> {

    static <MSG extends IPacket<?>> void handlePacket(MSG message) {
    }

    void encode(FriendlyByteBuf buffer);

}
