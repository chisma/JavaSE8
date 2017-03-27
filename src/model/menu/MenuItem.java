package model.menu;

import model.airport.Section;

public class MenuItem {
	private static int itemNo = 1;
	private int itemId;
	private String item;
	private double price;
	private Section section;

	public MenuItem(String item, double price, Section section) {
		itemId = itemNo++;
		setItem(item);
		setPrice(price);
		setSection(section);
	}

	public int getId() {
		return itemId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Section getSection() {
		return section;
	}
	
	public void setSection(Section section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return itemId + " " + String.format("%1$-18s", item) + " $" + price;
	}
}
