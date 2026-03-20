package hei.school.TD2.controller;

import hei.school.TD2.entity.Student;
import hei.school.TD2.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> students) {

        List<Student> allStudents = service.addStudents(students);

        StringBuilder result = new StringBuilder();

        for (Student s : allStudents) {
            result.append(s.getFirstName())
                    .append(" ")
                    .append(s.getLastName())
                    .append("\n");
        }

        return result.toString();
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        if ("text/plain".equals(accept)) {
            return service.getStudentNames();
        } else {
            return "Format not supported";
        }
    }
}
