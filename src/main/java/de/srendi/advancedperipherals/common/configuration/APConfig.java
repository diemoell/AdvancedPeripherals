package de.srendi.advancedperipherals.common.configuration;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.loading.FMLPaths;

import java.util.HashMap;
import java.util.Map;

public class APConfig {

    // public static final ConfigFileHandler CONFIG_FILE_HANDLER = new ConfigFileHandler();

    private static final Map<IConfigSpec, IAPConfig> KNOWN_CONFIGS = new HashMap<>();

    public static final GeneralConfig GENERAL_CONFIG = new GeneralConfig();
    public static final PeripheralsConfig PERIPHERALS_CONFIG = new PeripheralsConfig();
    public static final MetaphysicsConfig METAPHYSICS_CONFIG = new MetaphysicsConfig();
    public static final WorldConfig WORLD_CONFIG = new WorldConfig();

    /*public APConfig(IAPConfig config, ModContainer container) {
        super(config.getType(), config.getConfigSpec(), container, "Advancedperipherals/" + config.getFileName() + ".toml");
    }*/

    public APConfig(){

    }

    public static void register(ModLoadingContext context) {
        //Creates the config folder
        FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve("Advancedperipherals"));

        ModContainer modContainer = context.getActiveContainer();
        APConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, GENERAL_CONFIG);
        APConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, PERIPHERALS_CONFIG);
        APConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, METAPHYSICS_CONFIG);
        APConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, WORLD_CONFIG);
    }

    public static class APConfigHelper {
        public static String getAPConfigFilePath(IAPConfig config){
            return "Advancedperipherals/" + config.getFileName() + ".toml";
        }

        public static void registerConfig(Map<IConfigSpec, IAPConfig> knownConfigs, ModContainer modContainer, IAPConfig config){
            modContainer.registerConfig(config.getType(), config.getConfigSpec(), APConfigHelper.getAPConfigFilePath(config));
            knownConfigs.put(config.getConfigSpec(), config);
        }
    }

    /*@Override
    public ConfigFileTypeHandler getHandler() {
        return CONFIG_FILE_HANDLER;
    }

    public static class ConfigFileHandler extends ConfigFileTypeHandler {

        public static Path getPath(Path path) {
            if (path.endsWith("serverconfig")) return FMLPaths.CONFIGDIR.get();

            return path;
        }

        @Override
        public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) {
            return super.reader(getPath(configBasePath));
        }

        @Override
        public void unload(ModConfig config) {
            super.unload(getPath(configBasePath), config);
        }
    }*/
}
