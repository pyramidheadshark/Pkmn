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

    /**
     * Преобразует объект CardEntity в объект Card.
     * @param entity объект CardEntity для преобразования.
     * @return Card преобразованный объект Card.
     */
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