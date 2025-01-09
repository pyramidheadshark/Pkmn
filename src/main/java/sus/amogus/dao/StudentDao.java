package sus.amogus.dao;

import sus.amogus.entities.StudentEntity;
import sus.amogus.models.Student;
import sus.amogus.repos.StudentEntityRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDao {
    @Autowired
    private final StudentEntityRepository studententityrepository;

    @SneakyThrows
    public StudentEntity getBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName) {
        return studententityrepository.findBySurNameAndFirstNameAndFamilyName(surName, firstName, familyName).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public List<StudentEntity> getByGroup(String group) {
        return studententityrepository.findByGroup(group);
    }

    @SneakyThrows
    public List<StudentEntity> getAll(){
        return studententityrepository.findAll();
    }

    @SneakyThrows
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studententityrepository.save(studentEntity);
    }

    public boolean studentExists(Student student) {
        return studententityrepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(student.getSurName(), student.getFirstName(), student.getFamilyName(), student.getGroup()).isPresent();
    }
}
