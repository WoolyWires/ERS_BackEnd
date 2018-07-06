package daos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import models.Reimbursement;
import utilities.ConnectionUtility;

public class TicketDao {

	public ArrayList<Reimbursement> findTickets(int user_id) {
		try (Connection conn = ConnectionUtility.getConnection()) {

			String query = "SELECT * FROM ers.reimbursement "
							+ "WHERE author = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, user_id);

			ResultSet results = ps.executeQuery();
			ArrayList<Reimbursement> tickets = new ArrayList<>();
			
			while (results.next()) {
				Reimbursement ticket = new Reimbursement();
				ticket.setId(results.getInt("reimb_id"));
				ticket.setAuthor(results.getInt("author"));
				ticket.setAmount(results.getDouble("amount"));
				ticket.setDescription(results.getString("description"));
				ticket.setReceipt(results.getString("receipt_name"));
				ticket.setSubmitted(results.getTimestamp("submitted").toInstant());
				if(results.getTimestamp("resolved") != null) {
					ticket.setResolved(results.getTimestamp("resolved").toInstant());
				}
				ticket.setStatus(results.getInt("status_id"));
				ticket.setType(results.getInt("type_id"));
				tickets.add(ticket);
				
				byte[] imgBytes = results.getBytes("receipt_img");
				OutputStream out = new BufferedOutputStream(new FileOutputStream("src/downloaded/"+ticket.getReceipt()));
				out.write(imgBytes);
				out.close();
			}

			return tickets;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insertTicket(Reimbursement ticket) {
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "INSERT INTO ers.reimbursement (amount, submitted, description, receipt_name, receipt_img, author, status_id, type_id) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, ticket.getAmount()); 				// amount
			ps.setTimestamp(2, Timestamp.from(Instant.now())); 	// submitted
			ps.setString(3, ticket.getDescription()); 			// description

			File receipt = new File(ticket.getReceipt());
		    FileInputStream fis = new FileInputStream(receipt);
		    ps.setString(4, receipt.getName());
		    ps.setBinaryStream(5, fis, (int) receipt.length()); // receipt
		    
		    ps.setInt(6, ticket.getAuthor()); 					// author
		    ps.setInt(7, ticket.getStatusId());					// status
		    ps.setInt(8, ticket.getTypeId());					// type
			
			ps.execute();
			fis.close();
			System.out.println("Ticket submitted.");
		} catch (SQLException | IOException e) {
			System.out.println("Failed to submit ticket.");
			e.printStackTrace();
		}
	}
	
	public void updateTicket(int user_id, int ticket, int status) {
		Connection conn = ConnectionUtility.getConnection();
		String query = "UPDATE ers.reimbursement SET resolved = ?, resolver = ?, status_id = ?"
						+ "WHERE reimb_id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setTimestamp(1, Timestamp.from(Instant.now()));
			ps.setInt(2, user_id);
			ps.setInt(3, status);
			ps.setInt(4, ticket);
			
			int altered = ps.executeUpdate();
			if(altered == 1) {
				System.out.println("Updated ticket");
			} else {
				System.out.println("Could not find ticket.");
			}
		} catch(SQLException e) {
			System.out.println("Failed to update ticket.");
			e.printStackTrace();
		}
	}

	public ArrayList<Reimbursement> findTickets() {
		try (Connection conn = ConnectionUtility.getConnection()) {

			String query = "SELECT * FROM ers.reimbursement";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet results = ps.executeQuery();
			ArrayList<Reimbursement> tickets = new ArrayList<>();
			
			while (results.next()) {
				Reimbursement ticket = new Reimbursement();
				ticket.setId(results.getInt("reimb_id"));
				ticket.setAuthor(results.getInt("author"));
				ticket.setAmount(results.getDouble("amount"));
				ticket.setDescription(results.getString("description"));
				ticket.setReceipt(results.getString("receipt_name"));
				ticket.setSubmitted(results.getTimestamp("submitted").toInstant());
				if(results.getTimestamp("resolved") != null) {
					ticket.setResolved(results.getTimestamp("resolved").toInstant());
				}
				ticket.setStatus(results.getInt("status_id"));
				ticket.setType(results.getInt("type_id"));
				tickets.add(ticket);
				
				byte[] imgBytes = results.getBytes("receipt_img");
				OutputStream out = new BufferedOutputStream(new FileOutputStream("src/downloaded/"+ticket.getReceipt()));
				out.write(imgBytes);
				out.close();
			}

			return tickets;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
