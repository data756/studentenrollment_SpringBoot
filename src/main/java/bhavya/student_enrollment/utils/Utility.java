package bhavya.student_enrollment.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bhavya.student_enrollment.beans.Student;

public class Utility {

	public static Student extractStudent(Optional<Student> studentOp, Integer id) {
		List<Student> studentList = studentOp.stream().collect(Collectors.toList());
		Student student = null;
		for (Student s : studentList) {
			if (id == s.getId()) {
				student = s;
			}
		}
		return student;
	}
	
	public static Student nullStudent() {
		Student student= new Student();
		student.setId(0);
		student.setFirstName("Unknown");
		student.setLastName("Unknown");
		student.setNationality("Unknown");
		student.setClassInfo("Unknown");
		return student;
	}
}
