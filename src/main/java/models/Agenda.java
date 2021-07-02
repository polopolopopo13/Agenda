package models;

import java.util.ArrayList;

//d'évènements datés

public class Agenda {
	public ArrayList<Event> my_events = new ArrayList<Event>();
	public User owner;
	
	public Agenda(User owner_) {
		this.owner = owner_;
	}
	
	public ArrayList<Event> getEvents(){
		//will have to be sorted
		return this.my_events;
	}
	
}
