package br.com.attornatus.services;

import br.com.attornatus.entities.AddressEntity;

import java.util.List;

public interface AddressService {
  List<AddressEntity> listPersonAddress(Long idPerson);
}
