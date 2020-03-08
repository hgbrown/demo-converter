package dev.hbrown.democonverter.persistence;

import dev.hbrown.democonverter.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
