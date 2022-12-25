package com.swapnil.service;

import com.swapnil.exception.EventException;
import com.swapnil.exception.UserException;
import com.swapnil.model.Event;

public interface EventService {

	public Event createEvent(Event event,String email)throws EventException,UserException;
	
	public Event updateEvent(Event event,Integer eventId,String email) throws EventException,UserException;
	
	public Event deleteEvent(Integer eventId,String email) throws EventException,UserException;
	
	
}
