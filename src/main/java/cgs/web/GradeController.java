package cgs.web;

import cgs.Course;
import cgs.Grade;
import cgs.Student;
import cgs.data.CourseRepository;
import cgs.data.GradeRepository;
import cgs.data.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;


    // model attr
    @ModelAttribute(name = "grade")
    public Grade grade(){
        return new Grade();
    }
    @ModelAttribute(name = "gradeList")
    public List getAllGrades(){
        return  (List) gradeRepo.findAll();
    }
    @ModelAttribute(name = "studentList")
    public List getAllStudents() { return (List) studentRepo.findAll();}
    @ModelAttribute(name = "courseList")
    public List getAllCourses() { return (List) courseRepo.findAll();}


    @GetMapping
    public String seeAllGrades(){
        return "seeAllGrades";
    }
    @GetMapping("/addGrade")
    public String showGradeForm(){
        return "addGrade";
    }


    @PostMapping("/addGrade")
    public String processGrade(Grade grade,@RequestParam(name = "semester") String semester, @RequestParam(name = "selectedStudent") String selectedStudent, @RequestParam(name = "selectedCourse") String selectedCourse,
                               Errors errors){

        Student studentSelected = studentRepo.findById(Long.valueOf(Integer.parseInt(selectedStudent))).orElse(null);
        Course courseSelected = courseRepo.findById(Long.valueOf(Integer.parseInt(selectedCourse))).orElse(null);

        grade.setCourse(courseSelected);
        grade.setStudent(studentSelected);
        grade.setSemester(semester.charAt(0));

        if (errors.hasErrors() || studentSelected == null || courseSelected == null){
            showGradeForm();
        }
        studentSelected.addGrade(grade);
        courseSelected.addGrade(grade);

        gradeRepo.save(grade);
        long stId = grade.getStudent().getId();
        return "redirect:/student/" + stId;
    }
}
