package dev.hbrown.democonverter;

import dev.hbrown.democonverter.domain.model.Person;
import dev.hbrown.democonverter.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class SetupBean {

    private final PersonRepository personRepository;

    @PostConstruct
    public void setUp() {
        Stream.of(
                Person.withName("Amanda"),
                Person.withName("Betty")
        ).forEach(new Consumer<Person>() {
            @Override
            public void accept(final Person person) {
                final Person savedPerson = personRepository.save(person);
                log.debug("savedPerson=[{}]", savedPerson);
            }
        });
    }
}
