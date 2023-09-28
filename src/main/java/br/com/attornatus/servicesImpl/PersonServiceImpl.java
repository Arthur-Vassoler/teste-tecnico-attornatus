package br.com.attornatus.servicesImpl;

import br.com.attornatus.dtos.address.AddressRequestDto;
import br.com.attornatus.dtos.person.PersonRequestDto;
import br.com.attornatus.dtos.person.PersonResponseEntityDto;
import br.com.attornatus.dtos.person.PersonResponseDto;
import br.com.attornatus.entities.AddressEntity;
import br.com.attornatus.entities.PersonEntity;
import br.com.attornatus.repositories.AddressRepository;
import br.com.attornatus.repositories.PersonRepository;
import br.com.attornatus.services.PersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Override
  @Transactional
  public PersonResponseDto save(PersonRequestDto personRequestDto) {
    PersonEntity person = new PersonEntity();
    person.setName(personRequestDto.getNome());
    person.setDateBirth(LocalDate.parse(personRequestDto.getDataNascimento()));

    return new PersonResponseDto(personRepository.save(person).getId());
  }

  @Override
  @Transactional
  public PersonResponseDto update(Long id, PersonRequestDto personRequestDto) {
    PersonEntity existingPerson = personRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + id));

    existingPerson.setName(personRequestDto.getNome());
    existingPerson.setDateBirth(LocalDate.parse(personRequestDto.getDataNascimento()));

    PersonEntity updatedPerson = personRepository.save(existingPerson);

    return new PersonResponseDto(updatedPerson.getId());
  }

  @Override
  public List<PersonResponseEntityDto> findAll() {
    List<PersonEntity> people = personRepository.findAll();

    return people.stream()
      .map(this::convertToPersonResponseEntityDto)
      .collect(Collectors.toList());
  }

  @Override
  public PersonResponseEntityDto findById(Long id) {
    Optional<PersonEntity> person = personRepository.findById(id);

    return person.map(this::convertToPersonResponseEntityDto).orElse(null);
  }

  @Override
  @Transactional
  public void addAddressToPerson(Long personId, AddressRequestDto addressRequestDto) {
    PersonEntity person = personRepository.findById(personId)
      .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + personId));

    AddressEntity address = new AddressEntity();
    address.setPublicPlace(addressRequestDto.getPublicPlace());
    address.setZipCode(addressRequestDto.getZipCode());
    address.setNumber(addressRequestDto.getNumber());
    address.setCity(addressRequestDto.getCity());

    address.setPerson(person);

    addressRepository.save(address);

    person.getAddresses().add(address);

    personRepository.save(person);
  }

  @Override
  @Transactional
  public void updateMainAddress(Long personId, Long addressId) {
    PersonEntity person = personRepository.findById(personId)
      .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + personId));

    AddressEntity address = addressRepository.findById(addressId)
      .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + addressId));

    if (!person.getAddresses().contains(address)) {
      throw new IllegalArgumentException("O endereço não pertence à pessoa.");
    }

    person.setMainAddress(address);

    personRepository.save(person);
  }

  private PersonResponseEntityDto convertToPersonResponseEntityDto(PersonEntity personEntity) {
    Long mainAddressId = personEntity.getMainAddress() != null ? personEntity.getMainAddress().getId() : null;

    return new PersonResponseEntityDto(
      personEntity.getName(),
      personEntity.getDateBirth().toString(),
      mainAddressId,
      personEntity.getAddresses()
    );
  }
}
