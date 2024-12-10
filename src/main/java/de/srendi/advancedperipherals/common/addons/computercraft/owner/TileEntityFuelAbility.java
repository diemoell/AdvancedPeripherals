package de.srendi.advancedperipherals.common.addons.computercraft.owner;

import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.lib.peripherals.IPeripheralTileEntity;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public class TileEntityFuelAbility<T extends BlockEntity & IPeripheralTileEntity> extends FuelAbility<BlockEntityPeripheralOwner<T>> {

    public TileEntityFuelAbility(@NotNull BlockEntityPeripheralOwner<T> owner) {
        super(owner);
    }

    @Override
    protected boolean consumeFuel(int count) {
            var storage = owner.tileEntity.getLevel().getCapability(Capabilities.ENERGY.block(), owner.tileEntity.getBlockPos(), Direction.DOWN);
            int energyCount = count * APConfig.METAPHYSICS_CONFIG.energyToFuelRate.get();
            int extractedCount = 0;
            if (storage != null) {
                extractedCount = storage.extractEnergy(energyCount, true);
                if (extractedCount == energyCount) {
                    storage.extractEnergy(energyCount, false);
                    return true;
                }
                return false;
            }
            return false;
    }

    @Override
    protected int getMaxFuelConsumptionRate() {
        return DEFAULT_FUEL_CONSUMING_RATE;
    }

    @Override
    public boolean isFuelConsumptionDisable() {
        return !APConfig.PERIPHERALS_CONFIG.enablePoweredPeripherals.get();
    }

    @Override
    public int getFuelCount() {
        var storage = owner.tileEntity.getLevel().getCapability(Capabilities.ENERGY.block(), owner.tileEntity.getBlockPos(), Direction.DOWN);
        if(storage != null){
            return storage.getEnergyStored() / APConfig.METAPHYSICS_CONFIG.energyToFuelRate.get();
        }
        return 0;
    }

    @Override
    public int getFuelMaxCount() {
        var storage = owner.tileEntity.getLevel().getCapability(Capabilities.ENERGY.block(), owner.tileEntity.getBlockPos(), Direction.DOWN);
        if(storage != null){
            return storage.getMaxEnergyStored() / APConfig.METAPHYSICS_CONFIG.energyToFuelRate.get();
        }
        return 0;
    }

    @Override
    public void addFuel(int count) {
        var storage = owner.tileEntity.getLevel().getCapability(Capabilities.ENERGY.block(), owner.tileEntity.getBlockPos(), Direction.DOWN);
        if(storage != null){
            int energyCount = count * APConfig.METAPHYSICS_CONFIG.energyToFuelRate.get();
            storage.receiveEnergy(energyCount, false);
        }
    }
}
