package model.menu;
import java.util.ArrayList;
import java.util.List;

import model.airport.Section;

public class MenuList implements MenuInterface {
	private ArrayList<MenuItem> menu;

	public MenuList() {
		menu = new ArrayList<MenuItem>();
	}
	
	@Override
	public void addMenuItem(MenuItem newMenuItem) {
		menu.add(newMenuItem);
	}
	
	@Override
	public ArrayList<MenuItem> getMenu() {
		return menu;
	}
	
	public MenuItem getMenuItemObject(int id) {
		for (MenuItem item :  menu) {
			if((item.getId() == id)) {
				return item;
			}
		}
		return null;
	}

	public String toString(Section section) {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		for (MenuItem item : menu) {
			if(item.getSection().equals(section)) {
				result.append(String.format("%1$-7s", " Id: " + item.getId()));
				result.append(String.format("%1$-18s", " Item: " + item.getItem()));
				result.append(" Price: " + item.getPrice() + NEW_LINE );
			}
		}	
		return result.toString();
	}

	@Override
	public String toString() {
		List<MenuItem> results = menu;
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		for (MenuItem item : results) {
			result.append(String.format("%1$-7s", " Id: " + item.getId()));
			result.append(String.format("%1$-18s", " Item: " + item.getItem()));
			result.append(" Price: " + item.getPrice() + NEW_LINE );
		}
		return result.toString();
	}
}