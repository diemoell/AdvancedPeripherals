package de.srendi.advancedperipherals.common.data;

import dan200.computercraft.api.turtle.ITurtleUpgrade;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.*;
import de.srendi.advancedperipherals.common.setup.Blocks;
import de.srendi.advancedperipherals.common.setup.CCRegistration;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PocketUpgradesProvider {

    public static void generate(DataGenerator.PackGenerator output, CompletableFuture<HolderLookup.Provider> registries) {
        var newRegistries = RegistryPatchGenerator.createLookup(registries, Util.make(new RegistrySetBuilder(), builder -> {
            builder.add(ITurtleUpgrade.REGISTRY, upgrades -> {
                upgrades.register(id(CCRegistration.ID.CHATTY_POCKET), new TurtleChatBoxUpgrade(new ItemStack(Blocks.CHAT_BOX.get())));
                upgrades.register(id(CCRegistration.ID.PLAYER_POCKET), new TurtlePlayerDetectorUpgrade(new ItemStack(Blocks.PLAYER_DETECTOR.get())));
                upgrades.register(id(CCRegistration.ID.ENVIRONMENT_POCKET), new TurtleEnvironmentDetectorUpgrade(new ItemStack(Blocks.ENVIRONMENT_DETECTOR.get())));
                upgrades.register(id(CCRegistration.ID.GEOSCANNER_TURTLE), new TurtleGeoScannerUpgrade(new ItemStack(Blocks.GEO_SCANNER.get())));
                upgrades.register(id(CCRegistration.ID.COLONY_POCKET), new TurtleChunkyUpgrade(new ItemStack(Blocks.COLONY_INTEGRATOR.get())));
            });
        }));
        output.addProvider(o -> new DatapackBuiltinEntriesProvider(o, newRegistries, Set.of(AdvancedPeripherals.MOD_ID)));
    }

    /*@Override
    protected void addUpgrades(Consumer<Upgrade<UpgradeSerialiser<? extends IPocketUpgrade>>> addUpgrade) {
        simpleWithCustomItem(CCRegistration.ID.CHATTY_POCKET, CCRegistration.CHAT_BOX_POCKET.get(), Blocks.CHAT_BOX.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.PLAYER_POCKET, CCRegistration.PLAYER_DETECTOR_POCKET.get(), Blocks.PLAYER_DETECTOR.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.ENVIRONMENT_POCKET, CCRegistration.ENVIRONMENT_POCKET.get(), Blocks.ENVIRONMENT_DETECTOR.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.GEOSCANNER_POCKET, CCRegistration.GEO_SCANNER_POCKET.get(), Blocks.GEO_SCANNER.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.COLONY_POCKET, CCRegistration.COLONY_POCKET.get(), Blocks.COLONY_INTEGRATOR.get().asItem()).add(addUpgrade);
    }*/

    public static ResourceKey<ITurtleUpgrade> id(ResourceLocation id) {
        return ITurtleUpgrade.createKey(id);
    }
}
