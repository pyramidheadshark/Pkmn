package sus.amogus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sus.amogus.entities.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> { // Интерфейс репозитория для сущности StudentEntity с UUID в качестве первичного ключа

    Optional<StudentEntity> findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName); // Находит студента по ФИО (Optional)

    List<StudentEntity> findByGroup(String group); // Находит список студентов по группе
}