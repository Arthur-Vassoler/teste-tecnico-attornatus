package br.com.attornatus.controllers;

import br.com.attornatus.dtos.address.AddressRequestDto;
import br.com.attornatus.dtos.person.PersonRequestDto;
import br.com.attornatus.dtos.person.PersonResponseDto;
import br.com.attornatus.dtos.person.PersonResponseEntityDto;
import br.com.attornatus.services.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {
  @Autowired
  private PersonService personService;

  @PostMapping
  public ResponseEntity<?> save(@RequestBody PersonRequestDto personRequestDto) {
    try {
      PersonResponseDto savedPerson = personService.save(personRequestDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PersonRequestDto personRequestDto) {
    try {
      PersonResponseDto updatedPerson = personService.update(id, personRequestDto);
      return ResponseEntity.ok(updatedPerson);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
    }
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    List<PersonResponseEntityDto> people = personService.findAll();
    return ResponseEntity.ok(people);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    try {
      PersonResponseEntityDto person = personService.findById(id);
      return ResponseEntity.ok(person);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
    }
  }

  @PostMapping("/{personId}/addresses")
  public ResponseEntity<?> addAddressToPerson(
    @PathVariable Long personId,
    @RequestBody AddressRequestDto addressRequestDto) {
    try {
      personService.addAddressToPerson(personId, addressRequestDto);
      return ResponseEntity.ok("Endereço adicionado com sucesso à pessoa.");
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
    }
  }

  @PutMapping("/{personId}/main-address/{addressId}")
  public ResponseEntity<?> updateMainAddress(
    @PathVariable Long personId,
    @PathVariable Long addressId) {
    try {
      personService.updateMainAddress(personId, addressId);
      return ResponseEntity.ok("Endereço principal atualizado com sucesso.");
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
    }
  }
}
