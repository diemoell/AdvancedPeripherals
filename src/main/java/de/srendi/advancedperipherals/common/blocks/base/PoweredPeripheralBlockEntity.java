package de.srendi.advancedperipherals.common.blocks.base;

import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.lib.peripherals.BasePeripheral;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import org.jetbrains.annotations.NotNull;

public abstract class PoweredPeripheralBlockEntity<T extends BasePeripheral<?>> extends PeripheralBlockEntitys<T> {

    private final EnergyStorage energyStorage;

    public PoweredPeripheralBlockEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
        if (APConfig.PERIPHERALS_CONFIG.enablePoweredPeripherals.get()) {
            energyStorage = new EnergyStorage(this.getMaxEnergyStored());
        } else {
            energyStorage = null;
        }
    }

    protected abstract int getMaxEnergyStored();

    @Override
    public void saveAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(compound, registries);
        if (energyStorage != null) {
            compound.putInt("energy", this.energyStorage.getEnergyStored());
        }
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(compound, registries);
        if (energyStorage != null) {
            energyStorage.receiveEnergy(compound.getInt("energy") - this.energyStorage.getEnergyStored(), false);
        }
    }
}
