package de.srendi.advancedperipherals.common.blocks.blockentities;

import dan200.computercraft.shared.container.BasicContainer;
import de.srendi.advancedperipherals.common.addons.computercraft.peripheral.NBTStoragePeripheral;
import de.srendi.advancedperipherals.common.blocks.base.PeripheralBlockEntitys;
import de.srendi.advancedperipherals.common.setup.BlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class NBTStorageEntity extends PeripheralBlockEntitys<NBTStoragePeripheral> {

    private CompoundTag stored;

    public NBTStorageEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.NBT_STORAGE.get(), pos, state);
        stored = new CompoundTag();
    }

    @NotNull
    @Override
    protected NBTStoragePeripheral createPeripheral() {
        return new NBTStoragePeripheral(this);
    }

    public CompoundTag getStored() {
        return stored;
    }

    public void setStored(CompoundTag newStored) {
        stored = newStored;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compound, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("storedData", stored);
    }

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    @Override
    protected NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        BasicContainer.defaultSetItems(inventory, items);
    }

}
