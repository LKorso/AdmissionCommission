package com.admc.dao.creators;

import com.admc.dao.*;
import com.admc.dao.implementation.mysql.*;

public class MySqlDaoFactory implements IDaoFactory {
	
	protected MySqlDaoFactory() {
	}
	
	@Override
	public IApplicantMarkDao getApplicantMarkDao() {
		return new ApplicantMarkDao();
	}

	@Override
	public IApplicationDao getApplicationDao() {
		return new ApplicationDao();
	}

	@Override
	public IApplicationMarkDao getApplicationMarkDao() {
		return new ApplicationMarkDao();
	}

	@Override
	public IApplicationStatusDao getApplicationStatusDao() {
		return new ApplicationStatusDao();
	}

	@Override
	public IFacultyDao getFacultyDao() {
		return new FacultyDao();
	}

	@Override
	public IFacultySubjectDao getFacultySubjectDao() {
		return new FacultySubjectDao();
	}

	@Override
	public ISubjectDao getSubjectDao() {
		return new SubjectDao();
	}

	@Override
	public IUserDao getUserDao() {
		return new UserDao();
	}
	
	@Override
	public IExtendedApplicationDao getExtendedApplicationDao(){
		return new ExtendedApplicationDao();
	}
	
	@Override
	public IPriorityDao getPriorityDao() {
		return new PriorityDao();
	}
}
