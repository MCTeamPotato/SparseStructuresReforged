package com.teampotato.sparsestructuresreforged;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("sparsestructuresreforged")
public class SparseStructuresReforged {
    public static final ForgeConfigSpec config;
    public static final ForgeConfigSpec.DoubleValue extraSpacingPercentage, extraSeparationPercentage;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("SparseStructuresReforged");
        extraSpacingPercentage = builder.defineInRange("extraSpacingPercentage", 0.8, 0, Double.MAX_VALUE);
        extraSeparationPercentage = builder.defineInRange("extraSeparationPercentage", 0.8, 0, Double.MAX_VALUE);
        builder.pop();
        config = builder.build();
    }

    public SparseStructuresReforged() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, config);
    }
}