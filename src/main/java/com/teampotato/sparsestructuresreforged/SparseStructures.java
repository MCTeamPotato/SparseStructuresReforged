package com.teampotato.sparsestructuresreforged;

import com.google.gson.Gson;
import net.minecraftforge.fml.common.Mod;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Mod(SparseStructures.MOD_ID)
public class SparseStructures {
    public static final String MOD_ID = "sparsestructuresreforged";
    private static final String CONFIG_RESOURCE_NAME = "sparse-structures-default-config.json5";
    private static final String CONFIG_FILENAME = "sparsestructures.json5";
    private static final Path CONFIG_FILE_PATH = Paths.get("config", CONFIG_FILENAME);
    public static SparseStructuresConfig config;

    public SparseStructures() {
        if (!CONFIG_FILE_PATH.toFile().exists()) {
            try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONFIG_RESOURCE_NAME)) {
                if (in == null) throw new IllegalStateException("Failed to load SparseStructure's default config \"" + CONFIG_RESOURCE_NAME +"\"");
                Files.createDirectories(CONFIG_FILE_PATH);
                Files.copy(in, CONFIG_FILE_PATH, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try (final InputStream in = Files.newInputStream(CONFIG_FILE_PATH)) {
            config = new Gson().fromJson(new InputStreamReader(in), SparseStructuresConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("SparseStructure's config file is malformed! If you don't know what's causing this, delete the config file and restart the game.");
        }
    }
}
