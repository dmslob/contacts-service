package com.dmslob.contacts.service;

import com.dmslob.contacts.config.TestConfig;
import com.dmslob.contacts.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ContactFilterTest {

    @Autowired
    private ContactFilter contactFilter;

    private List<Contact> contacts;

    @Before
    public void setUp() {
        contacts = Arrays.asList(
                new Contact(1l, "Liam"),
                new Contact(2l, "Emma"),
                new Contact(3l, "Noah"),
                new Contact(4l, "Olivia"),
                new Contact(4l, "Anna"),
                new Contact(5l, "Bob")
        );
    }

    @Test
    public void testFindByPatternNotStartWithA() {
        String nameFilter = "^A.*$";
        List<Contact> filteredContacts = contactFilter.filter(contacts, nameFilter);

        assertThat(filteredContacts, hasSize(5));
    }

    @Test
    public void testFindByPatternNotContainsAEI() {
        String nameFilter = "^.*[aei].*$";
        List<Contact> filteredContacts = contactFilter.filter(contacts, nameFilter);

        assertThat(filteredContacts, hasSize(1));
    }
}
