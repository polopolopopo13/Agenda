package models;

import java.util.ArrayList;

//d'évènements datés

public class Agenda {
	public ArrayList<Event> my_events;
	public User owner;
	
	public Agenda(User owner_) {
		this.owner = owner_;
	}
	
	public void addEvent(Event event_) {
		this.my_events.add(event_);
	}
	
	public ArrayList<Event> getEvents(){
		return this.my_events;
	}
	
}
