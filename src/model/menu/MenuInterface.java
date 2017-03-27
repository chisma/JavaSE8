package model.menu;

import java.util.ArrayList;

public interface MenuInterface {

	void addMenuItem(MenuItem newMenuItem);

	ArrayList<MenuItem> getMenu();

	String toString();

}