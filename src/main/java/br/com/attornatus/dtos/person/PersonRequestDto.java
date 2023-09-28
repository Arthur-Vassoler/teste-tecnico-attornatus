package br.com.attornatus.dtos.person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonRequestDto {
  private String nome;
  private String dataNascimento;
}
