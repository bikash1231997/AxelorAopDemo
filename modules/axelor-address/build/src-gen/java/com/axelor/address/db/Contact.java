package com.axelor.address.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "ADDRESS_CONTACT")
public class Contact extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_CONTACT_SEQ")
	@SequenceGenerator(name = "ADDRESS_CONTACT_SEQ", sequenceName = "ADDRESS_CONTACT_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	private LocalDate dateOfBirth;

	@EqualsInclude
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String email;

	@Widget(massUpdate = true)
	@Size(max = 20)
	private String phone;

	@Widget(title = "About me")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "text")
	private String notes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Contact() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * Add the given {@link Address} item to the {@code addresses}.
	 *
	 * <p>
	 * It sets {@code item.contact = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addAddress(Address item) {
		if (getAddresses() == null) {
			setAddresses(new ArrayList<>());
		}
		getAddresses().add(item);
		item.setContact(this);
	}

	/**
	 * Remove the given {@link Address} item from the {@code addresses}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeAddress(Address item) {
		if (getAddresses() == null) {
			return;
		}
		getAddresses().remove(item);
	}

	/**
	 * Clear the {@code addresses} collection.
	 *
	 * <p>
	 * If you have to query {@link Address} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearAddresses() {
		if (getAddresses() != null) {
			getAddresses().clear();
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
		if (!(obj instanceof Contact)) return false;

		final Contact other = (Contact) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return Objects.equals(getEmail(), other.getEmail())
			&& (getEmail() != null);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("firstName", getFirstName())
			.add("lastName", getLastName())
			.add("dateOfBirth", getDateOfBirth())
			.add("email", getEmail())
			.add("phone", getPhone())
			.omitNullValues()
			.toString();
	}
}
