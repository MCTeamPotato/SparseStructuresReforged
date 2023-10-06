package com.teampotato.sparsestructuresreforged;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.*;
import java.util.List;
import java.util.Set;

public class SSRMixinPlugin implements IMixinConfigPlugin {
    static boolean initFailed;
    public static double extraSpacingPercentage, extraSeparationPercentage;

    public SSRMixinPlugin() {
        File config = new File(FMLLoader.getGamePath().toFile(), "config");
        config.mkdirs();
        File configFile = new File(config, "sparestructuresreforged.json");
        if (!configFile.exists()) {
            try {
                FileWriter fileWriter = writeFile(configFile);
                fileWriter.close();
            } catch (Exception e) {
                initFailed = true;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            extraSpacingPercentage = jsonObject.get("extraSpacingPercentage").getAsDouble();
            extraSeparationPercentage = jsonObject.get("extraSeparationPercentage").getAsDouble();
        } catch (Exception e) {
            initFailed = true;
        }
    }

    @NotNull
    private static FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("extraSpacingPercentage", 0.8);
        defaultConfig.addProperty("extraSeparationPercentage", 0.8);
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String s, String s1) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
