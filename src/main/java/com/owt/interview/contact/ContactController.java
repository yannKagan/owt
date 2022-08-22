package com.owt.interview.contact;

import com.owt.interview.contactSkill.ContactSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {

    private final ContactService contactService;
    private final ContactSkillService contactSkillService;

    @Autowired
    public ContactController(ContactService contactService, ContactSkillService contactSkillService) {
        this.contactService = contactService;
        this.contactSkillService = contactSkillService;
    }

    @GetMapping
    public List<Contact> getContacts(@RequestParam(required = false) Long id) {
        return contactService.getContacts(id);
    }

    @PostMapping
    public void createContact(@RequestBody Contact contact) {
        contactService.createContact(contact);
    }

    @DeleteMapping(path = "{contactId}")
    public void deleteContact(@PathVariable("contactId") Long id) {
        contactService.deleteContact(id);
    }

    @PutMapping(path = "{contactId}")
    public void updateContact(@PathVariable("contactId") Long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String fullName,
                              @RequestParam(required = false) String address,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String phone) {

        contactService.updateContact(id, firstName, lastName, fullName, address, email, phone);
    }

    @RequestMapping(path = "{contactId}/skill/{skillId}/{level}", method = { RequestMethod.POST, RequestMethod.PUT })
    public void addContactSkill(@PathVariable("contactId") Long    contactId,
                                @PathVariable("skillId")   Long    skillId,
                                @PathVariable("level")     Integer level) {
        contactSkillService.addContactSkill(contactId, skillId, level);
    }

    @DeleteMapping(path = "{contactId}/skill/{skillId}")
    public void deleteContactSkill(@PathVariable("contactId") Long contactId,
                                   @PathVariable("skillId")   Long skillId) {
        contactSkillService.deleteContactSkill(contactId, skillId);
    }
}
