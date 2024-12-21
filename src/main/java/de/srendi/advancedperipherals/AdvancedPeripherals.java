package de.srendi.advancedperipherals;

import dan200.computercraft.api.peripheral.PeripheralCapability;
import de.srendi.advancedperipherals.common.addons.APAddons;
import de.srendi.advancedperipherals.common.blocks.base.PeripheralBlockEntity;
import de.srendi.advancedperipherals.common.configuration.APConfig;
import de.srendi.advancedperipherals.common.setup.Registration;
import de.srendi.advancedperipherals.network.APNetworking;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(AdvancedPeripherals.MOD_ID)
public class AdvancedPeripherals {

    public static final String MOD_ID = "advancedperipherals";
    public static final String NAME = "Advanced Peripherals";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final Random RANDOM = new Random();

    public AdvancedPeripherals(IEventBus modBus) {
        LOGGER.info("AdvancedPeripherals says hello!");

        APConfig.register(ModLoadingContext.get());

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::registerCapabilities);

        Registration.register(modBus);
    }

    public static void debug(String message) {
        if (APConfig.GENERAL_CONFIG.enableDebugMode.get())
            LOGGER.info("[DEBUG] {}", message);
    }

    public static void debug(String message, Level level) {
        if (APConfig.GENERAL_CONFIG.enableDebugMode.get())
            LOGGER.log(level, "[DEBUG] {}", message);
    }

    public static ResourceLocation getRL(String resource) {
        return new ResourceLocation(MOD_ID, resource);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        APAddons.commonSetup();
        APNetworking.init();
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        Registration.BLOCK_ENTITIES.getEntries().forEach((entry) -> {
            event.registerBlockEntity(
                    PeripheralCapability.get(),
                    entry.get(),
                    (blockEntity, side) -> ((PeripheralBlockEntity<?>) blockEntity).createCapPeripheral());
            event.registerBlockEntity(
                    Capabilities.ItemHandler.BLOCK,
                    entry.get(),
                    (blockEntity, side) -> ((PeripheralBlockEntity<?>) blockEntity).createItemHandler());
            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    entry.get(),
                    (blockEntity, side) -> ((PeripheralBlockEntity<?>) blockEntity).createFluidHandler());
        });
    }

}
