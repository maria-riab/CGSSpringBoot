package cgs;

import cgs.Administrator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_course")
    @SequenceGenerator(name = "gen_course", sequenceName = "SEQ_COURSE", allocationSize = 1)
    private Long courseID;
    private String name;
    private String description;
    private Long administratorId;

    @OneToMany(mappedBy = "course")
    private Collection<Grade> grades;


    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Course(String name, String description, Long administratorId) {
        this.name = name;
        this.description = description;
        this.administratorId = administratorId;
    }

    public Collection<Grade> getCourseGrades() {
        return grades;
    }

    public void setCourseGrades(Collection<Grade> grades) {
        this.grades = grades;
    }
    public void addGrade(Grade grade){
        this.grades.add(grade);
    }
}
