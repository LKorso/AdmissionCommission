package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IFacultySubjectDao;
import com.admissioncommission.enteties.FacultySubject;

public class FacultySubjectDao implements IFacultySubjectDao {
	private static final String QUERY_FOR_DELETE = "DELETE FROM faculty_subject WHERE faculty_id = ? "
			+ "AND subject_id = ?";
	private static final String QUERY_FOR_CHANGE_SUBJECT = "UPDATE faculty_subject SET subject_id = ? WHERE faculty_id = ?"
			+ "AND subject_id = ?";
	private static final String QUERY_FOR_CHANGE_FACULTY = "UPDATE faculty_subject SET faculty_id = ? WHERE faculty_id = ?"
			+ "AND subject_id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO faculty_subject(faculty_id, subject_id, min_mark) VALUES (?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM faculty_subject";
	private static final String QUERY_FOR_FIND_BY_SUBJECT_ID = "SELECT * FROM faculty_subject WHERE subject_id = ";
	private static final String QUERY_FOR_FIND_BY_FACULTY_ID = "SELECT * FROM faculty_subject WHERE faculty_id = ";
	
	@Override
	public void delete(int facultyId, int subjectId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, facultyId);
			statement.setInt(2, subjectId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void changeSubject(int facultyId, int subjectId, int newSubjectId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_CHANGE_SUBJECT)) {
			statement.setInt(1, newSubjectId);
			statement.setInt(2, facultyId);
			statement.setInt(3, subjectId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void changeFaculty(int subjectId, int facultyId, int newFacultyId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_CHANGE_FACULTY)) {
			statement.setInt(1, newFacultyId);
			statement.setInt(2, facultyId);
			statement.setInt(3, subjectId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void insert(FacultySubject newFacultySubject) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setInt(1, newFacultySubject.getFacultyId());
			statement.setInt(2, newFacultySubject.getSubjectId());
			statement.setDouble(3, newFacultySubject.getMinMark());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<FacultySubject> selectAll() {
		List<FacultySubject> facultySubjects = new ArrayList<FacultySubject>();
		FacultySubject currentFacultySubject;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				currentFacultySubject = new FacultySubject();
				currentFacultySubject.setFacultyId(result.getInt("faculty_id"));
				currentFacultySubject.setSubjectId(result.getInt("subject_id"));
				currentFacultySubject.setMinMark(result.getDouble("min_mark"));
				facultySubjects.add(currentFacultySubject);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return facultySubjects;
	}

	@Override
	public List<FacultySubject> findByFacultyId(int facultyId) {
		List<FacultySubject> facultySubjects = new ArrayList<FacultySubject>();
		FacultySubject currentFacultySubject;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_FACULTY_ID + facultyId)) {
			while(result.next()){
				currentFacultySubject = new FacultySubject();
				currentFacultySubject.setFacultyId(result.getInt("faculty_id"));
				currentFacultySubject.setSubjectId(result.getInt("subject_id"));
				currentFacultySubject.setMinMark(result.getDouble("min_mark"));
				facultySubjects.add(currentFacultySubject);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return facultySubjects;
	}

	@Override
	public List<FacultySubject> findBySubjectId(int subjectId) {
		List<FacultySubject> facultySubjects = new ArrayList<FacultySubject>();
		FacultySubject currentFacultySubject;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_SUBJECT_ID + subjectId)) {
			while(result.next()){
				currentFacultySubject = new FacultySubject();
				currentFacultySubject.setFacultyId(result.getInt("faculty_id"));
				currentFacultySubject.setSubjectId(result.getInt("subject_id"));
				currentFacultySubject.setMinMark(result.getDouble("min_mark"));
				facultySubjects.add(currentFacultySubject);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return facultySubjects;
	}

}
