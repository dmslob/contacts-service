package com.dmslob.contacts.config;

import com.dmslob.contacts.controller.converter.ContactFromDtoConverter;
import com.dmslob.contacts.controller.converter.ContactToDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ContactFromDtoConverter());
        registry.addConverter(new ContactToDtoConverter());
    }
}
