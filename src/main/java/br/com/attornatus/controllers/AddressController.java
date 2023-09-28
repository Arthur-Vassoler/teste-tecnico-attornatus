package br.com.attornatus.controllers;

import br.com.attornatus.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
  @Autowired
  private AddressService addressService;

  @GetMapping("/{idPerson}")
  public ResponseEntity<?> findById(@PathVariable Long idPerson) {
    return new ResponseEntity<>(addressService.listPersonAddress(idPerson), HttpStatus.OK);
  }
}
