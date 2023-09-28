package br.com.attornatus.controllers;

import br.com.attornatus.dtos.address.AddressRequestDto;
import br.com.attornatus.dtos.person.PersonRequestDto;
import br.com.attornatus.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController {
  @Autowired
  private PersonService personService;

  @PostMapping
  public ResponseEntity<?> save(@RequestBody PersonRequestDto personRequestDto) {
    try {
      return new ResponseEntity<>(personService.save(personRequestDto), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erro ao criar um pessoa! \n\nErro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PersonRequestDto personRequestDto) {
    try {
      return new ResponseEntity<>(personService.update(id, personRequestDto), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erro ao criar um pessoa! \n\nErro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
  }

  @PostMapping("/{personId}/addresses")
  public ResponseEntity<?> addAddressToPerson(
    @PathVariable Long personId,
    @RequestBody AddressRequestDto addressRequestDto) {
      personService.addAddressToPerson(personId, addressRequestDto);
      return ResponseEntity.ok("Endereço adicionado com sucesso à pessoa.");
  }

  @PutMapping("/{personId}/main-address/{addressId}")
  public ResponseEntity<String> updateMainAddress(
    @PathVariable Long personId,
    @PathVariable Long addressId) {
      personService.updateMainAddress(personId, addressId);
      return ResponseEntity.ok("Endereço principal atualizado com sucesso.");
  }
}
