package com.owt.interview.contactSkill;

import com.owt.interview.contact.Contact;
import com.owt.interview.contact.ContactRepository;
import com.owt.interview.skill.Skill;
import com.owt.interview.skill.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactSkillService {

    private final ContactRepository contactRepository;
    private final SkillRepository skillRepository;
    private final ContactSkillRepository contactSkillRepository;

    @Autowired
    public ContactSkillService(ContactRepository contactRepository, SkillRepository skillRepository, ContactSkillRepository contactSkillRepository) {
        this.contactRepository = contactRepository;
        this.skillRepository = skillRepository;
        this.contactSkillRepository = contactSkillRepository;
    }

    public void addContactSkill(Long contactId, Long skillId, Integer level) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new IllegalStateException(String.format("Contact with id `%d` does not exist", contactId))
        );
        Skill skill = skillRepository.findById(skillId).orElseThrow(
                () -> new IllegalStateException(String.format("Skill with id `%d` not found", skillId))
        );
        Optional<ContactSkill> contactSkillOptional = contactSkillRepository.findByIds(contactId, skillId);

        if (!contactSkillOptional.isPresent()) {
            ContactSkill contactSkill = new ContactSkill();

            contactSkill.setContact(contact);
            contactSkill.setSkill(skill);
            contactSkill.setLevel(level);

            contact.getContactSkills().add(contactSkill);
            contactRepository.save(contact);
        } else {
            ContactSkill contactSkill = contactSkillOptional.get();

            contactSkill.setLevel(level);
            contactSkillRepository.save(contactSkill);
        }
    }

    public void deleteContactSkill(Long contactId, Long skillId) {
        ContactSkill contactSkill = contactSkillRepository.findByIds(contactId, skillId).orElseThrow(
                () -> new IllegalStateException(String.format("Could not find skill `%d` for contact `%d`", skillId, contactId))
        );

        contactSkillRepository.delete(contactSkill);
    }
}
