package com.eshopper.customerservice1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "password")
    private String password ;

    @Column(name = "firstname")
    private String firstName ;

    @Column(name = "middlename")
    private String middleName ;

    @Column(name = "lastname")
    private String lastName ;

    @Column(name = "address1")
    private String address1 ;

    @Column(name = "address2")
    private String address2 ;

    @Column(name = "city")
    private String city ;

    @Column(name = "state")
    private String state ;

    @Column(name = "postalcode")
    private Integer postalCode ;

    @Column(name = "email")
    private String email ;

    @Column(name = "phone1")
    private Integer phone1 ;

    @Column(name = "phone2")
    private Integer phone2 ;

    @Column(name = "registareddate")
    private Date registaredDate;
}
