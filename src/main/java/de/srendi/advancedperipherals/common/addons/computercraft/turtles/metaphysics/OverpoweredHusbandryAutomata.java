package de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics;

import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.upgrades.UpgradeType;
import de.srendi.advancedperipherals.client.ClientRegistry;
import de.srendi.advancedperipherals.common.addons.computercraft.peripheral.metaphysics.OverpoweredHusbandryAutomataCorePeripheral;
import de.srendi.advancedperipherals.common.setup.CCRegistration;
import de.srendi.advancedperipherals.lib.turtle.ClockwiseAnimatedTurtleUpgrade;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OverpoweredHusbandryAutomata extends ClockwiseAnimatedTurtleUpgrade<OverpoweredHusbandryAutomataCorePeripheral> {

    public OverpoweredHusbandryAutomata(ItemStack stack) {
        super(CCRegistration.ID.OP_HUSBANDRY_AUTOMATA, stack);
    }

    @Override
    public ModelResourceLocation getLeftModel() {
        return null;
    }

    @Override
    public ModelResourceLocation getRightModel() {
        return null;
    }

    @Override
    protected OverpoweredHusbandryAutomataCorePeripheral buildPeripheral(@NotNull ITurtleAccess turtle, @NotNull TurtleSide side) {
        return new OverpoweredHusbandryAutomataCorePeripheral(turtle, side);
    }

    @Override
    public UpgradeType<? extends ITurtleUpgrade> getType() {
        return ClientRegistry.OP_HUSBANDRY_TURTLE.get();
    }
}
