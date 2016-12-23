package com.admc.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class User implements Serializable{

	private int id;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private String sex;
	private String email;
	private String phone;
	private int userTypeId;
	private String password;
	private int facultyId;
}