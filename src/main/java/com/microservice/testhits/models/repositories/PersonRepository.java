package com.microservice.testhits.models.repositories;

import com.microservice.testhits.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository  extends JpaRepository<Person,Integer> {


}
