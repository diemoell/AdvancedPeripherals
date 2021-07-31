package de.srendi.advancedperipherals.network;

import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.network.messages.ClearHudCanvasMessage;
import de.srendi.advancedperipherals.network.messages.RequestHudCanvasMessage;
import de.srendi.advancedperipherals.network.messages.UpdateHudCanvasMessage;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class MNetwork {
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel NETWORK_CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(AdvancedPeripherals.MOD_ID, "main_channel"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int id = 0;

    public static void init() {
        NETWORK_CHANNEL.registerMessage(++id, ClearHudCanvasMessage.class, ClearHudCanvasMessage::encode,
                ClearHudCanvasMessage::decode, ClearHudCanvasMessage::handle);
        NETWORK_CHANNEL.registerMessage(++id, RequestHudCanvasMessage.class, RequestHudCanvasMessage::encode,
                RequestHudCanvasMessage::decode, RequestHudCanvasMessage::handle);
        NETWORK_CHANNEL.registerMessage(++id, UpdateHudCanvasMessage.class, UpdateHudCanvasMessage::encode,
                UpdateHudCanvasMessage::decode, UpdateHudCanvasMessage::handle);
    }

    /**
     * Sends a packet to the server.<p>
     * Must be called Client side.
     */
    public static void sendToServer(Object msg) {
        NETWORK_CHANNEL.sendToServer(msg);
    }

    /**
     * Send a packet to a specific player.<p>
     * Must be called Server side.
     */
    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            NETWORK_CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
        }
    }

    public static void sendPacketToAll(Object packet) {
        NETWORK_CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static  createTEUpdatePacket(TileEntity tile) {
        return new SUpdateTileEntityPacket(tile.getBlockPos(), -1, tile.getUpdateTag());
    }

    public static void sendToAllAround(Object mes, RegistryKey<World> dim, BlockPos pos, int radius) {
        NETWORK_CHANNEL.send(PacketDistributor.NEAR
                .with(() -> new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), radius, dim)), mes);
    }

    public static void sendToAllInWorld(Object mes, ServerWorld world) {
        NETWORK_CHANNEL.send(PacketDistributor.DIMENSION.with(world::dimension), mes);
    }
}
