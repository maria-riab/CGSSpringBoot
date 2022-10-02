package cgs;

import cgs.data.AdministratorRepository;
import cgs.data.CourseRepository;
import cgs.data.GradeRepository;
import cgs.data.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CollegeGradingSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(CollegeGradingSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(AdministratorRepository repo, StudentRepository studentRepo, CourseRepository courseRepo, GradeRepository gradeRepository) {
        return args -> {
            repo.deleteAll();
            repo.save( new Administrator("admin1", "1234", "SYS_ADM"));
            repo.save(new Administrator("teach_bryan", "1234", "I"));
            repo.save(new Administrator("teach_ashley", "1234", "I"));
            studentRepo.save( new Student("Maria", "Riabtchik", "1460 Bourbonniere", "Montreal"));
            courseRepo.save(new Course("English 101", "This is where you learn english!"));

        };
    }

}
