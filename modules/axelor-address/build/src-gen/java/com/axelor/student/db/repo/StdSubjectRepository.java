package com.axelor.student.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.student.db.StdSubject;

public class StdSubjectRepository extends JpaRepository<StdSubject> {

	public StdSubjectRepository() {
		super(StdSubject.class);
	}

}

