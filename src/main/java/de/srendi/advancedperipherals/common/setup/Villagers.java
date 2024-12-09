package de.srendi.advancedperipherals.common.setup;

import com.google.common.collect.ImmutableSet;
import dan200.computercraft.shared.ModRegistry;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;

import java.util.function.Supplier;

public class Villagers {

    public static final Supplier<PoiType> COMPUTER_SCIENTIST_POI = Registration.POI_TYPES.register("computer_scientist", () -> new PoiType(ImmutableSet.copyOf(ModRegistry.Blocks.COMPUTER_ADVANCED.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final Supplier<VillagerProfession> COMPUTER_SCIENTIST = Registration.VILLAGER_PROFESSIONS.register("computer_scientist", () -> new VillagerProfession(AdvancedPeripherals.MOD_ID + ":computer_scientist", holder -> holder.is(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getKey(COMPUTER_SCIENTIST_POI.get())), holder -> holder.is(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getKey(COMPUTER_SCIENTIST_POI.get())), ImmutableSet.of(), ImmutableSet.of(ModRegistry.Blocks.COMPUTER_ADVANCED.get()), SoundEvents.VILLAGER_WORK_TOOLSMITH));

    public static void register() {
    }

}
