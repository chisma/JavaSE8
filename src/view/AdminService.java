package view;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Booking;
import model.Flight;
import model.airport.FlightList;
import model.menu.MenuItem;
import model.menu.MenuList;

public class AdminService {
	private static int selected;
	private static Scanner keyboard = new Scanner(System.in);

	void showAdminSelection(ArrayList<Booking> bookingList, FlightList flightList, MenuList menuList) {
		for(;;) {
			System.out.println("1. List bookings");
			System.out.println("2. Show economic report");
//			System.out.println("3. Add a new item to the menu");
			System.out.println("3. Add a new flight");
			System.out.println("4. Exit admin interface");
			
			try {
				selected = Integer.parseInt(keyboard.nextLine());
			} catch (NumberFormatException e) {
				continue;
			}

			switch (selected) {
			case 1:
				System.out.println(listBookings(bookingList));
				
				if(checkIfExit()) {
					continue;
				}
				break;

			case 2:
				showEconomicReport(bookingList);

				if(checkIfExit()) {
					continue;
				}
				break;

//			case 3:
//				addNewMenuItem(menuList);
//				
//				if(checkIfExit()) {
//					continue;
//				}
//				break;
				
			case 3:
				addNewFlight(flightList);
				
				if(checkIfExit()) {
					continue;
				}
				break;
				
			case 5:
				System.out.println("Exited admin interface...");
				return;
				
			default:
				continue;
			}
		}
	}


	String listBookings(ArrayList<Booking> bookingList) {
		System.out.println("___List of bookings___");
		
		List<Booking> results = bookingList;
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		
		for (Booking booking : results) {
			result.append(NEW_LINE + booking.getCustomer() + NEW_LINE );
			result.append(" Flight: " + booking.getSelectedFlight().getFlightId() + " " + booking.getSelectedFlight().getFlightDetails() + ".");
			result.append(" Seat: " + booking. getSeat() + ". Price: " + booking.getBookedSeat().getSeatPrice() + "." + NEW_LINE );
			if(booking.getMenuOrder().getMenu().size() != 0) {
				result.append("FoodService:" + NEW_LINE + booking.getMenuOrder() + NEW_LINE );
			}
		}
		return result.toString();
	}

	private void showEconomicReport(ArrayList<Booking> bookingList) {
		System.out.println("___Economic report___");
		ArrayList<Booking> bookings = bookingList;
		double totalSeatRevenue = 0.0;
		double totalFoodRevenue = 0.0;
		double totalRevenue = 0.0;
		for(Booking order: bookings){
			totalSeatRevenue += order.getBookedSeat().getSeatPrice();
			for(MenuItem menuItem : order.getMenuOrder().getMenu()){
				totalFoodRevenue += menuItem.getPrice();
			}
		}
		totalRevenue = totalSeatRevenue + totalFoodRevenue;
		System.out.println("Total revenue from seats: " + totalSeatRevenue);
		System.out.println("Total revenue from menu orders " + totalFoodRevenue);
		System.out.println("Total revenue: " + totalRevenue + "\n");
		System.out.println("Total profit is " +  round(totalRevenue*0.3, 2));
	}

	private void addNewMenuItem(MenuList menuList) {
		System.out.println("___Add new item to the menu___");
		System.out.println("Select class:\n 1. First Class\n 2. Second Class");
		
		System.out.println("Add item name:");
		
		System.out.println("Add item price:");
		
		
	}
	
	private void addNewFlight(FlightList flightList) {
		System.out.println("___Add New Flight___\n");
		System.out.println("Add flight id: ");
		String flightId = keyboard.nextLine();
		
		System.out.println("Add flight description:\n");
		String flightDetails = keyboard.nextLine();
		
		flightList.addFlight(new Flight(flightId,flightDetails));
	}
	
	public boolean checkIfExit() {
		System.out.println("Press Enter to Exit");
		try {
			System.in.read();
		} catch (IOException e) {
			return false;
		}
			return true;
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
 }
