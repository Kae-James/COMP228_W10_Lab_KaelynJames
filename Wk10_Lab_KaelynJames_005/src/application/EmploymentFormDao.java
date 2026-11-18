package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmploymentFormDao {
// Draft the method for insert that submits/writes values to DB
	public int saveEmploymentForm(EmploymentForm employment) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getDBConnection();
			connection.setAutoCommit(false);
			String query = "INSERT INTO employment_form(full_name, current_address, contact_number, email_address, highest_educational_attainment, gender, date_available, desired_position, desired_salary, authorized_to_work, relatives_at_company, relatives_explanation) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int counter = 1;
			statement.setString(counter++, employment.getFullName());
			statement.setString(counter++, employment.getCurrentAddress());
			statement.setString(counter++, employment.getContactNumber());
			statement.setString(counter++, employment.getEmailAddress());
			statement.setString(counter++, employment.getHighestEducationalAttainment());
			statement.setString(counter++, employment.getGender());
			statement.setString(counter++, employment.getDateAvailable());
			statement.setString(counter++, employment.getDesiredPosition());
			statement.setString(counter++, employment.getDesiredSalary());
			statement.setBoolean(counter++, employment.isAuthorizedToWork());
			statement.setBoolean(counter++, employment.isRelativesAtCompany());
			statement.setString(counter++, employment.getRelativesExplanation());
			statement.executeUpdate();
			connection.commit();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException exception) {
		//	logger.log(Level.SEVERE, exception.getMessage());
			System.out.println("Insert failed: " + exception.getMessage());
			if (null != connection) {
				connection.rollback();
			}
		} finally {
			if (null != resultSet) {
				resultSet.close();
			}

			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}

		return 0;
	}
	
}
