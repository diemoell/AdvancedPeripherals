package de.srendi.advancedperipherals.common.util;

import dan200.computercraft.api.pocket.IPocketAccess;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.TurtleSide;
import de.srendi.advancedperipherals.common.addons.computercraft.owner.IPeripheralOwner;
import de.srendi.advancedperipherals.common.util.component.APDComponents;
import de.srendi.advancedperipherals.lib.peripherals.IPeripheralTileEntity;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DataStorageUtil {

    public static DataComponentPatch getDataStorage(@NotNull ITurtleAccess access, @NotNull TurtleSide side) {
        return access.getUpgradeData(side);
    }

    public static CompoundTag getDataStorage(@NotNull IPeripheralTileEntity tileEntity) {
        return tileEntity.getPeripheralSettings();
    }

    public static DataComponentPatch getDataStorage(@NotNull IPocketAccess pocket) {
        return pocket.getUpgradeData();
    }

    /**
     * This class is for persistent data sharing between peripherals and another part of systems
     * Like, for example, for ModelTransformingTurtle logic, because it's executed on the client where
     * aren't any peripherals available
     **/

    public static class RotationCharge {
        public static final int ROTATION_STEPS = 36;
        /**
         * Used for gear rotation animation
         */
        
        private static final String ROTATION_CHARGE_SETTING = "rotationCharge";

        public static int get(@NotNull ITurtleAccess access, @NotNull TurtleSide side) {
            return getDataStorage(access, side).get(APDComponents.ROTATION_CHARGE_SETTING.get()).orElseThrow(() -> new NoSuchElementException("Optional is empty"));
        }

        public static boolean consume(@NotNull ITurtleAccess access, @NotNull TurtleSide side) {
            DataComponentPatch data = getDataStorage(access, side);
            int currentCharge = data.get(APDComponents.ROTATION_CHARGE_SETTING.get()).orElseThrow(() -> new NoSuchElementException("Optional is empty"));
            if (currentCharge > 0) {
                DataComponentPatch.Builder builder = DataComponentPatch.builder();
                builder.set(APDComponents.ROTATION_CHARGE_SETTING.get(), Math.max(0, currentCharge - 1));
                access.setUpgradeData(side, builder.build());
                return true;
            }
            return false;
        }

        public static void addCycles(IPeripheralOwner owner, int count) {
            CompoundTag data = owner.getDataStorage();
            data.putInt(ROTATION_CHARGE_SETTING, Math.max(0, data.getInt(ROTATION_CHARGE_SETTING)) + count * ROTATION_STEPS);
            owner.markDataStorageDirty();
        }

    }
}
