package com.dmslob.contacts;

import com.dmslob.contacts.config.TestConfig;
import com.dmslob.contacts.model.Contact;
import com.dmslob.contacts.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ContactFilterTest {

    @Autowired
    @Qualifier("serviceMock")
    private ContactService contactService;

    @Test
    public void testNotNullContactServiceBean() {
        assertNotNull(contactService);
    }

    @Test
    public void testFindByPatternNotStartWithA() {
        String nameFilter = "^A.*$";
        List<Contact> filteredContacts = contactService.getByPattern(nameFilter);

        assertThat(filteredContacts.isEmpty(), is(false));
    }

    @Test
    public void testFindByPatternNotContainsAEI() {
        String nameFilter = "^.*[aei].*$";
        List<Contact> filteredContacts = contactService.getByPattern(nameFilter);

        assertThat(filteredContacts, hasSize(1));
    }
}
