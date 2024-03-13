package com.teampotato.sparsestructuresreforged;

import java.util.List;

public class SparseStructuresConfig {
    public double spreadFactor;
    public List<CustomSpreadFactors> customSpreadFactors;

    public SparseStructuresConfig(double spreadFactor, List<CustomSpreadFactors> customSpreadFactors) {
        this.spreadFactor = spreadFactor;
        this.customSpreadFactors = customSpreadFactors;
    }
}