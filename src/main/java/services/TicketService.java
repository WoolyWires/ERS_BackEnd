package services;

import java.util.ArrayList;
import daos.TicketDao;
import models.Reimbursement;

public class TicketService {
	TicketDao ticketDao = new TicketDao();

	public ArrayList<Reimbursement> viewTickets(int user_id) {
		return ticketDao.findTickets(user_id);
	}
	
	public ArrayList<Reimbursement> viewTickets() {
		return ticketDao.findTickets();
	}

	public void createTicket(Reimbursement ticket) {
		ticketDao.insertTicket(ticket);
	}
	
	public void updateTicket(int user_id, int ticket, int status) {
		ticketDao.updateTicket(user_id, ticket, status);
	}

}
