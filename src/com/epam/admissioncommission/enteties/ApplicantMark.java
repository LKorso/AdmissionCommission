package com.epam.admissioncommission.enteties;

public class ApplicantMark {
	private int id;
	private int applicantId;
	private int subjectId;
	private double mark;
	
	public ApplicantMark() {
	}

	public ApplicantMark(int id, int applicantId, int subjectId, double mark) {
		super();
		this.id = id;
		this.applicantId = applicantId;
		this.subjectId = subjectId;
		this.mark = mark;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("id: ");
		builder.append(id);
		builder.append("\napplicant id: ");
		builder.append(applicantId);
		builder.append("\nsubject id: ");
		builder.append(subjectId);
		builder.append("\nscore: ");
		builder.append(mark);
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicantId;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(mark);
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
		ApplicantMark other = (ApplicantMark) obj;
		if (applicantId != other.applicantId)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(mark) != Double.doubleToLongBits(other.mark))
			return false;
		if (subjectId != other.subjectId)
			return false;
		return true;
	}
}
