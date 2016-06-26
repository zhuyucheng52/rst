package com.echo.rst.customer;

import com.echo.rst.entity.CommonEntity;

import javax.persistence.*;

/**
 * Created by echo on 16-6-26.
 */
@Entity
@Table(name = "tbl_customer")
public class Customer extends CommonEntity {
    private String firstName;
    private String lastName;

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

