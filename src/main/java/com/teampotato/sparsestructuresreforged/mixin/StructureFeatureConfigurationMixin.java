package com.teampotato.sparsestructuresreforged.mixin;

import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.teampotato.sparsestructuresreforged.SSRMixinPlugin.extraSeparationPercentage;
import static com.teampotato.sparsestructuresreforged.SSRMixinPlugin.extraSpacingPercentage;

@Mixin(StructureFeatureConfiguration.class)
public abstract class StructureFeatureConfigurationMixin {
    @Shadow public int spacing;
    @Shadow public int separation;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(int spacing, int separation, int salt, CallbackInfo ci) {
        double newSpacing = (extraSpacingPercentage + 1.00) * spacing;
        double newSeparation = (extraSeparationPercentage + 1.00) * separation;
        if (newSpacing > 4095) newSpacing = 4095;
        if (newSpacing < 0) newSpacing = 5;
        if (newSeparation > 4095) newSeparation = 4095;
        if (newSeparation < 0) newSeparation = 5;
        if (newSpacing <= newSeparation) newSeparation = newSpacing - 3;
        this.spacing = (int) newSpacing;
        this.separation = (int) newSeparation;
    }
}
