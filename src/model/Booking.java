package model;

import model.menu.MenuOrder;
import view.BookingService;

public class Booking {
	BookingService cInt = new BookingService();
	private static int bookingNo = 1;
	private int bookingId;
	private Customer customer;
	private Flight selectedFlight;
	private Seat bookedSeat;
	private MenuOrder menuOrder;
	private int seat;

	public Booking() {
	}	
	
	public Booking(Flight selectedFlight, Seat seatObject, MenuOrder menuOrder, Customer customer) {
		this.bookingId = bookingNo++;
		this.selectedFlight = selectedFlight;
		this.customer = customer;
		this.menuOrder = menuOrder;
		this.bookedSeat = seatObject;
		this.seat = seatObject.getSeatNumber();	
		
	}

	public int getBookingId() {
		return bookingId;
	}
	
	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Seat getBookedSeat() {
		return bookedSeat;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MenuOrder getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(MenuOrder menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Override
	public String toString() {
		String orderOut;
		if(menuOrder.haveOrder()) {
			orderOut = "\nFood Order:\n" + menuOrder;
		} else { orderOut = ""; }
		
		return "Booking confirmation:\n Booking Id: " + getBookingId() + "\n Flight: " + selectedFlight.getFlightId() + " "+ selectedFlight.getFlightDetails() +
				". Seat no: " + seat + "\n " + customer + orderOut;
	}
}
