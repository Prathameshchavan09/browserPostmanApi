package com.springmvc.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.model.Student;
import com.springmvc.service.StudentService;

import ch.qos.logback.classic.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/")
	public String PostMan() {
		return "postMan";

	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getStudents(Student student) {
		System.out.print("controller of studentlist =========");
		// String Type=request.getParameter("Type");
		String students = studentService.getAllStudents();
		System.out.println("data================" + students);

		// model.addAttribute("studentlist",students);
		return students;

	}

	@PostMapping("/insertStudent")
	public String addStudent(@RequestBody String jsonData) {
		ObjectMapper objectMapper = new ObjectMapper();
		Student student = null;
		try {
			student = objectMapper.readValue(jsonData, Student.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		String Type = "insert";
		System.out.println("insert controller called");
		studentService.insertUser(student, Type, student.getId(), student.getName(), student.getCity(),
				student.getSalary());
		return "postMan";
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateStudentDetails(@PathVariable("id") long id, @RequestBody String jsonData, Model model) {

		ObjectMapper objectMapper = new ObjectMapper();
		Student student = null;
		try {
			student = objectMapper.readValue(jsonData, Student.class);

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		String Type = "update";
		System.out.println("called UpdateStudent controller");
		studentService.updateStudent(student, Type, student.getId(), student.getName(), student.getCity(),
				student.getSalary());

		return "postMan";

	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		System.out.println("deleted controlled");
		studentService.deleteStudent(id);

		return "postMan";

	}

	@GetMapping(value = "/getStudentById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getStudentById(@PathVariable("id") long id) {

		System.out.println("called GetByID controller");
        String retriveStudent = studentService.getStudentById(id);
        return retriveStudent;

	}
	
	

	/*
	 * @GetMapping("/editStudent/{id}") public String
	 * editStudentById(@PathVariable("id") Long id, Model model) {
	 * 
	 * String Type = "getById"; System.out.println("called editStudent controller");
	 * 
	 * Optional<Student> retriveStudent = studentService.editStudentById(Type, id);
	 * model.addAttribute("editStudent", retriveStudent.orElse(null));
	 * 
	 * return "updateUser"; }
	 * 
	 * 
	 * 
	 * 
	 */

}
