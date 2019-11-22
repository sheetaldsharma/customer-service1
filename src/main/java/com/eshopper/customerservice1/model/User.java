package com.eshopper.customerservice1.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "userpassword")
    @NonNull
    private String password;

    @Column(name = "active")
    @NonNull
    Boolean active;

    @Column(name = "roleid")
    @NonNull
    Integer roleId;

    @Column(name = "firstname")
    @NonNull
    private String firstName;

    @Column(name = "middlename")
    @NonNull
    private String middleName;

    @Column(name = "lastname")
    @NonNull
    private String lastName;

    @Column(name = "birthdate")
    @NonNull
    Date birthdate;

    @Column(name = "address1")
    @NonNull
    private String address1;

    @Column(name = "address2")
    @NonNull
    private String address2;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "state")
    @NonNull
    private String state;

    @Column(name = "country")
    @NonNull
    private String country;

    @Column(name = "postalcode")
    @NonNull
    Integer postalCode;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "phone1")
    @NonNull
    Integer phone1;

    @Column(name = "phone2")
    @NonNull
    Integer phone2;

    @Column(name = "registrationdate")
    @NonNull
    Date registrationDate;
}