package com.teampotato.sparsestructuresreforged.mixin;

import com.teampotato.sparsestructuresreforged.SSRMixinPlugin;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureFeatureConfiguration.class)
public abstract class StructureFeatureConfigurationMixin {
    @Mutable @Shadow @Final private int spacing;

    @Mutable @Shadow @Final private int separation;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(int i, int j, int k, CallbackInfo ci) {
        int newSpacing = (int) ((1.0D + SSRMixinPlugin.extraSpacingPercentage) * spacing);
        int newSeparation = (int) ((1.0D + SSRMixinPlugin.extraSeparationPercentage) * separation);
        this.spacing = newSpacing == 0 ? 2 : newSpacing;
        this.separation = newSeparation == 0 ? 1 : newSeparation;
    }
}
