package bhavya.student_enrollment.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import bhavya.student_enrollment.beans.Student;
import bhavya.student_enrollment.utils.Utility;


@Service
public class StudentRepositoryImpl implements StudentRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(StudentRepositoryImpl.class);

	@Autowired
	StudentRepository studentRepository;
	
	public StudentRepositoryImpl (@Lazy StudentRepository repo) {
		this.studentRepository=repo;
	}

	public Optional<Student> findStudentById(int id) {
		Optional<Student> studentList = null;
		try {
			log.info("Finding the student information from database with id =>" + id);
			studentList = studentRepository.findById(id);
			if (studentList.isPresent()) {
				log.info("Found the student information");
			} else {
				log.info("student information is not found " + id);
				return Optional.of(Utility.nullStudent());
			}
		} catch (Exception e) {
			log.info("Some Error occured while fetching the record from db =>" + e.getMessage());
			e.printStackTrace();
		}
		return studentList;
	}

	public void saveStudentRecord(Student student) {
		try {
			log.info("Saving the student information in DB with Id => " + student.getId());
			studentRepository.save(student);
			log.info("Student Record successfully saved in database");
		} catch (Exception e) {
			log.info("Some Error occured while writing the record to db =>" + e.getMessage());
			e.printStackTrace();
		}
	}

	public Student getStudentDetails(Integer id) {
		Student student = null;
		log.info("extracting student information from Optional List");
		try {
			Optional<Student> optionalStudent = findStudentById(id);
			Student stu = optionalStudent.get();
			if (stu.getId() != 0) {
				student = Utility.extractStudent(optionalStudent, id);
			} else {
				student = stu;
				log.info("No record found in Database");
			}
		} catch (Exception e) {
			log.info("Some error occured while  =>" + e.getMessage());
			e.printStackTrace();
		}
		return student;
	}

	public Student updateStudentInformation(Student student, Integer id) {
		Student existingStudent = getStudentDetails(id);
		if (existingStudent.getId() == 0) {
			return existingStudent;
		}

		try {
			log.info("Updating following attributes of  existing student with student id =>" + id);
			if (existingStudent != null) {
				if (student.getFirstName() != null) {
					log.info("updating first name of existing student");
					existingStudent.setFirstName(student.getFirstName());
				}
				if (student.getLastName() != null) {
					log.info("updating last name of existing student");
					existingStudent.setLastName(student.getLastName());
				}
				if (student.getClassInfo() != null) {
					log.info("updating class information of existing student");
					existingStudent.setClassInfo(student.getClassInfo());
				}
				if (student.getNationality() != null) {
					log.info("updating nationality of existing student");
					existingStudent.setNationality(student.getNationality());
				}
			}
			saveStudentRecord(existingStudent);

		} catch (Exception e) {
			log.info("Some error occured while updating student details =>" + e.getMessage());
			e.printStackTrace();
		}
		return existingStudent;
	}

	public boolean deleteStudentDetails(Integer id) {
		Student student = getStudentDetails(id);
		try {
			if (student.getId() != 0) {
				log.info("Deleting student information with Id =>" + String.valueOf(id));
				studentRepository.deleteById(id);
				log.info("Student information with id " + String.valueOf(id) + " is successfully deleted.");
				return true;
			} else {
				log.info("Student with information with id " + String.valueOf(id) + " does not exist");
				return false;
			}
		}

		catch (Exception e) {
			log.info("Error occured while deleting the student information because of =>" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> findAllByClassInfo(String classInformation) {

		List<Student> sameClass = new ArrayList<Student>();

		try {
			log.info("Finding all the student with the class =>" + classInformation);
			List<Student> allStudentList = studentRepository.findAll();

			sameClass = allStudentList.stream().filter(s -> s.getClassInfo().equalsIgnoreCase(classInformation))
					.collect(Collectors.toList());
			log.info("There are " + sameClass.size() + " students with the class " + classInformation);

		} catch (Exception e) {
			log.info("Error occured while fetching the Student information due to " + e.getMessage());
			e.printStackTrace();
		}
		return sameClass;
	}

}
