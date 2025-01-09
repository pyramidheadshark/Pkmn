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

    @Autowired
    private final StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/group/{group}")
    public List<Student> getStudentsByGroup(@PathVariable String group) {
        return studentService.getStudentsByGroup(group);
    }

    @GetMapping("")
    public Student getStudentByName(@RequestBody Student student) {
        return studentService.getStudentBySurNameAndFirstNameAndFamilyName(student.getSurName(), student.getFirstName(), student.getFamilyName());
    }

    @PostMapping("")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}