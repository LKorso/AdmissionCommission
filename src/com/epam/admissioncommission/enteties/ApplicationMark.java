package com.epam.admissioncommission.enteties;

public class ApplicationMark {
	private int applicationId;
	private int markId;
	
	public ApplicationMark() {
		super();
	}

	public ApplicationMark(int applicationId, int markId) {
		super();
		this.applicationId = applicationId;
		this.markId = markId;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getMarkId() {
		return markId;
	}

	public void setMarkId(int markId) {
		this.markId = markId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("application id: ");
		builder.append(applicationId);
		builder.append("\nmark id: ");
		builder.append(markId);
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicationId;
		result = prime * result + markId;
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
		ApplicationMark other = (ApplicationMark) obj;
		if (applicationId != other.applicationId)
			return false;
		if (markId != other.markId)
			return false;
		return true;
	}
}
