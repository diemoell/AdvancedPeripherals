package de.srendi.advancedperipherals.common.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.configuration.APConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ChunkPos;
import org.apache.logging.log4j.Level;

import java.util.Map;

public class NBTUtil {

    public static Tag toDirectNBT(Object object) {
        // Mostly dan200.computercraft.shared.util toNBTTag method
        // put this map storing changes
        // instead of map serialization use direct map as CompoundNBT
        // assuming that map keys are strings
        if (object == null) {
            return null;
        } else if (object instanceof Boolean bool) {
            return ByteTag.valueOf((byte) (bool ? 1 : 0));
        } else if (object instanceof Integer integer) {
            return IntTag.valueOf(integer);
        } else if (object instanceof Number number) {
            return DoubleTag.valueOf(number.doubleValue());
        } else if (object instanceof String) {
            return StringTag.valueOf(object.toString());
        } else if (object instanceof Map<?, ?> map) {
            CompoundTag nbt = new CompoundTag();

            for (Map.Entry<?, ?> item : map.entrySet()) {
                Tag value = toDirectNBT(item.getValue());
                if (item.getKey() != null && value != null) {
                    nbt.put(item.getKey().toString(), value);
                }
            }
            return nbt;
        } else {
            return null;
        }
    }

    public static CompoundTag fromText(String json) {
        try {
            return json == null ? null : TagParser.parseTag(json);
        } catch (CommandSyntaxException ex) {
            AdvancedPeripherals.debug("Could not parse json data to NBT", Level.ERROR);
            if(APConfig.GENERAL_CONFIG.enableDebugMode.get())
                ex.printStackTrace();
            return null;
        }
    }

    public static CompoundTag toNBT(BlockPos pos) {
        CompoundTag data = new CompoundTag();
        data.putInt("x", pos.getX());
        data.putInt("y", pos.getY());
        data.putInt("z", pos.getZ());
        return data;
    }

    public static BlockPos blockPosFromNBT(CompoundTag nbt) {
        return new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
    }

    public static CompoundTag toNBT(ChunkPos pos) {
        CompoundTag data = new CompoundTag();
        data.putInt("x", pos.x);
        data.putInt("z", pos.z);
        return data;
    }

    public static ChunkPos chunkPosFromNBT(CompoundTag nbt) {
        return new ChunkPos(nbt.getInt("x"), nbt.getInt("z"));
    }

    @Deprecated
    // In future versions we may use Data Components instead of the
    public static CompoundTag getUnsafeNbt(ItemStack item){
        if (item.get(DataComponents.CUSTOM_DATA) != null){
            return item.get(DataComponents.CUSTOM_DATA).getUnsafe();
        }
        else {
            CustomData.set(DataComponents.CUSTOM_DATA, item, new CompoundTag());
            return item.get(DataComponents.CUSTOM_DATA).getUnsafe();
        }
    }

    @Deprecated
    // In future versions we may use Data Components instead of the
    public static CompoundTag getUnsafeNbtOrDefault(ItemStack item, String key, String value){
        CompoundTag tag = new CompoundTag();
        if (item.get(DataComponents.CUSTOM_DATA) != null){
            return item.get(DataComponents.CUSTOM_DATA).getUnsafe();
        }
        else{
            tag.putString(key, value);
            CustomData.set(DataComponents.CUSTOM_DATA, item, tag);
            return item.get(DataComponents.CUSTOM_DATA).getUnsafe();
        }
    }
}
