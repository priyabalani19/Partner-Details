package com.example.demo.domain;

import java.util.List;

public class Response {

    List<Attendees> countries;

    public Response(){

    }

    public Response(List<Attendees> countries) {
        this.countries = countries;
    }

    public List<Attendees> getCountries() {
        return countries;
    }

    public void setCountries(List<Attendees> countries) {
        this.countries = countries;
    }

}

