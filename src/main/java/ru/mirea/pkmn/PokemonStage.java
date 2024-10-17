package ru.mirea.pkmn;

public enum PokemonStage {
    Basic("Basic"),
    Stage1("Stage1"),
    Stage2("Stage2"),
    Vstar("Vstar"),
    Vmax("Vmax");

    private final String pokemonStage;

    PokemonStage(String pokemonStage)
    {
        this.pokemonStage = pokemonStage;
    }

    public String getPokemonStage()
    {
        return pokemonStage;
    }
}