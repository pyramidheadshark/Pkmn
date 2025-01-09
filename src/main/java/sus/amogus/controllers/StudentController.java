package sus.amogus.controllers;

import sus.amogus.models.Student;
import sus.amogus.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     *  Возвращает список всех студентов.
     *  @return List<Student> список всех студентов.
     */
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     *  Возвращает список студентов по их группе.
     *  @param group группа студентов.
     *  @return List<Student> список студентов указанной группы.
     */
    @GetMapping("/group/{group}")
    public List<Student> getStudentsByGroup(@PathVariable String group) {
        return studentService.getStudentsByGroup(group);
    }

    /**
     *  Возвращает студента по его полному имени (фамилия, имя, отчество).
     *  @param student объект Student, содержащий фамилию, имя и отчество.
     *  @return Student найденный студент.
     */
    @GetMapping("")
    public Student getStudentByName(@RequestBody Student student) {
        return studentService.getStudentBySurNameAndFirstNameAndFamilyName(student.getSurName(), student.getFirstName(), student.getFamilyName());
    }

    /**
     *  Сохраняет нового студента.
     *  @param student данные студента для сохранения.
     *  @return Student сохраненный студент.
     */
    @PostMapping("")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}