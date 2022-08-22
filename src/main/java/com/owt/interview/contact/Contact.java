package com.owt.interview.contact;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.owt.interview.contactSkill.ContactSkill;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Contact {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Transient
    private String fullName;
    private String address;
    private String email;
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "contact")
    private Set<ContactSkill> contactSkills = new HashSet<>();

    public Contact() {
    }

    public Contact(Long id, String firstName, String lastName, String address, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<ContactSkill> getContactSkills() {
        return contactSkills;
    }

    public void setContactSkills(Set<ContactSkill> contactSkills) {
        this.contactSkills = contactSkills;
    }
}
