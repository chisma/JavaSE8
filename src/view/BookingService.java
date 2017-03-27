package view;

import java.util.Scanner;

import model.Flight;
import model.FlightInterface;
import model.Seat;
import model.airport.FlightList;
import model.airport.Section;
import model.menu.MenuList;
import model.menu.MenuItem;

public class BookingService { 
	private static Scanner keyboard = new Scanner(System.in);
	public int returnedClassInt;
	public int seatNumberInt;

	public int selectFlight(FlightList flightList) {
		int flightSelectedInt;
		for(;;) {
			System.out.println("_____WELCOME to AVIATION LTD_____");
			System.out.println("Select a flight by entering the id of the flight");
			System.out.println(flightList.toString());

			try {
				flightSelectedInt = Integer.parseInt(keyboard.nextLine());
			} catch (NumberFormatException e) {
				continue;
			}
			
			FlightInterface flight = flightList.getFlight(flightSelectedInt);
			
			if(flight != null) {
				return flightSelectedInt;
			} else { continue; }
			
		}	
	}
	
	public Section getSeatClass() {
		for(;;){
			System.out.println("Do you want to travel in First Class(1) or Second Class(2) ");	
			try {
				returnedClassInt = Integer.parseInt(keyboard.nextLine());
				if(returnedClassInt == 1){
					return Section.First;
				}
				else if(returnedClassInt == 2)
					return Section.Second;
			}
			catch (NumberFormatException e) {
				continue;
			}
		}
	}
	
	public boolean changeClassConfirmation(Section section) {
		System.out.print("There are no seats available in " + section + " class, would you like to change to " + 
				new Flight().swapSection(section) + " class(y)?");
		if(keyboard.nextLine().equalsIgnoreCase("y")){
			return true;
		}
		else
			return false;
	}

	public int bookSeat(Flight flight) {
		for(;;) {
			System.out.println("\nWhich seat number would you like to book?");
			try {
				seatNumberInt = Integer.parseInt(keyboard.nextLine());
				if((this.returnedClassInt == 1) && (seatNumberInt >= 1 && seatNumberInt<= 5) && flight.checkIfSeatIsAvailable(seatNumberInt)) {
					return seatNumberInt;
				} else if((this.returnedClassInt == 2) && (seatNumberInt >= 5 && seatNumberInt<= 10) && flight.checkIfSeatIsAvailable(seatNumberInt)){
					return seatNumberInt;
				} else { continue; }
			} catch (NumberFormatException e) {
				continue; }
		}
	}

	public Integer[] selectMenuListItem(MenuList menuList, Seat bookedSeat) {
		System.out.println("Do you wan't to preorder food? (y)");
		Integer items[] = new Integer[20];
		if(keyboard.nextLine().equalsIgnoreCase("y")) {
			int noOfItems=0, itemId;
			String input;
			do {
				System.out.println("Please select items from the menu:");
				System.out.println(menuList.toString(bookedSeat.getSeatClass()));
				System.out.println("When you are done ordering press \"d\" for done");
				input = keyboard.nextLine();
				try {
					itemId = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					continue;
				}
				MenuItem menuItem = menuList.getMenuItemObject(itemId);
				
				if( (menuItem != null) && (menuItem.getSection().equals(bookedSeat.getSeatClass()))) {
					System.out.println("Choosen item id: " + menuItem + "\n");
					items[noOfItems] = itemId;
					noOfItems++;
				} else { continue; }
			} while (!(input.equalsIgnoreCase("d")));
		}
		return items;
	}

	public String getCustomerName() {
		System.out.println("\nName:");
		String name = keyboard.nextLine();
		return name;
	}

	public String getCustomerEmail() {
		System.out.println("Email adress:");
		String email = keyboard.nextLine();
		return email;
	}

}
