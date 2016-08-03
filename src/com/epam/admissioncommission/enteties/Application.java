package com.epam.admissioncommission.enteties;

import java.sql.Date;

public class Application {
	private int id;
	private Date date;
	private int applicantId;
	private int facultyId;
	private int statusId;
	private String description;
	
	public Application() {
	}

	public Application(int id, Date date, int applicantId, int facultyId, int statusId, String descripshion) {
		super();
		this.id = id;
		this.date = date;
		this.applicantId = applicantId;
		this.facultyId = facultyId;
		this.statusId = statusId;
		this.description = descripshion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("id: ");
		builder.append(id);
		builder.append("\ndate: ");
		builder.append(date);
		builder.append("\napplicant id: ");
		builder.append(applicantId);
		builder.append("\nfaculty id: ");
		builder.append(facultyId);
		builder.append("\nstatus id: ");
		builder.append(statusId);
		builder.append("\ndescription: ");
		builder.append(description);
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicantId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + facultyId;
		result = prime * result + id;
		result = prime * result + statusId;
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
		Application other = (Application) obj;
		if (applicantId != other.applicantId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (facultyId != other.facultyId)
			return false;
		if (id != other.id)
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}
}
