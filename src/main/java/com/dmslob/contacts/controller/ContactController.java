package com.dmslob.contacts.controller;

import com.dmslob.contacts.model.ContactDto;
import com.dmslob.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hello/contacts")
public class ContactController {

    private ContactService contactService;

    private ConversionService conversionService;

    public ContactController() {

    }

    @Autowired
    public ContactController(@Qualifier("contactServiceImpl") ContactService contactService, ConversionService conversionService) {
        this.contactService = contactService;
        this.conversionService = conversionService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactDto>> get(@RequestParam String nameFilter) {
        List<ContactDto> contactDtos = contactService.getByPattern(nameFilter)
                .stream()
                .parallel()
                .map(contact -> conversionService.convert(contact, ContactDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(contactDtos);
    }
}
