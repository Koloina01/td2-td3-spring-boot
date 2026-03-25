package hei.school.TD2.validator;

import hei.school.TD2.entity.Student;
import hei.school.TD2.exception.BadRequestException;

public class StudentValidator {

    public void validate(Student student) {

        if (student.getReference() == null || student.getReference().isEmpty()) {
            throw new BadRequestException("reference is missing");
        }

        if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
            throw new BadRequestException("firstName is missing");
        }

        if (student.getLastName() == null || student.getLastName().isEmpty()) {
            throw new BadRequestException("lastName is missing");
        }
    }
}
