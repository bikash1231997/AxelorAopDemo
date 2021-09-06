package com.axelor.address.db.repo;

import com.axelor.address.db.Contact;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class ContactRepository extends JpaRepository<Contact> {

	public ContactRepository() {
		super(Contact.class);
	}

	public Contact findByEmail(String email) {
		return Query.of(Contact.class)
				.filter("self.email = :email")
				.bind("email", email)
				.fetchOne();
	}

}

