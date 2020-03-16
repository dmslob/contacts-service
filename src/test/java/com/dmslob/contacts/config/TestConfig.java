package com.dmslob.contacts.config;

import com.dmslob.contacts.service.ContactFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ContactFilter contactFilter() {
        return new ContactFilter();
    }
}
