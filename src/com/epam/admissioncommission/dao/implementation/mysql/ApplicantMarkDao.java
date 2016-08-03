package com.epam.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.admissioncommission.connection.Connector;
import com.epam.admissioncommission.dao.IApplicantMarkDao;
import com.epam.admissioncommission.dao.creators.QueryBuilder;
import com.epam.admissioncommission.enteties.ApplicantMark;

public class ApplicantMarkDao implements IApplicantMarkDao {
	public static final String ID = "id";
	public static final String APPLICANT_ID = "applicant_id";
	public static final String SUBJECT_ID = "subject_id";
	public static final String MARK = "mark";
	public static final String TABLE_NAME = "applicant_mark";
	
	private static final String QUERY_FOR_DELETE = "DELETE FROM applicant_mark WHERE id = ?";
	private static final String QUERY_FOR_UPDATE_MARK = "UPDATE applicant_mark SET mark = ? WHERE id = ?";
	private static final String QUERY_FOR_UPDATE_SUBJECT = "UPDATE applicant_mark SET subject_id = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO applicant_mark(applicant_id, subject_id, mark) VALUES(?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM applicant_mark";
	private static final String QUERY_FOR_FIND_BY_APPLICANT_ID = "SELECT * FROM applicant_mark WHERE applicant_id = ";
	private static final String QUERY_FOR_FIND_BY_SUBJECT_ID = "SELECT * FROM applicant_mark WHERE subject_id = ";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM applicant_mark WHERE id = ";
	
	@Override
	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException exception) {
			
		}

	}

	@Override
	public void updateMark(int id, double newMark) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE_MARK)) {
			statement.setInt(2, id);
			statement.setDouble(1, newMark);
			statement.executeUpdate();
		} catch(SQLException exception) {
			
		}
	}
	
	@Override
	public void updateSubject(int id, int subjectId){
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE_SUBJECT)) {
			statement.setInt(2, id);
			statement.setInt(1, subjectId);
			statement.executeUpdate();
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void insert(ApplicantMark newApplicantScore) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setInt(1, newApplicantScore.getApplicantId());
			statement.setInt(2, newApplicantScore.getSubjectId());
			statement.setDouble(3, newApplicantScore.getMark());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<ApplicantMark> selectAll() {
		List<ApplicantMark> mark = new ArrayList<ApplicantMark>();
		ApplicantMark applicantMark = new ApplicantMark();
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				applicantMark.setId(result.getInt(ID));
				applicantMark.setApplicantId(result.getInt(APPLICANT_ID));
				applicantMark.setSubjectId(result.getInt(SUBJECT_ID));
				applicantMark.setMark(result.getDouble(MARK));
				mark.add(applicantMark);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return mark;
	}

	@Override
	public List<ApplicantMark> findByApplicantId(int applicantId) {
		List<ApplicantMark> marks = new ArrayList<ApplicantMark>();
		ApplicantMark applicantMark;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_APPLICANT_ID + applicantId)) {
			while(result.next()){
				applicantMark = new ApplicantMark();
				applicantMark.setId(result.getInt(ID));
				applicantMark.setApplicantId(result.getInt(APPLICANT_ID));
				applicantMark.setSubjectId(result.getInt(SUBJECT_ID));
				applicantMark.setMark(result.getDouble(MARK));
				marks.add(applicantMark);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return marks;
	}

	@Override
	public List<ApplicantMark> findBySubjectId(int subjectId) {
		List<ApplicantMark> marks = new ArrayList<ApplicantMark>();
		ApplicantMark applicantMark = new ApplicantMark();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_SUBJECT_ID + subjectId)) {
			while(result.next()){
				applicantMark.setId(result.getInt(ID));
				applicantMark.setApplicantId(result.getInt(APPLICANT_ID));
				applicantMark.setSubjectId(result.getInt(SUBJECT_ID));
				applicantMark.setMark(result.getDouble(MARK));
				marks.add(applicantMark);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return marks;
	}
	
	public ApplicantMark findById(int id){
		ApplicantMark applicantMark = new ApplicantMark();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				applicantMark.setId(result.getInt(ID));
				applicantMark.setApplicantId(result.getInt(APPLICANT_ID));
				applicantMark.setSubjectId(result.getInt(SUBJECT_ID));
				applicantMark.setMark(result.getDouble(MARK));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applicantMark;
	}
}
