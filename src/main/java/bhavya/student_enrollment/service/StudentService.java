package bhavya.student_enrollment.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import bhavya.student_enrollment.beans.Student;
import bhavya.student_enrollment.repository.StudentRepository;
import bhavya.student_enrollment.repository.StudentRepositoryImpl;

@Service
public class StudentService {

	public static final Logger log = LoggerFactory.getLogger(StudentService.class);
		
	@Autowired
	static StudentRepository studentRepo;
	
	static StudentRepositoryImpl studentCustomRepo= new StudentRepositoryImpl(studentRepo);
	
	
	public boolean checkStudentExist(int id) {
		try {
			log.info("validating the Student exist or not");
			Optional<Student> student = studentCustomRepo.findStudentById(id);
			if (student.get().getId()!=0) {
				log.info("Student already exist for given id =>"+id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("No existing record found for given id => "+id);
		return false;
	}

}
