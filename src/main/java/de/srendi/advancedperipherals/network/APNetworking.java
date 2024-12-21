package de.srendi.advancedperipherals.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;

public class APNetworking {
    private static final String PROTOCOL_VERSION = ModLoadingContext.get().getActiveContainer().getModInfo().getVersion().toString();
    private static int id = 0;

    public static void init() {
        //registerServerToClient(ToastToClientPacket.class, ToastToClientPacket::decode);
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        //final IPayloadRegistrar registrar = event.registrar(AdvancedPeripherals.MOD_ID)
        //        .versioned(PROTOCOL_VERSION)
        //      .common(AdvancedPeripherals.getRL("toasttoclient"), ToastToClientPacket::decode, IPacket::handlePacket)
        //      .optional();


    }

    /*public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            NETWORK_CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
        }
    }

    public static void sendPacketToAll(Object packet) {
        NETWORK_CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static ClientboundBlockEntityDataPacket createTEUpdatePacket(BlockEntity tile) {
        return ClientboundBlockEntityDataPacket.create(tile);
    }

    public static void sendToAllAround(Object mes, ResourceKey<Level> dim, BlockPos pos, int radius) {
        NETWORK_CHANNEL.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), radius, dim)), mes);
    }

    public static void sendToAllInWorld(Object mes, ServerLevel world) {
        NETWORK_CHANNEL.send(PacketDistributor.DIMENSION.with(world::dimension), mes);
    }*/
}
