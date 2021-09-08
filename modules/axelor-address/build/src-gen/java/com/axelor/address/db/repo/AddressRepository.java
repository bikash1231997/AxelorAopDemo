package com.axelor.address.db.repo;

import com.axelor.address.db.Address;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class AddressRepository extends JpaRepository<Address> {

	public AddressRepository() {
		super(Address.class);
	}

	public Address findByName(String name) {
		return Query.of(Address.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

