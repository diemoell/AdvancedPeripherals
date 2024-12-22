package de.srendi.advancedperipherals.common.setup;

import dan200.computercraft.api.pocket.IPocketUpgrade;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.upgrades.UpgradeType;
import dan200.computercraft.shared.platform.PlatformHelper;
import dan200.computercraft.shared.platform.RegistrationHelper;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.integrations.IntegrationPeripheralProvider;
import de.srendi.advancedperipherals.common.addons.computercraft.pocket.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Supplier;

// @EventBusSubscriber(modid = AdvancedPeripherals.MOD_ID)
public class CCRegistration {

    public static IntegrationPeripheralProvider integrationPeripheralProvider;

    /*public static void register() {
        IntegrationPeripheralProvider.load();
        integrationPeripheralProvider = new IntegrationPeripheralProvider();
        ForgeComputerCraftAPI.registerGenericCapability(integrationPeripheralProvider);
    }*/

    public static class ID {

        public static final ResourceLocation CHATTY_TURTLE = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "chatty_turtle");
        public static final ResourceLocation PLAYER_TURTLE = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "player_turtle");
        public static final ResourceLocation ENVIRONMENT_TURTLE = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "environment_turtle");
        public static final ResourceLocation CHUNKY_TURTLE = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "chunky_turtle");
        public static final ResourceLocation GEOSCANNER_TURTLE = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "geoscanner_turtle");
        public static final ResourceLocation COMPASS_TURTLE = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "compass_turtle");
        public static final ResourceLocation WEAK_AUTOMATA = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "weak_automata");
        public static final ResourceLocation END_AUTOMATA = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "end_automata");
        public static final ResourceLocation HUSBANDRY_AUTOMATA = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "husbandry_automata");
        public static final ResourceLocation OP_WEAK_AUTOMATA = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "overpowered_weak_automata");
        public static final ResourceLocation OP_END_AUTOMATA = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "overpowered_end_automata");
        public static final ResourceLocation OP_HUSBANDRY_AUTOMATA = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "overpowered_husbandry_automata");

        public static final ResourceLocation CHATTY_POCKET = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "chatty_pocket");
        public static final ResourceLocation PLAYER_POCKET = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "player_pocket");
        public static final ResourceLocation ENVIRONMENT_POCKET = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "environment_pocket");
        public static final ResourceLocation GEOSCANNER_POCKET = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "geoscanner_pocket");
        public static final ResourceLocation COLONY_POCKET = ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "colony_pocket");

    }
}
