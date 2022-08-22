package com.owt.interview.skill;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.owt.interview.contactSkill.ContactSkill;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "skill")
    Set<ContactSkill> contactSkills = new HashSet<>();

    public Skill() {
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactSkill> getContactSkills() {
        return contactSkills;
    }

    public void setContactSkills(Set<ContactSkill> contactSkills) {
        this.contactSkills = contactSkills;
    }
}
