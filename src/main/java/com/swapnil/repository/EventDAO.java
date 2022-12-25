package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Event;

@Repository
public interface EventDAO extends JpaRepository<Event, Integer>{

}
