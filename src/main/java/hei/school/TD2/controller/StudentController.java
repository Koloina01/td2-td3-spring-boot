package hei.school.TD2.controller;

import hei.school.TD2.entity.Student;
import hei.school.TD2.exception.BadRequestException;
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
                    .body("missing parameter: name");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Welcome " + name);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> students) {

        try {
            List<Student> allStudents = service.addStudents(students);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(allStudents);

        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(
            @RequestHeader(value = "Accept", required = false) String accept) {

        try {
            if (accept == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("missing header: Accept");
            }

            if (!accept.equals("text/plain") && !accept.equals("application/json")) {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Format not supported");
            }

            if (accept.equals("text/plain")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(service.getStudentNames());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.getStudentNames());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error");
        }
    }
}
