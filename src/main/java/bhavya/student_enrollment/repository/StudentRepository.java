package bhavya.student_enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhavya.student_enrollment.beans.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,StudentRepositoryCustom {

}
