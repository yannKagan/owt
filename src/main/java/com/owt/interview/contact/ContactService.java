package com.owt.interview.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts(Long id) {
        if (id != null) {
            Contact contact = contactRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException(String.format("Contact with id `%d` does not exist", id))
            );
            return List.of(contact);
        }
        return contactRepository.findAll();
    }

    public void createContact(Contact contact) {
        Optional<Contact> optionalContact = contactRepository.findContactByEmail(contact.getEmail());

        if (optionalContact.isPresent()) {
            throw new IllegalStateException("Email already used");
        }
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Contact with id `%d` does not exist", id))
        );

        contactRepository.delete(contact);
    }

    @Transactional
    public void updateContact(Long id, String firstName, String lastName, String fullName, String address, String email, String phone) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Contact with id `%d` does not exist", id))
        );

        if (firstName != null) {
            contact.setFirstName(firstName);
        }
        if (lastName != null) {
            contact.setLastName(lastName);
        }
        if (address != null) {
            contact.setAddress(address);
        }
        if (email != null) {
            Optional<Contact> optionalContact = contactRepository.findContactByEmail(contact.getEmail());

            if (optionalContact.isPresent()) {
                throw new IllegalStateException("Email already used");
            }
            contact.setEmail(email);
        }
        if (phone != null) {
            contact.setPhone(phone);
        }
    }
}
