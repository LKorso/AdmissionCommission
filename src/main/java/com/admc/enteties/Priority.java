package com.admc.enteties;

import java.io.Serializable;

public class Priority implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int priority;
	
	public Priority() {
	}
	
	public Priority(int id, int priority) {
		this.id = id;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("id: ");
		builder.append(id);
		builder.append("\npriority: ");
		builder.append(priority);
		
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + priority;
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
		Priority other = (Priority) obj;
		if (id != other.id)
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}
}
