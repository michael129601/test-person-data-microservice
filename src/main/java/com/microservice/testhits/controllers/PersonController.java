package com.microservice.testhits.controllers;

import com.microservice.testhits.DTO.PersonCreate;
import com.microservice.testhits.DTO.PersonDTO;
import com.microservice.testhits.DTO.PersonUpdate;
import com.microservice.testhits.exceptions.PersonNotFound;
import com.microservice.testhits.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("person")
@CrossOrigin(origins = "*")
public class PersonController {
    @Autowired
    private PersonService personService ;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> getAll(){
        return personService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable(required = true) Integer id){
        try {
            return new ResponseEntity<PersonDTO>(personService.getById(id),HttpStatus.OK);
        }catch (PersonNotFound ex){
            return new ResponseEntity<PersonDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PersonCreate person){
        personService.create(person);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable(required = true) Integer id, @Valid @RequestBody PersonUpdate person){
        try {
            personService.update(id,person);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (PersonNotFound ex){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void>  delete(@PathVariable(required = true) Integer id){
        try {
            personService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (PersonNotFound ex){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
