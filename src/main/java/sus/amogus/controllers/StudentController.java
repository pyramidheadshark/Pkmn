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
@RequestMapping("/api/v1/students") // Базовый путь для всех запросов к студентам
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @GetMapping("/all") // Получение всех студентов
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}") // Получение студента по ID
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable UUID id) { // @PathVariable извлекает значение из URL
        StudentEntity student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build(); // Возвращает 200 OK, если студент найден, 404 Not Found иначе
    }

    @PostMapping("") // Создание нового студента
    public StudentEntity createStudent(@RequestBody StudentEntity student) { // @RequestBody десериализует JSON из тела запроса
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}") // Обновление студента по ID
    public StudentEntity updateStudent(@PathVariable UUID id, @RequestBody StudentEntity student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}") // Удаление студента по ID
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // Возвращает 204 No Content
    }

    @GetMapping("/group/{group}") // Получение студентов по группе
    public List<StudentEntity> getStudentsByGroup(@PathVariable String group) { // @PathVariable извлекает значение из URL
        return studentService.getStudentsByGroup(group);
    }

    @GetMapping("") // Получение студента по ФИО
    public StudentEntity getStudentByFullName(@RequestBody Student ownerRequest) { // @RequestBody десериализует JSON из тела запроса
        return studentService.getStudentByFullName(ownerRequest.getFirstName(), ownerRequest.getSurName(), ownerRequest.getFatherName());
    }
}