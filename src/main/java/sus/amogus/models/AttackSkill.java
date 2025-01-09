package sus.amogus.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttackSkill implements Serializable {
    public static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String cost;
    private int damage;
}