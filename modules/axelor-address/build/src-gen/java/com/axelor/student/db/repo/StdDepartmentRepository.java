package com.axelor.student.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.student.db.StdDepartment;

public class StdDepartmentRepository extends JpaRepository<StdDepartment> {

	public StdDepartmentRepository() {
		super(StdDepartment.class);
	}

}

