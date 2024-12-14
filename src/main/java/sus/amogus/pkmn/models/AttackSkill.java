package sus.amogus.pkmn.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class AttackSkill implements Serializable {
    private String name;
    private String description;
    private String cost;
    private int damage;
    @Serial
    private static final long serialVersionUID = 1L;

    public AttackSkill(String name, String description, String cost, int damage)
    {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.damage = damage;
    }

    @Override
    public String toString()
    {
        return  cost + "/" + description + "/" + name + "/" + damage;
    }
}