package br.com.attornatus.servicesImpl;

import br.com.attornatus.dtos.address.AddressRequestDto;
import br.com.attornatus.dtos.person.PersonRequestDto;
import br.com.attornatus.dtos.person.PersonResponseDto;
import br.com.attornatus.dtos.person.PersonResponseEntityDto;
import br.com.attornatus.entities.AddressEntity;
import br.com.attornatus.entities.PersonEntity;
import br.com.attornatus.repositories.AddressRepository;
import br.com.attornatus.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
  @InjectMocks
  private PersonServiceImpl personService;

  @Mock
  private PersonRepository personRepository;

  @Mock
  private AddressRepository addressRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSavePerson() {
    PersonRequestDto personRequestDto = new PersonRequestDto();
    personRequestDto.setNome("John Doe");
    personRequestDto.setDataNascimento("1990-01-01");

    PersonEntity savedPerson = new PersonEntity();
    savedPerson.setId(1L);
    savedPerson.setName(personRequestDto.getNome());
    savedPerson.setDateBirth(LocalDate.parse(personRequestDto.getDataNascimento()));

    when(personRepository.save(any(PersonEntity.class))).thenReturn(savedPerson);

    PersonResponseDto responseDto = personService.save(personRequestDto);

    assertNotNull(responseDto);
    assertEquals(1L, responseDto.getId());
  }

  @Test
  public void testUpdatePerson() {
    Long personId = 1L;

    PersonRequestDto personRequestDto = new PersonRequestDto();
    personRequestDto.setNome("Updated Name");
    personRequestDto.setDataNascimento("1995-05-05");

    PersonEntity existingPerson = new PersonEntity();
    existingPerson.setId(personId);
    existingPerson.setName("John Doe");
    existingPerson.setDateBirth(LocalDate.parse("1990-01-01"));

    when(personRepository.findById(personId)).thenReturn(Optional.of(existingPerson));
    when(personRepository.save(any(PersonEntity.class))).thenAnswer(invocation -> {
      PersonEntity personToSave = invocation.getArgument(0);
      existingPerson.setName(personToSave.getName());
      existingPerson.setDateBirth(personToSave.getDateBirth());
      return existingPerson;
    });

    PersonResponseDto responseDto = personService.update(personId, personRequestDto);

    assertNotNull(responseDto);

    assertEquals(personId, responseDto.getId());
    assertEquals(personRequestDto.getNome(), existingPerson.getName());
    assertEquals(LocalDate.parse(personRequestDto.getDataNascimento()), existingPerson.getDateBirth());
  }


  @Test
  public void testFindAllPersons() {
    List<PersonEntity> personList = new ArrayList<>();
    personList.add(createPerson(1L, "João", LocalDate.of(1990, 1, 15)));
    personList.add(createPerson(2L, "Maria", LocalDate.of(1985, 5, 20)));

    when(personRepository.findAll()).thenReturn(personList);

    List<PersonResponseEntityDto> result = personService.findAll();

    assertEquals(2, result.size());
    assertEquals("João", result.get(0).getNome());
    assertEquals("Maria", result.get(1).getNome());
  }

  private PersonEntity createPerson(Long id, String name, LocalDate dateOfBirth) {
    PersonEntity person = new PersonEntity();
    person.setId(id);
    person.setName(name);
    person.setDateBirth(dateOfBirth);
    return person;
  }

  @Test
  public void testFindPersonById() {
    Long personId = 1L;

    PersonEntity personEntity = new PersonEntity();
    personEntity.setId(personId);
    personEntity.setName("John Doe");
    personEntity.setDateBirth(LocalDate.parse("1990-01-01"));

    when(personRepository.findById(personId)).thenReturn(Optional.of(personEntity));

    PersonResponseEntityDto responseDto = personService.findById(personId);

    assertNotNull(responseDto);

    assertEquals(personEntity.getName(), responseDto.getNome());
    assertEquals(personEntity.getDateBirth().toString(), responseDto.getDataNascimento());
  }

  @Test
  public void testFindPersonByIdNotFound() {
    Long nonExistentPersonId = 999L;

    when(personRepository.findById(nonExistentPersonId)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> {
      personService.findById(nonExistentPersonId);
    });
  }

  @Test
  public void testAddAddressToPerson() {
    PersonEntity person = new PersonEntity();
    person.setId(1L);

    AddressRequestDto addressRequestDto = new AddressRequestDto();
    addressRequestDto.setPublicPlace("123 Main St");
    addressRequestDto.setZipCode("12345");
    addressRequestDto.setNumber("Apt 101");
    addressRequestDto.setCity("Cityville");

    when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(person));

    personService.addAddressToPerson(1L, addressRequestDto);

    verify(addressRepository, times(1)).save(any(AddressEntity.class));

    assertEquals(1, person.getAddresses().size());
    AddressEntity addedAddress = person.getAddresses().get(0);
    assertEquals("123 Main St", addedAddress.getPublicPlace());
    assertEquals("12345", addedAddress.getZipCode());
    assertEquals("Apt 101", addedAddress.getNumber());
    assertEquals("Cityville", addedAddress.getCity());
    assertEquals(person, addedAddress.getPerson());
  }

  @Test
  public void testAddAddressToPersonPersonNotFound() {
    when(personRepository.findById(1L)).thenReturn(java.util.Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> {
      AddressRequestDto addressRequestDto = new AddressRequestDto();
      addressRequestDto.setPublicPlace("123 Main St");
      addressRequestDto.setZipCode("12345");
      addressRequestDto.setNumber("Apt 101");
      addressRequestDto.setCity("Cityville");
      personService.addAddressToPerson(1L, addressRequestDto);
    });

    verify(addressRepository, never()).save(any(AddressEntity.class));
  }

  @Test
  public void testUpdateMainAddress() {
    PersonEntity person = new PersonEntity();
    person.setId(1L);

    AddressEntity address1 = new AddressEntity();
    address1.setId(101L);
    address1.setPerson(person);

    AddressEntity address2 = new AddressEntity();
    address2.setId(102L);
    address2.setPerson(person);

    person.getAddresses().add(address1);
    person.getAddresses().add(address2);

    when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(person));

    when(addressRepository.findById(102L)).thenReturn(java.util.Optional.of(address2));

    personService.updateMainAddress(1L, 102L);

    assertEquals(address2, person.getMainAddress());
  }

  @Test
  public void testUpdateMainAddressPersonNotFound() {
    when(personRepository.findById(1L)).thenReturn(java.util.Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> {
      personService.updateMainAddress(1L, 102L);
    });

    verify(addressRepository, never()).findById(any(Long.class));
  }

  @Test
  public void testUpdateMainAddressAddressNotFound() {
    PersonEntity person = new PersonEntity();
    person.setId(1L);

    when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(person));

    when(addressRepository.findById(102L)).thenReturn(java.util.Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> {
      personService.updateMainAddress(1L, 102L);
    });

    assertNull(person.getMainAddress());
  }
}
