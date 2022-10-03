package cgs.web;

import cgs.Student;
import cgs.data.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentRepository studentRepo;

    @Autowired
    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @ModelAttribute(name = "student")
    public Student student() {
        return new Student();
    }
    @ModelAttribute(name = "studentList")
    public List getAllStudent(){
        return (List) studentRepo.findAll();
    }

    @GetMapping("/addStudent")
    public String showStudentForm(){
        return "addStudent";
    }

    @GetMapping
    public String showAllStudents(){
        return "seeAllStudents";
    }

    @GetMapping("/{studentId}")
    public String showGradesByStudent(@PathVariable("studentId") long studentId, Model model){
        Student student = studentRepo.findById(studentId).orElse(null);
        log.info("Logging " + studentId);
        if (student != null) {
            model.addAttribute("studentGrades", student.getGrades());
            return "seeGradesByStudent";
        }
        return "seeAllStudents";

    }

    @PostMapping("/addStudent")
    private String processStudent(@Valid Student student, Errors errors){
        if (errors.hasErrors()){
            showStudentForm();
        }
        studentRepo.save(student);
        return "redirect:/student";
    }

}
