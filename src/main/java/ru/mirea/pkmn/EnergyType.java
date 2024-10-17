package ru.mirea.pkmn;

public enum EnergyType {
    Fire("Fire"),
    Grass("Grass"),
    Water("Water"),
    Lightning("Lightning"),
    Psychic("Psychic"),
    Fighting("Fighting"),
    Darkness("Darkness"),
    Metal("Metal"),
    Fairy("Fairy"),
    Dragon("Dragon"),
    Colorless("Colorless");

    private final String energyType;

    EnergyType(String energyType)
    {
        this.energyType = energyType;
    }

    public String getEnergyType()
    {
        return energyType;
    }
}