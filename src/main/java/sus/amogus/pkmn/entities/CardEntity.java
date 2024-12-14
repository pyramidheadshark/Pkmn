package sus.amogus.pkmn.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import sus.amogus.pkmn.models.PokemonStage;
import sus.amogus.pkmn.models.AttackSkill;



import static org.hibernate.type.SqlTypes.JSON;

@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(columnDefinition = "smallint")
    private short hp;

    @Column(name="cardNumber")
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="stage")
    private PokemonStage pokemonStage;

    @Column(name="retreat_cost")
    private String retreatCost;

    @Column(name="pokemon_type", nullable = true)
    private String pokemonType;

    @Column(name="weakness_type", nullable = true)
    private String weaknessType;

    @Column(name="resistance_type", nullable = true)
    private String resistanceType;

    @Column(name="game_set")
    private String gameSet;

    @Column(name="regulation_mark")
    private char regulationMark;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "pokemon_owner_id")
    private StudentEntity pokemonOwner;

    @JdbcTypeCode(JSON)
    @Column(name="attack_skills", columnDefinition = "JSON")
    private List<AttackSkill> skills;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from_id")
    private CardEntity evolvesFrom;
}