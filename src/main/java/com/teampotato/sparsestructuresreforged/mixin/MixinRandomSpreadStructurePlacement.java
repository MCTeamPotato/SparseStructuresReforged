package com.teampotato.sparsestructuresreforged.mixin;

import com.teampotato.sparsestructuresreforged.SSRMixinPlugin;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RandomSpreadStructurePlacement.class)
public abstract class MixinRandomSpreadStructurePlacement {
    @Mutable @Shadow @Final private int spacing;
    @Mutable @Shadow @Final private int separation;

    @Inject(method = "<init>(Lnet/minecraft/core/Vec3i;Lnet/minecraft/world/level/levelgen/structure/placement/StructurePlacement$FrequencyReductionMethod;FILjava/util/Optional;IILnet/minecraft/world/level/levelgen/structure/placement/RandomSpreadType;)V", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.spacing = Math.min((int) ((1 + SSRMixinPlugin.extraSpacingPercentage) * spacing), 4095);
        this.separation = Math.min((int) ((1 + SSRMixinPlugin.extraSeparationPercentage) * separation), 4094);
    }
}
