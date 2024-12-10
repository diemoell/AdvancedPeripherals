package de.srendi.advancedperipherals.common.blocks.blockentities;

import dan200.computercraft.shared.container.BasicContainer;
import de.srendi.advancedperipherals.common.addons.computercraft.peripheral.BlockReaderPeripheral;
import de.srendi.advancedperipherals.common.blocks.base.PeripheralBlockEntitys;
import de.srendi.advancedperipherals.common.setup.BlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockReaderEntity extends PeripheralBlockEntitys<BlockReaderPeripheral> {

    public BlockReaderEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.BLOCK_READER.get(), pos, state);
    }

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    @NotNull
    @Override
    protected BlockReaderPeripheral createPeripheral() {
        return new BlockReaderPeripheral(this);
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
