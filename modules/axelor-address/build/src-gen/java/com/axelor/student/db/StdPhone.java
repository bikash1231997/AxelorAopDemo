package com.axelor.student.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private Long phone_no = 0L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stdPh", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> student;

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

	public Long getPhone_no() {
		return phone_no == null ? 0L : phone_no;
	}

	public void setPhone_no(Long phone_no) {
		this.phone_no = phone_no;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	/**
	 * Add the given {@link Student} item to the {@code student}.
	 *
	 * <p>
	 * It sets {@code item.stdPh = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addStudent(Student item) {
		if (getStudent() == null) {
			setStudent(new ArrayList<>());
		}
		getStudent().add(item);
		item.setStdPh(this);
	}

	/**
	 * Remove the given {@link Student} item from the {@code student}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeStudent(Student item) {
		if (getStudent() == null) {
			return;
		}
		getStudent().remove(item);
	}

	/**
	 * Clear the {@code student} collection.
	 *
	 * <p>
	 * If you have to query {@link Student} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearStudent() {
		if (getStudent() != null) {
			getStudent().clear();
		}
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
