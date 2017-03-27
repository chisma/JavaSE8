package view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Booking;
import model.Customer;
import model.Flight;
import model.Seat;
import model.airport.FlightList;
import model.airport.Section;
import model.menu.MenuList;
import model.menu.MenuItem;
import model.menu.MenuOrder;

public class Client {

	public static void main(String[] args) {

		MenuList menuList = new MenuList();
		menuList.addMenuItem(new MenuItem("Wine", 65, Section.First));
		menuList.addMenuItem(new MenuItem("Beer", 55, Section.First));
		menuList.addMenuItem(new MenuItem("Cake", 65, Section.First));
		menuList.addMenuItem(new MenuItem("Crisps", 45, Section.First));
		menuList.addMenuItem(new MenuItem("Snaps", 100, Section.First));
		menuList.addMenuItem(new MenuItem("Baced Potato", 100, Section.First));
		menuList.addMenuItem(new MenuItem("Burger", 100, Section.First));
		menuList.addMenuItem(new MenuItem("Water", 10, Section.Second));
		menuList.addMenuItem(new MenuItem("Diluted Water", 5, Section.Second));
		menuList.addMenuItem(new MenuItem("Brackish vatten", 2, Section.Second));
		menuList.addMenuItem(new MenuItem("Pasta", 48, Section.Second));
		menuList.addMenuItem(new MenuItem("PopCorn", 30, Section.Second));
		menuList.addMenuItem(new MenuItem("Raspberry juice", 15, Section.Second));
		menuList.addMenuItem(new MenuItem("Noodles", 25, Section.Second));

		FlightList flightList = new FlightList();
		flightList.addFlight(new Flight("BA213","British Airways Boeing323 to Paris"));
		flightList.addFlight(new Flight("AF124","Air France Boeing200 to Frankfurt"));
		flightList.addFlight(new Flight("QA718","Qatar Airways Boeing202 to Singapore"));

		String input, name, email;
		Integer items[] = new Integer[50];
		MenuItem menuItem;
		Seat bookedSeat = null;
		Flight selectedFlight = null;
		Section section;
		BookingService customerView = new BookingService();
		AdminService adminView = new AdminService();
		ArrayList<Booking> bookingList = new ArrayList<>();

		Scanner keyboard = new Scanner(System.in);

		boolean repeat = true;
		mainloop: do {
			System.out.println("\nPress Enter for next booking");
			input = keyboard.nextLine();
			if (input.equals("admin")) {
				adminView.showAdminSelection(bookingList, flightList, menuList);
			} else {
				int flightSelection = customerView.selectFlight(flightList);
				int seatNumberToBeBooked = 0;
				selectedFlight = flightList.getFlight(flightSelection);
				section = customerView.getSeatClass(); 
				boolean confirmed = false;
				do {
					if (!selectedFlight.displaySeatMap(section)){ // Lists the seat map and returns true if at least one seat is available. NOT
						if(customerView.changeClassConfirmation(section)) {
							section = selectedFlight.swapSection(section);
							if (!selectedFlight.displaySeatMap(section)){ //NOT
								System.out.println("Sorry. No seats in this section either. Press Enter to return to the start and choose another flight.");
								try {
									System.in.read();
								} catch (IOException e) {
									continue mainloop;
								} continue mainloop;
							}
						} else { continue mainloop; }
					}
					seatNumberToBeBooked = customerView.bookSeat(selectedFlight); // Ask about seat, check that selected seat is in choosen class, and is available. From CI
					bookedSeat = selectedFlight.bookASeat(seatNumberToBeBooked); // From flight
					confirmed = true;
				} while (!confirmed);

				bookedSeat.displaySeatInformation();

				items = customerView.selectMenuListItem(menuList, bookedSeat);

				name = customerView.getCustomerName();
				email = customerView.getCustomerEmail();
				System.out.println("\n");

				//  Confirm order
				System.out.println("Thanks " + name);
				System.out.println("You want to book seat no " + (bookedSeat.getSeatNumber() + " in " + bookedSeat.getSeatClass() + 
						" class on the flight:\n" + selectedFlight.getSeatMap() + " at $" + bookedSeat.getSeatPrice()));
				double total=0.0;

				if(MenuOrder.haveOrder(items)) {
					System.out.println("with the following menu:");
					for(int i = 0; i < items.length; i++) {
						if (items[i] != null) {
							menuItem = menuList.getMenuItemObject(items[i]);
							System.out.println(menuItem);
							total += (menuItem.getPrice());
						}
					}
				}
				total += (bookedSeat.getSeatPrice());
				System.out.println("\nTotal cost is: " + total);

				boolean notConfirmed = true; 
				do {
					System.out.println("Please Confirm booking(y) or abort(n)");

					input = keyboard.nextLine();
					if(input.equalsIgnoreCase("y")) {
						notConfirmed = false;
						Customer customer = new Customer(name, email);
						MenuOrder order = new MenuOrder();
						for(int i = 0; i < items.length; i++) {
							if (items[i] != null) {
								order.addMenuItem(menuList.getMenuItemObject(items[i]));
							}
						}
						Booking booking = new Booking(selectedFlight,bookedSeat,order,customer);
						bookingList.add(booking);

						System.out.println("\nBooking confirmed. Thanks.\n");
						System.out.println(booking.toString());

					} else if (input.equalsIgnoreCase("n")) {
						notConfirmed = false;
						System.out.println("Booking aborted...");
					}

				} while(notConfirmed);
			} // End customer else clause
		} while(repeat);
		keyboard.close();
	}
}
