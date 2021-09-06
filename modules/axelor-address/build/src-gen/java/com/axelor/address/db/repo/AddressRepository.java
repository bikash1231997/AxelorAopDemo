package com.axelor.address.db.repo;

import com.axelor.address.db.Address;
import com.axelor.db.JpaRepository;

public class AddressRepository extends JpaRepository<Address> {

	public AddressRepository() {
		super(Address.class);
	}

}

