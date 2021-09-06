package com.axelor.employee.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.employee.db.Employee;

public class EmployeeRepository extends JpaRepository<Employee> {

	public EmployeeRepository() {
		super(Employee.class);
	}

	public Employee findByEmail(String email) {
		return Query.of(Employee.class)
				.filter("self.email = :email")
				.bind("email", email)
				.fetchOne();
	}

}

