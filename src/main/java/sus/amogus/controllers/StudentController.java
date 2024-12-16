package sus.amogus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sus.amogus.entities.StudentEntity;
import sus.amogus.models.Student;
import sus.amogus.services.StudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable UUID id) {
        StudentEntity student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public StudentEntity updateStudent(@PathVariable UUID id, @RequestBody StudentEntity student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{group}")
    public List<StudentEntity> getStudentsByGroup(@PathVariable String group) {
        return studentService.getStudentsByGroup(group);
    }

    @GetMapping("")
    public StudentEntity getStudentByFullName(@RequestBody Student ownerRequest) {
        return studentService.getStudentByFullName(ownerRequest.getFirstName(), ownerRequest.getSurName(), ownerRequest.getFatherName());
    }
}