package de.srendi.advancedperipherals.common.blocks.blockentities;

import dan200.computercraft.shared.container.BasicContainer;
import de.srendi.advancedperipherals.common.addons.computercraft.peripheral.PlayerDetectorPeripheral;
import de.srendi.advancedperipherals.common.blocks.base.PeripheralBlockEntitys;
import de.srendi.advancedperipherals.common.events.Events;
import de.srendi.advancedperipherals.common.setup.BlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PlayerDetectorEntity extends PeripheralBlockEntitys<PlayerDetectorPeripheral> {
    private Long lastConsumedMessage;

    public PlayerDetectorEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.PLAYER_DETECTOR.get(), pos, state);
        lastConsumedMessage = Events.getLastPlayerMessageID() - 1;
    }

    @NotNull
    @Override
    protected PlayerDetectorPeripheral createPeripheral() {
        return new PlayerDetectorPeripheral(this);
    }

    @Override
    public <T extends BlockEntity> void handleTick(Level level, BlockState state, BlockEntityType<T> type) {
        lastConsumedMessage = Events.traversePlayerMessages(lastConsumedMessage, message -> getConnectedComputers().forEach(computer -> {
            if (message.eventName().equals("playerChangedDimension")) {
                computer.queueEvent(message.eventName(), message.playerName(), message.fromDimension(), message.toDimension());
            } else computer.queueEvent(message.eventName(), message.playerName(), message.fromDimension());
        }));
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
