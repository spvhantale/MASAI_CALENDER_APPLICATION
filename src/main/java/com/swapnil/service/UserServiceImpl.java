package com.swapnil.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.dto.LoginDTO;
import com.swapnil.dto.UserDTO;
import com.swapnil.exception.EventException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUsersSession;
import com.swapnil.model.Event;
import com.swapnil.model.MyUser;
import com.swapnil.model.User;
import com.swapnil.repository.EventDAO;
import com.swapnil.repository.MyUserDAO;
import com.swapnil.repository.SessionDAO;
import com.swapnil.repository.UserDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO uDao;
	@Autowired
	private EventDAO eDao;
	@Autowired
	private MyUserDAO mDao;
	@Autowired
	private SessionDAO sDao;
	
	@Override
	public User registerUser(User user) throws UserException {
		
		Optional<User> u=uDao.findById(user.getMail());
		
		if(u.isPresent()) {
			throw new UserException("User already present");
		}else {
//			user.setPassword(pEnco.encode(user.getPassword()));
			MyUser myUser=new MyUser(user.getUserName(), user.getFirstName(), user.getPassword());
			mDao.save(myUser);
			User s=uDao.save(user);
			return s;
		}
	}

	@Override
	public User updateUser(UserDTO user) throws UserException {

		Optional<User> u=uDao.findById(user.getMail());
		
		if(u.isPresent()) {
			User us=u.get();
//			user.setPassword(pEnco.encode(user.getPassword()));
			MyUser my=mDao.findByUserName(us.getUserName());
			us.setBirthDate(user.getBirthDate());
			us.setFirstName(user.getFirstName());
			us.setLastName(user.getLastName());
			us.setPassword(user.getPassword());
			us.setMobileNumber(user.getMobileNumber());
			my.setName(user.getFirstName());
			my.setPassword(user.getPassword());	
			mDao.save(my);
			User use=uDao.save(us);
			return use;
		}
		throw new UserException("User not found");
	}

	@Override
	public String loginUser(LoginDTO login) {
		
		
		String key=RandomString.make(6);
		
		CurrentUsersSession cu=new CurrentUsersSession(login.getMail(), key, LocalDateTime.now());
		
		return cu+"";
		
	}

	@Override
	public List<Event> getEvent(String type, String  mail) throws EventException,UserException {
		List<Event> s=new ArrayList<>();
		Optional<User> u=uDao.findById(mail);
		if(u.isPresent()) {
			List<Event> elist=u.get().getEvents();
			if(type.toLowerCase().equals("month")) {
				
				 s=elist.stream().filter(e->e.getStartingDate().getMonth().equals(LocalDate.now().getMonth())).collect(Collectors.toList());
				return s;
			
			}else if(type.toLowerCase().equals("week")) {
				
				for(Event m:elist) {
					int year1=m.getStartingDate().getYear();
					int week1=m.getStartingDate().get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);
					
					LocalDate date=LocalDate.now();
					int year2=date.getYear();
					int week2=date.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);
					if(year1==year2 && week1==week2) {
						s.add(m);
					}
					
				}
				
				return s;
			
			}else if(type.toLowerCase().equals("day")){
				s=elist.stream().filter(e->e.getStartingDate().equals(LocalDate.now())==true).collect(Collectors.toList());
				return s;
			}else {
				throw new EventException("Event not found");
			}
		}else {
			throw new UserException("User not found");
		}
		
	}

}
