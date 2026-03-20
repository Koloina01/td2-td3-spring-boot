package hei.school.TD2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String reference;
    private String firstName;
    private String lastName;
    private int age;
}
