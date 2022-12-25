package com.swapnil.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.EventException;
import com.swapnil.exception.UserException;
import com.swapnil.model.Event;
import com.swapnil.service.EventService;

@RestController
@RequestMapping("/masaicalender")
public class EventController {

	@Autowired
	private EventService eService;
	
	@PostMapping("/event/{email}")
	public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event,@PathVariable("email") String email) throws EventException, UserException{
		Event e=eService.createEvent(event, email);
		return new ResponseEntity<Event>(e, HttpStatus.CREATED);
	}
	@PutMapping("/event/{eventId}/{email}")
	public ResponseEntity<Event> updateEvent(@Valid @RequestBody Event event,@PathVariable("eventId") Integer eventId,@PathVariable("email") String email) throws EventException, UserException{
		Event e=eService.updateEvent(event, eventId, email);
		return new ResponseEntity<Event>(e, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/event/{eventId}/{email}")
	public ResponseEntity<Event> deleteEvent(@PathVariable("eventId") Integer eventId,@PathVariable("email") String email) throws EventException, UserException{
		Event e=eService.deleteEvent(eventId, email);
		return new ResponseEntity<Event>(e, HttpStatus.CREATED);
	}
}
