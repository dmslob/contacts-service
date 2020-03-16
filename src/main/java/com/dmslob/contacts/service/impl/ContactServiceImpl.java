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

    final Object lock = new Object();

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactFilter contactFilter;

    private ConcurrentHashMap<String, List<Contact>> filterToContacts = new ConcurrentHashMap<>();

    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getByPattern(String nameFilter) {
        findContacts();
        return filterToContacts.computeIfAbsent(nameFilter, filter -> contactFilter.filter(contacts, filter));
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
