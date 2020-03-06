package com.dmslob.contacts.service.impl;

import com.dmslob.contacts.model.Contact;
import com.dmslob.contacts.repository.ContactRepository;
import com.dmslob.contacts.service.ContactFilter;
import com.dmslob.contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Qualifier("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

    Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    final transient Object lock = new Object();

    private ContactRepository contactRepository;

    private ContactFilter contactFilter;

    private ConcurrentHashMap<String, List<Contact>> filterToContacts = new ConcurrentHashMap<>();

    List<Contact> contacts = new ArrayList<>();

    public ContactServiceImpl() {
    }

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactFilter contactFilter) {
        this.contactRepository = contactRepository;
        this.contactFilter = contactFilter;
    }

    public List<Contact> getByPattern(String nameFilter) {
        findContacts();
        filterToContacts.computeIfAbsent(nameFilter, filter -> contactFilter.filter(contacts, filter));
        return filterToContacts.get(nameFilter);
    }

    /**
     * With the first request We select contacts from the database,
     * and store them in memory.
     */
    private void findContacts() {
        if (contacts.isEmpty()) {
            synchronized (lock) {
                if (contacts.isEmpty()) {
                    LOGGER.info("Hit the database");
                    List<Contact> all = (List<Contact>) contactRepository.findAll();
                    contacts.addAll(all);
                }
            }
        }
    }
}
