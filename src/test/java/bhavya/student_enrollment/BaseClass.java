package bhavya.student_enrollment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import bhavya.student_enrollment.beans.Student;

public class BaseClass{
	
	static final String DB_URL = "jdbc:h2:mem:testdb"; 
	static final String USER = "sa"; 
	static final String PASS = ""; 
	static Connection conn=null;
	static Statement statement=null;

	public static Student setStudentAttribute() {
		Student student= new Student();
		student.setId(5);
		student.setFirstName("Chester");
		student.setLastName("Benington");
		student.setClassInfo("7-A");
		student.setNationality("England");
		return student;
	}
	
	public static String getStudentResponseAsString() {
		return "{\r\n"
				+ "    \"id\": 5,\r\n"
				+ "    \"firstName\": \"Chester\",\r\n"
				+ "    \"lastName\": \"Benington\",\r\n"
				+ "    \"classInfo\": \"7-A\",\r\n"
				+ "    \"nationality\": \"England\"\r\n"
				+ "}";
	}
	
	public static Student updateStudentObject() {
		Student student= new Student();
		student.setFirstName("Chester1");
		student.setLastName("Benington2");
		student.setClassInfo("8-A");
		student.setNationality("Portugal");
		return student;
	}
	
	public static Optional<Student> getOptionalStudent(){
		Optional<Student> studentOptional= Optional.empty();
		studentOptional=Optional.of(setStudentAttribute());
		return studentOptional;
	}
	
	public static Student updatedStudentObject() {
		Student student= new Student();
		student.setId(5);
		student.setFirstName("Chester1");
		student.setLastName("Benington2");
		student.setClassInfo("8-A");
		student.setNationality("Portugal");
		return student;
	}
	
	public static String getStaticStudentInformationClass(){
		return "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 4,\r\n"
				+ "        \"firstName\": \"Rohan\",\r\n"
				+ "        \"lastName\": \"Sinha\",\r\n"
				+ "        \"classInfo\": \"3-A\",\r\n"
				+ "        \"nationality\": \"India\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 6,\r\n"
				+ "        \"firstName\": \"Rohan\",\r\n"
				+ "        \"lastName\": \"Sinha\",\r\n"
				+ "        \"classInfo\": \"3-A\",\r\n"
				+ "        \"nationality\": \"India\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 7,\r\n"
				+ "        \"firstName\": \"Jorge\",\r\n"
				+ "        \"lastName\": \"Silva\",\r\n"
				+ "        \"classInfo\": \"3-A\",\r\n"
				+ "        \"nationality\": \"Switzerland\"\r\n"
				+ "    }\r\n"
				+ "]";
	}
	
}
