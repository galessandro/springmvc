package com.cacti.workshop.web;

import com.cacti.workshop.business.NotFoundException;
import com.cacti.workshop.model.Person;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertNotNull;

/**
 * Created by domix on 2/3/17.
 */
public class PersonsControllerTests {

  @Test
  public void shouldFindAPersonById() throws Throwable {
    PersonsController personsController = new PersonsController();
    personsController.init();
    assertNotNull(personsController.findById("domix"));
  }

  @Test(expected = NotFoundException.class)
  public void shouldFailDueNotFoundPerson() throws Throwable {
    PersonsController personsController = new PersonsController();
    personsController.init();
    personsController.findById("domssix");
  }

  @Test
  public void shouldGetAListOfPersons() throws Throwable{
    PersonsController personsController = new PersonsController();
    personsController.init();
    assertNotNull(personsController.people());
  }

  @Test
  public void shouldAddANewPerson() throws Throwable{
    PersonsController personsController = new PersonsController();
    personsController.init();
    assertNotNull(personsController.post(Person.from("sandro", "Sandro", "gg@gg.com")));
  }

  @Test(expected = NotFoundException.class)
  public void shouldFailDeletePersonDueToPersonNotExists() throws Throwable{
    PersonsController personsController = new PersonsController();
    personsController.init();
    personsController.delete("pedrito");
  }

  @Test
  public void shouldDeletePerson() throws Throwable{
    PersonsController personsController = new PersonsController();
    personsController.init();
    personsController.delete("domix");
  }

  @Test
  public void shouldUpdateAPerson() throws Throwable{
    PersonsController personsController = new PersonsController();
    personsController.init();
    assertNotNull(personsController.put(Person.from("domixh", "Sandro", "gg@gg.com")));
  }


}
