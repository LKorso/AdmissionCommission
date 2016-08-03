package com.epam.admissioncommission.enteties;

import java.sql.Date;

public class ExtendedApplication {
	private int userId;
	private int facultyId;
	private int statusId;
	private int applicationId;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private String sex;
	private String phone;
	private String email;
	private String faculty;
	private String status;
	private Date fillingDate;
	private String description;
	
	public ExtendedApplication() {
	}

	public ExtendedApplication(int userId, int facultyId, int statusId, int applicationId, String lastName,
			String firstname, Date dateOfBirth, String sex, String phone, String email, String faculty, String status,
			Date fillingDate, String description) {
		this.userId = userId;
		this.facultyId = facultyId;
		this.statusId = statusId;
		this.applicationId = applicationId;
		this.lastName = lastName;
		this.firstName = firstname;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.faculty = faculty;
		this.status = status;
		this.fillingDate = fillingDate;
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(Date fillingDate) {
		this.fillingDate = fillingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("First name: ");
		builder.append(firstName);
		builder.append("\nLast name: ");
		builder.append(lastName);
		builder.append("\nEmail: ");
		builder.append(email);
		builder.append("\nPhone: ");
		builder.append(phone);
		builder.append("\nStatus: ");
		builder.append(status);
		builder.append("\nUser id: ");
		builder.append(userId);
		builder.append("\nFaculty id: ");
		builder.append(facultyId);
		builder.append("\nFaculty: ");
		builder.append(faculty);
		builder.append("\nStatus id: ");
		builder.append(statusId);
		builder.append("\nApplication id: ");
		builder.append(applicationId);
		builder.append("\nDate of birth: ");
		builder.append(dateOfBirth);
		builder.append("\nFilling date: ");
		builder.append(fillingDate);
		builder.append("\nSex: ");
		builder.append(sex);
		if (description != null){
			builder.append("\nDescription: ");
			builder.append(description);
		}
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicationId;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + facultyId;
		result = prime * result + ((fillingDate == null) ? 0 : fillingDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + statusId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtendedApplication other = (ExtendedApplication) obj;
		if (applicationId != other.applicationId)
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (facultyId != other.facultyId)
			return false;
		if (fillingDate == null) {
			if (other.fillingDate != null)
				return false;
		} else if (!fillingDate.equals(other.fillingDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sex != other.sex)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId != other.statusId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}
