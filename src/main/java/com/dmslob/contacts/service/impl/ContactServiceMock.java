package com.dmslob.contacts.service.impl;

import com.dmslob.contacts.model.Contact;
import com.dmslob.contacts.service.ContactFilter;
import com.dmslob.contacts.service.ContactService;
import com.dmslob.contacts.service.NameGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("serviceMock")
public class ContactServiceMock implements ContactService {

    private NameGeneratorService nameGenerator;

    private ContactFilter contactFilter;

    private static final long NUMBER_OF_CONTACTS = 22;

    @Autowired
    public ContactServiceMock(NameGeneratorService nameGenerator, ContactFilter contactFilter) {
        this.nameGenerator = nameGenerator;
        this.contactFilter = contactFilter;
    }

    @Override
    public List<Contact> getByPattern(String nameFilter) {
        List<Contact> contacts = getAll();
        List<Contact> filteredContacts = contactFilter.filter(contacts, nameFilter);

        return filteredContacts;
    }

    private List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        for (long i = 1; i < NUMBER_OF_CONTACTS; i++) {
            String name = nameGenerator.generate();
            contacts.add(new Contact(i, name));
        }
        return contacts;
    }
}
