package sus.amogus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sus.amogus.entities.StudentEntity;
import sus.amogus.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentDao {
    @Autowired
    private final StudentRepository studentRepository;

    public List<StudentEntity> findAll() { // Возвращает всех студентов
        return studentRepository.findAll();
    }

    public StudentEntity save(StudentEntity student) { // Сохраняет студента
        return studentRepository.save(student);
    }

    public void deleteById(UUID id) { // Удаляет студента по ID
        studentRepository.deleteById(id);
    }

    public List<StudentEntity> findByGroup(String group) { // Возвращает студентов по группе
        return studentRepository.findByGroup(group);
    }

    public Optional<StudentEntity> findByFullName(String firstName, String surName, String familyName) { // Возвращает студента по ФИО (Optional)
        return studentRepository.findByFirstNameAndSurNameAndFamilyName(firstName, surName, familyName);
    }

    public Optional<StudentEntity> findById(UUID id) { // Возвращает студента по ID (Optional)
        return studentRepository.findById(id);
    }
}