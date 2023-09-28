package br.com.attornatus.dtos.address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequestDto {
  private String publicPlace;
  private String zipCode;
  private String number;
  private String city;
}
