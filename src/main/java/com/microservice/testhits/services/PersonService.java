package com.microservice.testhits.services;

import com.microservice.testhits.DTO.PersonCreate;
import com.microservice.testhits.DTO.PersonDTO;
import com.microservice.testhits.DTO.PersonUpdate;
import com.microservice.testhits.exceptions.PersonNotFound;
import com.microservice.testhits.models.entities.Person;
import com.microservice.testhits.models.repositories.PersonRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class PersonService {

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected ModelMapper modelMapper;

    public List<PersonDTO> getAll(){
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(person -> modelMapper.map(person,PersonDTO.class)).toList();
    }

    public void create(PersonCreate person){
        personRepository.save(modelMapper.map(person, Person.class));
    }

    public void update(Integer id , PersonUpdate personUpdate) throws Exception {
         Person personEntity = this.getPersonEntityById(id);
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
         modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
         modelMapper.map(personUpdate,personEntity);
         personRepository.save(personEntity);
    }

    public void delete(Integer id) throws PersonNotFound {
        Person personEntity = this.getPersonEntityById(id);
        personRepository.delete(personEntity);
    }

    public PersonDTO getById(Integer id) throws PersonNotFound {
        return modelMapper.map(this.getPersonEntityById(id),PersonDTO.class);
    }

    protected Person getPersonEntityById(Integer id) throws PersonNotFound {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()){throw new PersonNotFound();}
        return person.get();
    }
}
