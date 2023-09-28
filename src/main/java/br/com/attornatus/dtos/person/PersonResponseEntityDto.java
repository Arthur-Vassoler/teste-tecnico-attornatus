package br.com.attornatus.dtos.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseEntityDto {
  private String nome;
  private String dataNascimento;
  private Long enderecoPrincipalId;
  private List<?> enderecos;
}
