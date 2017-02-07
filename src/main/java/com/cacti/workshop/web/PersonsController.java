package com.cacti.workshop.web;

import com.cacti.workshop.business.NotFoundException;
import com.cacti.workshop.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cacti.workshop.model.Person.from;

/**
 * Created by domix on 2/3/17.
 */
@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonsController {
  private List<Person> persons = new ArrayList<>();

  @PostConstruct
  public void init() {
    persons = Stream.of(
      from("id:1111", "Domingo", "dpmingo@dd"),
      from("id:2222", "Juan", "jua2@fn"))
      .collect(Collectors.toList());
  }

  //@CrossOrigin(origins = "http://localhost:8080")
  @GetMapping
  public List<Person> people() {
    return persons;
  }

  @GetMapping("/{idPerson}")
  public Person findById(
    @PathVariable("idPerson") String idPerson) throws Throwable {

    return persons.stream()
      .filter(person -> person.getId().equals(idPerson))
      .findFirst()
      .orElseThrow(() ->
        new NotFoundException("No se encontro", idPerson));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Person post(@RequestBody Person person) {
    person.setId("id:"+ ThreadLocalRandom.current().nextInt(1000, 2000 + 1));
    persons.add(person);
    return person;
  }

  @DeleteMapping("/{idPerson}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
    @PathVariable("idPerson") String idPerson) {
    if (!persons.removeIf(person -> person.getId().equals(idPerson))) {
      throw new NotFoundException("No se en", idPerson);
    }
  }

  @PutMapping()
  @ResponseStatus(HttpStatus.OK)
  public Person put(@RequestBody Person person){
    persons.forEach(u -> {
      if(u.getId().equals(person.getId())){
        u.setEmail(person.getEmail());
        u.setName((person.getName()));
      }
    });
    return person;

  }

}
