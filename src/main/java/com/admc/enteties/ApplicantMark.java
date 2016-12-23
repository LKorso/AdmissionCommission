package com.admc.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class ApplicantMark implements Serializable{

	private int id;
	private int applicantId;
	private int subjectId;
	private double mark;
}
