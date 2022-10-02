package cgs.data;

import cgs.Grade;
import cgs.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query(
            value = "SELECT * FROM Grade g WHERE g.student_id = ?1",
            nativeQuery = true)
    public List findAllByStudentId(Long studentId);

    @Query(
            value = "SELECT * FROM Grade g WHERE g.course_id = ?1",
            nativeQuery = true)
    public List findAllByCourseId(Long courseId);




}
