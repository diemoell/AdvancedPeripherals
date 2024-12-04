package de.srendi.advancedperipherals.common.util.component;

import com.mojang.serialization.Codec;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class APDComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(AdvancedPeripherals.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> Tag = register("Tag",
            builder -> builder.persistent(CompoundTag.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ROTATION_CHARGE_SETTING = register("rotationCharge",
            builder -> builder.persistent(Codec.intRange(Integer.MIN_VALUE, Integer.MAX_VALUE)));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
