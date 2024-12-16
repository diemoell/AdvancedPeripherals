package de.srendi.advancedperipherals.common.events;

import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.network.toclient.ToastToClientPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = AdvancedPeripherals.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class APNetwork {
    @SubscribeEvent
    public static void registerServerToClient(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                ToastToClientPacket.ID,
                ToastToClientPacket.CODEC,
                new DirectionalPayloadHandler<>(
                        ToastToClientPacket::handle,
                        ToastToClientPacket::handle
                )
        );
    }
}
