package com.microservice.testhits.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class PersonCreate {

    @NotNull
    @Size(max = 100)
    private String firstName;

    @NotNull
    @Size(max = 100)
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    @Email
    @Size(max = 300)
    private String email;

    @NotNull
    @Size(max = 200)
    private String address;

    @NotNull
    @Size(max = 13)
    private String dni;

    @NotNull
    @Size(min = 10 , max = 10)
    private String cellPhoneNumber;

    @Size(max = 50)
    @NotNull
    private String nationality;

}
