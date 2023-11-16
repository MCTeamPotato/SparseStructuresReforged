package com.teampotato.sparsestructuresreforged;

import net.minecraftforge.fml.common.Mod;

@Mod("sparsestructuresreforged")
public class SparseStructuresReforged {
    public SparseStructuresReforged() throws Throwable {
        if (SSRMixinPlugin.initFailed) throw SSRMixinPlugin.exception;
    }
}