package sus.amogus.models;

import lombok.Getter;

@Getter
public enum PokemonStage {
    BASIC("BASIC"),
    STAGE1("STAGE1"),
    STAGE2("STAGE2"),
    VSTAR("VSTAR"),
    VMAX("VMAX");

    private final String pokemonStage;

    PokemonStage(String pokemonStage)
    {
        this.pokemonStage = pokemonStage;
    }

}