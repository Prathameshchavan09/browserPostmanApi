package com.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "Student")

/*
 * @NamedStoredProcedureQueries({
 * 
 * @NamedStoredProcedureQuery( name = "CrudCallProcedureFromProcedure",
 * procedureName = "CrudCallProcedureFromProcedure",
 * 
 * parameters = {
 * 
 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "Type", type =
 * String.class),
 * 
 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type =
 * Long.class),
 * 
 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type =
 * String.class),
 * 
 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "city", type =
 * String.class),
 * 
 * @StoredProcedureParameter(mode = ParameterMode.IN, name = "salary", type =
 * Integer.class)
 * 
 * } )
 * 
 * 
 * })
 */

public class Student {

    @Id
    private long id;
    private String name;
    private String city;
    private int salary;
    
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Student(long id, String name, String city, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.salary = salary;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + ", salary=" + salary + "]";
	}

   
}
