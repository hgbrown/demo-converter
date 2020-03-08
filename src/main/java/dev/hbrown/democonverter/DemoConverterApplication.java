package dev.hbrown.democonverter;

import dev.hbrown.democonverter.domain.model.Person;
import dev.hbrown.democonverter.persistence.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class DemoConverterApplication {

    private final PersonRepository personRepository;

    public DemoConverterApplication(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoConverterApplication.class, args);
    }

    @Bean
    public Converter<String, Optional<Person>> personConverter() {
        return new Converter<String, Optional<Person>>() {
            @Override
            public Optional<Person> convert(final String id) {
                final Optional<Person> optional = personRepository.findById(Long.valueOf(id));
                log.debug("convert id=[{}] to person=[{}]", id, optional);
                return optional;
            }
        };
    }

}
