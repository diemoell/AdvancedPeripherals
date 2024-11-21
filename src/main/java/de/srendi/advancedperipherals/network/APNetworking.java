package de.srendi.advancedperipherals.network;

import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.network.base.IPacket;
import de.srendi.advancedperipherals.network.toclient.ToastToClientPacket;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.common.util.FakePlayer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.network.connection.ConnectionUtils;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import net.neoforged.network.NetworkDirection;
import net.neoforged.network.NetworkRegistry;
import net.neoforged.network.PacketDistributor;
import net.neoforged.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Function;

public class APNetworking {
    private static final String PROTOCOL_VERSION = ModLoadingContext.get().getActiveContainer().getModInfo().getVersion().toString();
    private static int id = 0;

    public static void init() {
        registerServerToClient(ToastToClientPacket.class, ToastToClientPacket::decode);
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(AdvancedPeripherals.MOD_ID)
                .versioned(PROTOCOL_VERSION)
                .common(AdvancedPeripherals.getRL("toasttoclient"), ToastToClientPacket::decode, IPacket::handlePacket)
                .optional();


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

    public static ClientboundBlockEntityDataPacket createTEUpdatePacket(BlockEntity tile) {
        return ClientboundBlockEntityDataPacket.create(tile);
    }

    public static void sendToAllAround(Object mes, ResourceKey<Level> dim, BlockPos pos, int radius) {
        NETWORK_CHANNEL.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), radius, dim)), mes);
    }

    public static void sendToAllInWorld(Object mes, ServerLevel world) {
        NETWORK_CHANNEL.send(PacketDistributor.DIMENSION.with(world::dimension), mes);
    }
}
