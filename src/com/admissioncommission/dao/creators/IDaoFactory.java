package com.admissioncommission.dao.creators;

import com.admissioncommission.dao.*;

public interface IDaoFactory {
	IApplicantMarkDao getApplicantMarkDao();
	IApplicationDao getApplicationDao();
	IApplicationMarkDao getApplicationMarkDao();
	IApplicationStatusDao getApplicationStatusDao();
	IFacultyDao getFacultyDao();
	IFacultySubjectDao getFacultySubjectDao();
	ISubjectDao getSubjectDao();
	IUserDao getUserDao();
	IUserTypeDao getUserTypeDao();
	IExtendedApplicationDao getExtendedApplicationDao();
}
