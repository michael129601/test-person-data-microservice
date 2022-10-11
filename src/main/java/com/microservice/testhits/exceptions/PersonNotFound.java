package com.microservice.testhits.exceptions;

public class PersonNotFound extends Exception{
    @Override
    public String getMessage() {
        return "Person not Found";
    }
}
