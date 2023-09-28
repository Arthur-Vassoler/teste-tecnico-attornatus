package br.com.attornatus.dtos.person;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonResponseEntityDto {
  private String nome;
  private String dataNascimento;
  private Long enderecoPrincipalId;
  private List<?> enderecos;
}
