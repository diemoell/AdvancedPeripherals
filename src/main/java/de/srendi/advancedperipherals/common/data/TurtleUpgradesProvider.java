package de.srendi.advancedperipherals.common.data;

import dan200.computercraft.api.turtle.ITurtleUpgrade;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.*;
import de.srendi.advancedperipherals.common.addons.computercraft.turtles.metaphysics.*;
import de.srendi.advancedperipherals.common.setup.Blocks;
import de.srendi.advancedperipherals.common.setup.CCRegistration;
import de.srendi.advancedperipherals.common.setup.Items;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TurtleUpgradesProvider{

    public static void generate(DataGenerator.PackGenerator output, CompletableFuture<HolderLookup.Provider> registries) {
        var newRegistries = RegistryPatchGenerator.createLookup(registries, Util.make(new RegistrySetBuilder(), builder -> {
            builder.add(ITurtleUpgrade.REGISTRY, upgrades -> {
                upgrades.register(id(CCRegistration.ID.CHATTY_TURTLE), new TurtleChatBoxUpgrade(new ItemStack(Blocks.CHAT_BOX.get())));
                upgrades.register(id(CCRegistration.ID.PLAYER_TURTLE), new TurtlePlayerDetectorUpgrade(new ItemStack(Blocks.PLAYER_DETECTOR.get())));
                upgrades.register(id(CCRegistration.ID.ENVIRONMENT_TURTLE), new TurtleEnvironmentDetectorUpgrade(new ItemStack(Blocks.ENVIRONMENT_DETECTOR.get())));
                upgrades.register(id(CCRegistration.ID.CHUNKY_TURTLE), new TurtleChunkyUpgrade(new ItemStack(Items.CHUNK_CONTROLLER)));
                upgrades.register(id(CCRegistration.ID.GEOSCANNER_TURTLE), new TurtleGeoScannerUpgrade(new ItemStack(Blocks.ENVIRONMENT_DETECTOR.get())));
                upgrades.register(id(CCRegistration.ID.COMPASS_TURTLE), new TurtleCompassUpgrade(new ItemStack(net.minecraft.world.item.Items.COMPASS)));
                upgrades.register(id(CCRegistration.ID.WEAK_AUTOMATA), new WeakAutomata(new ItemStack(Items.WEAK_AUTOMATA_CORE.get())));
                upgrades.register(id(CCRegistration.ID.END_AUTOMATA), new EndAutomata(new ItemStack(Items.END_AUTOMATA_CORE.get())));
                upgrades.register(id(CCRegistration.ID.HUSBANDRY_AUTOMATA), new HusbandryAutomata(new ItemStack(Items.HUSBANDRY_AUTOMATA_CORE.get())));
                upgrades.register(id(CCRegistration.ID.OP_WEAK_AUTOMATA), new OverpoweredWeakAutomata(new ItemStack(Items.OVERPOWERED_WEAK_AUTOMATA_CORE.get())));
                upgrades.register(id(CCRegistration.ID.OP_END_AUTOMATA), new OverpoweredEndAutomata(new ItemStack(Items.OVERPOWERED_END_AUTOMATA_CORE.get())));
                upgrades.register(id(CCRegistration.ID.OP_HUSBANDRY_AUTOMATA),new OverpoweredHusbandryAutomata(new ItemStack(Items.OVERPOWERED_HUSBANDRY_AUTOMATA_CORE.get())));
            });
        }));
        output.addProvider(o -> new DatapackBuiltinEntriesProvider(o, newRegistries, Set.of(AdvancedPeripherals.MOD_ID)));
    }

    public static ResourceKey<ITurtleUpgrade> id (ResourceLocation id) {
        return ITurtleUpgrade.createKey(id);
    }

    /*public static void addUpgrades(BootstrapContext<ITurtleUpgrade> upgrades) {
        upgrades.register(id(CCRegistration.ID.CHATTY_TURTLE), new TurtleChatBoxUpgrade(new ItemStack(Blocks.CHAT_BOX.get())));
        upgrades.register(id(CCRegistration.ID.PLAYER_TURTLE), new TurtlePlayerDetectorUpgrade(new ItemStack(Blocks.PLAYER_DETECTOR.get())));
        upgrades.register(id(CCRegistration.ID.ENVIRONMENT_TURTLE), new TurtleEnvironmentDetectorUpgrade(new ItemStack(Blocks.ENVIRONMENT_DETECTOR.get())));
        upgrades.register(id(CCRegistration.ID.CHUNKY_TURTLE), new TurtleChunkyUpgrade(new ItemStack(Items.CHUNK_CONTROLLER)));
        upgrades.register(id(CCRegistration.ID.GEOSCANNER_TURTLE), new TurtleGeoScannerUpgrade(new ItemStack(Blocks.ENVIRONMENT_DETECTOR.get())));
        upgrades.register(id(CCRegistration.ID.COMPASS_TURTLE), new TurtleCompassUpgrade(new ItemStack(net.minecraft.world.item.Items.COMPASS)));
        upgrades.register(id(CCRegistration.ID.WEAK_AUTOMATA), new WeakAutomata(new ItemStack(Items.WEAK_AUTOMATA_CORE.get())));
        upgrades.register(id(CCRegistration.ID.END_AUTOMATA), new EndAutomata(new ItemStack(Items.END_AUTOMATA_CORE.get())));
        upgrades.register(id(CCRegistration.ID.HUSBANDRY_AUTOMATA), new HusbandryAutomata(new ItemStack(Items.HUSBANDRY_AUTOMATA_CORE.get())));
        upgrades.register(id(CCRegistration.ID.OP_WEAK_AUTOMATA), new OverpoweredWeakAutomata(new ItemStack(Items.OVERPOWERED_WEAK_AUTOMATA_CORE.get())));
        upgrades.register(id(CCRegistration.ID.OP_END_AUTOMATA), new OverpoweredEndAutomata(new ItemStack(Items.OVERPOWERED_END_AUTOMATA_CORE.get())));
        upgrades.register(id(CCRegistration.ID.OP_HUSBANDRY_AUTOMATA),new OverpoweredHusbandryAutomata(new ItemStack(Items.OVERPOWERED_HUSBANDRY_AUTOMATA_CORE.get())));
    }*/

/*
    @Override
    protected void addUpgrades(Consumer<Upgrade<UpgradeSerialiser<? extends ITurtleUpgrade>>> addUpgrade) {
        simpleWithCustomItem(CCRegistration.ID.CHATTY_TURTLE, CCRegistration.CHAT_BOX_TURTLE.get(), Blocks.CHAT_BOX.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.PLAYER_TURTLE, CCRegistration.PLAYER_DETECTOR_TURTLE.get(), Blocks.PLAYER_DETECTOR.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.ENVIRONMENT_TURTLE, CCRegistration.ENVIRONMENT_TURTLE.get(), Blocks.ENVIRONMENT_DETECTOR.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.CHUNKY_TURTLE, CCRegistration.CHUNKY_TURTLE.get(), Items.CHUNK_CONTROLLER.get()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.GEOSCANNER_TURTLE, CCRegistration.GEO_SCANNER_TURTLE.get(), Blocks.GEO_SCANNER.get().asItem()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.COMPASS_TURTLE, CCRegistration.COMPASS_TURTLE.get(), net.minecraft.world.item.Items.COMPASS).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.WEAK_AUTOMATA, CCRegistration.WEAK_TURTLE.get(), Items.WEAK_AUTOMATA_CORE.get()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.END_AUTOMATA, CCRegistration.END_TURTLE.get(), Items.END_AUTOMATA_CORE.get()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.HUSBANDRY_AUTOMATA, CCRegistration.HUSBANDRY_TURTLE.get(), Items.HUSBANDRY_AUTOMATA_CORE.get()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.OP_WEAK_AUTOMATA, CCRegistration.OP_WEAK_TURTLE.get(), Items.OVERPOWERED_WEAK_AUTOMATA_CORE.get()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.OP_END_AUTOMATA, CCRegistration.OP_END_TURTLE.get(), Items.OVERPOWERED_END_AUTOMATA_CORE.get()).add(addUpgrade);
        simpleWithCustomItem(CCRegistration.ID.OP_HUSBANDRY_AUTOMATA, CCRegistration.OP_HUSBANDRY_TURTLE.get(), Items.OVERPOWERED_HUSBANDRY_AUTOMATA_CORE.get()).add(addUpgrade);

    }*/

}
