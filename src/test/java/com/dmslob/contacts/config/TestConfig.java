package com.dmslob.contacts.config;

import com.dmslob.contacts.service.ContactFilter;
import com.dmslob.contacts.service.ContactService;
import com.dmslob.contacts.service.NameGeneratorService;
import com.dmslob.contacts.service.impl.ContactServiceMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public NameGeneratorService generatorService() {
        return new NameGeneratorService();
    }

    @Bean
    public ContactFilter contactFilter() {
        return new ContactFilter();
    }

    @Bean
    public ContactService contactService() {
        return new ContactServiceMock(generatorService(), contactFilter());
    }
}
