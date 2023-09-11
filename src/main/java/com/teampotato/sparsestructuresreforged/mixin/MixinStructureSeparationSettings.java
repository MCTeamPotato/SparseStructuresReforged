package com.teampotato.sparsestructuresreforged.mixin;

import com.teampotato.sparsestructuresreforged.SparseStructuresReforged;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureSeparationSettings.class)
public abstract class MixinStructureSeparationSettings {
    @Mutable @Shadow @Final private int spacing;
    @Mutable @Shadow @Final private int separation;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(int spacing, int separation, int salt, CallbackInfo ci) {
        this.spacing = (int) ((1 + SparseStructuresReforged.extraSpacingPercentage.get()) * spacing);
        this.separation = (int) ((1 + SparseStructuresReforged.extraSeparationPercentage.get()) * separation);
    }
}
