package calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Month {
	public static final String[] MONTHS = {
		"January","February","March","April","May","June","July","August","September","October","November","December"	
	};
	
	private int number;
	private String name;
	private Day firstday;
	private Day lastday;
	private int year;
	
	private String next;
	private String previous;
	private ArrayList<Week> weeks;
	private int user_id;
	
	public Month(int number, Day firstday, Day lastday) {
		super();
		setName(number);
		this.number = number+1;
		this.year = year;
		this.firstday = firstday;
		this.lastday = lastday;
		this.next = next;
		this.previous = previous;
	}
	public Month(int number, int year, int user_id) {
		super();
		this.user_id = user_id;
		setName(number);
		this.number = number+1;
		this.year = year;
		
		setWeeks();
		setNext();
		setPrevious();
		
	}
	
	
	public int getNumber() {
		return number;
	}
	public ArrayList<Week> getWeeks() {
		return weeks;
	}
	public void setWeeks() {
		Calendar c = Calendar.getInstance();
		c.setTime(java.sql.Date.valueOf(getYear()+"-"+getNumber()+"-01"));
		c.set(Calendar.DAY_OF_WEEK, (c.getActualMinimum(Calendar.DAY_OF_WEEK)+1));
		
		int max_ = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList<Week> w = new ArrayList<Week>();

		int count = 0;
		int num_week = c.get(Calendar.WEEK_OF_YEAR);
		while(count <= max_) {
			ArrayList<Day> days = new ArrayList<Day>();
			for(int i=0; i<7; i++) {
				Day d = new Day(new java.sql.Date(c.getTimeInMillis()), user_id, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) ,i);
				days.add(d);
				c.add(Calendar.DAY_OF_MONTH,1);
				count++;
			}
			Week n_w = new Week(num_week, days);
			w.add(n_w);
			num_week++;
		}			
		this.weeks = w;
	}
	
	
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(int n_) {
		this.name = MONTHS[n_];
;
	}
	public Day getFirstday() {
		return firstday;
	}
	public void setFirstday(Day firstday) {
		this.firstday = firstday;
	}
	public Day getLastday() {
		return lastday;
	}
	public void setLastday(Day lastday) {
		this.lastday = lastday;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public String getNext() {
		return next;
	}
	public void setNext() {
		int m = getNumber()+1;
		
		if (m==13){
			this.next =  (getYear()+1)+"-"+"01";
		}
		else {
			this.next = getYear()+"-"+m;
		}
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious() {
		int m = getNumber()-1;
		if (m == 0){
			this.previous = (getYear()-1)+"-"+"12";
		}
		else {
			this.previous = getYear()+"-"+m;
		}
	}
	public static String[] getMonths() {
		return MONTHS;
	}

}
