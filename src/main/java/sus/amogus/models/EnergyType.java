package sus.amogus.models;

import lombok.Getter;

@Getter
public enum EnergyType {
    FIRE("FIRE"),
    GRASS("GRASS"),
    WATER("WATER"),
    LIGHTNING("LIGHTNING"),
    PSYCHIC("PSYCHIC"),
    FIGHTING("FIGHTING"),
    DARKNESS("DARKNESS"),
    METAL("METAL"),
    FAIRY("FAIRY"),
    DRAGON("DRAGON"),
    COLORLESS("COLORLESS");

    private final String energyType;

    EnergyType(String energyType)
    {
        this.energyType = energyType;
    }

}