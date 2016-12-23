package com.admc.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class ExtendedApplication implements Serializable{

	private int userId;
	private int facultyId;
	private int statusId;
	private int applicationId;
	private String lastName;
	private String firstName;
	private LocalDateTime dateOfBirth;
	private String sex;
	private String phone;
	private String email;
	private String faculty;
	private String status;
	private LocalDateTime fillingDate;
	private String description;
	private int priority;
	private double rating;
}
