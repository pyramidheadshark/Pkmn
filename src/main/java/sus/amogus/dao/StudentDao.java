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

    private final StudentEntityRepository studententityrepository;

    /**
     *  Возвращает StudentEntity по полному имени (фамилия, имя, отчество).
     *  @param surName фамилия студента.
     *  @param firstName имя студента.
     *  @param familyName отчество студента.
     *  @return StudentEntity найденный StudentEntity.
     *  @throws IllegalArgumentException если StudentEntity не найден.
     */
    @SneakyThrows
    public StudentEntity getBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName) {
        return studententityrepository.findBySurNameAndFirstNameAndFamilyName(surName, firstName, familyName).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    /**
     *  Возвращает список StudentEntity по группе.
     *  @param group группа студентов.
     *  @return List<StudentEntity> список StudentEntity.
     */
    @SneakyThrows
    public List<StudentEntity> getByGroup(String group) {
        return studententityrepository.findByGroup(group);
    }

    /**
     *  Возвращает список всех StudentEntity.
     *  @return List<StudentEntity> список всех StudentEntity.
     */
    @SneakyThrows
    public List<StudentEntity> getAll(){
        return studententityrepository.findAll();
    }


    /**
     *  Сохраняет StudentEntity.
     *  @param studentEntity StudentEntity для сохранения.
     *  @return StudentEntity сохраненный StudentEntity.
     */
    @SneakyThrows
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studententityrepository.save(studentEntity);
    }

    /**
     *  Проверяет, существует ли студент с указанными данными.
     *  @param student Student для проверки.
     *  @return boolean true если студент существует, false в противном случае.
     */
    public boolean studentExists(Student student) {
        return studententityrepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(student.getSurName(), student.getFirstName(), student.getFamilyName(), student.getGroup()).isPresent();
    }
}