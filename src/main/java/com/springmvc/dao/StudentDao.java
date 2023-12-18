package com.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springmvc.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

}

