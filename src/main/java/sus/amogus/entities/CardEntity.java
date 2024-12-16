package sus.amogus.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import sus.amogus.models.PokemonStage;
import sus.amogus.models.AttackSkill;

import static org.hibernate.type.SqlTypes.JSON;


@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name="name")
    public String name;

    @Column(columnDefinition = "smallint")
    public short hp;

    @Column(name="cardNumber")
    public String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="stage")
    public PokemonStage pokemonStage;

    @Column(name="retreat_cost")
    public String retreatCost;

    @Column(name="pokemon_type", nullable = true)
    public String pokemonType;

    @Column(name="weakness_type", nullable = true)
    public String weaknessType;

    @Column(name="resistance_type", nullable = true)
    public String resistanceType;

    @Column(name="game_set")
    public String gameSet;

    @Column(name="regulation_mark")
    public char regulationMark;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "pokemon_owner_id")
    public StudentEntity pokemonOwner;

    @JdbcTypeCode(JSON)
    @Column(name="attack_skills", columnDefinition = "JSON")
    public List<AttackSkill> skills;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from_id")
    public CardEntity evolvesFrom;

    @Override
    public String toString() {
        return "Card{" +
                "pokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", pokemonType=" + pokemonType +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", owner=" + ((pokemonOwner != null) ? pokemonOwner.toString() : pokemonOwner)+
                '}';
    }
}