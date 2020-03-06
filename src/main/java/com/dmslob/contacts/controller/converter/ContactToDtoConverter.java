package com.dmslob.contacts.controller.converter;

import com.dmslob.contacts.model.Contact;
import com.dmslob.contacts.model.ContactDto;
import org.springframework.core.convert.converter.Converter;

public class ContactToDtoConverter implements Converter<Contact, ContactDto> {

    @Override
    public ContactDto convert(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setName(contact.getName());

        return contactDto;
    }
}
