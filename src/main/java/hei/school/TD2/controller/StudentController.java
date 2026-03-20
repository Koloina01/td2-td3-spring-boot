package hei.school.TD2.controller;

import hei.school.TD2.entity.Student;
import hei.school.TD2.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {

        if (name == null || name.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Paramètre 'name' manquant");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Welcome " + name);
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
