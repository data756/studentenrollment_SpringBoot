package bhavya.student_enrollment.repository;

import java.util.List;

import bhavya.student_enrollment.beans.Student;



public interface StudentRepositoryCustom {

	List<Student> findAllByClassInfo(String classInformation);
}
