package com.admc.enteties;

import java.io.Serializable;

public class FacultySubject implements Serializable{
	private static final long serialVersionUID = 1L;
	private int facultyId;
	private int subjectId;
	private double minMark;
	
	public FacultySubject() {
	}

	public FacultySubject(int facultyId, int subjectId, double minMark) {
		super();
		this.facultyId = facultyId;
		this.subjectId = subjectId;
		this.minMark = minMark;
	}

	public double getMinMark() {
		return minMark;
	}

	public void setMinMark(double minMark) {
		this.minMark = minMark;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("faculty id: ");
		builder.append(facultyId);
		builder.append("\nsubject id: ");
		builder.append(subjectId);
		builder.append("\nmin mark: ");
		builder.append(minMark);
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + facultyId;
		long temp;
		temp = Double.doubleToLongBits(minMark);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + subjectId;
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
		FacultySubject other = (FacultySubject) obj;
		if (facultyId != other.facultyId)
			return false;
		if (Double.doubleToLongBits(minMark) != Double.doubleToLongBits(other.minMark))
			return false;
		if (subjectId != other.subjectId)
			return false;
		return true;
	}
}
