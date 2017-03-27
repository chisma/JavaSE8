package model;

import model.airport.Section;

public abstract class Seat {
	private int seatNumber;
	private boolean isBooked;
	private Section seatClass;
	private double seatPrice;
	public static boolean bookedFlag;

	public Seat(int SeatNumber, boolean isBooked, Section seatClass){
		this.isBooked = false;
		this.seatClass = seatClass;
		if(this.seatClass == Section.First) seatPrice = 20000;
		if(this.seatClass == Section.Second) seatPrice = 5000;
	}
/*	public int getSeatIndex() {
		return seatNumber;
	}*/
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked() {
		this.isBooked = true;
		bookedFlag = true;
	}
	public Section getSeatClass() {
		return seatClass;
	}

//	public void setSeatClass(Section seatClass) {
//		this.seatClass = seatClass;
//	}

	public double getSeatPrice() {
		return seatPrice;
	}
	public boolean findASeatBySeatNumber(int seatNumber){
		if(this.seatNumber == seatNumber) return true;
		return false;
	}
	
	
	public void displaySeatInformation(){
		System.out.println("Seat selected is no " + (seatNumber) + " in " + seatClass.toString() + " Class. Price is $ " + seatPrice);
	}
}
