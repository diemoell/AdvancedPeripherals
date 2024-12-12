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
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AdvancedPeripherals.MOD_ID)
public class CCRegistration {
    static final RegistrationHelper<UpgradeType<? extends ITurtleUpgrade>> TURTLE_REGISTRY = PlatformHelper.get().createRegistrationHelper(ITurtleUpgrade.typeRegistry());

    public static final Supplier<UpgradeType<TurtleChatBoxUpgrade>> CHAT_BOX_TURTLE = TURTLE_REGISTRY.register(ID.CHATTY_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleChatBoxUpgrade::new));
    public static final Supplier<UpgradeType<TurtlePlayerDetectorUpgrade>> PLAYER_DETECTOR_TURTLE = TURTLE_REGISTRY.register(ID.PLAYER_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtlePlayerDetectorUpgrade::new));
    public static final Supplier<UpgradeType<TurtleEnvironmentDetectorUpgrade>> ENVIRONMENT_TURTLE = TURTLE_REGISTRY.register(ID.ENVIRONMENT_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleEnvironmentDetectorUpgrade::new));
    public static final Supplier<UpgradeType<TurtleChunkyUpgrade>> CHUNKY_TURTLE = TURTLE_REGISTRY.register(ID.CHUNKY_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleChunkyUpgrade::new));
    public static final Supplier<UpgradeType<TurtleGeoScannerUpgrade>> GEO_SCANNER_TURTLE = TURTLE_REGISTRY.register(ID.GEOSCANNER_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleGeoScannerUpgrade::new));
    public static final Supplier<UpgradeType<TurtleCompassUpgrade>> COMPASS_TURTLE = TURTLE_REGISTRY.register(ID.COMPASS_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleCompassUpgrade::new));
    public static final Supplier<UpgradeType<WeakAutomata>> WEAK_TURTLE = TURTLE_REGISTRY.register(ID.WEAK_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(WeakAutomata::new));
    public static final Supplier<UpgradeType<EndAutomata>> END_TURTLE = TURTLE_REGISTRY.register(ID.END_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(EndAutomata::new));
    public static final Supplier<UpgradeType<HusbandryAutomata>> HUSBANDRY_TURTLE = TURTLE_REGISTRY.register(ID.HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(HusbandryAutomata::new));
    public static final Supplier<UpgradeType<OverpoweredWeakAutomata>> OP_WEAK_TURTLE = TURTLE_REGISTRY.register(ID.OP_WEAK_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(OverpoweredWeakAutomata::new));
    public static final Supplier<UpgradeType<OverpoweredEndAutomata>> OP_END_TURTLE = TURTLE_REGISTRY.register(ID.OP_END_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(OverpoweredEndAutomata::new));
    public static final Supplier<UpgradeType<OverpoweredHusbandryAutomata>> OP_HUSBANDRY_TURTLE = TURTLE_REGISTRY.register(ID.OP_HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(OverpoweredHusbandryAutomata::new));

    static final RegistrationHelper<UpgradeType<? extends IPocketUpgrade>> POCKET_REGISTRY = PlatformHelper.get().createRegistrationHelper(IPocketUpgrade.typeRegistry());

    public static final Supplier<UpgradeType<PocketChatBoxUpgrade>> CHAT_BOX_POCKET = POCKET_REGISTRY.register(ID.CHATTY_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketChatBoxUpgrade::new));
    public static final Supplier<UpgradeType<PocketPlayerDetectorUpgrade>> PLAYER_DETECTOR_POCKET = POCKET_REGISTRY.register(ID.PLAYER_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketPlayerDetectorUpgrade::new));
    public static final Supplier<UpgradeType<PocketEnvironmentUpgrade>> ENVIRONMENT_POCKET = POCKET_REGISTRY.register(ID.ENVIRONMENT_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketEnvironmentUpgrade::new));
    public static final Supplier<UpgradeType<PocketGeoScannerUpgrade>> GEO_SCANNER_POCKET = POCKET_REGISTRY.register(ID.GEOSCANNER_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketGeoScannerUpgrade::new));
    public static final Supplier<UpgradeType<PocketColonyIntegratorUpgrade>> COLONY_POCKET = POCKET_REGISTRY.register(ID.COLONY_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketColonyIntegratorUpgrade::new));

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
