package com.dmslob.contacts.service;

import com.dmslob.contacts.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ContactFilter {

    public ContactFilter() {
    }

    public List<Contact> filter(List<Contact> contacts, String nameFilter) {
        Pattern pattern = Pattern.compile(nameFilter);

        List<Contact> filteredContacts = contacts.stream()
                .parallel()
                .filter(contact -> !(pattern.matcher(contact.getName()).matches()))
                .collect(Collectors.toList());

        return filteredContacts;
    }
}
