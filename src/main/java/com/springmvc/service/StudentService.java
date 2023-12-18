package com.springmvc.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.dao.StudentDao;
import com.springmvc.model.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	@PersistenceContext

	private EntityManager em;

	public Student insertUser(Student student, String Type, Long id, String name, String city, int salary) {
		System.out.println("Service is called");
		StoredProcedureQuery query = (StoredProcedureQuery) em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
				.setParameter("Type", Type)
				.setParameter("id", id)
				.setParameter("name", name)
				.setParameter("city", city)
				.setParameter("salary", salary);

		query.execute();
		return studentDao.save(student);

	}

	public String  getAllStudents() {
		 List<Student> studentlist =null;
		  String json ="";
		try {
		//	StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
		//	.setParameter("Type", Type);
			
			 studentlist =  studentDao.findAll();
			 
		        ObjectMapper mapper = new ObjectMapper();
		         json = mapper.writeValueAsString(studentlist);
		        
        } catch (Exception ex) {

			System.out.println(ex.getMessage());
		}
		return json;

	}
	

	/*
	 * public List<Student> AllStudents(Student student, String Type, Long id,
	 * String name, String city, Integer salary) { try { StoredProcedureQuery query
	 * = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure");
	 * query.execute();
	 * 
	 * List<Student> students = query.getResultList(); return students;
	 * 
	 * } catch (Exception ex) {
	 * 
	 * System.out.println(ex.getMessage()); } return studentDao.findAll();
	 * 
	 * }
	 */

	/*
	 * @Transactional public Student updateStudent(Student student, String Type,
	 * Long id,String name,String city,int salary) { StoredProcedureQuery query =
	 * em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
	 * .setParameter("Type", Type) .setParameter("id", id) .setParameter("name",
	 * name) .setParameter("city", city) .setParameter("salary", salary);
	 * 
	 * query.execute();
	 * 
	 * Student existingStudent = studentDao.findById(id) .orElseThrow(() -> new
	 * EntityNotFoundException("Student not found with id: " + id));
	 * 
	 * 
	 * existingStudent.setId(id); existingStudent.setName(name);
	 * existingStudent.setCity(city); existingStudent.setSalary(salary);
	 * 
	 * 
	 * return studentDao.save(existingStudent); }
	 */

	/*
	 * public void deleteStudent(String Type,long id) {
	 * 
	 * studentDao.deleteById(id);
	 * 
	 * 
	 * }
	 */

//	@Transactional
//	 public Optional<Student> getStudentById(Student student,String Type ,Long id,String name,String city ,Integer salary)
//	{
//		 StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
//		           .setParameter("Type", Type)
//		   		   .setParameter("id", id)
//		           .setParameter("name", name)
//  		           .setParameter("city", city)
//  		           .setParameter("salary", salary);
//		 query.execute();
//		 
//			return studentDao.findById(id);
//	    }
//	
//	

	/*
	 * public Optional<Student> getStudentById(String type, Long id) { try {
	 * StoredProcedureQuery query =
	 * em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
	 * .setParameter("Type", type).setParameter("id", id);
	 * 
	 * query.execute(); List<Student> students = query.getResultList();
	 * 
	 * if (!students.isEmpty()) { return Optional.of(students.get(0)); } } catch
	 * (Exception ex) { System.out.println(ex.getMessage()); }
	 * 
	 * return studentDao.findById(id);
	 * 
	 * }
	 */

	/*
	 * public Optional<Student> editStudentById(String type, Long id) { try {
	 * StoredProcedureQuery query =
	 * em.createNamedStoredProcedureQuery("CrudCallProcedureFromProcedure")
	 * .setParameter("Type", type) .setParameter("id", id);
	 * 
	 * query.execute(); List<Student> students = query.getResultList();
	 * 
	 * if (!students.isEmpty()) { return Optional.of(students.get(0)); } } catch
	 * (Exception ex) { System.out.println(ex.getMessage()); }
	 * 
	 * return studentDao.findById(id);
	 * 
	 * }
	 */

}
