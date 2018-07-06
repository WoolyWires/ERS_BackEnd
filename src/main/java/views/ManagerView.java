package views;

import java.util.List;

import models.User;
import models.Reimbursement;
import services.HelperService;
import services.TicketService;

public class ManagerView {
	private User user;
	TicketService ticketService = new TicketService();

	public void options(User manager) {
		user = manager;
		int option = 0;
		
		while(option != 2) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("How may we assist you?");
			System.out.println("1. View Reimbursement Requests");
			System.out.println("2. Exit");
			
			option = HelperService.getUserInput(2);
			
			switch(option) {
			case 1: 
				viewTickets();
				break;
			case 2:
				return;
			}
		}
		
	}

	private void viewTickets() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		List<Reimbursement> tickets = ticketService.viewTickets();
		if(tickets.isEmpty()) {
			System.out.println("No tickets in database");
			return;
		}
		for(Reimbursement ticket: tickets)
				System.out.println(ticket);
		int option = 0;
		while(option != 3) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("1. Filter requests by status");
		System.out.println("2. Approve/deny requests");
		System.out.println("3. Exit");
			
			option = HelperService.getUserInput(3);
			
			switch(option) {
			case 1: 
				filterTickets();
				break;
			case 2:
				manageTickets();
				break;
			case 3:
				return;
			}
		}
		
	}

	private void filterTickets() {
		List<Reimbursement> tickets = ticketService.viewTickets();
		int option = 0;
		while(option != 4) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Filter by: ");
			System.out.println("1. Pending");
			System.out.println("2. Approved");
			System.out.println("3. Denied");
			System.out.println("4. Exit");
			
			option = HelperService.getUserInput(4);
			
			switch(option) {
			case 1: 
				for(Reimbursement ticket: tickets) {
					if(ticket.getStatusId() == 0) {
						System.out.println(ticket);
					}
				}
				break;
			case 2:
				for(Reimbursement ticket: tickets) {
					if(ticket.getStatusId() == 1) {
						System.out.println(ticket);
					}
				}
				break;
			case 3:
				for(Reimbursement ticket: tickets) {
					if(ticket.getStatusId() == 2) {
						System.out.println(ticket);
					}
				}
				break;
			case 4:
				return;
			}
		}
	}

	private void manageTickets() {
		int id, option = 0;
		while(option != 3) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("1. Approve");
			System.out.println("2. Deny");
			System.out.println("3. Exit");
			option = HelperService.getUserInput(3);
			
			switch(option) {
			case 1: 
				System.out.println("Ticket id to approve: ");
				id = HelperService.getUserInput();
				ticketService.updateTicket(user.getId(), id, 1);
				break;
			case 2:
				System.out.println("Ticket id to deny: ");
				id = HelperService.getUserInput();
				ticketService.updateTicket(user.getId(), id, 2);
				break;
			case 3:
				return;
			}
		}
	}
}
