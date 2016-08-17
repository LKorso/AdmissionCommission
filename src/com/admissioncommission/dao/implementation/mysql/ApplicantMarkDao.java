package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IApplicantMarkDao;
import com.admissioncommission.enteties.ApplicantMark;

public class ApplicantMarkDao implements IApplicantMarkDao {
	private static final String ID = "id";
	private static final String APPLICANT_ID = "applicant_id";
	private static final String SUBJECT_ID = "subject_id";
	private static final String MARK = "mark";
	private static final String TABLE_NAME = "applicant_mark";
	private static final String QUERY_FOR_DELETE = "DELETE FROM applicant_mark WHERE id = ?";
	private static final String QUERY_FOR_UPDATE_MARK = "UPDATE applicant_mark SET mark = ? WHERE id = ?";
	private static final String QUERY_FOR_UPDATE_SUBJECT = "UPDATE applicant_mark SET subject_id = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO applicant_mark(applicant_id, subject_id, mark) VALUES(?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM applicant_mark";
	private static final String QUERY_FOR_FIND_BY_APPLICANT_ID = "SELECT * FROM applicant_mark WHERE applicant_id = ";
	private static final String QUERY_FOR_FIND_BY_SUBJECT_ID = "SELECT * FROM applicant_mark WHERE subject_id = ";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM applicant_mark WHERE id = ";
	
	private static final Logger LOGGER = LogManager.getLogger(ApplicantMarkDao.class);
	
	@Override
	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException exception) {
			LOGGER.error("Error deleteing mark", exception);
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
			LOGGER.error("Error updating mark", exception);
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
			LOGGER.error("Error updating mark with new subject id", exception);
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
			LOGGER.error("Error inserting into table ", exception);
		}
	}

	@Override
	public List<ApplicantMark> selectAll() {
		List<ApplicantMark> mark = new ArrayList<ApplicantMark>();
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				mark.add(setApplicantMark(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
		}
		return mark;
	}

	@Override
	public List<ApplicantMark> findByApplicantId(int applicantId) {
		List<ApplicantMark> marks = new ArrayList<ApplicantMark>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_APPLICANT_ID + applicantId)) {
			while(result.next()){
				marks.add(setApplicantMark(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by applicant id", exception);
		}
		
		return marks;
	}

	@Override
	public List<ApplicantMark> findBySubjectId(int subjectId) {
		List<ApplicantMark> marks = new ArrayList<ApplicantMark>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_SUBJECT_ID + subjectId)) {
			while(result.next()){
				marks.add(setApplicantMark(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by subject id", exception);
		}
		
		return marks;
	}
	
	public ApplicantMark findById(int id){
		ApplicantMark applicantMark = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				applicantMark = setApplicantMark(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by id", exception);
		}
		
		return applicantMark;
	}
	
	private ApplicantMark setApplicantMark(ResultSet result) throws SQLException{
		ApplicantMark applicantMark = new ApplicantMark();
		applicantMark.setId(result.getInt(ID));
		applicantMark.setApplicantId(result.getInt(APPLICANT_ID));
		applicantMark.setSubjectId(result.getInt(SUBJECT_ID));
		applicantMark.setMark(result.getDouble(MARK));
		return applicantMark;
	}
}
