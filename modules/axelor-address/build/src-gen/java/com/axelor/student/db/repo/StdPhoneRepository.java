package com.axelor.student.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.student.db.StdPhone;

public class StdPhoneRepository extends JpaRepository<StdPhone> {

	public StdPhoneRepository() {
		super(StdPhone.class);
	}

}

