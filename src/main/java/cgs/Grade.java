package cgs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_grade")
    @SequenceGenerator(name = "gen_grade", sequenceName = "SEQ_GRADE", allocationSize = 1)
    private Long transcriptID;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    private int grade;
    private char semester;
    private int year;


    public Grade(int grade, int year) {
        this.grade = grade;
        this.year = year;
    }
    public Grade(int grade, char semester, int year) {
        this.grade = grade;
        this.semester = semester;
        this.year = year;
    }
    public Grade(Long transcriptID, int grade, char semester, int year) {
        this.transcriptID = transcriptID;
        this.grade = grade;
        this.semester = semester;
        this.year = year;
    }

    public Grade(Course course, Student student, int grade, char semester, int year) {
        this.course = course;
        this.student = student;
        this.grade = grade;
        this.semester = semester;
        this.year = year;
    }
}
