package com.teampotato.sparsestructuresreforged.mixin;

import net.minecraft.core.Vec3i;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.teampotato.sparsestructuresreforged.SSRMixinPlugin.extraSeparationPercentage;
import static com.teampotato.sparsestructuresreforged.SSRMixinPlugin.extraSpacingPercentage;

@Mixin(RandomSpreadStructurePlacement.class)
public abstract class StructureFeatureConfigurationMixin {
    @Mutable
    @Final
    @Shadow
    private int spacing;
    @Mutable
    @Final
    @Shadow
    private int separation;

    @Inject(method = "<init>(IILnet/minecraft/world/level/levelgen/structure/placement/RandomSpreadType;ILnet/minecraft/core/Vec3i;)V", at = @At("RETURN"))
    private void onInit(int spacing, int separation, RandomSpreadType f_204975_, int f_204976_, Vec3i f_204977_, CallbackInfo ci) {
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
