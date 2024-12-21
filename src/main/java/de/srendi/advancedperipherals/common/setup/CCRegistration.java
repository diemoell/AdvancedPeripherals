package de.srendi.advancedperipherals.common.setup;

import dan200.computercraft.api.pocket.IPocketUpgrade;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.upgrades.UpgradeSerialiser;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.integrations.IntegrationPeripheralProvider;
import de.srendi.advancedperipherals.common.addons.computercraft.pocket.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CCRegistration {

    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<TurtleChatBoxUpgrade>> CHAT_BOX_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.CHATTY_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleChatBoxUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<TurtlePlayerDetectorUpgrade>> PLAYER_DETECTOR_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.PLAYER_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtlePlayerDetectorUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<TurtleEnvironmentDetectorUpgrade>> ENVIRONMENT_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.ENVIRONMENT_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleEnvironmentDetectorUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<TurtleChunkyUpgrade>> CHUNKY_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.CHUNKY_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleChunkyUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<TurtleGeoScannerUpgrade>> GEO_SCANNER_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.GEOSCANNER_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleGeoScannerUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<TurtleCompassUpgrade>> COMPASS_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.COMPASS_TURTLE.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(TurtleCompassUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<WeakAutomata>> WEAK_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.WEAK_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(WeakAutomata::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<EndAutomata>> END_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.END_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(EndAutomata::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<HusbandryAutomata>> HUSBANDRY_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(HusbandryAutomata::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<OverpoweredWeakAutomata>> OP_WEAK_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.OP_WEAK_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(OverpoweredWeakAutomata::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<OverpoweredEndAutomata>> OP_END_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.OP_END_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(OverpoweredEndAutomata::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends ITurtleUpgrade>, UpgradeSerialiser<OverpoweredHusbandryAutomata>> OP_HUSBANDRY_TURTLE = Registration.TURTLE_SERIALIZER.register(ID.OP_HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(OverpoweredHusbandryAutomata::new));

    public static final DeferredHolder<UpgradeSerialiser<? extends IPocketUpgrade>, UpgradeSerialiser<PocketChatBoxUpgrade>> CHAT_BOX_POCKET = Registration.POCKET_SERIALIZER.register(ID.CHATTY_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketChatBoxUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends IPocketUpgrade>, UpgradeSerialiser<PocketPlayerDetectorUpgrade>> PLAYER_DETECTOR_POCKET = Registration.POCKET_SERIALIZER.register(ID.PLAYER_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketPlayerDetectorUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends IPocketUpgrade>, UpgradeSerialiser<PocketEnvironmentUpgrade>> ENVIRONMENT_POCKET = Registration.POCKET_SERIALIZER.register(ID.ENVIRONMENT_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketEnvironmentUpgrade::new));
    public static final DeferredHolder<UpgradeSerialiser<? extends IPocketUpgrade>, UpgradeSerialiser<PocketGeoScannerUpgrade>> GEO_SCANNER_POCKET = Registration.POCKET_SERIALIZER.register(ID.GEOSCANNER_POCKET.getPath(), () -> UpgradeSerialiser.simpleWithCustomItem(PocketGeoScannerUpgrade::new));

    public static IntegrationPeripheralProvider integrationPeripheralProvider;

    public static void register() {
        IntegrationPeripheralProvider.load();
        integrationPeripheralProvider = new IntegrationPeripheralProvider();
        //ForgeComputerCraftAPI.registerPeripheralProvider(integrationPeripheralProvider);
    }

    public static class ID {

        public static final ResourceLocation CHATTY_TURTLE = new ResourceLocation(AdvancedPeripherals.MOD_ID, "chatty_turtle");
        public static final ResourceLocation PLAYER_TURTLE = new ResourceLocation(AdvancedPeripherals.MOD_ID, "player_turtle");
        public static final ResourceLocation ENVIRONMENT_TURTLE = new ResourceLocation(AdvancedPeripherals.MOD_ID, "environment_turtle");
        public static final ResourceLocation CHUNKY_TURTLE = new ResourceLocation(AdvancedPeripherals.MOD_ID, "chunky_turtle");
        public static final ResourceLocation GEOSCANNER_TURTLE = new ResourceLocation(AdvancedPeripherals.MOD_ID, "geoscanner_turtle");
        public static final ResourceLocation COMPASS_TURTLE = new ResourceLocation(AdvancedPeripherals.MOD_ID, "compass_turtle");
        public static final ResourceLocation WEAK_AUTOMATA = new ResourceLocation(AdvancedPeripherals.MOD_ID, "weak_automata");
        public static final ResourceLocation END_AUTOMATA = new ResourceLocation(AdvancedPeripherals.MOD_ID, "end_automata");
        public static final ResourceLocation HUSBANDRY_AUTOMATA = new ResourceLocation(AdvancedPeripherals.MOD_ID, "husbandry_automata");
        public static final ResourceLocation OP_WEAK_AUTOMATA = new ResourceLocation(AdvancedPeripherals.MOD_ID, "overpowered_weak_automata");
        public static final ResourceLocation OP_END_AUTOMATA = new ResourceLocation(AdvancedPeripherals.MOD_ID, "overpowered_end_automata");
        public static final ResourceLocation OP_HUSBANDRY_AUTOMATA = new ResourceLocation(AdvancedPeripherals.MOD_ID, "overpowered_husbandry_automata");

        public static final ResourceLocation CHATTY_POCKET = new ResourceLocation(AdvancedPeripherals.MOD_ID, "chatty_pocket");
        public static final ResourceLocation PLAYER_POCKET = new ResourceLocation(AdvancedPeripherals.MOD_ID, "player_pocket");
        public static final ResourceLocation ENVIRONMENT_POCKET = new ResourceLocation(AdvancedPeripherals.MOD_ID, "environment_pocket");
        public static final ResourceLocation GEOSCANNER_POCKET = new ResourceLocation(AdvancedPeripherals.MOD_ID, "geoscanner_pocket");
        public static final ResourceLocation COLONY_POCKET = new ResourceLocation(AdvancedPeripherals.MOD_ID, "colony_pocket");

    }
}
