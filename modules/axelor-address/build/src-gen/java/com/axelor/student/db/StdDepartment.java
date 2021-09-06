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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "STUDENT_STD_DEPARTMENT")
public class StdDepartment extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_STD_DEPARTMENT_SEQ")
	@SequenceGenerator(name = "STUDENT_STD_DEPARTMENT_SEQ", sequenceName = "STUDENT_STD_DEPARTMENT_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Size(max = 255)
	private String stdDeptName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stdDept", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> stdDepts;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public StdDepartment() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getStdDeptName() {
		return stdDeptName;
	}

	public void setStdDeptName(String stdDeptName) {
		this.stdDeptName = stdDeptName;
	}

	public List<Student> getStdDepts() {
		return stdDepts;
	}

	public void setStdDepts(List<Student> stdDepts) {
		this.stdDepts = stdDepts;
	}

	/**
	 * Add the given {@link Student} item to the {@code stdDepts}.
	 *
	 * <p>
	 * It sets {@code item.stdDept = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addStdDept(Student item) {
		if (getStdDepts() == null) {
			setStdDepts(new ArrayList<>());
		}
		getStdDepts().add(item);
		item.setStdDept(this);
	}

	/**
	 * Remove the given {@link Student} item from the {@code stdDepts}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeStdDept(Student item) {
		if (getStdDepts() == null) {
			return;
		}
		getStdDepts().remove(item);
	}

	/**
	 * Clear the {@code stdDepts} collection.
	 *
	 * <p>
	 * If you have to query {@link Student} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearStdDepts() {
		if (getStdDepts() != null) {
			getStdDepts().clear();
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
		if (!(obj instanceof StdDepartment)) return false;

		final StdDepartment other = (StdDepartment) obj;
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
			.add("stdDeptName", getStdDeptName())
			.omitNullValues()
			.toString();
	}
}
