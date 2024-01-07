package com.teampotato.sparsestructuresreforged;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

@Mod(SparseStructuresReforged.MOD_ID)
public class SparseStructuresReforged {
    public static final String MOD_ID = "sparsestructuresreforged";

    public SparseStructuresReforged() {
        if (SSRMixinPlugin.initFailed) {
            LogManager.getLogger().fatal("Error occurs during sparse structures reforged config file initialization");
            if (SSRMixinPlugin.exceptionOnWriteFile != null) throw new RuntimeException(SSRMixinPlugin.exceptionOnWriteFile);
            if (SSRMixinPlugin.exceptionOnReadFile != null) throw new RuntimeException(SSRMixinPlugin.exceptionOnReadFile);
        }
    }
}
