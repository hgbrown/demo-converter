package dev.hbrown.democonverter.web.controller;

import dev.hbrown.democonverter.domain.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person getOne(@PathVariable("id") Optional<Person> optionalPerson) {
        log.debug("Request to get person=[{}]", optionalPerson);

        return optionalPerson
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find person by id"));
    }

}
