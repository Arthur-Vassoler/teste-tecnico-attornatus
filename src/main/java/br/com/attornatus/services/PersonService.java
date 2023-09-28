package br.com.attornatus.services;

import br.com.attornatus.dtos.address.AddressRequestDto;
import br.com.attornatus.dtos.person.PersonRequestDto;
import br.com.attornatus.dtos.person.PersonResponseEntityDto;
import br.com.attornatus.dtos.person.PersonResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PersonService {
  @Transactional
  PersonResponseDto save(PersonRequestDto personRequestDto);

  @Transactional
  PersonResponseDto update(Long id, PersonRequestDto personRequestDto);

  List<PersonResponseEntityDto> findAll();

  PersonResponseEntityDto findById(Long id);

  @Transactional
  void addAddressToPerson(Long personId, AddressRequestDto addressRequestDto);

  @Transactional
  void updateMainAddress(Long personId, Long addressId);
}
