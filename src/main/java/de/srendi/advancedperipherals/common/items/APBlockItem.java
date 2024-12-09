package de.srendi.advancedperipherals.common.items;

import de.srendi.advancedperipherals.common.items.base.BaseBlockItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class APBlockItem extends BaseBlockItems {

    private final Supplier<Boolean> enabledSup;

    public APBlockItem(Block blockIn, Item.Properties properties, Supplier<Boolean> enabledSup) {
        super(blockIn, properties);
        this.enabledSup = enabledSup;
    }

    public APBlockItem(Block blockIn, Supplier<Boolean> enabledSup) {
        super(blockIn);
        this.enabledSup = enabledSup;
    }

    @Override
    public boolean isEnabled() {
        return enabledSup.get();
    }

}
