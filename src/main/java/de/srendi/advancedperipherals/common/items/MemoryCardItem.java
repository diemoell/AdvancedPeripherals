package de.srendi.advancedperipherals.common.items;

import de.srendi.advancedperipherals.client.ClientUUIDCache;
import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.common.items.base.BaseItem;
import de.srendi.advancedperipherals.common.util.EnumColor;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MemoryCardItem extends BaseItem {

    public MemoryCardItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public boolean isEnabled() {
        return APConfig.PERIPHERALS_CONFIG.enableInventoryManager.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, levelIn, tooltip, flagIn);
        CompoundTag data = stack.getOrCreateTag();
        Minecraft minecraft = Minecraft.getInstance();
        if (data.contains("owner")) {
            tooltip.add(EnumColor.buildTextComponent(Component.translatable("item.advancedperipherals.tooltip.memory_card.bound", ClientUUIDCache.getUsername(data.getUUID("owner"), minecraft.player.getUUID()))));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (!worldIn.isClientSide) {
            ItemStack stack = playerIn.getItemInHand(handIn);
            CompoundTag data = stack.getOrCreateTag();
            if (data.contains("owner")) {
                playerIn.displayClientMessage(Component.translatable("text.advancedperipherals.removed_player"), true);
                data.remove("owner");
            } else {
                playerIn.displayClientMessage(Component.translatable("text.advancedperipherals.added_player"), true);
                data.putUUID("owner", playerIn.getUUID());
            }
        }
        return super.use(worldIn, playerIn, handIn);
    }
}
