package de.srendi.advancedperipherals.common.util.component;

import com.mojang.serialization.Codec;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;
import java.util.function.UnaryOperator;

public class APComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(AdvancedPeripherals.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> Tag = register("Tag",
            builder -> builder.persistent(CompoundTag.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ROTATION_CHARGE_SETTING = register("rotationCharge",
            builder -> builder.persistent(Codec.intRange(Integer.MIN_VALUE, Integer.MAX_VALUE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> STORED_DATA = register("storedData",
            builder -> builder.persistent(Codec.STRING));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> UUID_TAG = register("UUIDTag",
            builder -> builder.persistent(UUIDUtil.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> PERIPHERAL_TYPE = register("peripheralType",
            builder -> builder.persistent(Codec.STRING));
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
