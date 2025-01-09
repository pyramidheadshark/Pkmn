package sus.amogus.services;

import sus.amogus.models.Student;

import java.util.List;

public interface StudentService {
    /**
     *  Возвращает студента по полному имени.
     *  @param surName фамилия студента.
     *  @param firstName имя студента.
     *  @param familyName отчество студента.
     *  @return Student найденный студент.
     */
    Student getStudentBySurNameAndFirstNameAndFamilyName(String surName, String firstName, String familyName);
    /**
     *  Возвращает список студентов по группе.
     *  @param group группа студентов.
     *  @return List<Student> список студентов.
     */
    List<Student> getStudentsByGroup(String group);
    /**
     *  Возвращает список всех студентов.
     *  @return List<Student> список всех студентов.
     */
    List<Student> getAllStudents();
    /**
     *  Сохраняет студента.
     *  @param student студент для сохранения.
     *  @return Student сохраненный студент.
     */
    Student saveStudent(Student student);
}