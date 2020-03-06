package com.dmslob.contacts;

import com.dmslob.contacts.config.TestConfig;
import com.dmslob.contacts.service.NameGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class NameGeneratorServiceTest {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @Test
    public void testNotNullNameGeneratorBean() {
        assertNotNull(nameGeneratorService);
    }

    @Test
    public void testNextName() {
        String contactName = nameGeneratorService.generate();
        assertNotNull(contactName);

        String nextContactName = nameGeneratorService.generate();
        assertNotEquals("Name is not supposed to be the same!", contactName, nextContactName);
    }
}
