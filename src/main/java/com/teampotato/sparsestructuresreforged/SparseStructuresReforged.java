package com.teampotato.sparsestructuresreforged;

import net.minecraftforge.fml.common.Mod;

@Mod("sparsestructuresreforged")
public class SparseStructuresReforged {
    public SparseStructuresReforged() {
        if (SSRMixinPlugin.initFailed) throw new RuntimeException("Failed to create SSR config file");
    }
}