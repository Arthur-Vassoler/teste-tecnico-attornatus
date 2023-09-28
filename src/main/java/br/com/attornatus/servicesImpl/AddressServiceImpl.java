package br.com.attornatus.servicesImpl;

import br.com.attornatus.entities.AddressEntity;
import br.com.attornatus.repositories.AddressRepository;
import br.com.attornatus.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
  @Autowired
  private AddressRepository addressRepository;

  @Override
  public List<AddressEntity> listPersonAddress(Long idPerson) {
    return addressRepository.findByPersonId(idPerson);
  }
}
