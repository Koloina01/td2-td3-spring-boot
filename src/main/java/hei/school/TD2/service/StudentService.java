package hei.school.TD2.service;

import hei.school.TD2.entity.Student;
import hei.school.TD2.repository.StudentRepository;
import hei.school.TD2.validator.StudentValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentValidator validator = new StudentValidator();

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> addStudents(List<Student> students) {

        for (Student s : students) {
            validator.validate(s);
        }

        return repository.saveAll(students);
    }

    public String getStudentNames() {
        return repository.findAll()
                .stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }
}
