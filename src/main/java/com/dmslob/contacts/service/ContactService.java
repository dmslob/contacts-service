package com.dmslob.contacts.service;

import com.dmslob.contacts.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getByPattern(String nameFilter);
}
