package sus.amogus.models;

import lombok.Builder;
import lombok.Data;
import sus.amogus.entities.StudentEntity;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String surName;
    private String firstName;
    private String familyName;
    private String group;

    /**
     *  Преобразует объект StudentEntity в объект Student.
     *  @param entity объект StudentEntity для преобразования.
     *  @return Student преобразованный объект Student.
     */
    public static Student fromEntity(StudentEntity entity) {
        if (entity != null) {
            return Student.builder()
                    .surName(entity.getSurName())
                    .firstName(entity.getFirstName())
                    .familyName(entity.getFamilyName())
                    .group(entity.getGroup())
                    .build();
        }
        return null;
    }
}