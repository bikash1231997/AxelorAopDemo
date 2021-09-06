package com.axelor.student.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.student.db.StdAddress;

public class StdAddressRepository extends JpaRepository<StdAddress> {

	public StdAddressRepository() {
		super(StdAddress.class);
	}

}

