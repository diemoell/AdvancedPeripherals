package de.srendi.advancedperipherals.common.village;

import com.mojang.datafixers.util.Pair;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.configuration.APConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = AdvancedPeripherals.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class VillageStructures {

    // Adapted from Pneumaticcraft
    private static void addPieceToPool(Registry<StructureTemplatePool> templatePoolRegistry, Holder<StructureProcessorList> emptyProcessor, ResourceLocation poolRL, String nbtPieceRL, StructureTemplatePool.Projection projection, int weight) {
        // Grab the pool we want to add to
        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null)
            return;

        // Grabs the nbt piece and creates a SingleJigsawPiece of it that we can add to a structure's pool.
        // Note: street pieces are a legacy_single_pool_piece type, houses are single_pool_piece
        SinglePoolElement piece = poolRL.getPath().endsWith("streets") ?
                SinglePoolElement.legacy(nbtPieceRL, emptyProcessor).apply(projection) :
                SinglePoolElement.single(nbtPieceRL, emptyProcessor).apply(projection);

        // Use reflection to get the private fields
        Field templatesField = null;
        Field rawTemplatesField = null;
        try {
            templatesField = StructureTemplatePool.class.getDeclaredField("templates");
            rawTemplatesField = StructureTemplatePool.class.getDeclaredField("rawTemplates");
            templatesField.setAccessible(true);
            rawTemplatesField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return;
        }

        // Modify the templates field
        try {
            List<StructurePoolElement> templates = (List<StructurePoolElement>) templatesField.get(pool);
            for (int i = 0; i < weight; i++) {
                templates.add(piece);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Modify the rawTemplates field
        try {
            List<Pair<StructurePoolElement, Integer>> rawTemplates = (List<Pair<StructurePoolElement, Integer>>) rawTemplatesField.get(pool);
            List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(rawTemplates);
            listOfPieceEntries.add(new Pair<>(piece, weight));
            rawTemplatesField.set(pool, listOfPieceEntries);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void addStructures(ServerAboutToStartEvent event) {
        if (!APConfig.WORLD_CONFIG.enableVillagerStructures.get())
            return;

        Holder<StructureProcessorList> emptyProcessor = event.getServer().registryAccess().registryOrThrow(Registries.PROCESSOR_LIST)
                .getHolderOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.parse("minecraft:empty")));

        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registryOrThrow(Registries.TEMPLATE_POOL);

        for (String biome : new String[]{"desert", "snowy", "plains", "savanna", "taiga"}) {
            AdvancedPeripherals.debug("Register generating scientist_" + biome + " village house");
            addPieceToPool(templatePoolRegistry, emptyProcessor, ResourceLocation.parse("village/" + biome + "/houses"), AdvancedPeripherals.MOD_ID + ":villages/scientist_" + biome, StructureTemplatePool.Projection.RIGID, APConfig.WORLD_CONFIG.villagerStructureWeight.get());
        }
    }
}
