package com.axelor.student.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.student.db.Student;

public class StudentRepository extends JpaRepository<Student> {

	public StudentRepository() {
		super(Student.class);
	}

}

