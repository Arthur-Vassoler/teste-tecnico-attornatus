package br.com.attornatus.controllers;

import br.com.attornatus.entities.AddressEntity;
import br.com.attornatus.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
  @Autowired
  private AddressService addressService;

  @GetMapping("/{idPerson}")
  public ResponseEntity<?> findById(@PathVariable Long idPerson) {
    try {
      List<AddressEntity> addresses = addressService.listPersonAddress(idPerson);
      return ResponseEntity.ok(addresses);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
    }
  }
}
