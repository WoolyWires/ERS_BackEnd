package views;

import java.util.List;
import java.util.Scanner;

import models.Reimbursement;
import models.User;
import services.HelperService;
import services.TicketService;

public class EmployeeView {
	private User user;
	TicketService ticketService = new TicketService();
	static Scanner scan = new Scanner(System.in);

	public void options(User employee) {
		user = employee;
		System.out.println(user);
		int option = 0;
		
		while(option != 4) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("How may we assist you?");
			System.out.println("1. View Tickets");
			System.out.println("2. Reimbursement Request");
			System.out.println("3. Exit");
			
			option = HelperService.getUserInput(3);
			
			switch(option) {
			case 1: 
				viewTickets();
				break;
			case 2: 
				createTicket();
				break;
			case 3:
				return;
			}
		}
	}

	private void createTicket() {
		//double amount, String description, String receipt, String type, String status
		Reimbursement ticket = new Reimbursement();
		int type;
		String description, receipt;
		double amount;
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Amount: ");
		amount = HelperService.getUserInputDouble();
		System.out.println("Description: ");
		description = scan.nextLine();
		System.out.println("Receipt Image: ");
		receipt = "src/receipts/" + scan.nextLine();
		System.out.println("Type: ");
		System.out.println("1. Lodging");
		System.out.println("2. Travel");
		System.out.println("3. Food");
		System.out.println("4. Other");
		
		type = HelperService.getUserInput(4);
		
		
		ticket.setAuthor(user.getId());
		ticket.setAmount(amount);
		ticket.setDescription(description);
		ticket.setReceipt(receipt);
		ticket.setStatus(0);
		ticket.setType(type-1);
		ticketService.createTicket(ticket);
	}

	private void viewTickets() {
		List<Reimbursement> tickets = ticketService.viewTickets(user.getId());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		if(tickets.isEmpty()) {
			System.out.println("No tickets in database");
			return;
		}
		for(Reimbursement ticket: tickets)
			System.out.println(ticket);
	}
}
