package com.admc.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class User implements Serializable{

	private int id;
	private String lastName;
	private String firstName;
	private LocalDateTime dateOfBirth;
	private String sex;
	private String email;
	private String phone;
	private UserRole role;
	private String password;
	private int facultyId;

	public enum UserRole {
		ADMIN, APPLICANT, STUDENT
	}
}