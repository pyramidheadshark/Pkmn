package sus.amogus.models;

import sus.amogus.entities.CardEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Card implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;
    PokemonStage pokemonStage;
    String name;
    int hp;
    EnergyType pokemonType;
    public Card evolvesFrom;
    List<AttackSkill> skills;
    EnergyType weaknessType;
    EnergyType resistanceType;
    String retreatCost;
    String gameSet;
    char regulationMark;
    Student pokemonOwner;
    String number;

    public static Card fromEntity(CardEntity entity) {
        if (entity != null) {
            return Card.builder()
                    .pokemonStage(entity.getPokemonStage())
                    .name(entity.getName())
                    .hp(entity.getHp())
                    .pokemonType(entity.getPokemonType())
                    .evolvesFrom(fromEntity(entity.getEvolvesFrom()))
                    .skills(entity.getSkills())
                    .weaknessType(entity.getWeaknessType())
                    .resistanceType(entity.getResistanceType())
                    .retreatCost(entity.getRetreatCost())
                    .gameSet(entity.getGameSet())
                    .regulationMark(entity.getRegulationMark())
                    .pokemonOwner(Student.fromEntity(entity.getPokemonOwner()))
                    .number(entity.getNumber())
                    .build();
        }
        return null;
    }
}
