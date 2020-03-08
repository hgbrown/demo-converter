package dev.hbrown.democonverter.web.controller;

import dev.hbrown.democonverter.domain.model.Person;
import dev.hbrown.democonverter.persistence.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    void sanityTest() {
        assertAll(
                () -> assertThat(mvc).isNotNull(),
                () -> assertThat(personRepository).isNotNull()
        );
    }

    @Test
    void shouldBeAbleToFindPersonById() throws Exception {
        final Long id = -10L;
        final Person person = new Person(id, "Adam");

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        mvc.perform(
                get("/person/{id}", id)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(person.getName()));
    }
}