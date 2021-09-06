package com.axelor.student.db;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "STUDENT_STD_SUBJECT")
public class StdSubject extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_STD_SUBJECT_SEQ")
	@SequenceGenerator(name = "STUDENT_STD_SUBJECT_SEQ", sequenceName = "STUDENT_STD_SUBJECT_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Size(max = 255)
	private String stdSubName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "stdSub", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Student> stdSubs;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public StdSubject() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getStdSubName() {
		return stdSubName;
	}

	public void setStdSubName(String stdSubName) {
		this.stdSubName = stdSubName;
	}

	public Set<Student> getStdSubs() {
		return stdSubs;
	}

	public void setStdSubs(Set<Student> stdSubs) {
		this.stdSubs = stdSubs;
	}

	/**
	 * Add the given {@link Student} item to the {@code stdSubs}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addStdSub(Student item) {
		if (getStdSubs() == null) {
			setStdSubs(new HashSet<>());
		}
		getStdSubs().add(item);
	}

	/**
	 * Remove the given {@link Student} item from the {@code stdSubs}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeStdSub(Student item) {
		if (getStdSubs() == null) {
			return;
		}
		getStdSubs().remove(item);
	}

	/**
	 * Clear the {@code stdSubs} collection.
	 *
	 */
	public void clearStdSubs() {
		if (getStdSubs() != null) {
			getStdSubs().clear();
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
		if (!(obj instanceof StdSubject)) return false;

		final StdSubject other = (StdSubject) obj;
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
			.add("stdSubName", getStdSubName())
			.omitNullValues()
			.toString();
	}
}
