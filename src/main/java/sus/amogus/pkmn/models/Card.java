package sus.amogus.pkmn.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class Card implements Serializable {
    private PokemonStage pokemonStage;
    private String name;
    private int hp;
    private EnergyType pokemonType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;
    private EnergyType weaknessType;
    private EnergyType resistanceType;
    private String retreatCost;
    private String gameSet;
    private char regulationMark;
    private Student pokemonOwner;
    private String number;
    @Serial
    private static final long serialVersionUID = 1L;

    public Card()
    {

    }

    public Card (String name)
    {
        this.name = name;
    }

    public Card(PokemonStage pokemonStage, String name, int hp, EnergyType pokemonType,
                Card evolvesFrom, List<AttackSkill> skills, EnergyType weaknessType, EnergyType resistanceType,
                String retreatCost, String gameSet, char regulationMark, Student pokemonOwner, String number)
    {
        this.pokemonStage = pokemonStage;
        this.name = name;
        this.hp = hp;
        this.pokemonType = pokemonType;
        this.evolvesFrom = evolvesFrom;
        this.skills = skills;
        this.weaknessType = weaknessType;
        this.resistanceType = resistanceType;
        this.retreatCost = retreatCost;
        this.gameSet = gameSet;
        this.regulationMark = regulationMark;
        this.pokemonOwner = pokemonOwner;
        this.number=number;
    }

    @Override
    public String toString() {
        return String.format("\u001b[38;5;111m%s pokemon:\u001b[38;5;15m \n" +
                "1. ", this.pokemonStage) + this.pokemonStage + "\n2. " + name + "\n3. " + hp + "\n4. " + pokemonType + "\n5. " + evolvesFrom + "\n6. " + skills + "\n7. " + weaknessType + "\n8. " + resistanceType + "\n9. " + retreatCost + "\n10. " + gameSet + "\n11. " + regulationMark + "\n12. " + pokemonOwner + "\n13. "+ number + "\n";
    }
}