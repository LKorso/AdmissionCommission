package com.admissioncommission.enteties;

import java.io.Serializable;

public class Faculty implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int studentsNumber;
	private String shortName;
	
	public Faculty() {
	}

	public Faculty(int id, String name, int studentsNumber, String shortName) {
		super();
		this.id = id;
		this.name = name;
		this.studentsNumber = studentsNumber;
		this.shortName = shortName;
	}

	public int getStudentsNumber() {
		return studentsNumber;
	}

	public void setStudentsNumber(int studentsNumber) {
		this.studentsNumber = studentsNumber;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("id: ");
		builder.append(id);
		builder.append("\nname: ");
		builder.append(name);
		builder.append("\nshort name: ");
		builder.append(shortName);
		builder.append("\nnumber of students: ");
		builder.append(studentsNumber);
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + studentsNumber;
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
		Faculty other = (Faculty) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (studentsNumber != other.studentsNumber)
			return false;
		return true;
	}
}
