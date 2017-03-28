package model.airport;

import java.util.ArrayList;

import model.Flight;
import model.FlightInterface;
import model.Seat;
import model.SeatMap;

public class AirbusA280 extends Flight implements FlightInterface{

	
	private ArrayList<Seat> listOfAvailableSeats;
	
	public AirbusA280(String carrierType)
	{
		super();
		
		//carrierthis.getCarrierType(); 
	}

	@Override
	public void createSeatArray() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean haveASeatInEconomy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SeatMap listOfAvailableSeatsInFirstClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeatMap listOfAvailableSeatsInEconomy() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
