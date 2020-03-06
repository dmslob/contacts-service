package com.dmslob.contacts.controller.converter;

import com.dmslob.contacts.model.Contact;
import com.dmslob.contacts.model.ContactDto;
import org.springframework.core.convert.converter.Converter;

public class ContactFromDtoConverter implements Converter<ContactDto, Contact> {

    @Override
    public Contact convert(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setName(contactDto.getName());

        return contact;
    }
}
