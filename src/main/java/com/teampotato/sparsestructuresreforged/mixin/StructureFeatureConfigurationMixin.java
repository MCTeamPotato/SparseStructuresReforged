package com.teampotato.sparsestructuresreforged.mixin;

import com.teampotato.sparsestructuresreforged.SparseStructuresReforged;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureFeatureConfiguration.class)
public class StructureFeatureConfigurationMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(int spacing, int separation, int salt, CallbackInfo ci) {
        ssr$initialize((StructureFeatureConfiguration) (Object) this);
    }

    @Unique
    private static void ssr$initialize(@NotNull StructureFeatureConfiguration configuration) {
        final int limit = 4095;

        double multiplier = SparseStructuresReforged.getExtraSpacingPercentage() + 1;
        double spacing = configuration.spacing;
        double separation = configuration.separation;
        if (multiplier <= 0) multiplier = 0.01D;
        spacing = (int) (spacing * multiplier);
        if (spacing >= limit) spacing = limit - 1;

        multiplier = SparseStructuresReforged.getExtraSeparationPercentage() + 1;
        if (multiplier <= 0) multiplier = 0.01D;
        separation = (int) (separation * multiplier);
        if (separation >= limit) separation = limit - 1;

        if (spacing <= separation) {
            if (spacing <= 1) spacing = 3;
            separation = spacing - 1;
        }

        configuration.spacing = (int) spacing;
        configuration.separation = (int) separation;
    }
}
