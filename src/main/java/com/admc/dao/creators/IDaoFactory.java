package com.admc.dao.creators;

import com.admc.dao.*;

public interface IDaoFactory {
	IApplicantMarkDao getApplicantMarkDao();
	IApplicationDao getApplicationDao();
	IApplicationMarkDao getApplicationMarkDao();
	IApplicationStatusDao getApplicationStatusDao();
	IFacultyDao getFacultyDao();
	IFacultySubjectDao getFacultySubjectDao();
	ISubjectDao getSubjectDao();
	IUserDao getUserDao();
	IExtendedApplicationDao getExtendedApplicationDao();
	IPriorityDao getPriorityDao();
}
