package com.admc.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Application implements Serializable{

	private int id;
	private Date date;
	private int applicantId;
	private int facultyId;
	private int statusId;
	private int priorityId;
	private String description;
}
