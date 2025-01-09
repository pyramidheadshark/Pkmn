package sus.amogus.repositories;

import org.springframework.stereotype.Repository;
import sus.amogus.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    /**
     *  Находит StudentEntity по полному имени (фамилия, имя, отчество).
     *  @param surName фамилия студента.
     *  @param firstName имя студента.
     *  @param familyName отчество студента.
     *  @return Optional<StudentEntity> обертка над найденным StudentEntity.
     */
    Optional<StudentEntity> findBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName);

    /**
     *  Находит список StudentEntity по группе.
     *  @param group группа студентов.
     *  @return List<StudentEntity> список StudentEntity.
     */
    List<StudentEntity> findByGroup(String group);

    /**
     *  Возвращает список всех StudentEntity.
     *  @return List<StudentEntity> список всех StudentEntity.
     */
    List<StudentEntity> findAll();

    /**
     *  Находит StudentEntity по полному имени и группе.
     * @param surName фамилия студента.
     * @param firstName имя студента.
     * @param familyName отчество студента.
     * @param group группа студента.
     * @return Optional<StudentEntity> обертка над найденным StudentEntity.
     */
    Optional<StudentEntity> findBySurNameAndFirstNameAndFamilyNameAndGroup(String surName, String firstName, String familyName, String group);
}