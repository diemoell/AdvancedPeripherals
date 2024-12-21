package de.srendi.advancedperipherals.common.blocks.base;

import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.lib.peripherals.BasePeripheral;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public abstract class PoweredPeripheralBlockEntity<T extends BasePeripheral<?>> extends PeripheralBlockEntity<T> {

    private final IEnergyStorage lazyEnergyStorage;

    public PoweredPeripheralBlockEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
        if (APConfig.PERIPHERALS_CONFIG.enablePoweredPeripherals.get()) {
            lazyEnergyStorage = new EnergyStorage(this.getMaxEnergyStored());
        } else {
            lazyEnergyStorage = null;
        }
    }

    protected abstract int getMaxEnergyStored();

    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("energy", lazyEnergyStorage.getEnergyStored());
    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);
        lazyEnergyStorage.receiveEnergy(compound.getInt("energy") - lazyEnergyStorage.getEnergyStored(), false);
    }

    //TODO
    /*@Override
    public <T1> @NotNull LazyOptional<T1> getCapability(@NotNull Capability<T1> cap, @Nullable Direction direction) {
        if (cap == ForgeCapabilities.ENERGY && lazyEnergyStorage.isPresent()) {
            return lazyEnergyStorage.cast();
        }
        return super.getCapability(cap, direction);
    }*/

}
