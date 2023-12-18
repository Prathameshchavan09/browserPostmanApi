package com.springmvc.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String PostMan(){
          return "postMan";
	
	}
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getStudents(Student student)
	{	
		System.out.print("controller of studentlist =========");
	//	String Type=request.getParameter("Type");
        String students = studentService.getAllStudents();
        System.out.println("data================"+students);
        

    //    model.addAttribute("studentlist",students);
        return students;
        
    }
		  

	@PostMapping("/insertStudent")
    public String addStudent(@RequestBody String jsonData)
	{
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
        studentService.insertUser(student, Type, student.getId(), student.getName(), student.getCity(), student.getSalary());
        return "postMan";
    }
	
	/*
	 * @PutMapping(value="/update/{id}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public String
	 * updateStudentDetails(@PathVariable("id") long id,@RequestBody String
	 * jsonData,Model model) {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); Student student = null; try {
	 * student = objectMapper.readValue(jsonData, Student.class); } catch (Exception
	 * e) { e.printStackTrace(); return "error"; } String Type = "update";
	 * System.out.println("called UpdateStudent controller"); Student user =
	 * studentService.updateStudent(student,Type,id,student.getName(),student.
	 * getCity(), student.getSalary()); model.addAttribute("user",user); return
	 * "postMan";
	 * 
	 * }
	 */

	    
	/*
	 * @GetMapping("/getStudent/{id}") public String
	 * getStudentById(@PathVariable("id") long id, Model model) {
	 * 
	 * String Type = "getById"; System.out.println("called GetByID controller");
	 * 
	 * Optional<Student> retriveStudent = studentService.getStudentById(Type, id);
	 * model.addAttribute("SpecificStudentt", retriveStudent.orElse(null));
	 * 
	 * return "specificStudent";
	 * 
	 * }
	 * 
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
	 * @GetMapping("/delete/{id}")
	 * 
	 * public RedirectView delete(@PathVariable("id") long id,HttpServletRequest
	 * request) { System.out.println("deleted controlled"); String Type="delete";
	 * studentService.deleteStudent(Type,id);
	 * 
	 * RedirectView redirectView = new RedirectView();
	 * redirectView.setUrl(request.getContextPath() + "/"); return redirectView;
	 * 
	 * 
	 * }
	 */

}
