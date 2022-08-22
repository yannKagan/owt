package com.owt.interview.contactSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.owt.interview.contact.Contact;
import com.owt.interview.skill.Skill;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class ContactSkill {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id")
    @JsonBackReference(value = "contact")
    private Contact contact;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id")
    @JsonBackReference(value = "skill")
    private Skill skill;

    private Integer level = 0;

    public ContactSkill() {
    }

    public ContactSkill(Long id, Contact contact, Skill skill, Integer level) {
        this.id = id;
        this.contact = contact;
        this.skill = skill;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactSkill)) {
            return false;
        }
        ContactSkill that = (ContactSkill) o;
        return that.contact.getId().equals(this.contact.getId()) &&
               that.skill.getId().equals(this.skill.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact.getId(), skill.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
