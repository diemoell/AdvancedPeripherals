package de.srendi.advancedperipherals.common.blocks.base;

import dan200.computercraft.api.peripheral.IPeripheral;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public final class PeripheralBlockCapabilities {

    public static final BlockCapability<IPeripheral, @Nullable Direction> PERIPHERAL_CAP =
            BlockCapability.create(
                    ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "peripheralCap"),
                    IPeripheral.class,
                    Direction.class);

    public static final BlockCapability<IFluidHandler, @Nullable Direction> FLUID_HANDLER =
            BlockCapability.create(
                    ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "fluidHandler"),
                    IFluidHandler.class,
                    Direction.class);

    public static final BlockCapability<IItemHandler, @Nullable Direction> HANDLER =
            BlockCapability.create(
                    ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "handler"),
                    IItemHandler.class,
                    Direction.class);

}
