package hei.school.TD2.service;

import hei.school.TD2.entity.Student;
import hei.school.TD2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> addStudents(List<Student> students) {
        return repository.saveAll(students);
    }

    public String getStudentNames() {
        return repository.findAll()
                .stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }
}
