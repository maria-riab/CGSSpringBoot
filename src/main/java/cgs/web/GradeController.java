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
    @PostMapping
    private String modifyOrDeleteGrade(
            @RequestParam(name = "transcriptID") String transcriptID, @RequestParam(name = "actionType", required = false) String actionType, Grade grade, Model model,
            @RequestParam(name = "semester", required = false) String semester, @RequestParam(name = "selectedStudent", required = false) String selectedStudent, @RequestParam(name = "selectedCourse", required = false) String selectedCourse) {


        if (actionType.equalsIgnoreCase("delete")) {
//            Grade gradeInRepo =  gradeRepo.findById(Long.parseLong(transcriptID)).orElse(null);
//            gradeInRepo.getStudent().deleteGrade(grade);
//            gradeInRepo.getCourse().deleteGrade(grade);
            gradeRepo.deleteById(Long.parseLong(transcriptID));
            return "redirect:/grade";

        } else if (actionType.equalsIgnoreCase("sendToModify")){
            model.addAttribute("gradeToModify",  gradeRepo.findById(Long.parseLong(transcriptID)).get());
            return "editGrade";
        }
        else if (actionType.equalsIgnoreCase("edit")){

            if (grade != null && grade.getTranscriptID() == Long.parseLong(transcriptID)){
                Student studentSelected = studentRepo.findById(Long.valueOf(Integer.parseInt(selectedStudent))).orElse(null);
                Course courseSelected = courseRepo.findById(Long.valueOf(Integer.parseInt(selectedCourse))).orElse(null);

                grade.setCourse(courseSelected);
                grade.setStudent(studentSelected);
                grade.setSemester(semester.charAt(0));

//                studentSelected.updateGrade(grade);
//                courseSelected.updateGrade(grade);
                gradeRepo.save(grade);
            }
        }
        long stId = grade.getStudent().getId();
        return "redirect:/student/" + stId;
    }
}
