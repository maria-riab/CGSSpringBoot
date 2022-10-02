package cgs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_student")
    @SequenceGenerator(name = "gen_student", sequenceName = "SEQ_STUDENT", allocationSize = 1)
    private Long id;
    @NotBlank(message="First name is required")
    private String firstName;
    @NotBlank(message="Last name is required")
    private String lastName;
    @NotBlank(message="Address is required")
    private String address;
    @NotBlank(message="City is required")
    private String city;

    @OneToMany(mappedBy = "student")
    private Collection<Grade> grades;

    public Student(String firstName, String lastName, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }

    public Collection<Grade> getGrade() {
        return grades;
    }

    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }
    public void addGrade(Grade grade){
        this.grades.add(grade);
    }
}
