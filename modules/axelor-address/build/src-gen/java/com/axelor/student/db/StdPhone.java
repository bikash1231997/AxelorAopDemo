package com.axelor.student.db;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "STUDENT_STD_PHONE")
public class StdPhone extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_STD_PHONE_SEQ")
	@SequenceGenerator(name = "STUDENT_STD_PHONE_SEQ", sequenceName = "STUDENT_STD_PHONE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private Integer phone_no = 0;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "stdPh", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Student student;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public StdPhone() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPhone_no() {
		return phone_no == null ? 0 : phone_no;
	}

	public void setPhone_no(Integer phone_no) {
		this.phone_no = phone_no;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		if (getStudent() != null) {
			getStudent().setStdPh(null);
		}
		if (student != null) {
			student.setStdPh(this);
		}
		this.student = student;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof StdPhone)) return false;

		final StdPhone other = (StdPhone) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("phone_no", getPhone_no())
			.omitNullValues()
			.toString();
	}
}
