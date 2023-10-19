package com.teampotato.sparsestructuresreforged.mixin;

import com.teampotato.sparsestructuresreforged.SSRMixinPlugin;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(RandomSpreadStructurePlacement.class)
public abstract class MixinRandomSpreadStructurePlacement {
    @Mutable @Shadow @Final private int spacing;
    @Mutable @Shadow @Final private int separation;

    @Inject(method = "<init>(Lnet/minecraft/core/Vec3i;Lnet/minecraft/world/level/levelgen/structure/placement/StructurePlacement$FrequencyReductionMethod;FILjava/util/Optional;IILnet/minecraft/world/level/levelgen/structure/placement/RandomSpreadType;)V", at = @At("TAIL"))
    private void onInit(Vec3i p_227000_, StructurePlacement.FrequencyReductionMethod p_227001_, float p_227002_, int p_227003_, Optional p_227004_, int p_227005_, int p_227006_, RandomSpreadType p_227007_, CallbackInfo ci) {
        int newSpacing = (int) ((1.0D + SSRMixinPlugin.extraSpacingPercentage) * spacing);
        int newSeparation = (int) ((1.0D + SSRMixinPlugin.extraSeparationPercentage) * separation);
        if (newSeparation >= newSpacing) newSpacing = newSeparation + 1;
        this.spacing = newSpacing <= 0 ? 2 : newSpacing;
        this.separation = newSeparation <= 0 ? 1 : newSeparation;
    }
}
