package de.srendi.advancedperipherals.common.addons.create;
// TODO: creat in 1.21 not ready
public class BasinIntegration {
}

/*

import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import dan200.computercraft.api.lua.LuaFunction;
import de.srendi.advancedperipherals.common.util.LuaConverter;
import de.srendi.advancedperipherals.lib.peripherals.APGenericPeripheral;
import net.neoforged.common.capabilities.ForgeCapabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BasinIntegration implements APGenericPeripheral {
    @NotNull
    @Override
    public String getPeripheralType() {
        return "basin";
    }

    @LuaFunction(mainThread = true)
    public final List<Object> getInputFluids(BasinBlockEntity blockEntity) {
        IFluidHandler handler = blockEntity.getTanks().getFirst().getCapability().orElse(null);
        if (handler == null)
            return null;

        int size = handler.getTanks();
        List<Object> tanks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            FluidStack fluid = handler.getFluidInTank(i);
            Map<String, Object> data = new HashMap<>();
            data.put("amount", fluid.getAmount());
            data.put("fluid", ForgeRegistries.FLUIDS.getKey(fluid.getFluid()).toString());
            tanks.add(data);
        }
        return tanks;
    }

    @LuaFunction(mainThread = true)
    public final List<Object> getOutputFluids(BasinBlockEntity blockEntity) {
        IFluidHandler handler = blockEntity.getTanks().getSecond().getCapability().orElse(null);
        if (handler == null) {
            return null;
        }
        int size = handler.getTanks();
        List<Object> tanks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            FluidStack fluid = handler.getFluidInTank(i);
            Map<String, Object> data = new HashMap<>();
            data.put("amount", fluid.getAmount());
            data.put("fluid", ForgeRegistries.FLUIDS.getKey(fluid.getFluid()).toString());
            tanks.add(data);
        }
        return tanks;
    }

    @LuaFunction(mainThread = true)
    public final Map<String, Object> getFilter(BasinBlockEntity blockEntity) {
        return LuaConverter.stackToObject(blockEntity.getFilter().getFilter());
    }

    @LuaFunction(mainThread = true)
    public final List<Object> getInventory(BasinBlockEntity blockEntity) {
        Optional<IItemHandler> handlerOptional = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
        if (handlerOptional.isEmpty()) return null;
        IItemHandler handler = handlerOptional.get();
        List<Object> items = new ArrayList<>();
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            items.add(LuaConverter.stackToObject(handler.getStackInSlot(slot)));
        }
        return items;
    }
}
*/
