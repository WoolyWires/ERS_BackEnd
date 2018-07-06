package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.ConnectionUtility;
import models.User;

public class UserDao {

	public User extractUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("user_id");
		String username = rs.getString("username");
		String password = rs.getString("hash");
		String firstname = rs.getString("first_name");
		String lastname = rs.getString("last_name");
		String email = rs.getString("email");
		int role = rs.getInt("role_id");
		
		User user = new User(username, password, firstname, lastname, email, role);
		user.setId(id);
		
		return user;
	}
	
	public User findUser(int userId) {
		Connection conn = ConnectionUtility.getConnection();
		String query = "SELECT user_id, username, hash, first_name, last_name, email, role_id" 
				+ "FROM ers.users WHERE user_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// extract and return user
				return extractUser(rs);
			}
			// if no user found
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error from SQL Server.");
		}

		return null;
	}
	
	public User findUserByUsername(String username) {
		Connection conn = ConnectionUtility.getConnection();
		String query = "SELECT user_id, username, hash, first_name, last_name, email, role_id" 
				+ "FROM ers.users WHERE username = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// extract and return user
				return extractUser(rs);
			}
			// if no user found
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error from SQL Server.");
		}

		return null;
	}
	
	public User findUserByEmail(String email) {
		Connection conn = ConnectionUtility.getConnection();
		String query = "SELECT user_id, username, hash, first_name, last_name, email, role_id" 
				+ "FROM ers.users WHERE email = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// extract and return user
				return extractUser(rs);
			}
			// if no user found
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error from SQL Server.");
		}

		return null;
	}

	public User findUserByLogin(String name, String password) {
		Connection conn = ConnectionUtility.getConnection();
		String query = "SELECT user_id, username, hash, first_name, last_name, email, role_id "
						+"FROM ers.users WHERE username = ? AND hash = (CRYPT(?, hash))";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, name);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// extract and return user
				return extractUser(rs);
			}
			// if no user found
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error from SQL Server.");
		}

		return null;
	}
}
