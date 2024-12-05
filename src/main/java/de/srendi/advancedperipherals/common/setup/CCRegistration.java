package de.srendi.advancedperipherals.common.setup;

import dan200.computercraft.api.ForgeComputerCraftAPI;
import dan200.computercraft.api.upgrades.UpgradeSerialiser;
import dan200.computercraft.impl.ComputerCraftAPIForgeService;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.integrations.IntegrationPeripheralProvider;
import de.srendi.advancedperipherals.common.addons.computercraft.pocket.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AdvancedPeripherals.MOD_ID)
public class CCRegistration {

    public static final Supplier<UpgradeSerialiser<TurtleChatBoxUpgrade>> CHAT_BOX_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.CHATTY_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleChatBoxUpgrade::new));
    public static final Supplier<UpgradeSerialiser<TurtlePlayerDetectorUpgrade>> PLAYER_DETECTOR_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.PLAYER_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtlePlayerDetectorUpgrade::new));
    public static final Supplier<UpgradeSerialiser<TurtleEnvironmentDetectorUpgrade>> ENVIRONMENT_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.ENVIRONMENT_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleEnvironmentDetectorUpgrade::new));
    public static final Supplier<UpgradeSerialiser<TurtleChunkyUpgrade>> CHUNKY_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.CHUNKY_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleChunkyUpgrade::new));
    public static final Supplier<UpgradeSerialiser<TurtleGeoScannerUpgrade>> GEO_SCANNER_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.GEOSCANNER_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleGeoScannerUpgrade::new));
    public static final Supplier<UpgradeSerialiser<TurtleCompassUpgrade>> COMPASS_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.COMPASS_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleCompassUpgrade::new));
    public static final Supplier<UpgradeSerialiser<WeakAutomata>> WEAK_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.WEAK_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(WeakAutomata::new));
    public static final Supplier<UpgradeSerialiser<EndAutomata>> END_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.END_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(EndAutomata::new));
    public static final Supplier<UpgradeSerialiser<HusbandryAutomata>> HUSBANDRY_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(HusbandryAutomata::new));
    public static final Supplier<UpgradeSerialiser<OverpoweredWeakAutomata>> OP_WEAK_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.OP_WEAK_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(OverpoweredWeakAutomata::new));
    public static final Supplier<UpgradeSerialiser<OverpoweredEndAutomata>> OP_END_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.OP_END_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(OverpoweredEndAutomata::new));
    public static final Supplier<UpgradeSerialiser<OverpoweredHusbandryAutomata>> OP_HUSBANDRY_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.OP_HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(OverpoweredHusbandryAutomata::new));

    public static final Supplier<UpgradeSerialiser<PocketChatBoxUpgrade>> CHAT_BOX_POCKET = Registration.POCKET_SERIALIZER.register(ID.CHATTY_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketChatBoxUpgrade::new));
    public static final Supplier<UpgradeSerialiser<PocketPlayerDetectorUpgrade>> PLAYER_DETECTOR_POCKET = Registration.POCKET_SERIALIZER.register(ID.PLAYER_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketPlayerDetectorUpgrade::new));
    public static final Supplier<UpgradeSerialiser<PocketEnvironmentUpgrade>> ENVIRONMENT_POCKET = Registration.POCKET_SERIALIZER.register(ID.ENVIRONMENT_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketEnvironmentUpgrade::new));
    public static final Supplier<UpgradeSerialiser<PocketGeoScannerUpgrade>> GEO_SCANNER_POCKET = Registration.POCKET_SERIALIZER.register(ID.GEOSCANNER_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketGeoScannerUpgrade::new));
    public static final Supplier<UpgradeSerialiser<PocketColonyIntegratorUpgrade>> COLONY_POCKET = Registration.POCKET_SERIALIZER.register(ID.COLONY_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketColonyIntegratorUpgrade::new));

    public static IntegrationPeripheralProvider integrationPeripheralProvider;

    public static void register() {
        IntegrationPeripheralProvider.load();
        integrationPeripheralProvider = new IntegrationPeripheralProvider();
        ForgeComputerCraftAPI.registerPeripheralProvider(integrationPeripheralProvider);
    }

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
