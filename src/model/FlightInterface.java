package model;

import model.airport.Section;

public interface FlightInterface {

	public abstract Section swapSection(Section section);
	
	public abstract void createSeatArray();

	public abstract Seat bookASeat(int seatNumberToBeBooked);

	public abstract boolean haveASeatInFirstClass();

	public abstract boolean haveASeatInEconomy();
	
	public abstract SeatMap listOfAvailableSeatsInFirstClass();
	public abstract SeatMap listOfAvailableSeatsInEconomy();
	
	
}