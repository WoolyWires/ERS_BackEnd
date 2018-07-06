package views;

import java.util.Scanner;
import models.User;
import services.HelperService;
import services.UserService;
import views.EmployeeView;
import views.ManagerView;

public class UserView {
	
	static Scanner scan = new Scanner(System.in);
	UserService userService = new UserService();
	EmployeeView employeeView = new EmployeeView();
	ManagerView managerView = new ManagerView();

	public void options() {
		System.out.println("-----------------------------------\n");
		System.out.println("Hello. Welcome to GitYoMoney");
		System.out.println("Please choose an option: ");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		
		int option = HelperService.getUserInput(2);
		
		switch(option) {
			case 1: 
				userLogin();
				break;
			case 2:
				System.out.println("-----------------------------------\n");
				System.out.println("Goodbye.");
				System.out.println("-----------------------------------\n");
				System.exit(0);
		}
		options();
	}
	
	private void userLogin() {
		System.out.println("-----------------------------------\n");
		// check for valid account using user input
		System.out.println("Username: " );
		String username = scan.nextLine();
		System.out.println("Password: " );
		String password = scan.nextLine();
		User user = userService.findUserByLogin(username, password);
		if(user == null) {
			System.out.println("Account not found. Exiting...\n");
			options();
		}
		
		// redirect to view according to user role
		if(user.getRole() == 0) managerView.options(user);
		else employeeView.options(user);
	}

}
