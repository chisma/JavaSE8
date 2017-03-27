package model.menu;
import java.util.ArrayList;
import java.util.List;

public class MenuOrder implements MenuInterface{
	private ArrayList<MenuItem> menu;

	public MenuOrder() {
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

	public static boolean haveOrder(Integer[] items) {
		boolean haveOrder = false;
		for(Object item : items) {
			if (item != null) {
				haveOrder =  true;
				break;
			}
		}
		return haveOrder;
	}

	public boolean haveOrder() {
		boolean haveOrder = false;
		for (Object item : menu) {
			if(item != null) {  
				haveOrder = true;
				break;
			}
		}
		return haveOrder;
	}
	
	@Override
	public String toString() {
		List<MenuItem> results = menu;
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		
		for (MenuItem item : results) {
			result.append(String.format("%1$-7s", " Id: " + item.getId()));
			result.append(String.format("%1$-25s", " Item: " + item.getItem()));
			result.append(" Price: " + item.getPrice() + NEW_LINE );
		}
		return result.toString();
	}

}