package sus.amogus.pkmn.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {
    @Serial
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name = "firstName")
    public String firstName;

    @Column(name = "surName")
    public String surName;

    @Column(name = "familyName")
    public String familyName;

    @Column(name = "\"group\"")
    public String group;

    public String getFatherName() {
        return familyName;
    }
}
