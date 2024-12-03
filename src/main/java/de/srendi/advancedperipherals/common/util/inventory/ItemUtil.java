package de.srendi.advancedperipherals.common.util.inventory;

import dan200.computercraft.shared.ModRegistry;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.util.StringUtil;
import net.minecraft.ResourceLocationException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import org.apache.logging.log4j.Level;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static final Item TURTLE_NORMAL = ModRegistry.Items.TURTLE_NORMAL.get();
    public static final Item TURTLE_ADVANCED = ModRegistry.Items.TURTLE_ADVANCED.get();

    public static final Item POCKET_NORMAL = ModRegistry.Items.POCKET_COMPUTER_NORMAL.get();
    public static final Item POCKET_ADVANCED = ModRegistry.Items.POCKET_COMPUTER_ADVANCED.get();

    private ItemUtil() {
    }

    public static <T> T getRegistryEntry(String name, Registry<T> forgeRegistry) {
        ResourceLocation location;
        try {
            location = ResourceLocation.parse(name);
        } catch (ResourceLocationException ex) {
            location = null;
        }

        T value;
        if (location != null && forgeRegistry.containsKey(location) && (value = forgeRegistry.get(location)) != null) {
            return value;
        } else {
            return null;
        }
    }

    /**
     * Fingerprints are MD5 hashes generated out of the nbt tag, the registry name and the display name from item stacks
     * Used to filter inventory specific operations. {@link de.srendi.advancedperipherals.common.addons.computercraft.peripheral.InventoryManagerPeripheral}
     *
     * @return A generated MD5 hash from the item stack
     */
    public static String getFingerprint(ItemStack stack) {
        // now we use hoverName
        String fingerprint = stack.getHoverName() + getRegistryKey(stack).toString() + stack.getDisplayName().getString();
        try {
            byte[] bytesOfHash = fingerprint.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            return StringUtil.toHexString(md.digest(bytesOfHash));
        } catch (NoSuchAlgorithmException ex) {
            AdvancedPeripherals.debug("Could not parse fingerprint.", Level.ERROR);
            ex.printStackTrace();
        }
        return "";
    }

    public static ItemStack makeTurtle(Item turtle, String upgrade) {
        ItemStack stack = new ItemStack(turtle);
        stack.getOrDefault(DataComponents.CUSTOM_DATA, upgrade);
        // stack.getOrCreateTag().putString("RightUpgrade", upgrade);
        return stack;
    }

    public static ItemStack makePocket(Item turtle, String upgrade) {
        ItemStack stack = new ItemStack(turtle);
        stack.getOrDefault(DataComponents.CUSTOM_DATA, upgrade);
        // stack.getOrCreateTag().putString("Upgrade", upgrade);
        return stack;
    }

    //Gathers all items in handler and returns them
    public static List<ItemStack> getItemsFromItemHandler(IItemHandler handler) {
        List<ItemStack> items = new ArrayList<>(handler.getSlots());
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            items.add(handler.getStackInSlot(slot).copy());
        }

        return items;
    }

    public static void addComputerItemToTab(ResourceLocation turtleID, ResourceLocation pocketID, NonNullList<ItemStack> items) {
        if (turtleID != null) {
            items.add(makeTurtle(TURTLE_ADVANCED, turtleID.toString()));
            items.add(makeTurtle(TURTLE_NORMAL, turtleID.toString()));
        }
        if (pocketID != null) {
            items.add(makePocket(POCKET_ADVANCED, pocketID.toString()));
            items.add(makePocket(POCKET_NORMAL, pocketID.toString()));
        }
    }

    public static ResourceLocation getRegistryKey(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static ResourceLocation getRegistryKey(ItemStack item) {
        return BuiltInRegistries.ITEM.getKey(item.copy().getItem());
    }
}
