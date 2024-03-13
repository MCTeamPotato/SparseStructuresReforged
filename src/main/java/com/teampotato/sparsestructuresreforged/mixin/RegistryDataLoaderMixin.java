package com.teampotato.sparsestructuresreforged.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Decoder;
import com.teampotato.sparsestructuresreforged.CustomSpreadFactors;
import com.teampotato.sparsestructuresreforged.SparseStructures;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

@Mixin(RegistryDataLoader.class)
public abstract class RegistryDataLoaderMixin {
    @Inject(method = "loadRegistryContents", at = @At(value = "INVOKE", remap = false, target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static <E> void ssr$init(RegistryOps.RegistryInfoLookup lookup, ResourceManager manager, ResourceKey<? extends Registry<E>> registryKey, WritableRegistry<E> registry, Decoder<E> decoder, Map<ResourceKey<?>, Exception> exceptions, CallbackInfo ci, String string, FileToIdConverter converter, RegistryOps<JsonElement> registryOps, Iterator<Map.Entry<ResourceLocation, Resource>> iterator, Map.Entry<ResourceLocation, Resource> entry, ResourceLocation resourcelocation, ResourceKey<E> resourceKey, Resource resource, Reader reader, JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject placement = jsonObject.getAsJsonObject("placement");
        if (string.equals("worldgen/structure_set") && !placement.get("type").getAsString().equals("minecraft:concentric_rings")) {
            double factor = SparseStructures.config.customSpreadFactors.stream().filter(s -> {
                if (s == null) return false;
                String structure_set = resourceKey.location().toString();
                return structure_set.equals(s.structure()) || jsonObject.getAsJsonArray("structures").asList().stream().anyMatch(p -> p.getAsJsonObject().get("structure").getAsString().equals(s.structure()));
            }).findFirst().orElse(new CustomSpreadFactors("", SparseStructures.config.spreadFactor)).factor();

            int spacing;
            int separation;

            if (placement.get("spacing") == null) spacing = 1;
            else spacing = (int)(Math.min(placement.get("spacing").getAsDouble() * factor, 4096.0));
            if (placement.get("separation") == null) separation = 1;
            else separation = (int)(Math.min(placement.get("separation").getAsDouble() * factor, 4096.0));
            if (separation >= spacing) {
                if (spacing == 0) spacing = 1;
                separation = spacing - 1;
            }

            placement.addProperty("spacing", spacing);
            placement.addProperty("separation", separation);
        }
    }
}
