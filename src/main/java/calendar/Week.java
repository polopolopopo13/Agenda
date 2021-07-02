package calendar;

import java.util.ArrayList;

public class Week {
	
	public final static String[] DAYS = {
		"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
	};
	
	private Day firstDay;
	private int number;

	private ArrayList<Day> days = new ArrayList<Day>();

	public Week(int number, ArrayList<Day> days) {
		super();
		this.firstDay = firstDay;
		this.number = number;
		this.days = days;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Day> getDays() {
		return days;
	}

	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}

}
