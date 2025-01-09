package sus.amogus.services;

import sus.amogus.dao.StudentDao;
import sus.amogus.entities.StudentEntity;
import sus.amogus.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    /**
     *  Возвращает студента по полному имени.
     *  @param surName фамилия студента.
     *  @param firstName имя студента.
     *  @param familyName отчество студента.
     *  @return Student найденный студент.
     */
    @Override
    public Student getStudentBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName) {
        StudentEntity studentEntity = studentDao.getBySurNameAndFirstNameAndFamilyName(surName, firstName, familyName);
        return Student.fromEntity(studentEntity);
    }

    /**
     *  Возвращает список студентов по группе.
     *  @param group группа студентов.
     *  @return List<Student> список студентов.
     */
    @Override
    public List<Student> getStudentsByGroup(String group) {
        return studentDao.getByGroup(group).stream().map(Student::fromEntity).toList();
    }

    /**
     *  Возвращает список всех студентов.
     *  @return List<Student> список всех студентов.
     */
    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll().stream().map(Student::fromEntity).toList();
    }

    /**
     *  Сохраняет нового студента.
     *  @param student данные студента для сохранения.
     *  @return Student сохраненный студент.
     *  @throws IllegalArgumentException если студент с таким именем и группой уже существует.
     */
    @Override
    public Student saveStudent(Student student) {
        if (studentDao.studentExists(student)) {
            throw new IllegalArgumentException("Студент уже есть в базе данных");
        }
        return Student.fromEntity(studentDao.saveStudent(StudentEntity.toEntity(student)));
    }
}