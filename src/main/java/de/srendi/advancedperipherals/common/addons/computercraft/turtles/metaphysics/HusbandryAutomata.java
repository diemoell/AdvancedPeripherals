package de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics;

import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.TurtleSide;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.base.ModelTransformingTurtle;
import de.srendi.advancedperipherals.common.addons.computercraft.peripheral.metaphysics.HusbandryAutomataCorePeripheral;
import de.srendi.advancedperipherals.common.setup.Items;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HusbandryAutomata extends ModelTransformingTurtle<HusbandryAutomataCorePeripheral> {
    public static final ResourceLocation ID = new ResourceLocation(AdvancedPeripherals.MOD_ID, "husbandry_automata");

    public HusbandryAutomata() {
        super(ID, new ItemStack(Items.HUSBANDRY_AUTOMATA_CORE.get()));
    }

    @Override
    protected ModelResourceLocation getLeftModel() {
        return null;
    }

    @Override
    protected ModelResourceLocation getRightModel() {
        return null;
    }

    @Override
    protected HusbandryAutomataCorePeripheral buildPeripheral(@NotNull ITurtleAccess turtle, @NotNull TurtleSide side) {
        return new HusbandryAutomataCorePeripheral(turtle, side);
    }
}
