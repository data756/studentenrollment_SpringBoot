package bhavya.student_enrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bhavya.student_enrollment.beans.Student;
import bhavya.student_enrollment.repository.StudentRepository;
import bhavya.student_enrollment.repository.StudentRepositoryImpl;
import bhavya.student_enrollment.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	static StudentRepository studentRepo;
	
	static StudentRepositoryImpl studentCustomRepo= new StudentRepositoryImpl(studentRepo);

	@Autowired
	StudentService studentService;
	
	

	@PostMapping("/addstudent")
	public ResponseEntity addStudent(@RequestBody Student student) {
		boolean studentExist = studentService.checkStudentExist(student.getId());
		if (!studentExist) {
			studentCustomRepo.saveStudentRecord(student);
			return new ResponseEntity<String>("Student is added", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Student already exist with Id " + student.getId(), HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getstudent/{id}")
	public Student getStudentById(@PathVariable(value = "id") String id) {
		Student s = studentCustomRepo.getStudentDetails(Integer.valueOf(id));
		return s;
	}

	@PutMapping("/updatestudent/{id}")
	public ResponseEntity<Student> updateStudentDetails(@PathVariable(value = "id") String id,
			@RequestBody Student student) {
		Student existingStudent = studentCustomRepo.updateStudentInformation(student, Integer.valueOf(id));
		return new ResponseEntity<Student>(existingStudent, HttpStatus.OK);
	}

	@DeleteMapping("/deletestudent")
	public ResponseEntity<String> deleteStudentDetails(@RequestBody Student student) {
		boolean isDeleted = studentCustomRepo.deleteStudentDetails(student.getId());
		if (isDeleted) {
			return new ResponseEntity<String>("Student information is Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid Input. Please check Student id again", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/fetchStudents")
	public List<Student> getStudentByClass(@RequestParam(value = "classInformation") String classInformation) {
		return studentCustomRepo.findAllByClassInfo(classInformation);

	}

	@GetMapping("/fetchStudent")
	public Student getStudentById(@RequestParam(value = "id") Integer id) {
		return getStudentById(String.valueOf(id));
	}

}
