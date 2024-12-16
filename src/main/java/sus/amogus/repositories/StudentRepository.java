package sus.amogus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sus.amogus.entities.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);
    List<StudentEntity> findByGroup(String group);
}
