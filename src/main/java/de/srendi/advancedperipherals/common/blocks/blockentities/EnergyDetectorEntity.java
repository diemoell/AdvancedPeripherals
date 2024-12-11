package de.srendi.advancedperipherals.common.blocks.blockentities;

import com.google.errorprone.annotations.Var;
import dan200.computercraft.shared.container.BasicContainer;
import de.srendi.advancedperipherals.common.addons.computercraft.peripheral.EnergyDetectorPeripheral;
import de.srendi.advancedperipherals.common.blocks.base.PeripheralBlockEntitys;
import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.common.setup.BlockEntityTypes;
import de.srendi.advancedperipherals.common.util.EnergyStorageProxy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EnergyDetectorEntity extends PeripheralBlockEntitys<EnergyDetectorPeripheral> {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    //a zero size, zero transfer energy storage to ensure that cables connect
    private final EnergyStorage zeroStorage = new EnergyStorage(0, 0, 0);
    public int transferRate = 0;
    //storageProxy that will forward the energy to the output but limit it to maxTransferRate
    public EnergyStorageProxy storageProxy = new EnergyStorageProxy(this, APConfig.PERIPHERALS_CONFIG.energyDetectorMaxFlow.get());
    Direction energyInDirection = Direction.NORTH;
    Direction energyOutDirection = Direction.SOUTH;
    @NotNull
    private Optional<IEnergyStorage> outReceivingStorage = Optional.empty();

    public EnergyDetectorEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.ENERGY_DETECTOR.get(), pos, state);
    }

    @NotNull
    @Override
    protected EnergyDetectorPeripheral createPeripheral() {
        return new EnergyDetectorPeripheral(this);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(compound, registries);
        compound.putInt("rateLimit", storageProxy.getMaxTransferRate());
    }

    @Override
    public <T extends BlockEntity> void handleTick(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide) {
            // this handles the rare edge case that receiveEnergy is called multiple times in one tick
            transferRate = storageProxy.getTransferedInThisTick();
            storageProxy.resetTransferedInThisTick();
        }
    }

    public void invalidateStorages() {
        outReceivingStorage = Optional.empty();
    }

    // returns the cached output storage of the receiving block or refetches it if it has been invalidated
    @NotNull
    public Optional<IEnergyStorage> getOutputStorage() {
        // the documentation says that the value of the LazyOptional should be cached locally and invallidated using addListener
        if (outReceivingStorage.isEmpty()) {
            BlockEntity teOut = level.getBlockEntity(worldPosition.relative(energyOutDirection));
            if (teOut == null) {
                return Optional.empty();
            }
            var lazyOptionalOutStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, teOut.getBlockPos(), energyOutDirection.getOpposite());
            if(lazyOptionalOutStorage == null){
                outReceivingStorage = Optional.empty();
            }else {
                outReceivingStorage = Optional.of(lazyOptionalOutStorage);
            }
        }
        return outReceivingStorage;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        BasicContainer.defaultSetItems(inventory, items);
    }
}
