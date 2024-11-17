package de.srendi.advancedperipherals.network.toclient;

import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.util.ToastUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record ToastToClientPacket(Component title, Component component) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ToastToClientPacket> ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "chat"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ToastToClientPacket> CODEC = StreamCodec.composite(
            ComponentSerialization.TRUSTED_STREAM_CODEC, ToastToClientPacket::title,
            ComponentSerialization.TRUSTED_STREAM_CODEC, ToastToClientPacket::component,
            ToastToClientPacket::new
    );

    public static void handle(final ToastToClientPacket data, final IPayloadContext context) {
        // Should in the theory not happen, but safe is safe.
        if (!FMLEnvironment.dist.isClient()) {
            AdvancedPeripherals.debug("Tried to display toasts on the server, aborting.");
            return;
        }
        ToastUtil.displayToast(data.title, data.component);
    }


    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
