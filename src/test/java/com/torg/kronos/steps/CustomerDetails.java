package com.torg.kronos.steps;

/**
 * Custom object to take customer details from a feature file and convert into an object for use in assertions
 * Follows pattern taken from http://www.thinkcode.se/blog/2014/06/30/cucumber-data-tables
 */
public class CustomerDetails {
    private String name;
    private String telephone;
    private String email;

    public CustomerDetails(String name, String telephone, String email) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    String getName() {
        return name;
    }
    String getTelephoneNumber() {
        return telephone;
    }
    String getEmail() {
        return email;
    }
}
