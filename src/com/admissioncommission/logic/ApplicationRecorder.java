package com.admissioncommission.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.admissioncommission.dao.IApplicationDao;
import com.admissioncommission.dao.IApplicationStatusDao;
import com.admissioncommission.dao.IUserDao;
import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.dao.creators.QueryBuilder;
import com.admissioncommission.enteties.ExtendedApplication;
import com.admissioncommission.enteties.Faculty;
import com.admissioncommission.enteties.Priority;

public class ApplicationRecorder {
	private List<List<ExtendedApplication>> applicationsByFaculties;
	private HashMap<Integer, Integer> faculties = new HashMap<>();
	private IDaoFactory daoFactory;
	private HashMap<Integer, Integer> students = new HashMap<>();
	private List<Integer> acceptedApplicationsId = new ArrayList<>();
	private List<Priority> priorities;
	
	public ApplicationRecorder(){
		daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		setApplications();
		setFaculties();
		setPriorities();
	}
	
	public void enrollStudents(){
		processApplications();
		insertStudentsToBase();
		insertAcceptedApplicationToBase();
		insertUnacceptedApplicationToBase();
	}
	
	private void processApplications(){
		Iterator<Priority> prioritiesIterator = priorities.iterator();
		Priority currentPriority = prioritiesIterator.next();
		while(!applicationsByFaculties.isEmpty()){
			boolean changePriorityFlag;
			
			do {
				deleteFacultyLists();
				List<ExtendedApplication> topApplications = new ArrayList<>();
				for(List<ExtendedApplication> currentFacultyList : applicationsByFaculties){
					topApplications.add(currentFacultyList.get(0));
				}
				
				changePriorityFlag = enrollStudents(topApplications, currentPriority);
			} while (changePriorityFlag);
			
			currentPriority = prioritiesIterator.next();
		}
	}
	
	private boolean enrollStudents(List<ExtendedApplication> currentApplications, Priority priority){
		boolean enrollFlag = false;
		List<Integer> studentsId = new ArrayList<>();
		
		for(ExtendedApplication currentApplication : currentApplications){
			if(currentApplication.getPriority() <= priority.getPriority()){
				enrollFlag = true;
				addStudent(currentApplication);
				addAcceptedApplication(currentApplication);
				decrementFacultyPositions(currentApplication.getFacultyId());
				studentsId.add(currentApplication.getUserId());
			}
		}
		
		if(enrollFlag){
			deleteEnrolledStudents(studentsId);
		}
		
		return enrollFlag;
	}
	
	private void deleteEnrolledStudents(List<Integer> studentsId){
		for(List<ExtendedApplication> facultyList : applicationsByFaculties){
			Iterator<ExtendedApplication> iterator = facultyList.iterator();
			while(iterator.hasNext()){
				ExtendedApplication currentApplicaiton = iterator.next();
				for(Integer studentId : studentsId){
					if(currentApplicaiton.getUserId() == studentId){
						iterator.remove();
						break;
					}
				}
			}
		}
	}
	
	private void deleteFacultyLists(){
		Iterator<List<ExtendedApplication>> iterator = applicationsByFaculties.iterator();
		while(iterator.hasNext()){
			List<ExtendedApplication> currentList = iterator.next();
			if(currentList.isEmpty() || 
					faculties.get(currentList.get(0).getFacultyId()) == 0){
				iterator.remove();
			}
		}
	}
	
	private void insertStudentsToBase(){
		IUserDao userDao = daoFactory.getUserDao();
		HashMap<String, String> changes = new HashMap<>();
		HashMap<String, String> criterions = new HashMap<>();
		int sudentTypeId = daoFactory.getUserTypeDao().findByType("Student").getId();
		for(Integer studentId : students.keySet()){
			changes.put("user_type_id", Integer.toString(sudentTypeId));
			changes.put("faculty_id", Integer.toString(students.get(studentId)));
			criterions.put("id", studentId.toString());
			userDao.update(changes, criterions);
		}
	} 
	
	private void insertAcceptedApplicationToBase(){
		IApplicationDao applicationDao = daoFactory.getApplicationDao();
		int acceptedStatusId = daoFactory.getApplicationStatusDao().findByStatus("Accepted").getId();
		HashMap<String, String> changes = new HashMap<>();
		changes.put("status_id", Integer.toString(acceptedStatusId));
		HashMap<String, String> criterions = new HashMap<>();
		for(Integer id : acceptedApplicationsId){
			criterions.put("id", id.toString());
			applicationDao.update(changes, criterions);
		}
	}
	
	private void setApplications(){
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("status_id", Integer.toString(daoFactory.getApplicationStatusDao().findByStatus("Passed").getId()));
		List<ExtendedApplication> allApplications = daoFactory.getExtendedApplicationDao().customSelectSorted(criterions, QueryBuilder.ORDER_DESC);
		List<ExtendedApplication> currentFacultyApplications;
		applicationsByFaculties = new ArrayList<>();
		
		for(Faculty faculty : daoFactory.getFacultyDao().selectAll()){
			currentFacultyApplications = new ArrayList<>();
			for(ExtendedApplication currentApplication : allApplications){
				if(currentApplication.getFacultyId() == faculty.getId()){
					currentFacultyApplications.add(currentApplication);
				}
			}
			if(!currentFacultyApplications.isEmpty()){
				applicationsByFaculties.add(currentFacultyApplications);
			}
		}
	}
	
	private void setFaculties(){
		for(Faculty faculty : daoFactory.getFacultyDao().selectAll()){
			faculties.put(faculty.getId(), faculty.getStudentsNumber());
		}	
	}
	
	private void setPriorities(){
		priorities = daoFactory.getPriorityDao().selectAllSorted();
	}
	
	private void decrementFacultyPositions(int id){
		faculties.put(id, faculties.get(id) - 1);
	}
	
	private void addStudent(ExtendedApplication acceptedApplication){
		students.put(acceptedApplication.getUserId(), acceptedApplication.getFacultyId());
	}
	
	private void addAcceptedApplication(ExtendedApplication acceptedApplication){
		acceptedApplicationsId.add(acceptedApplication.getApplicationId());
	}
	
	private void insertUnacceptedApplicationToBase(){
		IApplicationStatusDao statusDao = daoFactory.getApplicationStatusDao();
		int acceptedApplicationId = statusDao.findByStatus("Accepted").getId();
		int unacceptedApplicationId = statusDao.findByStatus("Unaccepted").getId();
		daoFactory.getApplicationDao().notEqualUpdate("status_id", Integer.toString(unacceptedApplicationId), 
				Integer.toString(acceptedApplicationId));
	}
}
