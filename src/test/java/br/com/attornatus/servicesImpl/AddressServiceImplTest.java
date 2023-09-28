package br.com.attornatus.servicesImpl;

import br.com.attornatus.entities.AddressEntity;
import br.com.attornatus.repositories.AddressRepository;
import br.com.attornatus.services.AddressService;
import br.com.attornatus.servicesImpl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
  @InjectMocks
  private AddressService addressService = new AddressServiceImpl();

  @Mock
  private AddressRepository addressRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testListPersonAddress() {
    Long personId = 1L;
    AddressEntity address1 = new AddressEntity();
    address1.setPublicPlace("Rua A");
    address1.setZipCode("12345-678");
    address1.setNumber("123");
    address1.setCity("Cidade A");

    AddressEntity address2 = new AddressEntity();
    address2.setPublicPlace("Rua B");
    address2.setZipCode("98765-432");
    address2.setNumber("456");
    address2.setCity("Cidade B");

    List<AddressEntity> expectedAddresses = Arrays.asList(address1, address2);

    when(addressRepository.findByPersonId(personId)).thenReturn(expectedAddresses);

    List<AddressEntity> actualAddresses = addressService.listPersonAddress(personId);

    assertEquals(expectedAddresses, actualAddresses);
  }
}
