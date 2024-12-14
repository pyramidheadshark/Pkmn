package sus.amogus.pkmn.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class Student implements Serializable {
    private String firstName;
    private String surName;
    private String familyName;
    private String group;
    @Serial
    private static final long serialVersionUID = 1L;

    public Student()
    {

    }

    public Student(String firstName, String surName, String familyName, String group)
    {
        this.firstName = firstName;
        this.surName = surName;
        this.familyName = familyName;
        this.group = group;
    }

    @Override
    public String toString()
    {
        return firstName + "/" + surName + "/" + familyName + "/" + group;
    }
}