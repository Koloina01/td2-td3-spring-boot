package hei.school.TD2.repository;

import hei.school.TD2.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public List<Student> saveAll(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public List<Student> findAll() {
        return students;
    }
}
