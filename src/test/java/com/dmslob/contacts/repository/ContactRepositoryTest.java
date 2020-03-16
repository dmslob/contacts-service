package com.dmslob.contacts.repository;

import com.dmslob.contacts.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testNotNullRepositoryBean() {
        assertNotNull(contactRepository);
    }

    @Test
    public void testFindAll() {
        final int EXPECTED_ROWS_COUNT = 5000;
        List<Contact> result = (List<Contact>) contactRepository.findAll();

        assertEquals(EXPECTED_ROWS_COUNT, result.size());
    }

    @Test
    public void testFindOne() {
        Long contactsId = 1L;
        Contact contact = contactRepository.findOne(contactsId);

        assertNotNull(contact);
    }

    @Test
    public void testSave() {
        Contact newOne = new Contact();
        newOne.setName("Betty");

        Contact saved = contactRepository.save(newOne);
        Contact expected = contactRepository.findOne(saved.getId());

        assertNotNull(expected);
    }
}
