package sus.amogus.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import sus.amogus.deserializer.SkillDeserializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import sus.amogus.models.AttackSkill;
import sus.amogus.models.Card;
import sus.amogus.models.EnergyType;
import sus.amogus.models.PokemonStage;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name = "stage")
    @Enumerated(EnumType.STRING)
    private PokemonStage pokemonStage;

    @Column(name = "name")
    private String name;

    @Column(name = "hp", nullable = false)
    private int hp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evolves_from_id")
    private CardEntity evolvesFrom;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attack_skills")
    @JsonDeserialize(using = SkillDeserializer.class)
    private List<AttackSkill> skills;

    @Column(name = "weakness_type")
    @Enumerated(EnumType.STRING)
    private EnergyType weaknessType;

    @Column(name = "resistance_type")
    @Enumerated(EnumType.STRING)
    private EnergyType resistanceType;

    @Column(name = "retreat_cost")
    private String retreatCost;

    @Column(name = "game_set")
    private String gameSet;

    @Column(name = "pokemon_type")
    @Enumerated(EnumType.STRING)
    private EnergyType pokemonType;

    @Column(name = "regulation_mark", nullable = false)
    private char regulationMark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokemon_owner_id")
    private StudentEntity pokemonOwner;

    @Column(name = "card_number")
    private String number;

    /**
     *  Преобразует объект Card в объект CardEntity.
     *  @param card объект Card для преобразования.
     *  @return CardEntity преобразованный объект CardEntity.
     */
    public static CardEntity toEntity(Card card) {
        if (card != null) {
            return CardEntity.builder()
                    .id(UUID.randomUUID())
                    .pokemonStage(card.getPokemonStage())
                    .name(card.getName())
                    .hp(card.getHp())
                    .pokemonType(card.getPokemonType())
                    .evolvesFrom(toEntity(card.getEvolvesFrom()))
                    .skills(card.getSkills())
                    .weaknessType(card.getWeaknessType())
                    .resistanceType(card.getResistanceType())
                    .retreatCost(card.getRetreatCost())
                    .gameSet(card.getGameSet())
                    .regulationMark(card.getRegulationMark())
                    .pokemonOwner(StudentEntity.toEntity(card.getPokemonOwner()))
                    .number(card.getNumber())
                    .build();
        }
        return null;
    }
}