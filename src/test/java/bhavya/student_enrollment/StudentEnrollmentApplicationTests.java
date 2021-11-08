package bhavya.student_enrollment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import bhavya.student_enrollment.beans.Student;
import bhavya.student_enrollment.controller.StudentController;
import bhavya.student_enrollment.repository.StudentRepository;
import bhavya.student_enrollment.repository.StudentRepositoryImpl;
import bhavya.student_enrollment.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class StudentEnrollmentApplicationTests extends BaseClass {

	static Student student = setStudentAttribute();

	@Autowired
	StudentController studentController;

	@Autowired
	StudentRepository repository;

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	StudentRepositoryImpl studentRespository;

	@MockBean
	StudentService studentService;

	@Test
	public void validateStatusCodeAddStudentExisting() {
		when(studentService.checkStudentExist(student.getId())).thenReturn(true);
		ResponseEntity response = studentController.addStudent(student);
		assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
	}

	@Test
	public void validateStatusCodeAddNewStudent() {

		when(studentService.checkStudentExist(student.getId())).thenReturn(false);
		ResponseEntity response = studentController.addStudent(student);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	public void validateMessageAddStudentExisting() {
		when(studentService.checkStudentExist(student.getId())).thenReturn(true);
		ResponseEntity response = studentController.addStudent(student);
		assertEquals(response.getBody().toString(), "Student already exist with Id " + student.getId());
	}

	@Test
	public void validateMessageNewStudent() {

		when(studentService.checkStudentExist(student.getId())).thenReturn(false);
		ResponseEntity response = studentController.addStudent(student);
		assertEquals(response.getBody().toString(), "Student is added");
	}

	@Test
	public void validateGetStudentById() {
		try {
			ObjectMapper map = new ObjectMapper();
			String jsonExpected = map.writeValueAsString(student);
			when(studentRespository.getStudentDetails(student.getId())).thenReturn(student);
			this.mockMVC.perform(get("/getstudent/" + student.getId())).andDo(print()).andExpect(status().isOk())
					.andExpect(content().json(jsonExpected));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validateUpdateStudentInformationImple() {
		try {
			Student newStudentInformation = updateStudentObject();
			ObjectMapper map = new ObjectMapper();
			String studentInfo = map.writeValueAsString(newStudentInformation);
			String newStudentInformationAsString = map.writeValueAsString(updatedStudentObject());
			when(studentRespository.getStudentDetails(student.getId())).thenReturn(student);
			when(studentRespository.updateStudentInformation(newStudentInformation, student.getId()))
					.thenReturn(updatedStudentObject());
			this.mockMVC
					.perform(put("/updatestudent/" + student.getId()).contentType(MediaType.APPLICATION_JSON)
							.content(studentInfo))
					.andDo(print()).andExpect(status().isOk()).andExpect(content().json(newStudentInformationAsString));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Test
	public void validateDeleteExistingStudentById() {
		when(studentRespository.deleteStudentDetails(student.getId())).thenReturn(true);
		try {
			this.mockMVC
					.perform(delete("/deletestudent").contentType(MediaType.APPLICATION_JSON)
							.content("{\"id\" : \"" + student.getId() + "\"}"))
					.andExpect(status().isOk()).andExpect(content().string("Student information is Deleted"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void validateDeleteNotExistingStudentById() {
		when(studentRespository.deleteStudentDetails(student.getId())).thenReturn(false);
		try {
			this.mockMVC
					.perform(delete("/deletestudent").contentType(MediaType.APPLICATION_JSON)
							.content("{\"id\" : \"" + student.getId() + "\"}"))
					.andExpect(status().isNotFound()).andExpect(content().string("Invalid Input. Please check Student id again"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void validateFetchStudentById() {
		try {
			ObjectMapper map = new ObjectMapper();
			String jsonExpected = map.writeValueAsString(student);
			when(studentRespository.getStudentDetails(student.getId())).thenReturn(student);
			this.mockMVC.perform(get("/fetchStudent?id="+student.getId())).andDo(print()).andExpect(status().isOk())
					.andExpect(content().json(jsonExpected));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
