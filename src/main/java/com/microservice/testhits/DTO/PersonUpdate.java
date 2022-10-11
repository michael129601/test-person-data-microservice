package com.microservice.testhits.DTO;

import com.microservice.testhits.models.entities.Person;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.function.Function;

@Getter
@Setter
public class PersonUpdate  {

    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    private Integer age;

    @Email
    @Size(max = 300)
    private String email;


    @Size(max = 200)
    private String address;


    @Size(min = 10 , max = 10)
    private String cellPhoneNumber;

    @Size(max = 50)
    private String nationality;

}
