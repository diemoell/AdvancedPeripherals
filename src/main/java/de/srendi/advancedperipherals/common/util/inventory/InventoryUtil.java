package de.srendi.advancedperipherals.common.util.inventory;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.util.CapabilityUtil;
import de.srendi.advancedperipherals.common.addons.computercraft.owner.IPeripheralOwner;
import de.srendi.advancedperipherals.common.util.CoordUtil;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class InventoryUtil {

    private InventoryUtil() {
    }


    // from dan200.computercraft
    @Nullable
    private static IItemHandler extractHandler(IPeripheral peripheral) {
        var object = peripheral.getTarget();
        var direction = peripheral instanceof dan200.computercraft.shared.peripheral.generic.GenericPeripheral sided ? sided.side() : null;

        if (object instanceof BlockEntity blockEntity) {
            if (blockEntity.isRemoved()) return null;

            var level = blockEntity.getLevel();
            if (!(level instanceof ServerLevel serverLevel)) return null;

            var result = CapabilityUtil.getCapability(serverLevel, Capabilities.ItemHandler.BLOCK, blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction);
            if (result != null) return result;
        }

        if (object instanceof IItemHandler handler) return handler;
        if (object instanceof Container container) return new InvWrapper(container);
        return null;
    }

    public static int moveItem(IItemHandler inventoryFrom, IItemHandler inventoryTo, ItemFilter filter) {
        if (inventoryFrom == null) return 0;

        int fromSlot = filter.getFromSlot();
        int toSlot = filter.getToSlot();

        int amount = filter.getCount();
        int transferableAmount = 0;

        // The logic changes with storage systems since these systems do not have slots
        if (inventoryFrom instanceof IStorageSystemItemHandler storageSystemHandler) {
            for (int i = toSlot == -1 ? 0 : toSlot; i < (toSlot == -1 ? inventoryTo.getSlots() : toSlot + 1); i++) {
                ItemStack extracted = storageSystemHandler.extractItem(filter, filter.getCount(), true);
                if (extracted.isEmpty())
                    continue;
                ItemStack inserted;
                if (toSlot == -1) {
                    inserted = ItemHandlerHelper.insertItem(inventoryTo, extracted, false);
                } else {
                    inserted = inventoryTo.insertItem(toSlot, extracted, false);
                }
                amount -= extracted.getCount() - inserted.getCount();
                transferableAmount += storageSystemHandler.extractItem(filter, extracted.getCount() - inserted.getCount(), false).getCount();
                if (transferableAmount >= filter.getCount())
                    break;
            }
            return transferableAmount;
        }

        if (inventoryTo instanceof IStorageSystemItemHandler storageSystemHandler) {
            for (int i = fromSlot == -1 ? 0 : fromSlot; i < (fromSlot == -1 ? inventoryFrom.getSlots() : fromSlot + 1); i++) {
                if (filter.test(inventoryFrom.getStackInSlot(i))) {
                    ItemStack extracted = inventoryFrom.extractItem(i, amount - transferableAmount, true);
                    if (extracted.isEmpty())
                        continue;
                    ItemStack inserted = storageSystemHandler.insertItem(toSlot, extracted, false);

                    amount -= inserted.getCount();
                    transferableAmount += inventoryFrom.extractItem(i, extracted.getCount() - inserted.getCount(), false).getCount();
                    if (transferableAmount >= filter.getCount())
                        break;
                }
            }
            return transferableAmount;
        }

        for (int i = fromSlot == -1 ? 0 : fromSlot; i < (fromSlot == -1 ? inventoryFrom.getSlots() : fromSlot + 1); i++) {
            if (filter.test(inventoryFrom.getStackInSlot(i))) {
                ItemStack extracted = inventoryFrom.extractItem(i, amount - transferableAmount, true);
                if (extracted.isEmpty())
                    continue;
                ItemStack inserted;
                if (toSlot == -1) {
                    inserted = ItemHandlerHelper.insertItem(inventoryTo, extracted, false);
                } else {
                    inserted = inventoryTo.insertItem(toSlot, extracted, false);
                }
                amount -= inserted.getCount();
                transferableAmount += inventoryFrom.extractItem(i, extracted.getCount() - inserted.getCount(), false).getCount();
                if (transferableAmount >= filter.getCount())
                    break;
            }
        }
        return transferableAmount;
    }

    public static int moveFluid(IFluidHandler inventoryFrom, IFluidHandler inventoryTo, FluidFilter filter) {
        if (inventoryFrom == null) return 0;

        int amount = filter.getCount();
        int transferableAmount = 0;

        // The logic changes with storage systems since these systems do not have slots
        if (inventoryFrom instanceof IStorageSystemFluidHandler storageSystemHandler) {
            FluidStack extracted = storageSystemHandler.drain(filter, IFluidHandler.FluidAction.SIMULATE);
            int inserted = inventoryTo.fill(extracted, IFluidHandler.FluidAction.EXECUTE);

            transferableAmount += storageSystemHandler.drain(filter.setCount(inserted), IFluidHandler.FluidAction.EXECUTE).getAmount();

            return transferableAmount;
        }

        if (inventoryTo instanceof IStorageSystemFluidHandler storageSystemHandler) {
            if (filter.test(inventoryFrom.getFluidInTank(0))) {
                FluidStack toExtract = inventoryFrom.getFluidInTank(0).copy();
                toExtract.setAmount(amount);
                FluidStack extracted = inventoryFrom.drain(toExtract, IFluidHandler.FluidAction.SIMULATE);
                if (extracted.isEmpty())
                    return 0;
                int inserted = storageSystemHandler.fill(extracted, IFluidHandler.FluidAction.EXECUTE);

                extracted.setAmount(inserted);
                transferableAmount += inventoryFrom.drain(extracted, IFluidHandler.FluidAction.EXECUTE).getAmount();
            }

            return transferableAmount;
        }

        return transferableAmount;
    }


    @Nullable
    public static IItemHandler getHandlerFromName(@NotNull IComputerAccess access, String name) throws LuaException {
        IPeripheral location = access.getAvailablePeripheral(name);
        if (location == null)
            return null;

        return extractHandler((IPeripheral) location.getTarget());
    }

    @Nullable
    public static IItemHandler getHandlerFromDirection(@NotNull String direction, @NotNull IPeripheralOwner owner) throws LuaException {
        Level level = owner.getLevel();
        Objects.requireNonNull(level);
        Direction relativeDirection = CoordUtil.getDirection(owner.getOrientation(), direction);
        BlockEntity target = level.getBlockEntity(owner.getPos().relative(relativeDirection));
        if (target == null)
            return null;

        return extractHandler((IPeripheral) target);
    }
}
