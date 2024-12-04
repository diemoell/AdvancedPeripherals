package de.srendi.advancedperipherals.common.util.inventory;

import com.minecolonies.api.util.constant.TagConstants;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.util.CapabilityUtil;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.owner.IPeripheralOwner;
import de.srendi.advancedperipherals.common.util.CoordUtil;
import de.srendi.advancedperipherals.common.util.StringUtil;
import de.srendi.advancedperipherals.common.util.component.APDComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class FluidUtil {

    private FluidUtil() {
    }

    @Nullable
    private static IFluidHandler extractHandler(IPeripheral peripheral) {
        var object = peripheral.getTarget();
        var direction = peripheral instanceof dan200.computercraft.shared.peripheral.generic.GenericPeripheral sided ? sided.side() : null;

        if (object instanceof BlockEntity blockEntity) {
            if (blockEntity.isRemoved()) return null;

            var level = blockEntity.getLevel();
            if (!(level instanceof ServerLevel serverLevel)) return null;

            var result = CapabilityUtil.getCapability(serverLevel, Capabilities.FluidHandler.BLOCK, blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction);
            if (result != null) return result;
        }

        if (object instanceof IFluidHandler handler) return handler;
        return null;
    }

    @NotNull
    public static IFluidHandler getHandlerFromDirection(@NotNull String direction, @NotNull IPeripheralOwner owner) throws LuaException {
        Level level = owner.getLevel();
        Objects.requireNonNull(level);
        Direction relativeDirection = CoordUtil.getDirection(owner.getOrientation(), direction);
        BlockEntity target = level.getBlockEntity(owner.getPos().relative(relativeDirection));
        if (target == null)
            throw new LuaException("Target '" + direction + "' is empty or not a fluid handler");

        IFluidHandler handler = extractHandler((IPeripheral) target);
        // IFluidHandler handler = level.getCapability(Capabilities.FluidHandler.BLOCK, target.getBlockPos(), Direction.byName(direction));
        if (handler == null)
            throw new LuaException("Target '" + direction + "' is not a fluid handler");
        return handler;
    }

    @Nullable
    public static IFluidHandler getHandlerFromName(@NotNull IComputerAccess access, String name) throws LuaException {
        IPeripheral location = access.getAvailablePeripheral(name);

        // Tanks/Block Entities can't be accessed if the bridge is not exposed to the same network as the target tank/block entity
        // This can occur when the bridge was wrapped via a side and not via modems
        if (location == null)
            return null;

        IFluidHandler handler = extractHandler(location);
        if (handler == null)
            throw new LuaException("Target '" + name + "' is not a fluid handler");
        return handler;
    }

    @NotNull
    public static String getFingerprint(@NotNull FluidStack stack) {
        String fingerprint = stack.getOrDefault(APDComponents.Tag, new CompoundTag()) + getRegistryKey(stack).toString() + stack.getHoverName().getString();
        try {
            byte[] bytesOfHash = fingerprint.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            return StringUtil.toHexString(md.digest(bytesOfHash));
        } catch (NoSuchAlgorithmException ex) {
            AdvancedPeripherals.debug("Could not parse fingerprint.", org.apache.logging.log4j.Level.ERROR);
            ex.printStackTrace();
        }
        return "";
    }

    public static ResourceLocation getRegistryKey(Fluid fluid) {
        return BuiltInRegistries.FLUID.getKey(fluid);
    }

    public static ResourceLocation getRegistryKey(FluidStack fluid) {
        return BuiltInRegistries.FLUID.getKey(fluid.copy().getFluid());
    }
}
