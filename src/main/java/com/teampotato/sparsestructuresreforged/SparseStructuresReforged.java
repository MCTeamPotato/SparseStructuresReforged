package com.teampotato.sparsestructuresreforged;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

@Mod(SparseStructuresReforged.MOD_ID)
public class SparseStructuresReforged {
    public static final String MOD_ID = "sparsestructuresreforged";
    public static final Logger LOGGER = LogManager.getLogger(SparseStructuresReforged.class);

    public static double getExtraSpacingPercentage() {
        return Config.INSTANCE.extraSpacingPercentage;
    }

    public static double getExtraSeparationPercentage() {
        return Config.INSTANCE.extraSeparationPercentage;
    }

    private static final class Config {
        private double extraSpacingPercentage, extraSeparationPercentage;
        private static final Config INSTANCE = new Config();

        private Config() {
            File config = new File(FMLPaths.CONFIGDIR.get().toFile(), MOD_ID + "-v2.json");
            if (!config.exists()) {
                try {
                    FileWriter writer = new FileWriter(config);
                    JsonObject defaultConfig = new JsonObject();
                    defaultConfig.addProperty("extraSpacingPercentage", 0.8D);
                    defaultConfig.addProperty("extraSeparationPercentage", 0.8D);
                    writer.write(defaultConfig.toString());
                    writer.close();
                } catch (IOException e) {
                    LOGGER.error("Failed to write SparseStructuresReforged default config", e);
                }
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader(config));
                JsonObject configObject = new JsonParser().parse(reader).getAsJsonObject();
                this.extraSpacingPercentage = configObject.get("extraSpacingPercentage").getAsDouble();
                this.extraSeparationPercentage = configObject.get("extraSeparationPercentage").getAsDouble();
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Failed to read SparseStructuresReforged config", e);
            }
        }
    }
}
