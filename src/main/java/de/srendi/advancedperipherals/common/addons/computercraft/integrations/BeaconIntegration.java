package de.srendi.advancedperipherals.common.addons.computercraft.integrations;

import dan200.computercraft.api.lua.LuaFunction;
import de.srendi.advancedperipherals.lib.peripherals.APGenericPeripheral;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;

import java.lang.reflect.Field;

public class BeaconIntegration implements APGenericPeripheral {
    @Override
    public String getPeripheralType() {
        return "beacon";
    }

    @LuaFunction(mainThread = true)
    public final int getLevel(BeaconBlockEntity blockEntity) {
        // because levels are now protected field .... why?
        CompoundTag savedData = blockEntity.saveWithoutMetadata(blockEntity.getLevel().registryAccess());
        return savedData.getInt("Levels");
    }

    @LuaFunction(mainThread = true)
    public final String getPrimaryEffect(BeaconBlockEntity blockEntity) {
        try {
            Class<?> beaconClass = BeaconBlockEntity.class;

            Field primaryPowerField = beaconClass.getDeclaredField("primaryPower");

            primaryPowerField.setAccessible(true);

            Object primaryPower = primaryPowerField.get(blockEntity);

            return primaryPower == null ? "none" : ((MobEffect) primaryPower).getDescriptionId();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return "none";
        }
    }

    @LuaFunction(mainThread = true)
    public final String getSecondaryEffect(BeaconBlockEntity blockEntity) {
        try {
            Class<?> beaconClass = BeaconBlockEntity.class;

            Field primaryPowerField = beaconClass.getDeclaredField("secondaryPower");

            primaryPowerField.setAccessible(true);

            Object secondaryPower = primaryPowerField.get(blockEntity);

            return secondaryPower == null ? "none" : ((MobEffect) secondaryPower).getDescriptionId();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return "none";
        }
    }

}
