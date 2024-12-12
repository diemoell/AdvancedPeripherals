package de.srendi.advancedperipherals.common.util;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class NBTConverter {
    public static CompoundTag fluidStackConver(FluidStack fluidStack) {
        return (CompoundTag) DataComponentPatch.CODEC.encodeStart(
                ServerLifecycleHooks.getCurrentServer().registryAccess().createSerializationContext(NbtOps.INSTANCE),
                fluidStack.getComponentsPatch()).getOrThrow();
    }

}
