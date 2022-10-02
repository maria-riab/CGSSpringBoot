package cgs.web;

import cgs.Course;
import cgs.data.AdministratorRepository;
import cgs.data.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// add a check to see if a course list is null before displaying it
@Slf4j
@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseRepository courseRepo;
    //private AdministratorRepository administratorRepo;

    @Autowired
    public CourseController(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    @ModelAttribute(name = "courseList")
    public List getAllCourses(){
        List<Course> repo = null;
        try {
            repo = (List) courseRepo.findAll();

        } catch (Exception e){
            log.info("Cant get courses for some reason");
        }
        return repo;
    }

    @ModelAttribute(name = "course")
    public Course course(){
        return new Course();
    }
//    @ModelAttribute(name = "instructors")
//    public List getAllInstructors(){
//        return administratorRepo.findAllInstructors();
//    }

    @GetMapping
    public String showAllCourses(){
        return "seeAllCourses";
    }

    @GetMapping("/addCourse")
    public String showCourseForm(){
        return "addCourse";
    }

    @PostMapping("/addCourse")
    public String processCourse(@Valid Course course, Errors errors){
        if (errors.hasErrors()){
            return "addCourse";
        }
        courseRepo.save(course);
        return "redirect:/course";
    }
}
