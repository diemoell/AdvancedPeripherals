package de.srendi.advancedperipherals.client;

import com.google.common.eventbus.Subscribe;
import dan200.computercraft.api.client.turtle.RegisterTurtleModellersEvent;
import dan200.computercraft.api.client.turtle.TurtleUpgradeModeller;
import dan200.computercraft.api.upgrades.UpgradeType;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.pocket.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics.*;
import de.srendi.advancedperipherals.common.container.InventoryManagerScreen;
import de.srendi.advancedperipherals.common.setup.CCRegistration;
import de.srendi.advancedperipherals.common.setup.ContainerTypes;
import de.srendi.advancedperipherals.common.setup.Registration;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AdvancedPeripherals.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistry {

    public static final Supplier<UpgradeType<TurtleChunkyUpgrade>> CHUNKY_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.CHUNKY_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleChunkyUpgrade::new));
    public static final Supplier<UpgradeType<TurtleChatBoxUpgrade>> CHAT_BOX_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.CHATTY_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleChatBoxUpgrade::new));
    public static final Supplier<UpgradeType<TurtlePlayerDetectorUpgrade>> PLAYER_DETECTOR_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.PLAYER_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtlePlayerDetectorUpgrade::new));
    public static final Supplier<UpgradeType<TurtleEnvironmentDetectorUpgrade>> ENVIRONMENT_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.ENVIRONMENT_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleEnvironmentDetectorUpgrade::new));
    public static final Supplier<UpgradeType<TurtleGeoScannerUpgrade>> GEO_SCANNER_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.GEOSCANNER_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleGeoScannerUpgrade::new));
    public static final Supplier<UpgradeType<TurtleCompassUpgrade>> COMPASS_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.COMPASS_TURTLE.getPath(), () -> UpgradeType.simpleWithCustomItem(TurtleCompassUpgrade::new));
    public static final Supplier<UpgradeType<WeakAutomata>> WEAK_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.WEAK_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(WeakAutomata::new));
    public static final Supplier<UpgradeType<EndAutomata>> END_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.END_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(EndAutomata::new));
    public static final Supplier<UpgradeType<HusbandryAutomata>> HUSBANDRY_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(HusbandryAutomata::new));
    public static final Supplier<UpgradeType<OverpoweredWeakAutomata>> OP_WEAK_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.OP_WEAK_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(OverpoweredWeakAutomata::new));
    public static final Supplier<UpgradeType<OverpoweredEndAutomata>> OP_END_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.OP_END_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(OverpoweredEndAutomata::new));
    public static final Supplier<UpgradeType<OverpoweredHusbandryAutomata>> OP_HUSBANDRY_TURTLE = Registration.TURTLE_SERIALIZER.register(CCRegistration.ID.OP_HUSBANDRY_AUTOMATA.getPath(), () -> UpgradeType.simpleWithCustomItem(OverpoweredHusbandryAutomata::new));

    public static final Supplier<UpgradeType<PocketChatBoxUpgrade>>             CHAT_BOX_POCKET = Registration.POCKET_SERIALIZER.register(CCRegistration.ID.CHATTY_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketChatBoxUpgrade::new));
    public static final Supplier<UpgradeType<PocketPlayerDetectorUpgrade>>      PLAYER_DETECTOR_POCKET = Registration.POCKET_SERIALIZER.register(CCRegistration.ID.PLAYER_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketPlayerDetectorUpgrade::new));
    public static final Supplier<UpgradeType<PocketEnvironmentUpgrade>>         ENVIRONMENT_POCKET = Registration.POCKET_SERIALIZER.register(CCRegistration.ID.ENVIRONMENT_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketEnvironmentUpgrade::new));
    public static final Supplier<UpgradeType<PocketGeoScannerUpgrade>>          GEO_SCANNER_POCKET = Registration.POCKET_SERIALIZER.register(CCRegistration.ID.GEOSCANNER_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketGeoScannerUpgrade::new));
    public static final Supplier<UpgradeType<PocketColonyIntegratorUpgrade>>    COLONY_POCKET = Registration.POCKET_SERIALIZER.register(CCRegistration.ID.COLONY_POCKET.getPath(), () -> UpgradeType.simpleWithCustomItem(PocketColonyIntegratorUpgrade::new));

    private static final String[] TURTLE_MODELS = new String[]{"turtle_chat_box_upgrade_left", "turtle_chat_box_upgrade_right", "turtle_environment_upgrade_left", "turtle_environment_upgrade_right", "turtle_player_upgrade_left", "turtle_player_upgrade_right", "turtle_geoscanner_upgrade_left", "turtle_geoscanner_upgrade_right"};

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        for (String model : TURTLE_MODELS) {
            event.register(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, model), "standalone"));
        }
    }

    @SubscribeEvent
    public static void menuRegister(RegisterMenuScreensEvent event) {
        event.register(ContainerTypes.INVENTORY_MANAGER_CONTAINER.get(), InventoryManagerScreen::new);
    }

    @SubscribeEvent
    public static void onClientSetup(RegisterTurtleModellersEvent event) {

        event.register(CHUNKY_TURTLE.get(), TurtleUpgradeModeller.flatItem());
        event.register(COMPASS_TURTLE.get(), TurtleUpgradeModeller.flatItem());
        event.register(CHAT_BOX_TURTLE.get(), TurtleUpgradeModeller.sided(AdvancedPeripherals.getRL("turtle_chat_box_upgrade_left"), AdvancedPeripherals.getRL("turtle_chat_box_upgrade_right")));
        event.register(ENVIRONMENT_TURTLE.get(), TurtleUpgradeModeller.sided(AdvancedPeripherals.getRL("turtle_environment_upgrade_left"), AdvancedPeripherals.getRL("turtle_environment_upgrade_right")));
        event.register(GEO_SCANNER_TURTLE.get(), TurtleUpgradeModeller.sided(AdvancedPeripherals.getRL("turtle_geoscanner_upgrade_left"), AdvancedPeripherals.getRL("turtle_geoscanner_upgrade_right")));
        event.register(PLAYER_DETECTOR_TURTLE.get(), TurtleUpgradeModeller.sided(AdvancedPeripherals.getRL("turtle_player_upgrade_left"), AdvancedPeripherals.getRL("turtle_player_upgrade_right")));
        event.register(OP_END_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        event.register(OP_HUSBANDRY_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        event.register(OP_WEAK_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        event.register(HUSBANDRY_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        event.register(END_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
        event.register(WEAK_TURTLE.get(), new MetaTurtleUpgradeModeller<>());
    }

    @SubscribeEvent
    public static void onClientSetup(RegisterKeyMappingsEvent event) {
        KeyBindings.register(event);
    }

    //TODO change the icon of the curio icon
    /*@SubscribeEvent
    public static void onTextureStitching(TextureStitchEvent.Pre event) {
        event.addSprite(ResourceLocation.fromNamespaceAndPath(AdvancedPeripherals.MOD_ID, "item/empty_glasses_slot"));
    }*/
}
